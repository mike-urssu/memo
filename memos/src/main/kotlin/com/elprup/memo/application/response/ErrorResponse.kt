package com.elprup.memo.application.response

import org.springframework.http.HttpStatus
import java.util.Date

class ErrorResponse(
    val timestamp: Date,
    val status: Int,
    val error: String,
    val message: String
) {
    constructor(httpStatus: HttpStatus, errCode: String, message: String) : this(
        timestamp = Date(),
        status = httpStatus.value(),
        error = errCode,
        message = message
    )
}