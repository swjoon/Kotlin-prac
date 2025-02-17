package org.SBB.global.util

import org.SBB.domain.entity.UrlFormat

class UrlUtil {
    companion object {
        fun getUrl(url: String): UrlFormat {
            val parts = url.split("?")
            val action = parts[0]

            val params = mutableMapOf<String, String>()

            if(parts.size > 1) {
                var paramsPart = parts[1].split("&")
                paramsPart.forEach { param ->
                    val KV = param.split("=")
                    if(KV.size == 2) {
                        params[KV[0]] = KV[1]
                    }
                }
            }

            return UrlFormat(action, params)
        }
    }
}