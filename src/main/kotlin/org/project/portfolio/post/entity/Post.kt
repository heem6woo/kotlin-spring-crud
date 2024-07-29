package org.project.portfolio.post.entity

import jakarta.persistence.*
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import org.project.portfolio.member.entity.Member
import java.time.LocalDate
import java.time.LocalDateTime


@Entity(name = "post")
class Post(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,

    @Column(nullable = false)
    var title: String,

    @Column(nullable = false)
    var content: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    var member: Member? = null

) {
    @CreationTimestamp
    val createdAt : LocalDateTime? = null

    @UpdateTimestamp
    var updatedAt : LocalDateTime? = null

    var deleted : Boolean = false



}