package org.project.portfolio

import com.google.gson.Gson
import org.junit.jupiter.api.Test
import org.project.portfolio.common.MemberRole
import org.project.portfolio.member.dto.MemberRequest
import org.project.portfolio.member.dto.MemberResponse
import org.project.portfolio.member.service.MemberService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.post
import org.springframework.validation.BindException

@SpringBootTest
@AutoConfigureMockMvc
class MemberPersistentTest(
    @Autowired private val mvc: MockMvc,
    @Autowired  private val gson: Gson
){

    private fun doRegisterTest(input: MemberRequest, expectation: MemberResponse) {
        mvc.post("/api/auth/register") {
            contentType = MediaType.APPLICATION_JSON
            content = gson.toJson(input)
            accept = MediaType.APPLICATION_JSON
        }.andExpect {
            status { isOk() }
            content { contentType(MediaType.APPLICATION_JSON) }
            content { json(gson.toJson(expectation)) }
        }
    }

    private fun doRegisterFailTest(input: MemberRequest, expectation: MemberResponse) {
        mvc.post("/api/auth/register") {
            contentType = MediaType.APPLICATION_JSON
            content = gson.toJson(input)
            accept = MediaType.APPLICATION_JSON
        }.andExpect {
            status { isBadRequest() }
        }
    }


    @Test
    fun memberCreating(
        @Autowired memberService: MemberService
    ) {
        // Given
        val name = "testId"
        val email = "test@mail.com"
        val password = "testPassword"

        memberService.createMember(
            MemberRequest(
                name = name,
                email = email,
                password = password
            ))

    }

    @Test
    fun `register member with valid input`() {
        // Given
        val input = MemberRequest(
            name = "우희민",
            email = "heem6woo@mail.com",
            password = "Password12345!!"
        )
        val expectation = MemberResponse(
            name = "우희민",
            email = "heem6woo@mail.com",
            role = MemberRole.USER
        )

        doRegisterTest(input, expectation);

    }

    @Test
    fun `register member with invalid name input`() {
        // Given
        val input = MemberRequest(
            name = "Heemin Woo",
            email = "heem6woo@mail.com",
            password = "Password12345!!"
        )
        val expectation = MemberResponse(
            name = "우희민",
            email = "heem6woo@mail.com",
            role = MemberRole.USER
        )

        doRegisterFailTest(input, expectation);

    }


}