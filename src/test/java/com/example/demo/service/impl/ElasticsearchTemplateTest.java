package com.example.demo.service.impl;

import com.example.demo.model.Commodity;
import org.elasticsearch.index.query.QueryBuilders;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;

import java.util.stream.Collectors;

@SpringBootTest
public class ElasticsearchTemplateTest {

    @Autowired
    public ElasticsearchRestTemplate elasticsearchTemplate;

    @Test
    public void testInsert() {
        Commodity commodity = new Commodity();
        commodity.setSkuId("1501009005");
        commodity.setName("葡萄吐司面包（10片装）");
        commodity.setCategory("101");
        commodity.setPrice(160);
        commodity.setBrand("良品铺子");

        elasticsearchTemplate.save(commodity);
    }

    @Test
    public void testQuery() {
        NativeSearchQuery searchQuery = new NativeSearchQueryBuilder()
            .withQuery(QueryBuilders.matchQuery("name", "吐司"))
            .build();
        SearchHits<Commodity> search = elasticsearchTemplate.search(searchQuery, Commodity.class);
        System.out.println(search.stream().collect(Collectors.toList()));
    }

}
