package com.woo.progress.autoconfigure.web.servlet.globalexception;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.reactive.function.client.WebClientRequestException;
import org.springframework.web.reactive.function.client.WebClientResponseException;


@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
public class WooGlobalExceptionHandler {

    /**
     * WooRuntimeException 예외 처리
     *
     * @param failed
     * @return
     */
    @ExceptionHandler(WooRuntimeException.class)
    public ProblemDetail wooRuntimeException(WooRuntimeException failed) {

        errorLog(failed);
        ErrorCode errorCode = failed.getErrorCode();
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(errorCode.getHttpStatus(), errorCode.getMessage());
        if (!CollectionUtils.isEmpty(failed.getDetail())) {
            problemDetail.setProperty("more", failed.getDetailString());
        }
        return problemDetail;
    }

    @ExceptionHandler(WebClientRequestException.class)
    public ProblemDetail webClientRequestException(WebClientRequestException failed) {

        errorLog(failed);
        return ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, failed.getMessage());
    }

    @ExceptionHandler(WebClientResponseException.class)
    public ProblemDetail webClientResponseException(WebClientResponseException failed) {

        errorLog(failed);
        return ProblemDetail.forStatusAndDetail(failed.getStatusCode(), failed.getMessage());
    }

    /**
     * Group 예외처리
     *
     * @param failed
     * @return
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ProblemDetail constraintViolationException(ConstraintViolationException failed) {

        errorLog(failed);
        return ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, getConstraintViolationMessage(failed));
    }

    /**
     * Valid 예외 처리
     *
     * @param failed
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail methodArgumentNotValidException(MethodArgumentNotValidException failed) {

        errorLog(failed);
        return failed.getBody();
    }

    @ExceptionHandler(Exception.class)
    public ProblemDetail exception(Exception failed) {

        errorLog(failed);
        return ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, failed.getMessage());
    }

    private String getConstraintViolationMessage(ConstraintViolationException failed) {
        StringBuilder sb = new StringBuilder();
        for (ConstraintViolation violation : failed.getConstraintViolations()) {
            sb.append("[")
                    .append(violation.getPropertyPath().toString())
                    .append(": ").append(violation.getMessage())
                    .append("] ");
        }
        return sb.toString();
    }

    private void errorLog(Exception failed) {
        log.error("### Error: '{}'", failed.getMessage());
    }
}
