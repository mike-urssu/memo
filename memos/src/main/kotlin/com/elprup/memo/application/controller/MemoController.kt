package com.elprup.memo.application.controller

import com.elprup.memo.application.request.CreateMemoRequest
import com.elprup.memo.application.request.UpdateMemoRequest
import com.elprup.memo.application.response.GetMemoResponse
import com.elprup.memo.application.response.GetMemosResponse
import com.elprup.memo.domain.service.MemoService
import io.swagger.annotations.ApiOperation
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDate
import javax.validation.Valid

@RestController
@RequestMapping("/v1/api/memos")
class MemoController(
    private val memoService: MemoService
) {
    @ApiOperation("메모 생성하기")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createMemo(@RequestBody @Valid request: CreateMemoRequest) {
        memoService.createMemo(request.title, request.content)
    }

    @ApiOperation("특정 메모 조회하기")
    @GetMapping("/{memoId}")
    @ResponseStatus(HttpStatus.OK)
    fun getMemo(@PathVariable memoId: Int): GetMemoResponse {
        val dto = memoService.getMemo(memoId)
        return GetMemoResponse(dto)
    }

    @ApiOperation("메모 수정하기")
    @PutMapping("/{memoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun updateMemo(
        @PathVariable memoId: Int,
        @RequestBody @Valid request: UpdateMemoRequest
    ) {
        memoService.updateMemo(memoId, request.title, request.content)
    }

    @ApiOperation("메모 삭제하기")
    @DeleteMapping("/v1/api/memo/{memoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteMemo(
        @PathVariable memoId: Int
    ) {
        return memoService.deleteMemo(memoId)
    }

    @ApiOperation("메모 목록 조회하기")
    @GetMapping("/v1/api/memos")
    @ResponseStatus(HttpStatus.OK)
    fun getMemos(
        @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") date: LocalDate,
        @RequestParam page: Int
    ): GetMemosResponse {
        val memos = memoService.getMemos(date, PageRequest.of(page, 5, Sort.by("updatedAt").descending()))
        return GetMemosResponse(memos)
    }
}