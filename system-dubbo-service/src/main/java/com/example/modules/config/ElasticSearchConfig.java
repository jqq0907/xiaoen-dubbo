package com.example.modules.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author jiangqiangqiang
 * @description: es配置类
 * @date 2021/12/2 2:58 下午
 */
@Configuration
public class ElasticSearchConfig {
    public static final RequestOptions COMMON_OPTIONS;

    static {
        // 创建一个singgleton示例
        RequestOptions.Builder builder = RequestOptions.DEFAULT.toBuilder();
        COMMON_OPTIONS = builder.build();
    }

    /**
     * 创建es示例
     *
     * @return /
     */
    @Bean
    public RestHighLevelClient restHighLevelClient() {
        return new RestHighLevelClient(RestClient.builder(new HttpHost("10.0.0.3", 9200)));
    }
}
