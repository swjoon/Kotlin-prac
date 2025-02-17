package org.SBB.service

import org.SBB.entity.Post
import org.SBB.repository.AppFileRepository

class AppService(private val appRepository: AppFileRepository) {

    fun savePost(content: String, author: String): Int = appRepository.save(Post(0, author, content))

    fun getPost(id: Int): Post? = appRepository.findPostById(id)

    fun getPosts(): List<Post> = appRepository.findAll()

    fun modifyPost(postId: Int, content: String, author: String) = appRepository.modify(postId, content, author)

    fun deletePost(id: Int) = appRepository.delete(id)

    fun buildJson() = appRepository.buildJson()
}
