package org.SBB

import org.SBB.domain.controller.AppController
import org.SBB.global.config.SingletonManager
import org.SBB.global.util.UrlUtil

fun main() {
    println("== 명언 앱 ==")
    switch(SingletonManager.controller)
}

fun switch(appController: AppController) {
    while (true) {
        print("명령) ")
        val input = readlnOrNull()!!.trim();
        val url = UrlUtil.getUrl(input);

        when (url.action) {
            "등록" -> appController.SaveController();
            "목록" -> appController.ListController(url);
            "삭제" -> appController.DeleteController(url);
            "수정" -> appController.ModifyController(url);
            "빌드" -> appController.BuildController()
            "종료" -> {
                appController.BuildController()
                return
            }
        }
    }
}