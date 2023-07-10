// package com.example.demo.config;
//
// import org.apache.http.HttpHost;
// import org.elasticsearch.client.RestClient;
// import org.elasticsearch.client.RestHighLevelClient;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
//
// /**
//  * RestHighLevelClient 配置类，可以不写会自动根据spring.elasticsearch.rest.uris 连接
//  */
// @Configuration
// public class ElasticSearchClientConfig {
//     @Bean
//     public RestHighLevelClient restHighLevelClient(){
//         RestHighLevelClient client = new RestHighLevelClient(
//             RestClient.builder(
//                 new HttpHost("192.168.2.100", 9200, "http")));
//         return client;
//     }
// }
