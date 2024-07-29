package org.project.portfolio.post.exception

import org.project.portfolio.post.dto.PostErrorResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler

class FoundHandler {

    @ExceptionHandler(NotFoundException::class)
    fun handle(ex: NotFoundException): ResponseEntity<PostErrorResponse> {

        return ResponseEntity.badRequest().body(
            PostErrorResponse(
                ex.message.toString(),
                HttpStatus.NOT_FOUND
            )
        )

    }
}