package com.elprup.memo.common

import com.elprup.memo.domain.model.entity.Memo
import com.elprup.memo.domain.model.repository.MemoRepository
import com.elprup.memo.domain.service.MemoService
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort

@ExtendWith(MockitoExtension::class)
open class MemoServiceTest {
    @Mock
    lateinit var memoRepository: MemoRepository

    @InjectMocks
    lateinit var memoService: MemoService

    val memo = Memo(id = Integer.MAX_VALUE, title = "test title", content = "test content")

    val defaultPageable = PageRequest.of(0, 5, Sort.by("updatedAt").descending())
}