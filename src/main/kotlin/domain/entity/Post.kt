package org.SBB.entity

import kotlinx.serialization.Serializable

@Serializable
data class Post(val id: Int, var content: String , var author: String) {
}