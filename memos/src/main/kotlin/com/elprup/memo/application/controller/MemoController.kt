package com.elprup.memo.application.controller

import com.elprup.memo.application.request.CreateMemoRequest
import com.elprup.memo.application.request.UpdateMemoRequest
import com.elprup.memo.application.response.GetMemoResponse
import com.elprup.memo.domain.service.MemoService
import io.swagger.annotations.ApiOperation
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
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

    @ApiOperation("특정 메모 조회하기")
    @GetMapping("/v1/api/memo/{memoId}")
    @ResponseStatus(HttpStatus.OK)
    fun getMemo(@PathVariable memoId: Int): GetMemoResponse {
        val getMemoDto = memoService.getMemo(memoId)
        return GetMemoResponse(getMemoDto)
    }

    @ApiOperation("특정 메모 수정하기")
    @PutMapping("/v1/api/memo/{memoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun updateMemo(
        @PathVariable memoId: Int,
        @RequestBody @Valid updateMemoRequest: UpdateMemoRequest
    ) {
        return memoService.updateMemo(memoId, updateMemoRequest)
    }
}