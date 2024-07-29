package org.project.portfolio.member.dto

import lombok.AllArgsConstructor
import org.project.portfolio.common.MemberRole

@AllArgsConstructor
data class MemberResponse(

    var name: String? = null,

    var email: String? = null,

    var role: MemberRole = MemberRole.USER

    ) {
}