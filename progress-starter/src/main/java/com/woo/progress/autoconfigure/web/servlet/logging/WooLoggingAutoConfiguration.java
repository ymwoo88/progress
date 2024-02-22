package com.woo.progress.autoconfigure.web.servlet.logging;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import static org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication.Type;

@Slf4j
@AutoConfiguration
@ConditionalOnWebApplication(type = Type.SERVLET)
@ConditionalOnProperty(prefix = "woo.logging", name = "enabled", matchIfMissing = true)
@EnableConfigurationProperties(WooLoggingProperties.class)
public class WooLoggingAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean(name = "loggingFilter", value = LoggingFilter.class)
    public LoggingFilter loggingFilter() {
        log.debug("### WooAutoconfigure Bean: '{}'", "loggingFilter");

        return new LoggingFilter();
    }
}
