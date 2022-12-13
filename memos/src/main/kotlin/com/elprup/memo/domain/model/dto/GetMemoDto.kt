package com.elprup.memo.domain.model.dto

import com.elprup.memo.domain.model.entity.Memo
import java.time.format.DateTimeFormatter

class GetMemoDto(memo: Memo) {
    val memoId = memo.id!!
    val title = memo.title
    val content = memo.content
    val updatedAt: String = memo.updatedAt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
}