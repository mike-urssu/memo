package com.elprup.memo.service

import com.elprup.memo.common.MemoServiceTest
import com.elprup.memo.domain.exception.MemoNotFoundException
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.BDDMockito.given
import java.time.format.DateTimeFormatter
import java.util.*

class GetMemoTest : MemoServiceTest() {
    @Test
    @DisplayName("특정 메모 조회하기(성공)")
    fun getMemo_Success() {
        // given
        given(memoRepository.findById(memo.id!!))
            .willReturn(Optional.of(memo))

        // when
        val getMemoDto = memoService.getMemo(memo.id!!)

        // then
        assertEquals(memo.id, getMemoDto.memoId)
        assertEquals(memo.title, getMemoDto.title)
        assertEquals(memo.content, getMemoDto.content)
        assertEquals(memo.updatedAt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")), getMemoDto.updatedAt)
    }

    @Test
    @DisplayName("특정 메모 조회하기(실패-존재하지 않는 메모)")
    fun getMemo_Fail_MemoNotFound() {
        assertThrows<MemoNotFoundException> {
            val invalidMemoId = Integer.MIN_VALUE
            memoService.getMemo(invalidMemoId)
        }
    }
}