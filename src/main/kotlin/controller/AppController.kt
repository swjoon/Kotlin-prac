package org.SBB.controller

import org.SBB.util.UrlFormat

class AppController {
    private var postId = 0;
    private var postList = LinkedHashMap<Int, Post>()

    fun SaveController(){
        print("명언 : ");
        val content = readlnOrNull()?.trim();
        print("작가 : ")
        val author = readlnOrNull()?.trim();
        val id = ++postId
        println("${id}번 명언이 등록되었습니다.")
        postList.put(id,Post(content?:"명언 없음",author?:"작가 미상"))
    }

    fun ListController(){
        println("번호 / 작가 / 명언")
        println("----------------------")
        postList.entries.reversed().forEach{(id,post) ->
            println("$id / ${post.author} / ${post.content}")
        }
    }

    fun DeleteController(id: Int){
        if(postList.containsKey(id)){
            postList.remove(id)
            return;
        }
        println("${id}번 명언은 존재하지 않습니다.")
    }

    fun ModifyController(id: Int){
        if(postList.containsKey(id)){
            var post = postList.getValue(id)
            println("명언(기존) : ${post.content}")
            print("명언 : ")
            postList.getValue(id).content = readlnOrNull()!!.trim()
            println("작가(기존) : ${post.author}")
            print("작가 : ")
            postList.getValue(id).author = readlnOrNull()!!.trim()
            return;
        }
        println("${id}번 명언은 존재하지 않습니다.")
    }

}

data class Post(var content: String , var author: String) {
}
