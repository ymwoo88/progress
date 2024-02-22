package com.woo.progress.autoconfigure.web.servlet.logging;

import com.woo.progress.autoconfigure.http.WooHttpHeader;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.boot.web.servlet.filter.OrderedFilter;
import org.springframework.core.Ordered;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
public class LoggingFilter extends OncePerRequestFilter implements OrderedFilter {
    @Override
    protected void doFilterInternal(final HttpServletRequest request, final HttpServletResponse response, final FilterChain filterChain) throws ServletException, IOException {

        putRemoteAddr(request);
        filterChain.doFilter(request, response);
        removeRemoteAddr();
    }

    /**
     * server.forward-headers-strategy: framework
     * 설정이 되어 있지 않으면 프록시의 IP가 노출된다.
     *
     * @param request
     */
    private void putRemoteAddr(final HttpServletRequest request) {
        try {
            MDC.put(WooHttpHeader.X_FORWARDED_FOR_VALUE, request.getRemoteAddr());
        } catch (IllegalArgumentException e) {
            log.error("### Error addRemoteAddr");
        }
    }

    private void removeRemoteAddr() {
        try {
            MDC.remove(WooHttpHeader.X_FORWARDED_FOR_VALUE);
        } catch (IllegalArgumentException e) {
            log.error("### Error removeRemoteAddr");
        }
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }
}
