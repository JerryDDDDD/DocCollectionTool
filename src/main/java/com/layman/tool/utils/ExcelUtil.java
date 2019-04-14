package com.layman.tool.utils;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName ExcelUtil
 * @Description TODO
 * @Author 叶泽文
 * @Data 2019/4/11 22:24
 * @Version 3.0
 **/
public class ExcelUtil {
    public static String getCellTypes(Cell cell) {

        String cellValue = null;
        if (null != cell) {
            // 以下是判断数据的类型
            switch (cell.getCellType()){
                case HSSFCell.CELL_TYPE_NUMERIC: // 数字
                    // 处理日期格式、时间格式
                    if (HSSFDateUtil.isCellDateFormatted(cell)) {
                        Date d = cell.getDateCellValue();
                        DateFormat formater = new SimpleDateFormat("yyyy-MM-dd");
                        cellValue = formater.format(d);
                    }
                    else{
                        // 数字以String形式返回
                        cell.setCellType(HSSFCell.CELL_TYPE_STRING);
                        cellValue = cell.getStringCellValue();
                        //cellValue = cell.getNumericCellValue() + "";
                    }
                    break;

                case HSSFCell.CELL_TYPE_STRING: // 字符串
                    cellValue = cell.getStringCellValue();
                    break;

                case HSSFCell.CELL_TYPE_BOOLEAN: // Boolean
                    cellValue = cell.getBooleanCellValue() + "";
                    break;

                case HSSFCell.CELL_TYPE_FORMULA: // 公式
                    // cellValue = cell.getCellFormula() + "";
                    try {
                        DecimalFormat df = new DecimalFormat("0.0000");
                        cellValue = String.valueOf(df.format(cell.getNumericCellValue()));

                    } catch (IllegalStateException e) {
                        cellValue = String.valueOf(cell.getRichStringCellValue());
                    }
                    break;

                case HSSFCell.CELL_TYPE_BLANK: // 空值
                    cellValue = "";
                    break;

                case HSSFCell.CELL_TYPE_ERROR: // 故障
                    cellValue = "非法字符";
                    break;

                default:
                    cellValue = "未知类型";
                    break;
            }
        }
        return cellValue;
    }
}
