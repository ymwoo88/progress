package com.woo.progress.autoconfigure.web.servlet.globalexception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.web.servlet.DispatcherServletAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import static org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication.Type;

@Slf4j
@AutoConfiguration(after = DispatcherServletAutoConfiguration.class)
@ConditionalOnWebApplication(type = Type.SERVLET)
@ConditionalOnProperty(prefix = "woo.exception", name = "enabled", matchIfMissing = true)
@EnableConfigurationProperties(WooGlobalExceptionProperties.class)
public class WooGlobalExceptionAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public WooGlobalExceptionHandler wooGlobalExceptionHandler() {
        log.debug("### WooAutoconfigure Bean: '{}'", "wooGlobalExceptionHandler");

        return new WooGlobalExceptionHandler();
    }
}
