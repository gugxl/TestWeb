package com.example.demo.service.impl;

import com.example.demo.model.Commodity;
import com.example.demo.service.CommodityService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class CommodityServiceImplTest {

    @Autowired
    private CommodityService commodityService;

    @Test
    public void count() {
        long count = commodityService.count();
        System.out.println(count);
        assert count > 0 ;
    }

    @Test
    public void save() {
        Commodity commodity = new Commodity();
        commodity.setSkuId("1501009001");
        commodity.setName("原味切片面包（10片装）");
        commodity.setCategory("101");
        commodity.setPrice(880);
        commodity.setBrand("良品铺子");
        Commodity save = commodityService.save(commodity);

        commodity = new Commodity();
        commodity.setSkuId("1501009002");
        commodity.setName("原味切片面包（6片装）");
        commodity.setCategory("101");
        commodity.setPrice(680);
        commodity.setBrand("良品铺子");
        commodityService.save(commodity);

        commodity = new Commodity();
        commodity.setSkuId("1501009004");
        commodity.setName("元气吐司850g");
        commodity.setCategory("101");
        commodity.setPrice(120);
        commodity.setBrand("百草味");
        commodityService.save(commodity);
    }

    @Test
    public void delete() {
        Commodity commodity = new Commodity();
        commodity.setSkuId("1501009002");
        commodityService.delete(commodity);
    }

    @Test
    public void getAll() {
        Iterable<Commodity> iterable = commodityService.getAll();
        iterable.forEach(e->System.out.println(e.toString()));
    }

    @Test
    public void getByName() {
        List<Commodity> list = commodityService.getByName("面包");
        assertEquals(list.toString(), "[Commodity(skuId=1501009001, name=原味切片面包（10片装）, category=101, price=880, brand=良品铺子, stock=null), Commodity(skuId=1501009002, name=原味切片面包（6片装）, category=101, price=680, brand=良品铺子, stock=null)]");
    }

    @Test
    public void pageQuery() {
        Page<Commodity> page = commodityService.pageQuery(0, 10, "切片");
        System.out.println(page.getTotalPages());
        System.out.println(page.getNumber());
        System.out.println(page.getContent());
    }
}