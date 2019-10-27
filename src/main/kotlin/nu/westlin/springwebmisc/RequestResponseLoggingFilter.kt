package nu.westlin.springwebmisc

import org.slf4j.LoggerFactory
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component
import javax.servlet.Filter
import javax.servlet.FilterChain
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
@Order(1)
class RequestResponseLoggingFilter : Filter {

    companion object {
        private val logger = LoggerFactory.getLogger(RequestResponseLoggingFilter::class.java)
    }

    override fun doFilter(request: ServletRequest, response: ServletResponse, chain: FilterChain) {
        chain.doFilter(request, response)
        val req = request as HttpServletRequest
        val res = response as HttpServletResponse
        logger.info("${req.method} ${req.requestURI} - ${res.status} ${res.contentType}")
    }


}