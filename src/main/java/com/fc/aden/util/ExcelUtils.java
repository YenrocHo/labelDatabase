package com.fc.aden.util;

import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 描述: 处理excel工具类
 */
public class ExcelUtils {

    public static List<Map<String, String>> getExcelData(MultipartFile file, String[] title) {
        checkFile(file);
        // 获得Workbook工作薄对象
        Workbook workbook = getWorkBook(file);
        // 创建返回对象，把每行中的值作为一个map，所有行作为一个集合返回
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        if (workbook != null) {
            for (int sheetNum = 0; sheetNum < workbook.getNumberOfSheets(); sheetNum++) {
                // 获得当前sheet工作表
                Sheet sheet = workbook.getSheetAt(sheetNum);
                if (sheet == null) {
                    continue;
                }
                // 获得当前sheet的开始行
                int firstRowNum = sheet.getFirstRowNum();
                // 获得当前sheet的结束行
                int lastRowNum = sheet.getLastRowNum();
                // 获取表头
                // String[] cellTr = new
                // String[sheet.getRow(0).getLastCellNum()];
                // 循环所有行
                for (int rowNum = firstRowNum; rowNum <= lastRowNum; rowNum++) {
                    Map<String, String> map = new HashMap<String, String>();
                    // 获得当前行
                    Row row = sheet.getRow(rowNum);
                    if (row == null) {
                        continue;
                    }
                    // 获得当前行的开始列
                    int firstCellNum = row.getFirstCellNum();
                    // 获得当前行的列数
                    int lastCellNum = row.getLastCellNum();
                    // 循环当前行
                    for (int cellNum = firstCellNum; cellNum < lastCellNum; cellNum++) {
                        // 获取表头用于组装数据
                        if (rowNum == 0) {
                            continue;
                        } else {
                            try {
                                if(title.length>cellNum) {
                                    Cell  cell = row.getCell(cellNum);
                                    map.put(title[cellNum], getCellValue(cell).replace("\\", "/").trim());
                                }
                            }catch (Exception e){
                                e.printStackTrace();
                            }
                        }
                    }
                    if (rowNum > 0) {
                        list.add(map);
                    }
                }
            }
        }
        return list;
    }

    /**
     * 检查文件 @param file @throws
     */
    public static void checkFile(MultipartFile file) {
        // 判断文件是否存在
        if (null == file) {
            throw new RuntimeException("文件不存在");
        }
        // 获得文件名
        String fileName = file.getOriginalFilename();
        // 判断文件是否是excel文件
        if (!fileName.endsWith("xls") && !fileName.endsWith("xlsx")) {
            throw new RuntimeException("文件类型错误");
        }
    }

    public static Workbook getWorkBook(MultipartFile file) {
        // 获得文件名
        String fileName = file.getOriginalFilename();
        // 创建Workbook工作薄对象，表示整个excel
        Workbook workbook = null;
        try {
            // 获取excel文件的io流
            InputStream is = file.getInputStream();
            // 根据文件后缀名不同(xls和xlsx)获得不同的Workbook实现类对象
            if (fileName.endsWith("xls")) {
                // 2003
                workbook = new HSSFWorkbook(is);
            } else if (fileName.endsWith("xlsx")) {
                // 2007 及2007以上
                workbook = new XSSFWorkbook(is);
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return workbook;
    }

    public static String getCellValue(Cell cell) {
        String cellValue = "";
        if (cell == null) {
            return cellValue;
        }
        // 判断数据的类型
        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_NUMERIC: // 数字
                cellValue = stringDateProcess(cell);
                break;
            case Cell.CELL_TYPE_STRING: // 字符串
                cellValue = String.valueOf(cell.getStringCellValue());
                break;
            case Cell.CELL_TYPE_BOOLEAN: // Boolean
                cellValue = String.valueOf(cell.getBooleanCellValue());
                break;
            case Cell.CELL_TYPE_FORMULA: // 公式
                cellValue = String.valueOf(cell.getCellFormula());
                break;
            case Cell.CELL_TYPE_BLANK: // 空值
                cellValue = "";
                break;
            case Cell.CELL_TYPE_ERROR: // 故障
                cellValue = "非法字符";
                break;
            default:
                cellValue = "未知类型";
                break;
        }
        return cellValue;
    }

    /**
     * 时间格式处理
     */
    public static String stringDateProcess(Cell cell) {

        String result = new String();
        if (HSSFDateUtil.isCellDateFormatted(cell)) {// 处理日期格式、时间格式
            SimpleDateFormat sdf = null;
            if (cell.getCellStyle().getDataFormat() == HSSFDataFormat.getBuiltinFormat("h:mm")) {
                sdf = new SimpleDateFormat("HH:mm");
            } else {// 日期
                sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            }
            Date date = cell.getDateCellValue();
            result = sdf.format(date);
        } else if (cell.getCellStyle().getDataFormat() == 58) {
            // 处理自定义日期格式：m月d日(通过判断单元格的格式id解决，id的值是58)
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            double value = cell.getNumericCellValue();
            Date date = org.apache.poi.ss.usermodel.DateUtil.getJavaDate(value);
            result = sdf.format(date);
        } else {
            double value = cell.getNumericCellValue();
            CellStyle style = cell.getCellStyle();
            DecimalFormat format = new DecimalFormat();
            String temp = style.getDataFormatString();
            // 单元格设置成常规
            if (temp.equals("General")) {
                format.applyPattern("#");
            }
            result = format.format(value);
        }
        return result;
    }

    public static void exportExcel(HttpServletResponse response, List<String> title, List<List<String>> data)
            throws Exception {
        response.setHeader("content-Type", "application/vnd.ms-excel");
        // 下载文件的默认名称
        response.setHeader("Content-Disposition", "attachment;filename=" + "下载.xlsx");
        response.flushBuffer();
        getExportExcel(title, data, response.getOutputStream());
    }

    public static void getExportExcel(List<String> title, List<List<String>> data, OutputStream out) throws Exception {
        if (data.size() > 0 && title.size() != data.get(0).size()) {
            throw new RuntimeException("数据格式错误");
        }
        SXSSFWorkbook wb = new SXSSFWorkbook(100);
        Sheet sheet = wb.createSheet();
        int rowIndex = 0;
        int colIndex = 0;
        // 创建第一行 设置表头
        Row titleRow = sheet.createRow(rowIndex);
        for (String field : title) {
            Cell cell = titleRow.createCell(colIndex);
            cell.setCellValue(field);
            colIndex++;
        }
        // 自增1
        rowIndex++;
        // 添加数据
        for (List<String> rowData : data) {
            Row dataRow = sheet.createRow(rowIndex);
            colIndex = 0;
            for (Object cellData : rowData) {
                Cell cell = dataRow.createCell(colIndex);
                if (cellData != null) {
                    cell.setCellValue(cellData.toString());
                } else {
                    cell.setCellValue("");
                }
                colIndex++;
            }
            rowIndex++;
        }
        sheet.autoSizeColumn(1, true);
        wb.write(out);
        out.close();
        wb.dispose();
    }
}