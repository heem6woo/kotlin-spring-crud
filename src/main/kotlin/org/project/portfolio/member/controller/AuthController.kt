package org.project.portfolio.member.controller

import jakarta.validation.Valid
import org.project.portfolio.member.dto.AuthRequest
import org.project.portfolio.member.dto.AuthResponse
import org.project.portfolio.member.dto.MemberRequest
import org.project.portfolio.member.dto.MemberResponse
import org.project.portfolio.member.service.MemberService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/api/auth")
class AuthController(
    private val memberService: MemberService,
    private val authenticationManager: AuthenticationManager
) {

    @PostMapping("/register")
    fun createMember(@Valid @RequestBody memberRequest: MemberRequest): ResponseEntity<MemberResponse> {

        return ResponseEntity.ok( memberService.createMember(memberRequest)
            ?.toDto()
            ?: throw ResponseStatusException(HttpStatus.BAD_REQUEST,"Member not created"))
    }

    @PostMapping("/login")
    fun login(@RequestBody authRequest: AuthRequest): ResponseEntity<AuthResponse> {

        val authentication = authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(authRequest.email, authRequest.password)
        ) //

        SecurityContextHolder.getContext().authentication = authentication

        val member = authRequest.email?.let { memberService.getMember(it)
        } ?: throw ResponseStatusException(HttpStatus.UNAUTHORIZED, "Member not found")

        return ResponseEntity.ok(member.email?.let { AuthResponse(it, member.role) })
    }
}