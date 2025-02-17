package org.project.portfolio.member.dto

import lombok.AllArgsConstructor
import org.project.portfolio.common.MemberRole

@AllArgsConstructor
data class AuthResponse(
    val email: String = "",
    val role: MemberRole = MemberRole.USER
) {

}