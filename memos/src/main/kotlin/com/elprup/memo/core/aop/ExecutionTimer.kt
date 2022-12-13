package com.elprup.memo.core.aop

import mu.KotlinLogging
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.reflect.MethodSignature
import org.springframework.stereotype.Component
import org.springframework.util.StopWatch
import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes
import java.util.concurrent.TimeUnit

@Aspect
@Component
class ExecutionTimer {
    private val log = KotlinLogging.logger { }

    @Around("bean(*Controller)")
    fun measureExecutionTime(joinPoint: ProceedingJoinPoint): Any? {
        val stopWatch = StopWatch()
        stopWatch.start()
        val response = joinPoint.proceed()
        stopWatch.stop()

        if (stopWatch.totalTimeSeconds <= TimeUnit.SECONDS.toSeconds(1)) {
            val signature = joinPoint.signature as MethodSignature
            log.warn { "================ WARNING REPORT ================" }
            log.warn { "[HTTP METHOD] ${getHttpMethod()}" }
            log.warn { "[URI]         ${getRequestURI()}" }
            log.warn { "[REQUEST]     ${getRequest(joinPoint.args, signature.parameterNames)}" }
            log.warn { "[SPENT]       ${stopWatch.totalTimeMillis} (ms)" }
            log.warn { "================================================" }
        }
        return response
    }

    private fun getHttpMethod(): String? {
        val requestAttributes = RequestContextHolder.getRequestAttributes() ?: return null
        return (requestAttributes as ServletRequestAttributes).request.method
    }

    private fun getRequestURI(): String? {
        val requestAttributes = RequestContextHolder.getRequestAttributes() ?: return null
        return (requestAttributes as ServletRequestAttributes).request.requestURI
    }

    private fun getRequest(args: Array<Any>, parameters: Array<String>): String {
        val requests = mutableListOf<String>()
        for (i in args.indices) {
            requests.add("${args[i]}: ${args[i].javaClass.simpleName}(${parameters[i]})")
        }
        return requests.joinToString(", ")
    }
}
