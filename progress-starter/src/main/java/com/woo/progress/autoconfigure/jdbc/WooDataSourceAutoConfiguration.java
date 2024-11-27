package com.woo.progress.autoconfigure.jdbc;

import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.core.Ordered;
import org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy;

import javax.sql.DataSource;

@Slf4j
@AutoConfiguration(before = DataSourceAutoConfiguration.class)
@AutoConfigureOrder(Ordered.HIGHEST_PRECEDENCE)
@ConditionalOnClass(DataSource.class)
@ConditionalOnProperty(prefix = "woo.datasource.default", name = {
        "write.driver-class-name",
        "write.jdbc-url",
        "write.username",
        "write.password",
        "read.driver-class-name",
        "read.jdbc-url",
        "read.username",
        "read.password"
})
@EnableConfigurationProperties({
        WooDefaultReadDataSourceProperties.class,
        WooDefaultWriteDataSourceProperties.class
})
public class WooDataSourceAutoConfiguration {

    @Bean
    @ConfigurationProperties(prefix = "woo.datasource.default.write.hikari")
    public HikariDataSource defaultWriteDataSource(final WooDefaultWriteDataSourceProperties writeProperties) {
        log.debug("### WooAutoconfigure Bean: '{}'", "defaultWriteDataSource");

        return DataSourceBuilder.create()
                .type(HikariDataSource.class)
                .driverClassName(writeProperties.getDriverClassName())
                .url(writeProperties.getJdbcUrl())
                .username(writeProperties.getUsername())
                .password(writeProperties.getPassword())
                .build();
    }

    @Bean
    @ConfigurationProperties(prefix = "woo.datasource.default.read.hikari")
    public HikariDataSource defaultReadDataSource(final WooDefaultReadDataSourceProperties readProperties) {
        log.debug("### WooAutoconfigure Bean: '{}'", "defaultReadDataSource");

        return DataSourceBuilder.create().type(HikariDataSource.class)
                .type(HikariDataSource.class)
                .driverClassName(readProperties.getDriverClassName())
                .url(readProperties.getJdbcUrl())
                .username(readProperties.getUsername())
                .password(readProperties.getPassword())
                .build();
    }

    @Bean
    public DataSource defaultRoutingDataSource(@Qualifier("defaultWriteDataSource") DataSource writeDataSource,
                                               @Qualifier("defaultReadDataSource") DataSource readDataSource) {
        log.debug("### WooAutoconfigure Bean: '{}'", "defaultRoutingDataSource");

        return ReplicationRoutingDataSource.of(writeDataSource, readDataSource);
    }

    @Primary
    @Bean
    public DataSource dataSource(@Qualifier("defaultRoutingDataSource") DataSource routingDataSource) {
        log.debug("### WooAutoconfigure Bean: '{}'", "dataSource");

        return new LazyConnectionDataSourceProxy(routingDataSource);
    }
}
