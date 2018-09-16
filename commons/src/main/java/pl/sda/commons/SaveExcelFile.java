package pl.sda.commons;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;

public class SaveExcelFile implements SaveFile {

    @Override
    public <T> void save(List<T> sheetDataList, String filePathAndName) throws IOException {

        Workbook workbook = new XSSFWorkbook(); // new HSSFWorkbook() for generating `.xls` file
        Sheet sheet = workbook.createSheet("Sheet");

        Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerFont.setFontHeightInPoints((short) 14);
        headerFont.setColor(IndexedColors.RED.getIndex());
        CellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setFont(headerFont);

        Row headerRow = sheet.createRow(0);
        String[] columns = FileProcessors.checkHeaders(sheetDataList.get(0));
        for (int i = 0; i < columns.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columns[i]);
            cell.setCellStyle(headerCellStyle);
        }

        int rowNum = 1;
        for (T dataObject : sheetDataList) {
            Row row = sheet.createRow(rowNum++);
            int column = 0;
            for (Field field : dataObject.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                try {
                    Object value = field.get(dataObject);
                    row.createCell(column++).setCellValue(value.toString());
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

        for (int i = 0; i < columns.length; i++) {
            sheet.autoSizeColumn(i);
        }

        FileOutputStream fileOut = new FileOutputStream(filePathAndName);
        workbook.write(fileOut);
        fileOut.close();
        workbook.close();
    }
}
