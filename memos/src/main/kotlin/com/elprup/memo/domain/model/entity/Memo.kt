package com.elprup.memo.domain.model.entity

import com.fasterxml.jackson.annotation.JsonFormat
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.DynamicUpdate
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime
import javax.persistence.*

@DynamicUpdate
@Entity
@Table(name = "memos")
data class Memo(
    @field:Column(nullable = false, length = 50)
    var title: String,

    @field:Column(nullable = false, columnDefinition = "TEXT")
    var content: String
) {
    @field:Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null

    @CreationTimestamp
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @field:Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", updatable = false)
    private val createdAt = LocalDateTime.now()

    @UpdateTimestamp
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @field:Column(
        nullable = false,
        columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP"
    )
    var updatedAt: LocalDateTime = LocalDateTime.now()
}