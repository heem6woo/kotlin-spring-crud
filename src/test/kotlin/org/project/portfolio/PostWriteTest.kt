package org.project.portfolio

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.project.portfolio.member.entity.Member
import org.project.portfolio.member.repository.MemberRepository
import org.project.portfolio.post.dto.PostRequest
import org.project.portfolio.post.entity.Post
import org.project.portfolio.post.repository.PostRepository
import org.project.portfolio.post.service.PostService
import org.springframework.boot.test.context.SpringBootTest
import kotlin.test.assertEquals

@SpringBootTest
class PostWriteTest {
    private val postRepository : PostRepository = mockk()
    private val memberRepository : MemberRepository = mockk()
    private val postService: PostService = PostService(postRepository, memberRepository)


    val post = Post(
        id = 1L,
        title = "포스트 제목",
        content = "포스트 내용",
        member = Member(
            id = 1L,
            name = "우희민",
            email = "heem6woo@gmail.com",
            passwordHash = "1234"
        )
    )

    @Test
    fun `포스트 생성 테스트1`() {

        val userEmail = "heem6woo@gmail.com"

        val postRequest = PostRequest(
            title = "포스트 제목",
            content = "포스트 내용"
        )

        every { postRepository.save(post)} returns post

        val result = postService.createPost(userEmail, postRequest)

        //then
        verify(exactly = 1) { postRepository.save(post)};
        assertEquals(post, result)
    }

    @Test
    @DisplayName("포스트 수정 테스트")
    fun updatePostTest() {

        postService.updatePost(
            userEmail = "heem6woo@gmail.com",
            postId = 1L,
            PostRequest(
                title = "수정된 포스트 제목",
                content = "수정된 포스트 내용"
            )
        )
    }
}