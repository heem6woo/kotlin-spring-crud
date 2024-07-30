package org.project.portfolio.post.dto

data class PostResponse(
    val title: String,
    val content: String,
    val createdAt: String,
    val updatedAt: String,
    val deletedAt: String,
    val author: String
) {
}