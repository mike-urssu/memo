package com.elprup.memo.application.exception

import com.elprup.memo.application.response.ErrorResponse
import com.elprup.memo.domain.exception.MemoNotFoundException
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class MemoExceptionHandler {
    @ExceptionHandler(MemoNotFoundException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun handleMemoNotFound(exception: MemoNotFoundException): ErrorResponse {
        return ErrorResponse(HttpStatus.NOT_FOUND, "Memo-001", exception.message!!)
    }
}