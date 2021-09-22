package com.elprup.memo.controller

import com.elprup.memo.common.MemoControllerTest
import com.elprup.memo.domain.model.entity.Memo
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.test.web.servlet.get

class GetMemoTest : MemoControllerTest() {
    lateinit var memo: Memo

    @BeforeEach
    fun initData() {
        memo = createMockMemo()
    }

    @Test
    @DisplayName("특정 메모 조회(성공)")
    fun getMemo_Success() {
        val test = mockMvc.get("/v1/api/memo/{memoId}", memo.id)

        test.andExpect {
            status { isOk() }
            jsonPath("memo.memoId") { value(memo.id) }
            jsonPath("memo.title") { value(memo.title) }
            jsonPath("memo.content") { value(memo.content) }
        }
    }

    @Test
    @DisplayName("특정 메모 조회(실패-존재하지 않는 메모)")
    fun getMemo_Fail_MemoNotFound() {
        val test = mockMvc.get("/v1/api/memo/{memoId}", invalidMemoId)

        test.andExpect {
            status { isNotFound() }
            jsonPath("status") { value(404) }
            jsonPath("error") { value("Memo-001") }
        }
    }
}