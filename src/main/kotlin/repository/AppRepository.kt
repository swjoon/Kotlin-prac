package org.SBB.repository

import org.SBB.entity.Post

class AppRepository {
    private var postId = 0;
    private var postList = LinkedHashMap<Int, Post>()

    fun findPostById(id: Int): Post? {
        return postList.get(id)
    }

    fun save(post: Post): Int{
        val id = ++postId
        postList.put(id, post)
        return id;
    }

    fun findAll(): List<MutableMap.MutableEntry<Int, Post>> {
        return postList.entries.reversed()
    }

    fun delete(id: Int) {
        postList.remove(id)
    }
}