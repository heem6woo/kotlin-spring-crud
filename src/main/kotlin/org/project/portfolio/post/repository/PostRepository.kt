package org.project.portfolio.post.repository

import org.project.portfolio.post.entity.Post
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.time.LocalDateTime

interface PostRepository : JpaRepository<Post, Long> {
//    fun findByMemberName(memberName: Long): List<Post>
//    fun findByMemberNameAndId(memberId: Long, id: Long): Post?
//
//    fun deleteByMemberNameAndId(memberId: Long, id: Long)

    @Query("SELECT p FROM post p WHERE p.id = :id AND p.createdAt = :createdAt")
    fun findByIdAndCreatedAt(id: Long): Post?

    // within 10 days
    @Query("SELECT p FROM post p WHERE p.id = :id AND p.createdAt >= :date")
    fun findPostByIdAnCreatedAfter(id: Long, date: LocalDateTime): Post?


    fun findAllByOrderByCreatedAtDesc(): List<Post>

    fun findByTitleContaining(title: String): List<Post>

    @Query("SELECT p FROM post p WHERE p.deleted = true AND p.updatedAt > :date")
    fun findByDeletedAndAfterDeletedAt(date : LocalDateTime): List<Post>
}