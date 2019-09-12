package com.ctguqmx.recruit.util;

import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class ExcelUtil {
    /**
     * 导出excel
     * @param headerName （excel列名称）
     * @param headerKey （导出对象属性名）
     * @param sheetName （excel 页签名称）
     * @param dataList （导出的数据）
     * @return
     */
    public static HSSFWorkbook createExcel(String[] headerName, String[] headerKey, String sheetName, List dataList) {
        try {
            if (headerKey.length <= 0) {
                return null;
            }
            if (dataList.size() <= 0) {
                return null;
            }
            HSSFWorkbook wb = new HSSFWorkbook();
            HSSFSheet sheet;
            if ((sheetName == null) || (sheetName.equals("")))
                sheet = wb.createSheet("Sheet1");
            else {
                sheet = wb.createSheet(sheetName);
            }
            HSSFRow row = sheet.createRow(0);
            HSSFCellStyle style = wb.createCellStyle();
            style.setAlignment((short)2);
            HSSFCell cell = null;
            if (headerName.length > 0) {
                for (int i = 0; i < headerName.length; i++) {
                    cell = row.createCell(i);
                    cell.setCellValue(headerName[i]);
                    cell.setCellStyle(style);

                }
            }
            int n = 0;
            HSSFCellStyle contextstyle = wb.createCellStyle();
            contextstyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("#,##0.00_);(#,##0.00)"));

            HSSFCellStyle contextstyle1 = wb.createCellStyle();
            HSSFDataFormat format = wb.createDataFormat();
            contextstyle1.setDataFormat(format.getFormat("@"));

            HSSFCell cell0 = null;
            HSSFCell cell1 = null;

            for (Iterator localIterator = dataList.iterator(); localIterator.hasNext();) {
                Object obj = localIterator.next();
                Field[] fields = obj.getClass().getDeclaredFields();
                row = sheet.createRow(n + 1);
                for (int j = 0; j < headerKey.length; j++) {
                    if (headerName.length <= 0) {
                        cell0 = row.createCell(j);
                        cell0.setCellValue(headerKey[j]);
                        cell0.setCellStyle(style);

                    }
                    for (int i = 0; i < fields.length; i++) {
                        if (fields[i].getName().equals(headerKey[j])) {
                            fields[i].setAccessible(true);
                            if (fields[i].get(obj) == null) {
                                row.createCell(j).setCellValue("");
                                break;
                            }
                            if ((fields[i].get(obj) instanceof Number)) {
                                cell1 = row.createCell(j);
                                cell1.setCellType(0);
                                cell1.setCellStyle(contextstyle);
                                cell1.setCellValue(Double.parseDouble(fields[i].get(obj).toString()));
                                break;
                            }
                            if ("".equals(fields[i].get(obj))) {
                                cell1 = row.createCell(j);
                                cell1.setCellStyle(contextstyle1);
                                row.createCell(j).setCellValue("");
                                cell1.setCellType(1);
                                break;
                            }
                            row.createCell(j).setCellValue(fields[i].get(obj).toString());
                            break;
                        }

                    }
                }
                n++;
            }
            for (int i = 0; i < headerKey.length; i++) {
                sheet.setColumnWidth(i, headerKey[i].getBytes().length*2*256);
            }
            HSSFWorkbook localHSSFWorkbook1 = wb;
            return localHSSFWorkbook1;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
        }
    }
}
