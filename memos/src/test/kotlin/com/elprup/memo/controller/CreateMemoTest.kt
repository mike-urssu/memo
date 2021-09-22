package com.elprup.memo.controller

import com.elprup.memo.application.request.CreateMemoRequest
import com.elprup.memo.common.MemoControllerTest
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.post

class CreateMemoTest : MemoControllerTest() {
    @Test
    @DisplayName("메모 생성하기(성공)")
    fun createMemo_Success() {
        val requestDto = CreateMemoRequest(title = "test title", content = "test content")

        val test = mockMvc.post("/v1/api/memo") {
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(requestDto)
        }

        test.andExpect {
            status { isCreated() }
        }
    }
}