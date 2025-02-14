package org.SBB.util

class UrlUtil {

    fun getUrl(url:String):UrlFormat{
        var part = url.split("?")

        var action = part.getOrNull(0) ?: url

        val id = try {
            if (part.size > 1 && part[1].contains("=")) {
                part[1].split("=")[1].toInt()
            } else {
                0
            }
        } catch (e: NumberFormatException) {
            0
        }

        return UrlFormat(action?: "",id?:0)
    }
}

data class UrlFormat(val action: String, val id: Int){
}