package com.elprup.memo.service

import com.elprup.memo.common.MemoServiceTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.given
import org.springframework.data.domain.PageImpl
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class GetMemosTest : MemoServiceTest() {
    @Test
    @DisplayName("메모 목록 조회하기(성공)")
    fun getMemos_Success() {
        // given
        val memosPage = PageImpl(listOf(memo), defaultPageable, 1)

        given(
            memoRepository.findAllByUpdatedAtIsAfterOrderByUpdatedAtDescIdDesc(
                LocalDate.now().atStartOfDay(),
                defaultPageable
            )
        ).willReturn(memosPage)

        // when
        val memos = memoService.getMemos(LocalDate.now(), defaultPageable)

        // then
        assertEquals(memo.id, memos.content[0].memoId)
        assertEquals(memo.title, memos.content[0].title)
        assertEquals(memo.content, memos.content[0].content)
        assertEquals(
            memo.updatedAt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
            memos.content[0].updatedAt
        )
    }
}