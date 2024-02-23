package com.woo.progress.autoconfigure.web.servlet.globalexception;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class WooRuntimeException extends RuntimeException {

    protected final ErrorCode errorCode;
    protected String detailMessage;
    protected List<Object> detail = new ArrayList<>();

    public WooRuntimeException(ErrorCode errorCode, Object detail) {
        this(errorCode, null, detail);
    }

    public WooRuntimeException(ErrorCode errorCode, String detailMessage, Object detail) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
        this.detailMessage = detailMessage;

        if (detail == null) return;

        if (detail instanceof List castDetail) {
            this.detail = castDetail;
        } else {
            this.detail = List.of(detail.toString());
        }
    }

    public WooRuntimeException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    /**
     * WooRuntimeException(ErrorCode errorCode, Object detail) 생성자와 타입유추시 충돌날수 있어서
     * hrowable cause을 첫번째 인자로 결정
     *
     * @param cause
     * @param errorCode
     */
    public WooRuntimeException(Throwable cause, ErrorCode errorCode) {
        super(errorCode.getMessage(), cause);
        this.errorCode = errorCode;
    }

    public String getDetailString() {
        StringBuilder sb = new StringBuilder();
        for (Object message : detail) {
            sb.append("\n").append(message.toString());
        }
        sb.delete(0, 1);
        return sb.toString();
    }
}
