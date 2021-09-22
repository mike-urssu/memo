package com.elprup.memo.domain.model.repository

import com.elprup.memo.domain.model.entity.Memo
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

@Repository
interface MemoRepository : JpaRepository<Memo, Int> {
    fun findAllByUpdatedAtIsAfter(updatedAt: LocalDateTime, pageRequest: Pageable): Page<Memo>
}