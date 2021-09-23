package com.elprup.memo.application.response

import com.elprup.memo.domain.model.dto.GetMemoDto
import org.springframework.data.domain.Page

class GetMemosResponse(page: Page<GetMemoDto>) : PageResponse<GetMemoDto>(page) {
    val memos: List<GetMemoDto> = page.content
}