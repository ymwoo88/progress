package com.hanatour.hats.test.base;

import org.elasticsearch.client.RestClient;
import org.springframework.boot.test.autoconfigure.data.elasticsearch.DataElasticsearchTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchClients;

import java.time.Duration;


@DataElasticsearchTest
@Import(DataElasticSearchTestExt.TestElasticSearchConfiguration.class)
public abstract class DataElasticSearchTestExt extends MockServerTest implements DefaultTest {

    /**
     * 기본 ElasticSearch 테스트를 위한 환경설정을 만들어 준다.
     */
    @TestConfiguration
    static class TestElasticSearchConfiguration {
        @Bean
        public RestClient elasticsearchRestClient() {
            ClientConfiguration clientConfiguration = ClientConfiguration.builder()
                    .connectedTo(getIntSocketAddress())
                    .withConnectTimeout(Duration.ofSeconds(10L))
                    .withSocketTimeout(Duration.ofSeconds(10L))
                    .build();
            return ElasticsearchClients.getRestClient(clientConfiguration);
        }
    }
}
