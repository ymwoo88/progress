package com.woo.progress.autoconfigure.web.servlet.logging;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "woo.logging")
public class WooLoggingProperties {

    private boolean enabled = true;
}
