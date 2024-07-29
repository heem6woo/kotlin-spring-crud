package org.project.portfolio.member.entity

import jakarta.persistence.*
import lombok.Data
import lombok.NoArgsConstructor
import org.project.portfolio.common.MemberRole
import org.project.portfolio.member.dto.MemberDto
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.time.LocalDateTime


@Data
@NoArgsConstructor
@Entity(name = "member")
class Member (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0L,

    @Column(name = "member_id")
    var memberId: String = "Guest",

    @Column(name = "email")
    var email: String = "Guest",

    @Column(name = "password")
    var passwordHash: String,

    @Column(name = "Role")
    var role: MemberRole = MemberRole.USER,

    val createdAt : LocalDateTime = LocalDateTime.now()
) : UserDetails {

    fun toDto() = MemberDto(
        memberId = memberId,
        email = email,
        password = passwordHash
    )

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return mutableListOf( GrantedAuthority { role.name })
    }

    override fun getPassword(): String {
        return passwordHash
    }

    override fun getUsername(): String {
        return memberId
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }
}