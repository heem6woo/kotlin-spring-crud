package org.project.portfolio

import com.google.gson.Gson
import org.junit.jupiter.api.BeforeAll
import org.project.portfolio.member.controller.AuthController
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc

@SpringBootTest
@AutoConfigureMockMvc
class MemberSignInTest (
    private val mvc: MockMvc,
    private val gson: Gson
){



}