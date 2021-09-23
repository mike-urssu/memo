package com.elprup.memo.controller

import com.elprup.memo.common.MemoControllerTest
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.test.web.servlet.get
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class GetMemosTest : MemoControllerTest() {
    @Test
    @DisplayName("메모 목록 조회하기(성공)")
    fun getMemos_Success() {
        val test = mockMvc.get("/v1/api/memos") {
            param("date", "${LocalDate.now()}")
            param("page", "0")
        }

        test.andExpect {
            status { isOk() }
            jsonPath("memos[0].memoId") { value(memos[memos.lastIndex].id) }
            jsonPath("memos[0].title") { value(memos[memos.lastIndex].title) }
            jsonPath("memos[0].content") { value(memos[memos.lastIndex].content) }
            jsonPath("memos[0].updatedAt") { value(memos[memos.lastIndex].updatedAt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))) }
            jsonPath("page.size") { exists() }
            jsonPath("page.totalElements") { exists() }
            jsonPath("page.totalPages") { exists() }
            jsonPath("page.number") { exists() }
        }
    }
}