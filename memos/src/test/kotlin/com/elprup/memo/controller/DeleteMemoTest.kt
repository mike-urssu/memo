package com.elprup.memo.controller

import com.elprup.memo.common.MemoControllerTest
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.test.web.servlet.delete

class DeleteMemoTest : MemoControllerTest() {
    @Test
    @DisplayName("메모 삭제하기(성공)")
    fun deleteMemo_Success() {
        val test = mockMvc.delete("/v1/api/memo/{memoId}", memo.id)

        test.andExpect {
            status { isNoContent() }
        }
    }

    @Test
    @DisplayName("메모 삭제하기(실패-존재하지 않는 메모)")
    fun deleteMemo_Fail_MemoNotFound() {
        val test = mockMvc.delete("/v1/api/memo/{memoId}", invalidMemoId)

        test.andExpect {
            status { isNotFound() }
            jsonPath("status") { value(404) }
            jsonPath("error") { value("Memo-001") }
        }
    }
}