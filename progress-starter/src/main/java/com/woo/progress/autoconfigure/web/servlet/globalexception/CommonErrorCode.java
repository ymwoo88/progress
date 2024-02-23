package com.woo.progress.autoconfigure.web.servlet.globalexception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum CommonErrorCode implements ErrorCode {

    SERVER_ERROR("E_000", "서버 오류", HttpStatus.INTERNAL_SERVER_ERROR),
    INVALID_REQUEST("E_001", "잘못된 요청"),
    INVALID_REQUEST_PARAM("E_002", "요청 파라미터 오류"),
    UNHANDLED_ERROR("E_003", "서버 오류. 확인 요청 필요", HttpStatus.INTERNAL_SERVER_ERROR),
    NOT_FOUND_ROLE("E_004", "존재하지 않는 롤"),
    INVOICE_ID_GENERATE_LOOP_LIMIT("E_005", "아이디를 생성할수 없습니다", HttpStatus.INTERNAL_SERVER_ERROR),
    INVALID_CRYPT("E_006", "암호화 중 오류가 발생했습니다.", HttpStatus.INTERNAL_SERVER_ERROR),
    JSON_PARSING_ERROR("E_007", "JSON 파싱 중 오류가 발생했습니다.", HttpStatus.INTERNAL_SERVER_ERROR),
    ;

    String code;
    String message;
    HttpStatus httpStatus;

    CommonErrorCode(String code, String message) {
        this(code, message, HttpStatus.BAD_REQUEST);
    }
}
