package com.elprup.memo.common

import com.elprup.memo.domain.model.entity.Memo
import com.elprup.memo.domain.model.repository.MemoRepository
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MemoControllerTest : BaseControllerTest() {
    @Autowired
    lateinit var memoRepository: MemoRepository

    lateinit var memo: Memo

    val invalidMemoId = -1

    @BeforeAll
    fun initData() {
        memo = createMockMemo()
    }

    fun createMockMemo(): Memo {
        val memo = Memo(title = "test title", content = "test content")
        return memoRepository.save(memo)
    }
}