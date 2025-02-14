package org.SBB.service

import org.SBB.entity.Post
import org.SBB.repository.AppRepository

class AppService(private val appRepository: AppRepository) {

    fun savePost(content: String, author: String):Int {
        return appRepository.save(Post(author, content))
    }

    fun getPost(id: Int): Post? {
        return appRepository.findPostById(id)
    }

    fun getPosts(): List<MutableMap.MutableEntry<Int, Post>> {
        return appRepository.findAll()
    }

    fun modifyPost(postId: Int, content: String, author: String) {
        val post = appRepository.findPostById(postId)!!
        post.author = author
        post.content = content
    }

    fun deletePost(id: Int) {
        appRepository.delete(id)
    }
}