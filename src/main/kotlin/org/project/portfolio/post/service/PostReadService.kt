package org.project.portfolio.post.service

import org.project.portfolio.post.entity.Post
import org.project.portfolio.post.repository.PostRepository
import org.springframework.stereotype.Service
import java.time.LocalDateTime


@Service
class PostReadService(
    final val postRepository: PostRepository
) {

    fun findAllByOrderByCreatedAtDesc() : List<Post> {

        return postRepository.findAllByOrderByCreatedAtDesc()
    }

    fun findByTitle(title: String) : List<Post> {
        return postRepository.findByTitleContaining(title)
    }

    fun findByDeletedAndAfterDeletedAt(date : LocalDateTime) : List<Post> {
        return postRepository.findByDeletedAndAfterDeletedAt(date)
    }



}