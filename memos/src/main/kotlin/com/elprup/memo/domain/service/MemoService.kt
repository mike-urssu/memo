package com.elprup.memo.domain.service

import com.elprup.memo.domain.exception.MemoNotFoundException
import com.elprup.memo.domain.model.dto.GetMemoDto
import com.elprup.memo.domain.model.entity.Memo
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
    fun createMemo(title: String, content: String) =
        memoRepository.save(Memo(title, content))

    @Transactional(readOnly = true)
    fun getMemo(memoId: Int): GetMemoDto =
        memoRepository.findById(memoId)
            .map { GetMemoDto(it) }
            .orElseThrow { MemoNotFoundException() }

    fun updateMemo(memoId: Int, title: String, content: String): Memo =
        memoRepository.findById(memoId)
            .map {
                it.update(title, content)
                memoRepository.save(it)
            }
            .orElseThrow { MemoNotFoundException() }

    fun deleteMemo(memoId: Int) =
        memoRepository.findById(memoId)
            .ifPresentOrElse(
                {
                    memoRepository.delete(it)
                },
                {
                    throw MemoNotFoundException()
                }
            )

    fun getMemos(date: LocalDate, pageRequest: Pageable): Page<GetMemoDto> {
        return memoRepository.findAllByUpdatedAtIsAfterOrderByUpdatedAtDescIdDesc(date.atStartOfDay(), pageRequest)
            .map {
                GetMemoDto(it)
            }
    }
}