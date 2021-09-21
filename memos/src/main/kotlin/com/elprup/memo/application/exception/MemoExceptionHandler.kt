package com.elprup.memo.application.exception

import com.elprup.memo.application.response.ErrorResponse
import com.elprup.memo.domain.MemoNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class MemoExceptionHandler {
    @ExceptionHandler(MemoNotFoundException::class)
    fun handleMemoNotFound(exception: MemoNotFoundException): ErrorResponse {
        return ErrorResponse(HttpStatus.NOT_FOUND, "Memo-001", exception.message!!)
    }
}