package com.woo.progress.autoconfigure.jdbc;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@NoArgsConstructor
@ConfigurationProperties(prefix = "woo.datasource.default.write")
public class WooDefaultWriteDataSourceProperties {

    private String driverClassName;
    private String jdbcUrl;
    private String username;
    private String password;
}
