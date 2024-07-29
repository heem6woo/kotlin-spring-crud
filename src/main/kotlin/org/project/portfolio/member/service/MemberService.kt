package org.project.portfolio.member.service

import jakarta.transaction.Transactional
import mu.KotlinLogging
import org.project.portfolio.member.dto.MemberRequest
import org.project.portfolio.member.dto.MemberResponse
import org.project.portfolio.member.entity.Member
import org.project.portfolio.member.repository.MemberRepository
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

private val log = KotlinLogging.logger {}
@Service
class MemberService(
    private val memberRepository: MemberRepository,
    private val passwordEncoder: BCryptPasswordEncoder
) {

    @Transactional
    fun createMember(memberRequest: MemberRequest): Member? {

        val member = memberRequest.name?.let {
            memberRequest.email?.let { it1 ->
                Member (
                    name = it,
                    email = it1,
                    passwordHash = passwordEncoder.encode(memberRequest.password)
                )
            }
        }
        return member?.let { memberRepository.save(it) }
    }

    fun getMember(email: String): MemberResponse {
        val member = memberRepository.findByEmail(email) ?: throw IllegalArgumentException("Member not found")
        return member.toDto()
    }

    fun getMembers(): List<Member> {
        return memberRepository.findAll()
    }

    fun updateMember(member: Member): Member {
        return memberRepository.save(member)
    }

    fun deleteMember(email: String) {
        memberRepository.deleteMemberByEmail(email)
    }
}