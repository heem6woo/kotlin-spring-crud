package org.project.portfolio.member.entity

import jakarta.persistence.*
import lombok.Data
import lombok.NoArgsConstructor
import org.project.portfolio.common.MemberRole
import org.project.portfolio.member.dto.MemberDto
import java.time.LocalDateTime


@Data
@NoArgsConstructor
@Entity(name = "member")
class Member (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0L,

    @Column(name = "member_id")
    var memberId: String,

    @Column(name = "email")
    var email: String,

    @Column(name = "password")
    var passwordHash: String,

    @Column(name = "Role")
    var role: MemberRole = MemberRole.USER,

    val createdAt : LocalDateTime = LocalDateTime.now()
) {

    fun toDto() = MemberDto(
        memberId = memberId,
        email = email,
        password = passwordHash
    )



}