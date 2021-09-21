package com.elprup.memo.application.controller

import com.elprup.memo.application.request.CreateMemoRequest
import com.elprup.memo.domain.service.MemoService
import io.swagger.annotations.ApiOperation
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
class MemoController(
    private val memoService: MemoService
) {
    @ApiOperation("메모 생성하기")
    @PostMapping("/v1/api/memo")
    @ResponseStatus(HttpStatus.CREATED)
    fun createMemo(@RequestBody @Valid createMemoRequest: CreateMemoRequest) {
        return memoService.createMemo(createMemoRequest)
    }
}