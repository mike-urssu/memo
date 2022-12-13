package com.elprup.memo.service

import com.elprup.memo.domain.model.entity.Memo
import com.elprup.memo.domain.model.repository.MemoRepository
import com.elprup.memo.domain.service.MemoService
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.lenient
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.transaction.annotation.Transactional

@ExtendWith(MockitoExtension::class)
@Transactional
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
}
