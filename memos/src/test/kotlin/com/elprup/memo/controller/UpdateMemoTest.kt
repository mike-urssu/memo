package com.elprup.memo.controller

import com.elprup.memo.application.request.UpdateMemoRequest
import com.elprup.memo.common.MemoControllerTest
import com.elprup.memo.domain.model.entity.Memo
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.put

class UpdateMemoTest : MemoControllerTest() {
    @Test
    @DisplayName("메모 수정하기(성공)")
    fun updateMemo_Success() {
        val requestDto = UpdateMemoRequest(title = "updated test title", content = "updated test content")

        val test = mockMvc.put("/v1/api/memo/{memoId}", memo.id) {
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(requestDto)
        }

        test.andExpect {
            status { isNoContent() }
        }
    }

    @Test
    @DisplayName("메모 수정하기(실패-존재하지 않는 메모)")
    fun updateMemo_Fail_MemoNotFound() {
        val requestDto = UpdateMemoRequest(title = "updated test title", content = "updated test content")

        val test = mockMvc.put("/v1/api/memo/{memoId}", invalidMemoId) {
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(requestDto)
        }

        test.andExpect {
            status { isNotFound() }
            jsonPath("status") { value(404) }
            jsonPath("error") { value("Memo-001") }
        }
    }
}