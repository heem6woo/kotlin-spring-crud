package org.project.portfolio.post.controller

import jakarta.servlet.http.HttpServletRequest
import jakarta.validation.Valid
import org.project.portfolio.member.entity.Member
import org.project.portfolio.post.dto.PostRequest
import org.project.portfolio.post.service.PostService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.security.Principal

@RestController
@RequestMapping("/api/post")
class PostController(
    private final val postService: PostService
) {

    @PostMapping
    fun createPost(principal: Principal,@Valid @RequestBody postRequest: PostRequest): ResponseEntity<String> {
        val userEmail : String = principal.name

        postService.createPost(userEmail, postRequest)

        return ResponseEntity.ok("Post created")
    }

    @PutMapping("/{id}")
    fun updatePost(principal: Principal, @PathVariable("id") postId: Long,@RequestBody postRequest: PostRequest): ResponseEntity<String> {
        val userEmail : String = principal.name

        postService.updatePost(userEmail, postId, postRequest)

        return ResponseEntity.ok("Post updated")
    }

    @DeleteMapping("/{id}")
    fun deletePost(principal: Principal, @PathVariable("id") postId: Long): ResponseEntity<String> {
        val userEmail : String = principal.name

        postService.deletePost(userEmail, postId)

        return ResponseEntity.ok("Post deleted")
    }
}