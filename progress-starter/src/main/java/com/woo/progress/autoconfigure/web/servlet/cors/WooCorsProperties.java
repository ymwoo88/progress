package com.woo.progress.autoconfigure.web.servlet.cors;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@Getter
@Setter
@ConfigurationProperties(prefix = "woo.cors")
public class WooCorsProperties {
    private boolean enabled = true;
    private List<String> allowOriginPatterns = List.of(
            "http://*.hanatour.com:[*]",
            "https://*.hanatour.com:[*]",
            "http://localhost:[*]",
            "https://localhost:[*]"
    );
    private boolean allowCredential = true;
    private long maxAge = 3600L;
    private List<String> allowedHeaders = List.of("*");
    private List<String> allowedMethods = List.of("*");
    private List<String> exposedHeaders = List.of("*");
}
