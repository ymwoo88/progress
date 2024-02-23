package com.woo.progress.autoconfigure.web.servlet.logging;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.AutoConfigurations;
import org.springframework.boot.test.context.runner.WebApplicationContextRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

class WooLoggingAutoConfigurationTest {
    private final WebApplicationContextRunner contextRunner = new WebApplicationContextRunner()
            .withConfiguration(AutoConfigurations.of(WooLoggingAutoConfiguration.class));


    @Test
    @DisplayName("enabled를 false로 하면 빈을 로딩하지 않는다")
    void enabled를_false로_하면_빈을_로딩하지_않는다() {

        this.contextRunner.withPropertyValues("woo.logging.enabled=false")
                .run(context -> {
                    assertThat(context).getBeans(LoggingFilter.class).hasSize(0);
                });
    }

    @Test
    @DisplayName("로깅필터 빈이 존재하면 빈을 생성하지 않는다")
    void 로깅필터_빈이_존재하면_빈을_생성하지_않는다() {

        this.contextRunner
                .withBean("mockHntLoggingFilter", LoggingFilter.class, () -> mock(LoggingFilter.class))
                .run(context -> {
                    assertThat(context).getBeans(LoggingFilter.class).hasSize(1);
                    assertThat(context).getBean("loggingFilter").isNull();
                });
    }

    @Test
    @DisplayName("다른 클래스 이지만 같은 이름의 로깅 필터가 존재하면 빈을 생성하지 않는다")
    void 다른_클래스_이지만_같은_이름의_로깅_필터가_존재하면_빈을_생성하지_않는다() {

        this.contextRunner
                .withBean("loggingFilter", FakeLoggingFilter.class, () -> mock(FakeLoggingFilter.class))
                .run(context -> {
                    assertThat(context).getBean("loggingFilter").isNotNull();
                    assertThat(context).getBeans(LoggingFilter.class).hasSize(0);
                });
    }

    static class FakeLoggingFilter {
    }
}
