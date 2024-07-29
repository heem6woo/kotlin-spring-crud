package org.project.portfolio.member.dto

import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Pattern

data class AuthRequest(
    @field:NotNull(message = "Email is required")
    @field:Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}\$", message = "Invalid email format")
    val email: String? = null,

    val password: String? = null
) {

}