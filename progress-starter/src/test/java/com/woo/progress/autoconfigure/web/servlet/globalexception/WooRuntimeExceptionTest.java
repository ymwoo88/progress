package com.woo.progress.autoconfigure.web.servlet.globalexception;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class WooRuntimeExceptionTest {

    @Test
    @DisplayName("단건 에러 메세지 확인")
    void 단건_에러_메세지_확인() {

        // given
        String detailMessage = "TEST";
        WooRuntimeException exception = new WooRuntimeException(CommonErrorCode.INVALID_REQUEST_PARAM, detailMessage);

        // when
        String detailString = exception.getDetailString();

        // then
        assertThat(detailString).doesNotStartWith("\n").contains(detailMessage);
    }

    @Test
    @DisplayName("배열 에러 메세지 확인")
    void 배열_에러_메세지_확인() {

        // given
        List<String> details = List.of("test", "message", "error");
        WooRuntimeException exception = new WooRuntimeException(CommonErrorCode.SERVER_ERROR, details);

        // when
        String detailString = exception.getDetailString();

        // then
        assertThat(detailString).doesNotStartWith("\n").contains(details);
    }
}
