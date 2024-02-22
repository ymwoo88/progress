package com.woo.progress.autoconfigure.http;

import lombok.Getter;

/**
 * 참고: {@link org.springframework.http.HttpHeaders}.
 */
@Getter
public enum WooHttpHeader {
    X_FORWARDED_FOR(WooHttpHeader.X_FORWARDED_FOR_VALUE),
    X_ELASTIC_PRODUCT(WooHttpHeader.X_ELASTIC_PRODUCT_VALUE),
    ;

    public static final String X_FORWARDED_FOR_VALUE = "X-Forwarded-For";
    public static final String X_ELASTIC_PRODUCT_VALUE = "X-Elastic-Product";

    private final String value;

    WooHttpHeader(String value) {
        this.value = value;
    }
}
