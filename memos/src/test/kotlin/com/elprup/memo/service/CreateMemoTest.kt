package com.elprup.memo.service

import com.elprup.memo.application.request.CreateMemoRequest
import com.elprup.memo.common.MemoServiceTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.mockito.Mockito.lenient

class CreateMemoTest : MemoServiceTest() {
    @Test
    @DisplayName("메모 생성하기")
    fun createMemo_Success() {
        // given
        val createMemoRequest = CreateMemoRequest(title = "test title", content = "test content")
        lenient().`when`(memoRepository.save(createMemoRequest.toMemo()))
            .thenReturn(memo)

        // when
        memoService.createMemo(createMemoRequest)

        // then
        assertEquals(memo.title, createMemoRequest.title)
        assertEquals(memo.content, createMemoRequest.content)
    }
}