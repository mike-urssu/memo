package com.elprup.memo.domain.service

import com.elprup.memo.domain.model.repository.MemoRepository
import org.springframework.stereotype.Service

@Service
class MemoService(
    val memoRepository: MemoRepository
) {

}