package org.project.portfolio.member.controller

import org.apache.coyote.Response
import org.project.portfolio.member.dto.AuthRequest
import org.project.portfolio.member.dto.AuthResponse
import org.project.portfolio.member.dto.MemberDto
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
    fun createMember(@RequestBody memberRequest: MemberDto): ResponseEntity<MemberDto> {

        return ResponseEntity.ok( memberService.createMember(memberRequest)
            ?.toDto()
            ?: throw ResponseStatusException(HttpStatus.BAD_REQUEST,"Member not created"))
    }

    @PostMapping("/login")
    fun login(@RequestBody authRequest: AuthRequest): ResponseEntity<AuthResponse> {

        val authentication = authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(authRequest.memberId, authRequest.password)
        ) //

        SecurityContextHolder.getContext().authentication = authentication

        val member = authRequest.memberId?.let { memberService.getMember(it)
        } ?: throw ResponseStatusException(HttpStatus.UNAUTHORIZED, "Member not found")

        return ResponseEntity.ok(member.memberId?.let { member.email?.let { it1 -> AuthResponse(it, it1) } })
    }
}