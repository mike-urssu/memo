package com.elprup.memo.domain.service

import com.elprup.memo.application.request.CreateMemoRequest
import com.elprup.memo.domain.MemoNotFoundException
import com.elprup.memo.domain.model.dto.MemoDto
import com.elprup.memo.domain.model.repository.MemoRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class MemoService(
    private val memoRepository: MemoRepository
) {
    fun createMemo(createMemoRequest: CreateMemoRequest) {
        val memo = createMemoRequest.toMemo()
        memoRepository.save(memo)
    }

    @Transactional(readOnly = true)
    fun getMemo(memoId: Int): MemoDto {
        return memoRepository.findById(memoId).map {
            MemoDto(it)
        }.orElseThrow {
            MemoNotFoundException()
        }
    }
}