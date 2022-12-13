package com.elprup.memo.application.request

import javax.validation.constraints.NotNull

class UpdateMemoRequest(
    @field:NotNull
    val title: String,
    @field:NotNull
    val content: String
) {
    override fun toString() = """
            { "title": "$title", "content": "$content" }
        """.trimIndent()
}