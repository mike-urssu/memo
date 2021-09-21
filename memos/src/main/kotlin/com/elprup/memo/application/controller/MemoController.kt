package com.elprup.memo.application.controller

import com.elprup.memo.domain.service.MemoService
import org.springframework.web.bind.annotation.RestController

@RestController
class MemoController(
    val memoService: MemoService
) {

}