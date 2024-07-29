package org.project.portfolio.post.dto

data class PostErrorResponse(
    val message: String,
    val httpStatus: org.springframework.http.HttpStatus
)
