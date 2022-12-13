package com.elprup.memo.application.request

import com.elprup.memo.domain.model.entity.Memo
import org.jetbrains.annotations.NotNull

class CreateMemoRequest(
    @field:NotNull
    val title: String,
    @field:NotNull
    val content: String
) {
    fun toMemo(): Memo {
        return Memo(
            title = title,
            content = content
        )
    }

    override fun toString() = """
            { "title": "$title", "content": "$content" }
        """.trimIndent()
}
