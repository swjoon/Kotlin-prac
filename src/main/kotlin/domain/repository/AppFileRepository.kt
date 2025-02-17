package org.SBB.domain.repository

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.SBB.domain.entity.Post
import org.SBB.domain.entity.UrlFormat
import java.io.File

class AppFileRepository : AppRepository {
    private val dbPath = "db/wiseSaying"
    private val lastIdFile = "$dbPath/lastId.txt"
    private var postId = loadLastId()
    private val postList = loadPosts()

    init {
        File(dbPath).mkdirs()
    }

    override fun findPostById(id: Int): Post?{
        return postList[id]
    }

    override fun save(post: Post): Int {
        val id = ++postId
        val newPost = post.copy(id = id)
        postList[id] = newPost
        savePostToFile(newPost)
        saveLastId()
        return id
    }

    override fun findAll(): List<Post> {
        return postList.values.sortedByDescending { it.id }
    }

    override fun findAllBySearch(urlFormat: UrlFormat): List<Post> {
        val type = urlFormat.params["keywordType"]
        val keyword = urlFormat.params["keyword"]!!

        return when(type){
            "content" -> findAll().filter { it.content.contains(keyword,ignoreCase = true) }
            "author" -> findAll().filter { it.author.contains(keyword,ignoreCase = true) }
            else -> emptyList()
        }
    }

    override fun delete(id: Int) {
        postList.remove(id)
        File("$dbPath/$id.json").delete()
    }

    override fun modify(postId: Int, content: String, author: String) {
        postList[postId]?.apply {
            this.content = content
            this.author = author
            savePostToFile(this)
        }
    }

    override fun buildJson() {
        File("$dbPath/data.json").writeText(Json.encodeToString(findAll()))
        println("data.json 파일이 생성되었습니다.")
    }

    private fun savePostToFile(post: Post) {
        File("$dbPath/${post.id}.json").writeText(Json.encodeToString(post))
    }

    private fun saveLastId() {
        File(lastIdFile).writeText(postId.toString())
    }

    private fun loadLastId(): Int {
        return File(lastIdFile).takeIf { it.exists() }?.readText()?.toIntOrNull() ?: 0
    }

    private fun loadPosts(): MutableMap<Int, Post> {
        val file = File("$dbPath/data.json")
        if (!file.exists()) return mutableMapOf()

        return try {
            val posts: List<Post> = Json.decodeFromString(file.readText())
            posts.associateBy { it.id }.toMutableMap()
        } catch (e: Exception) {
            mutableMapOf()
        }
    }
}

