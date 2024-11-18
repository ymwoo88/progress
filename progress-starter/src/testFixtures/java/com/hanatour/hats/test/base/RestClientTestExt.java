package com.hanatour.hats.test.base;

import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.web.reactive.function.client.WebClient;

@RestClientTest
@Import(RestClientTestExt.TestWebClientConfiguration.class)
public abstract class RestClientTestExt extends MockServerTest implements DefaultTest {


    /**
     * 기본 RestClient 테스트를 위한 환경설정을 만들어 준다.
     */
    @TestConfiguration
    static class TestWebClientConfiguration {
        @Bean
        public WebClient webClient() {
            return WebClient.create(getHostAndPort());
        }
    }
}
