package org.SBB.domain.controller

import org.SBB.domain.entity.UrlFormat
import org.SBB.domain.service.AppService

class AppController(private val appService: AppService) {
    private val pageSize = 5

    fun SaveController() {
        print("명언 : ");
        val content = readlnOrNull()!!.trim();
        print("작가 : ")
        val author = readlnOrNull()!!.trim();

        val id = appService.savePost(content, author);

        println("${id}번 명언이 등록되었습니다.")
    }

    fun ListController(urlFormat: UrlFormat) {
        val posts = appService.getPosts(urlFormat)
        val page = urlFormat.params["page"]?.toInt() ?: 1
        val pagedPosts = posts.drop((page - 1) * pageSize).take(pageSize)

        println("번호 / 작가 / 명언")
        println("----------------------")
        pagedPosts.forEach {
            println("${it.id} / ${it.author} / ${it.content}")
        }

        val totalPages = if (posts.size != 0) (posts.size + pageSize - 1) / pageSize else 1
        println("----------------------")
        println("페이지 : $page / $totalPages")
    }

    fun ModifyController(urlFormat: UrlFormat) {
        val id = urlFormat.params["id"]?.toInt() ?: 0
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

    fun DeleteController(urlFormat: UrlFormat) {
        val id = urlFormat.params["id"]?.toInt() ?: 0
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
