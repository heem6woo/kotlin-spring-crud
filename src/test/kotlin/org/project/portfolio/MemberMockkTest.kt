package org.project.portfolio

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test
import org.project.portfolio.member.entity.Member
import org.project.portfolio.member.repository.MemberRepository
import org.project.portfolio.member.service.MemberService
import kotlin.test.DefaultAsserter.assertEquals
import kotlin.test.assertEquals

class MemberMockkTest {
    val memberRepository: MemberRepository = mockk()
    val memberService: MemberService = MemberService(memberRepository, passwordEncoder = mockk())

    val expected = Member(
        id = 1L,
        name = "우희민",
        email = "heem6woo@gmail.com",
        passwordHash = "1234"
    )
    @Test
    fun `회원 조회 테스트`() {

        every { memberRepository.findByEmail("heem6woo@gmail.com") } returns expected

        val result = memberService.getMember("heem6woo@gmail.com")

        //then
        verify(exactly = 1) { memberRepository.findByEmail("heem6woo@gmail.com")};
        assertEquals(expected.toDto(), result)

    }

}