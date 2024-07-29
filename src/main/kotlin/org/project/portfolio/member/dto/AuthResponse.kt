package org.project.portfolio.member.dto

import lombok.AllArgsConstructor

@AllArgsConstructor
data class AuthResponse(
    private val memberId: String = "",
    private val email: String = ""
) {

}