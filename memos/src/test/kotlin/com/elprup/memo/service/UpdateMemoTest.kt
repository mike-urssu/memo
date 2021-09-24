package com.elprup.memo.service

import com.elprup.memo.application.request.UpdateMemoRequest
import com.elprup.memo.common.MemoServiceTest
import com.elprup.memo.domain.exception.MemoNotFoundException
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.BDDMockito.given
import java.util.*

class UpdateMemoTest : MemoServiceTest() {
    @Test
    @DisplayName("메모 수정하기(성공)")
    fun updateMemo_Success() {
        // given
        val updateMemoRequest = UpdateMemoRequest(title = "updated title", content = "updated content")
        given(memoRepository.findById(memo.id!!))
            .willReturn(Optional.of(memo))
        given(memoRepository.save(memo))
            .willReturn(memo)

        // when
        memoService.updateMemo(memo.id!!, updateMemoRequest)

        // then
        assertEquals(memo.title, updateMemoRequest.title)
        assertEquals(memo.content, updateMemoRequest.content)
    }

    @Test
    @DisplayName("메모 수정하기(실패-존재하지 않는 메모)")
    fun updateMemo_Fail_MemoNotFound() {
        assertThrows<MemoNotFoundException> {
            val invalidMemoId = Integer.MIN_VALUE
            val updateMemoRequest = UpdateMemoRequest(title = "updated title", content = "updated content")
            memoService.updateMemo(invalidMemoId, updateMemoRequest)
        }
    }
}