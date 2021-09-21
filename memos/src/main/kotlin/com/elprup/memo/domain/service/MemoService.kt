package com.elprup.memo.domain.service

import com.elprup.memo.application.request.CreateMemoRequest
import com.elprup.memo.domain.model.repository.MemoRepository
import org.springframework.stereotype.Service

@Service
class MemoService(
    private val memoRepository: MemoRepository
) {
    fun createMemo(createMemoRequest: CreateMemoRequest) {
        val memo = createMemoRequest.toMemo()
        memoRepository.save(memo)
    }
}