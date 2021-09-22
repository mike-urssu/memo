package com.elprup.memo.common

import com.elprup.memo.domain.model.entity.Memo
import com.elprup.memo.domain.model.repository.MemoRepository
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MemoControllerTest : BaseControllerTest() {
    @Autowired
    lateinit var memoRepository: MemoRepository

    val invalidMemoId = -1

    fun createMockMemo(): Memo {
        val memo = Memo(title = "test title", content = "test content")
        return memoRepository.save(memo)
    }
}