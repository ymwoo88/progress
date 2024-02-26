package com.woo.progress.autoconfigure.web.servlet.cors;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.web.servlet.DispatcherServletAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import static org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication.Type;

@Slf4j
@AutoConfiguration(after = DispatcherServletAutoConfiguration.class)
@ConditionalOnWebApplication(type = Type.SERVLET)
@ConditionalOnProperty(prefix = "woo.cors", name = "enabled", matchIfMissing = true)
@EnableConfigurationProperties(WooCorsProperties.class)
public class WooCorsAutoConfiguration {

    @Primary
    @Bean
    public CorsConfigurationSource corsConfigurationSource(WooCorsProperties properties) {
        log.debug("### WooAutoconfigure Bean: '{}'", "corsConfigurationSource");

        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(properties.isAllowCredential());
        config.setAllowedOriginPatterns(properties.getAllowOriginPatterns());
        config.setAllowedHeaders(properties.getAllowedHeaders());
        config.setAllowedMethods(properties.getAllowedMethods());
        config.setExposedHeaders(properties.getExposedHeaders());
        config.setMaxAge(properties.getMaxAge());

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        return source;
    }
}
