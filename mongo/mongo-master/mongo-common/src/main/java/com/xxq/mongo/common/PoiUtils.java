package com.xxq.mongo.common;

import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @ Author     ：XuXiuquan.
 * @ Date       ：Created in 21:48 2020/4/17
 * @ Description：POI相关操作 （教程网址：https://www.yiibai.com/apache_poi/apache_poi_cells.html）
 * @ Modified By：
 * @Version : 1.0
 */
public class PoiUtils {
    public static File createExcelFile(XSSFWorkbook workbook, String fileName){
        OutputStream stream = null;
        File file = null;
        try {
            file = File.createTempFile(fileName,".xlsx");
            stream = new FileOutputStream(file.getAbsoluteFile());
            workbook.write(stream);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            IOUtils.closeQuietly(workbook);
            IOUtils.closeQuietly(stream);
        }
        return file;
    }
}
