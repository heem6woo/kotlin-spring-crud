package org.project.portfolio.config

import org.project.portfolio.member.repository.MemberRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.SecurityFilterChain

@Configuration
class SecurityConfig(
    private val memberRepository: MemberRepository
) {

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {

         http
             .csrf { it.disable() }
             .csrf {it.disable() }
             .httpBasic{
             } // to enable basic authentication
             .authorizeHttpRequests {
                 it.requestMatchers("/api/auth/**").permitAll()
                 it.anyRequest().authenticated()
             }
//             .sessionManagement {
//                    it.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//             }

        return http.build()
    }

    @Bean
    fun authenticationManager(authenticationConfiguration: AuthenticationConfiguration): AuthenticationManager {
        return authenticationConfiguration.getAuthenticationManager()
    }

    @Bean

    fun userDetailsService(): UserDetailsService {

        return UserDetailsService {
            val member = memberRepository.findByEmail(it) ?: throw IllegalArgumentException("Member not found")
            User.builder()
                .username(member.email)
                .password(member.passwordHash)
                .roles(member.role.toString())
                .build()
        }
    }


    @Bean
    fun passwordEncoder() = BCryptPasswordEncoder()
}