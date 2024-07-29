package org.project.portfolio.member.dto

import org.springframework.http.HttpStatus

data class AuthErrorResponse(
    val message: String,
    val status: HttpStatus
) {

}