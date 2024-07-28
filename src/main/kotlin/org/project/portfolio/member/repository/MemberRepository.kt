package org.project.portfolio.member.repository

import org.project.portfolio.member.entity.Member
import org.springframework.data.jpa.repository.JpaRepository

interface MemberRepository : JpaRepository<Member, Long> {
    fun findByMemberId(memberId: String): Member?

    fun deleteMemberByMemberId(memberId: String)
}