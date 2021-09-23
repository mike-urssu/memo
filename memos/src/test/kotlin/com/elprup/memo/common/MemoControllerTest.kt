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

    lateinit var memos: List<Memo>

    val invalidMemoId = -1

    @BeforeAll
    fun initData() {
        memo = createMockMemo()
        memos = createMockMemos()
    }

    fun createMockMemo(): Memo {
        val memo = Memo(title = "test title", content = "test content")
        return memoRepository.save(memo)
    }

    fun createMockMemos(): List<Memo> {
        val memos = mutableListOf<Memo>()
        memos.add(Memo(title = "test title 0", content = "test content 0"))
        memos.add(Memo(title = "test title 1", content = "test content 1"))
        memos.add(Memo(title = "test title 2", content = "test content 2"))
        memos.add(Memo(title = "test title 3", content = "test content 3"))
        memos.add(Memo(title = "test title 4", content = "test content 4"))
        memos.add(Memo(title = "test title 5", content = "test content 5"))
        memos.add(Memo(title = "test title 6", content = "test content 6"))
        memos.add(Memo(title = "test title 7", content = "test content 7"))
        return memoRepository.saveAll(memos)
    }
}