package org.project.portfolio.post.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

data class PostRequest(


    @field:Size(max = 200)
    var title: String? = null,

    @field:Size(max = 1000)
    var content: String? = null
)
