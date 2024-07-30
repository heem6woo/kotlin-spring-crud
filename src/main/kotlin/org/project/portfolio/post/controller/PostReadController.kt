package org.project.portfolio.post.controller

import org.project.portfolio.post.dto.PostResponse
import org.project.portfolio.post.entity.Post
import org.project.portfolio.post.service.PostReadService
import org.project.portfolio.post.service.PostService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/post")
class PostReadController(
    private final val postService: PostReadService
) {


    @GetMapping("/")
    fun getPosts(@RequestParam(required = false) page: Int = 0, @RequestParam(required = false) pageSize: Int = 0, @RequestParam(required = false) title: String? = null): ResponseEntity<List<Post>> {

        if (page < 0 || pageSize < 0) {
            return ResponseEntity.badRequest().build()
        }
        var posts : List<Post>? = null

        if(title == null && page == 0 && pageSize == 0) {
            // return all posts in created desc order without after created date
            posts = postService.findAllByOrderByCreatedAtDesc()
        }

        return ResponseEntity.ok(posts)
    }




}