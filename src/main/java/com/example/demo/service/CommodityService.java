package com.example.demo.service;

import com.example.demo.model.Commodity;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CommodityService {
    /**
     * 查询数量
     * @return
     */
    long count();

    /**
     * 保存数据
     * @param commodity
     * @return
     */
    Commodity save(Commodity commodity);

    /**
     * 删除数据
     * @param commodity
     * @return
     */
    void delete(Commodity commodity);

    /**
     * 获取所有数据
     * @return
     */
    Iterable<Commodity> getAll();

    /**
     * 根据名字搜索
     * @param name
     * @return
     */
    List<Commodity> getByName(String name);

    /**
     * 分页查询
     * @param pageNo
     * @param pageSize
     * @param kw
     * @return
     */
    Page<Commodity> pageQuery(Integer pageNo, Integer pageSize, String kw);
}
