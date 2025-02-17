package org.SBB.domain.service

import org.SBB.domain.entity.Post
import org.SBB.domain.entity.UrlFormat
import org.SBB.domain.repository.AppFileRepository

class AppService(private val appRepository: AppFileRepository) {

    fun savePost(content: String, author: String): Int = appRepository.save(Post(0, author, content))

    fun getPost(id: Int): Post? = appRepository.findPostById(id)

    fun getPosts(urlFormat: UrlFormat): List<Post> =
        if (urlFormat.params.containsKey("keywordType") && urlFormat.params.containsKey("keyword")) appRepository.findAllBySearch(urlFormat) else appRepository.findAll()

    fun modifyPost(postId: Int, content: String, author: String) = appRepository.modify(postId, content, author)

    fun deletePost(id: Int) = appRepository.delete(id)

    fun buildJson() = appRepository.buildJson()
}
