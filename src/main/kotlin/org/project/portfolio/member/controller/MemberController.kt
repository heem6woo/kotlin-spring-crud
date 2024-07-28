package org.project.portfolio.member.controller

import mu.KotlinLogging
import org.project.portfolio.member.dto.MemberDto
import org.project.portfolio.member.entity.Member
import org.project.portfolio.member.service.MemberService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException

private val log = KotlinLogging.logger {}
@RestController
@RequestMapping("/api/members")
class MemberController(
    private val memberService: MemberService
) {

    @GetMapping
    fun getMembers() = memberService.getMembers()

    @PostMapping
    fun createMember(@RequestBody memberRequest: MemberDto): MemberDto {
        log.info("Creating member with id: ${memberRequest.memberId}")
        return memberService.createMember(memberRequest)
            ?.toDto()
            ?: throw ResponseStatusException(HttpStatus.BAD_REQUEST,"Member not created")
    }




    @GetMapping("/{userId}")
    fun getMember(@PathVariable userId: String): ResponseEntity<MemberDto> {
        return ResponseEntity.ok(memberService.getMember(userId))
    }


}