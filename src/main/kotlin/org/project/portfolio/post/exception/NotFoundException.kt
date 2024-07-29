package org.project.portfolio.post.exception

import org.springframework.http.HttpStatus

class NotFoundException(
    override val message: String = "Not Found",
    val httpStatus: HttpStatus = HttpStatus.NOT_FOUND
) : RuntimeException() {


}