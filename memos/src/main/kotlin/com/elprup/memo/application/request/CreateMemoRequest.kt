package com.elprup.memo.application.request

import org.jetbrains.annotations.NotNull

class CreateMemoRequest(
    @field:NotNull
    val title: String,
    @field:NotNull
    val content: String
) {
    override fun toString() = """
            { "title": "$title", "content": "$content" }
        """.trimIndent()
}
