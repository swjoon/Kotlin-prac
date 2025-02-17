package org.SBB.repository

import org.SBB.entity.Post

interface AppRepository {

    fun findPostById(id: Int): Post?

    fun save(post: Post): Int

    fun findAll(): List<Post>

    fun delete(id: Int)

    fun modify(postId: Int, content: String, author: String)

    fun buildJson()

}