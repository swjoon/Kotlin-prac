package org.SBB.domain.repository

import org.SBB.domain.entity.Post
import org.SBB.domain.entity.UrlFormat

class AppMemoryRepository : AppRepository {
    private var postId = 0;
    private var postList = LinkedHashMap<Int, Post>()

    override fun findPostById(id: Int): Post? {
        return postList[id]
    }

    override fun save(post: Post): Int{
        val id = ++postId
        postList[id] = post
        return id;
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
    }

    override fun modify(postId: Int, content: String, author: String){
        val post = postList.get(postId)!!
        post.content = content
        post.author = author
    }

    override fun buildJson(){

    }
}