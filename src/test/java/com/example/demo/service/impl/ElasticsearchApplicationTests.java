package com.example.demo.service.impl;

import com.example.demo.model.es.User;
import net.minidev.json.JSONValue;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

@SpringBootTest
public class ElasticsearchApplicationTests {

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    @Test
    void testCreateIndex() throws IOException {
        // 1.创建索引请求
        CreateIndexRequest request = new CreateIndexRequest("gugu_test");
        // 2.客户端执行请求IndicesClient，执行create方法创建索引，请求后获得响应
        CreateIndexResponse response =
            restHighLevelClient.indices().create(request, RequestOptions.DEFAULT);
        System.out.println(response.index());
    }

    @Test
    void testExistIndex() throws IOException {
        // 1.查询索引请求
        GetIndexRequest request = new GetIndexRequest("gugu_test");
        // 2.执行exists方法判断是否存在
        boolean exists = restHighLevelClient.indices().exists(request, RequestOptions.DEFAULT);
        System.out.println(exists);
    }

    @Test
    void testDeleteIndex() throws IOException {
        // 1.删除索引请求
        DeleteIndexRequest request = new DeleteIndexRequest("gugu_test");
        // 执行delete方法删除指定索引
        AcknowledgedResponse delete = restHighLevelClient.indices().delete(request, RequestOptions.DEFAULT);
        System.out.println(delete.isAcknowledged());
    }

    @Test
    void testAddUser() throws IOException {
        // 1.创建对象
        User user = new User("Go", 21, new String[]{"看书", "吃饭"});
        // 2.创建请求
        IndexRequest request = new IndexRequest("gugu_test");
        // 3.设置规则 PUT /gugu_test/_doc/1
        // 设置文档id=6，设置超时=1s等，不设置会使用默认的
        // 同时支持链式编程如 request.id("6").timeout("1s");
        request.id("1");
        request.timeout("1s");

        // 4.将数据放入请求，要将对象转化为json格式
        // XContentType.JSON，告诉它传的数据是JSON类型
        request.source(JSONValue.toJSONString(user), XContentType.JSON);

        // 5.客户端发送请求，获取响应结果
        IndexResponse indexResponse = restHighLevelClient.index(request, RequestOptions.DEFAULT);
        System.out.println(indexResponse.toString());
        System.out.println(indexResponse.status());
    }

    @Test
    void testBulkAddUser() throws IOException {
        BulkRequest bulkRequest = new BulkRequest();
        // 设置超时
        bulkRequest.timeout("10s");

        ArrayList<User> list = new ArrayList<>();
        list.add(new User("Java", 25, new String[]{"内卷"}));
        list.add(new User("Go", 18, new String[]{"内卷"}));
        list.add(new User("C", 30, new String[]{"内卷"}));
        list.add(new User("C++", 26, new String[]{"内卷"}));
        list.add(new User("Python", 20, new String[]{"内卷"}));

        int id = 1;
        // 批量处理请求
        for (User u : list) {
            // 不设置id会生成随机id
            bulkRequest.add(new IndexRequest("gugu_test")
                .id("" + id++)
                .source(JSONValue.toJSONString(u), XContentType.JSON));
        }

        BulkResponse bulkResponse = restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);
        System.out.println(bulkResponse.hasFailures());// 是否执行失败,false为执行成功
    }

    @Test
    void testSearch() throws IOException {
        SearchRequest searchRequest=new SearchRequest("gugu_test");//里面可以放多个索引
        SearchSourceBuilder sourceBuilder=new SearchSourceBuilder();//构造搜索条件

        //此处可以使用QueryBuilders工具类中的方法
        //1.查询所有
        sourceBuilder.query(QueryBuilders.matchAllQuery());
        //2.查询name中含有Java的
        sourceBuilder.query(QueryBuilders.multiMatchQuery("java","name"));
        //3.分页查询
        sourceBuilder.from(0).size(5);

        //4.按照score正序排列
        //sourceBuilder.sort(SortBuilders.scoreSort().order(SortOrder.ASC));
        //5.按照id倒序排列（score会失效返回NaN）
        //sourceBuilder.sort(SortBuilders.fieldSort("_id").order(SortOrder.DESC));

        //6.给指定字段加上指定高亮样式
        HighlightBuilder highlightBuilder=new HighlightBuilder();
        highlightBuilder.field("name").preTags("<span style='color:red;'>").postTags("</span>");
        sourceBuilder.highlighter(highlightBuilder);

        searchRequest.source(sourceBuilder);
        SearchResponse searchResponse=restHighLevelClient.search(searchRequest,RequestOptions.DEFAULT);

        //获取总条数
        System.out.println(searchResponse.getHits().getTotalHits().value);
        //输出结果数据（如果不设置返回条数，大于10条默认只返回10条）
        SearchHit[] hits=searchResponse.getHits().getHits();
        for(SearchHit hit :hits){
            System.out.println("分数:"+hit.getScore());
            Map<String,Object> source=hit.getSourceAsMap();
            System.out.println("index->"+hit.getIndex());
            System.out.println("id->"+hit.getId());
            for(Map.Entry<String,Object> s:source.entrySet()){
                System.out.println(s.getKey()+"--"+s.getValue());
            }
        }
    }
}
