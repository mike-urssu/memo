package com.elprup.memo.service

import com.elprup.memo.application.request.UpdateMemoRequest
import com.elprup.memo.common.MemoServiceTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
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
}