package com.elprup.memo.application.response

import org.springframework.data.domain.Page

open class PageResponse<T>(page: Page<T>) {
    val page = PageInfo(page.size, page.totalElements, page.totalPages, page.number)

    class PageInfo(
        val size: Int,
        val totalElements: Long,
        val totalPages: Int,
        val number: Int
    )
}