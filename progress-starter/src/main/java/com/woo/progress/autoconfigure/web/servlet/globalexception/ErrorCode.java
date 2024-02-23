package com.woo.progress.autoconfigure.web.servlet.globalexception;

import org.springframework.http.HttpStatus;

public interface ErrorCode {

    String getCode();
    String getMessage();
    HttpStatus getHttpStatus();
}
