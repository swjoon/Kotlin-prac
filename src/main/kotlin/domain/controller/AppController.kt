package org.SBB.controller

import org.SBB.service.AppService

class AppController(private val appService: AppService) {

    fun SaveController() {
        print("명언 : ");
        val content = readlnOrNull()!!.trim();
        print("작가 : ")
        val author = readlnOrNull()!!.trim();

        val id = appService.savePost(content, author);

        println("${id}번 명언이 등록되었습니다.")
    }

    fun ListController(page: Int = 1, pageSize: Int = 5) {
        val posts = appService.getPosts()
        val pagedPosts = posts.drop((page - 1) * pageSize).take(pageSize)

        println("번호 / 작가 / 명언")
        println("----------------------")
        pagedPosts.forEach {
            println("${it.id} / ${it.author} / ${it.content}")
        }

        val totalPages = (posts.size + pageSize - 1) / pageSize
        println("----------------------")
        println("페이지 : ${if (page > 1) "[${page - 1}]" else ""} $page / ${if (page < totalPages) "[${page + 1}]" else ""}")
    }

    fun ModifyController(id: Int) {
        val post = appService.getPost(id)

        if (post == null) {
            println("${id}번 명언은 존재하지 않습니다.")
            return;
        }

        println("명언(기존) : ${post.content}")
        print("명언 : ")
        val content = readlnOrNull()!!.trim()
        println("작가(기존) : ${post.author}")
        print("작가 : ")
        val author = readlnOrNull()!!.trim()

        appService.modifyPost(id, content, author)
    }

    fun DeleteController(id: Int) {
        val post = appService.getPost(id)

        if (post == null) {
            println("${id}번 명언은 존재하지 않습니다.")
            return;
        }

        appService.deletePost(id)
    }

    fun BuildController() {
        appService.buildJson()
    }
}
