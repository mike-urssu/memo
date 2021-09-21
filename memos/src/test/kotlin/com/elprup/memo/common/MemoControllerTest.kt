package com.elprup.memo.common

import com.elprup.memo.domain.model.repository.MemoRepository
import org.springframework.beans.factory.annotation.Autowired

class MemoControllerTest : BaseControllerTest() {
    @Autowired
    lateinit var memoRepository: MemoRepository
}