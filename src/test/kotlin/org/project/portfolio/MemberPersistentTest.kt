package org.project.portfolio

import org.junit.jupiter.api.Test
import org.project.portfolio.member.dto.MemberDto
import org.project.portfolio.member.entity.Member
import org.project.portfolio.member.repository.MemberRepository
import org.project.portfolio.member.service.MemberService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class MemberPersistentTest {

    @Test
    fun MemberGenerate(
        @Autowired memberService: MemberService
    ) {
        // Given
        val memberId = "testId"
        val email = "test@mail.com"
        val password = "testPassword"

        memberService.createMember(
            MemberDto(
                memberId = memberId,
                email = email,
                password = password
            ))



    }
}