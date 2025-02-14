package org.SBB

import org.SBB.controller.AppController
import org.SBB.repository.AppRepository
import org.SBB.service.AppService
import org.SBB.util.UrlUtil
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    println("== 명언 앱 ==")
    switch(
        appController = AppController(AppService(appRepository = AppRepository())),
        urlUtil = UrlUtil(),
    )
}

fun switch(appController: AppController, urlUtil: UrlUtil){
    while(true){
        print("명령) ")
        val input = readlnOrNull()!!.trim();
        val url = urlUtil.getUrl(input);

        when(url.action){
            "등록" -> appController.SaveController();
            "목록" -> appController.ListController();
            "삭제" -> appController.DeleteController(url.id);
            "수정" -> appController.ModifyController(url.id);
            "종료" -> return;
        }

    }
}