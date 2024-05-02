package com.example.demo.export;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.example.demo.model.ExportUser;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.List;

/**
 * @author 小谷
 * @description
 * @since 2024/4/30
 */
@Slf4j
public abstract class AbstractExport<T, K> {
    public abstract void export(ExportUser sysUser) throws InterruptedException;

    /**
     * 导出
     *
     * @param response 输出流
     * @param pageSize 每页大小
     * @param t 导出条件
     * @param k Excel内容实体类
     * @param fileName 文件名称
     * @throws Exception
     */
    public void export(HttpServletResponse response, int pageSize, T t, Class<K> k, String fileName) throws Exception {
        ExcelWriter writer = getExcelWriter(response, fileName);
        //查询导出总条数
        int total = this.countExport(t);
        //页数
        int loopCount = PageUtil.totalPage(total, pageSize);
        org.apache.commons.beanutils.BeanUtils.setProperty(t, "pageSize", pageSize);

        for (int i = 0; i < loopCount; i++) {
            org.apache.commons.beanutils.BeanUtils.setProperty(t, "pageNum", PageUtil.getStart(i + 1, pageSize));
            //获取Excel导出信息
            List<K> kList = this.getExportDetail(t);
            WriteSheet writeSheet = EasyExcel.writerSheet(fileName).head(k).build();
            writer.write(kList, writeSheet);
        }
    }


    /**
     * 查询导出总条数
     * @param t
     * @return
     */
    public abstract  int countExport(T t);

    public ExcelWriter getExcelWriter(HttpServletResponse response, String fileName) throws Exception {
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
        String fileNameUtf = URLEncoder.encode(fileName, "UTF-8").replaceAll("\\+", "%20");
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileNameUtf + ".xlsx");
        return EasyExcel.write(response.getOutputStream()).build();
    }

    /**
     * 模版导出
     * @param t
     * @param fileName
     * @param response
     */
    public abstract void complexFillWithTable(T t, String fileName, HttpServletResponse response);

    /**
     * 获取导出详情
     * @param t
     * @return
     */
    public abstract List<K> getExportDetail(T t);

}
