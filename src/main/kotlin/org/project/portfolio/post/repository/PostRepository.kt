package org.project.portfolio.post.repository

import org.project.portfolio.post.entity.Post
import org.springframework.data.jpa.repository.JpaRepository

interface PostRepository : JpaRepository<Post, Long> {
//    fun findByMemberName(memberName: Long): List<Post>
//    fun findByMemberNameAndId(memberId: Long, id: Long): Post?
//
//    fun deleteByMemberNameAndId(memberId: Long, id: Long)
}