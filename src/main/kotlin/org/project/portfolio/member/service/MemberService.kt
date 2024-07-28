package org.project.portfolio.member.service

import jakarta.persistence.Id
import jakarta.transaction.Transactional
import mu.KotlinLogging
import org.project.portfolio.member.dto.MemberDto
import org.project.portfolio.member.entity.Member
import org.project.portfolio.member.repository.MemberRepository
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

private val log = KotlinLogging.logger {}
@Service
class MemberService(
    private val memberRepository: MemberRepository,
    private val passwordEncoder: BCryptPasswordEncoder
) {

    @Transactional
    fun createMember(memberRequest: MemberDto): Member? {

        val member = memberRequest.memberId?.let {
            memberRequest.email?.let { it1 ->
                Member (
                    memberId = it,
                    email = it1,
                    passwordHash = passwordEncoder.encode(memberRequest.password)
                )
            }
        }
        return member?.let { memberRepository.save(it) }
    }

    fun getMember(memberId: String): MemberDto {
        val member = memberRepository.findByMemberId(memberId) ?: throw IllegalArgumentException("Member not found")
        return member.toDto()
    }

    fun getMembers(): List<Member> {
        return memberRepository.findAll()
    }

    fun updateMember(member: Member): Member {
        return memberRepository.save(member)
    }

    fun deleteMember(memberId: String) {
        memberRepository.deleteMemberByMemberId(memberId)
    }
}