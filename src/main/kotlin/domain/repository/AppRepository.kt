package org.SBB.domain.repository

import org.SBB.domain.entity.Post
import org.SBB.domain.entity.UrlFormat

interface AppRepository {

    fun findPostById(id: Int): Post?

    fun save(post: Post): Int

    fun findAll(): List<Post>

    fun findAllBySearch(urlFormat: UrlFormat): List<Post>

    fun delete(id: Int)

    fun modify(postId: Int, content: String, author: String)

    fun buildJson()

}