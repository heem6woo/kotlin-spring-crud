package org.project.portfolio.member.entity

import jakarta.persistence.*
import lombok.Data
import lombok.NoArgsConstructor
import org.project.portfolio.common.MemberRole
import org.project.portfolio.member.dto.MemberResponse
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.time.LocalDateTime


@Data
@NoArgsConstructor
@Entity(name = "member")
class Member (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,

    @Column(name = "name")
    val name: String = "Guest",

    @Column(name = "email")
    val email: String = "Guest",

    @Column(name = "password")
    var passwordHash: String,

    @Column(name = "Role")
    var role: MemberRole = MemberRole.USER,

    val createdAt : LocalDateTime = LocalDateTime.now()
) : UserDetails {

    fun toDto() = MemberResponse(
        name = name,
        email = email,
        role = role
    )

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return mutableListOf( GrantedAuthority { role.name })
    }

    override fun getPassword(): String {
        return passwordHash
    }

    override fun getUsername(): String {
        return email
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