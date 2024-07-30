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

    var deletedAt : LocalDateTime? = null

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        else if (other !is Post) {
            return false
        } else {
            val otherPost = other as Post
            return content.equals(otherPost.content)
                    && title.equals(otherPost.title)
                    && member?.email.equals(otherPost.member?.email)
        }

    }

}