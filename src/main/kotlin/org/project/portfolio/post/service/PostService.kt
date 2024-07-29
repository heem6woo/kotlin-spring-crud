package org.project.portfolio.post.service

import jakarta.transaction.Transactional
import org.project.portfolio.member.repository.MemberRepository
import org.project.portfolio.post.dto.PostRequest
import org.project.portfolio.post.entity.Post
import org.project.portfolio.post.repository.PostRepository
import org.springframework.stereotype.Service
import java.net.PasswordAuthentication
import java.time.LocalDateTime

@Service
@Transactional
class PostService(
    private final val postRepository: PostRepository,
    private final val memberRepository: MemberRepository
) {

    fun createPost(userEmail : String, postRequest: PostRequest){

        val member = memberRepository.findByEmail(userEmail) ?: throw IllegalArgumentException("Member not found")

        val post =
            postRequest.title?.let {
                postRequest.content?.let { it1 ->
                    Post(
                        title = it,
                        content = it1,
                        member = member
                    )
                }
            }


        if (post != null) {
            postRepository.save(post)
        }
    }

    fun updatePost(userEmail: String, postId: Long, postRequest: PostRequest) {
        val member = memberRepository.findByEmail(userEmail) ?: throw IllegalArgumentException("Member not found")

        // check if the post  is created 10 days ago, then cannot update
        val tenDays = LocalDateTime.now().minusDays(10)

        // crruent date < created date + 10 days
        val post = postRepository.findPostByIdAnCreatedAfter(postId, tenDays) ?: throw IllegalArgumentException("Post not found")


        if (post.member?.id != member.id) {
            throw IllegalArgumentException("You are not the author of this post")
        }

        post.title = postRequest.title ?: post.title
        post.content = postRequest.content ?: post.content

        postRepository.save(post)
    }




}