package org.project.portfolio.member.entity

import jakarta.persistence.*
import lombok.Data
import lombok.NoArgsConstructor
import org.project.portfolio.common.UserRole
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails


@Data
@NoArgsConstructor
@Entity(name = "user")
class Member (

    @Column(name = "user_id")
    var userId: String,

    @Column(name = "email")
    var email: String,

    @Column(name = "password")
    var passwordHash: String,

    @Column(name = "Role")
    var role: UserRole = UserRole.USER
) : UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null


    override fun getAuthorities(): MutableCollection<out GrantedAuthority>? {
        return mutableListOf(SimpleGrantedAuthority(role.name));
    }

    override fun getPassword(): String {
        return passwordHash
    }

    override fun getUsername(): String {
        return userId
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

    companion object {
        private const val serialVersionUID = 1L
    }


}