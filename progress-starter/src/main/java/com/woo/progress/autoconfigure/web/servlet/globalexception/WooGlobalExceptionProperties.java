package com.woo.progress.autoconfigure.web.servlet.globalexception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "woo.exception")
public class WooGlobalExceptionProperties {
    private boolean enabled = true;
}
