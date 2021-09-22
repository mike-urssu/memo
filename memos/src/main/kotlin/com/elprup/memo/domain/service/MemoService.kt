package com.elprup.memo.domain.service

import com.elprup.memo.application.request.CreateMemoRequest
import com.elprup.memo.application.request.UpdateMemoRequest
import com.elprup.memo.domain.exception.MemoNotFoundException
import com.elprup.memo.domain.model.dto.GetMemoDto
import com.elprup.memo.domain.model.repository.MemoRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate

@Service
class MemoService(
    private val memoRepository: MemoRepository
) {
    fun createMemo(createMemoRequest: CreateMemoRequest) {
        val memo = createMemoRequest.toMemo()
        memoRepository.save(memo)
    }

    @Transactional(readOnly = true)
    fun getMemo(memoId: Int): GetMemoDto {
        return memoRepository.findById(memoId).map {
            GetMemoDto(it)
        }.orElseThrow {
            MemoNotFoundException()
        }
    }

    fun updateMemo(memoId: Int, updateMemoRequest: UpdateMemoRequest) {
        memoRepository.findById(memoId).map {
            it.title = updateMemoRequest.title
            it.content = updateMemoRequest.content
            memoRepository.save(it)
        }.orElseThrow {
            MemoNotFoundException()
        }
    }

    fun deleteMemo(memoId: Int) {
        memoRepository.findById(memoId).map {
            memoRepository.delete(it)
        }.orElseThrow {
            MemoNotFoundException()
        }
    }

    fun getMemos(date: LocalDate, pageRequest: Pageable): Page<GetMemoDto> {
        return memoRepository.findAllByUpdatedAtIsAfter(date.atStartOfDay(), pageRequest).map {
            GetMemoDto(it)
        }
    }
}