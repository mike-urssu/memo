package com.elprup.memo.service

import com.elprup.memo.domain.exception.MemoNotFoundException
import com.elprup.memo.domain.model.dto.GetMemoDto
import com.elprup.memo.domain.model.entity.Memo
import com.elprup.memo.domain.model.repository.MemoRepository
import com.elprup.memo.domain.service.MemoService
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito.given
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.lenient
import org.mockito.junit.jupiter.MockitoExtension
import java.util.Optional

@ExtendWith(MockitoExtension::class)
class MemoServiceTest {
    @InjectMocks
    lateinit var memoService: MemoService

    @Mock
    lateinit var memoRepository: MemoRepository

    @Test
    @DisplayName("Memo 생성하기")
    fun createMemo_Success() {
        val title = "title for service unit test"
        val content = "content for service unit test"
        val memo = Memo(title, content)

        // given
        lenient().`when`(memoRepository.save(memo)).thenReturn(memo)

        // when
        val createdMemo = memoService.createMemo(title, content)

        // then
        assertEquals(memo, createdMemo)
    }

    @Test
    @DisplayName("특정 메모 조회하기_성공")
    fun getMemo_Success() {
        val id = 1
        val title = "title for service unit test"
        val content = "content for service unit test"
        val memo = Memo(title, content)
        memo.id = id
        val expectedDto = GetMemoDto(memo)

        // given
        given(memoRepository.findById(id)).willReturn(Optional.of(memo))

        // when
        val actualDto = memoService.getMemo(id)

        // then
        assertEquals(expectedDto.memoId, actualDto.memoId)
        assertEquals(expectedDto.title, actualDto.title)
        assertEquals(expectedDto.content, actualDto.content)
    }

    @Test
    @DisplayName("특정 메모 조회하기_실패_해당 메모가 존재하지 않음")
    fun getMemo_Fail_MemoNotFound() {
        val invalidId = Int.MIN_VALUE

        // given
        given(memoRepository.findById(invalidId)).willThrow(MemoNotFoundException())

        // when, then
        assertThrows<MemoNotFoundException> { memoService.getMemo(invalidId) }
    }

    @Test
    @DisplayName("메모 수정하기_성공")
    fun updateMemo_Success() {
        val id = 1
        val title = "title for service unit test"
        val content = "content for service unit test"
        val memo = Memo(title, content)
        memo.id = id

        val newTitle = "updated title for service unit test"
        val newContent = "updated content for service unit test"
        val expectedMemo = memo.copy(newTitle, newContent)
        expectedMemo.id = memo.id

        // given
        given(memoRepository.findById(id)).willReturn(Optional.of(memo))
        given(memoRepository.save(expectedMemo)).willReturn(expectedMemo)

        // when
        val actualMemo = memoService.updateMemo(id, newTitle, newContent)

        // then
        assertEquals(expectedMemo, actualMemo)
    }

    @Test
    @DisplayName("메모 수정하기_실패_해당 메모가 존재하지 않음")
    fun updateMemo_Fail_MemoNotFound() {
        val invalidId = Int.MIN_VALUE
        val newTitle = "updated title for service unit test"
        val newContent = "updated content for service unit test"

        // given
        given(memoRepository.findById(invalidId)).willThrow(MemoNotFoundException())

        // when, then
        assertThrows<MemoNotFoundException> { memoService.updateMemo(invalidId, newTitle, newContent) }
    }
}
