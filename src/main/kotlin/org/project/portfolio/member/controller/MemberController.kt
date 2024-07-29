package org.project.portfolio.member.controller

import mu.KotlinLogging
import org.project.portfolio.member.dto.MemberResponse
import org.project.portfolio.member.service.MemberService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


private val log = KotlinLogging.logger {}
@RestController
@RequestMapping("/api/members")
class MemberController(
    private val memberService: MemberService
) {

    @GetMapping
    fun getMembers() = memberService.getMembers()


    @GetMapping("/{email}")
    fun getMember(@PathVariable email: String): ResponseEntity<MemberResponse> {
        return ResponseEntity.ok(memberService.getMember(email))

    }


}