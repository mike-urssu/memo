package com.elprup.memo.controller

import com.elprup.memo.common.MemoControllerTest
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.test.web.servlet.delete

class DeleteMemoTest : MemoControllerTest() {
    @Test
    @DisplayName("특정 메모 삭제하기(성공)")
    fun deleteMemo_Success() {
        val test = mockMvc.delete("/v1/api/memo/{memoId}", memo.id)

        test.andExpect {
            status { isNoContent() }
        }
    }
}