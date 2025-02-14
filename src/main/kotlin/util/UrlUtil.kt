package org.SBB.util

import org.SBB.entity.UrlFormat

class UrlUtil {

    fun getUrl(url: String): UrlFormat {

        if(!url.contains("?")){
            return UrlFormat(url, 0)
        }

        var part = url.split("?")

        var action = part.getOrNull(0) ?: url

        val id =
            try {
                if (part.size > 1 && part[1].contains("=") && part[1].split("=").size > 1) {
                    part[1].split("=")[1].toInt()
                } else {
                    0
                }
            } catch (e: RuntimeException) {
                0
            }

        return UrlFormat(action ?: "", id ?: 0)
    }
}