package com.ketd.common.excel;

import com.alibaba.excel.EasyExcel;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;

/**
 * @Description:
 * @BelongsProject: gulimall
 * @BelongsPackage: com.ketd.common.excel
 * @Author: ketd
 * @CreateTime: 2024-04-20  14:07
 */
public class excel {
    public static void extracted(List<?> list, HttpServletResponse response, Class<?> clazz) {
        try {
            //HttpServletResponse消息头参数设置
            response.setCharacterEncoding("UTF-8");
            response.setHeader("Content-Transfer-Encoding", "binary");
            response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
            response.setHeader("Pragma", "public");
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=UTF-8");
            String fileName = "导出列表"+ ".xlsx";
            fileName = new String(fileName.getBytes(), "ISO-8859-1");
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName );
            EasyExcel.write(response.getOutputStream(), clazz)
                    .autoCloseStream(Boolean.FALSE)
                    .sheet("导出列表")
                    .doWrite(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
