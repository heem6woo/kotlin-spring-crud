package org.project.portfolio.member.exception


import org.project.portfolio.member.dto.AuthErrorResponse
import org.springframework.context.annotation.Configuration
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindException
import org.springframework.web.bind.annotation.ExceptionHandler


@Configuration
class ValidationHandler {

    @ExceptionHandler(BindException::class)
    fun handleBindingException(ex: BindException): ResponseEntity<AuthErrorResponse> {

        return ResponseEntity.badRequest().body(
            AuthErrorResponse(
                ex.fieldError?.defaultMessage ?: "Invalid request",
                org.springframework.http.HttpStatus.BAD_REQUEST
            )
        )
    }
}