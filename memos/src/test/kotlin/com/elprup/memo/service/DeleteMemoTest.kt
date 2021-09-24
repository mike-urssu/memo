package com.elprup.memo.service

import com.elprup.memo.common.MemoServiceTest
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.given
import org.mockito.Mockito.verify
import java.util.*

class DeleteMemoTest : MemoServiceTest() {
    @Test
    @DisplayName("메모 삭제하기(성공)")
    fun deleteMemo_Success() {
        // given
        given(memoRepository.findById(memo.id!!))
            .willReturn(Optional.of(memo))

        // when
        memoService.deleteMemo(memo.id!!)

        //then
        verify(memoRepository).delete(memo)
    }
}