package com.company.sqa.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ExcelUtil {
    private static final Logger logger = LogManager.getLogger(ExcelUtil.class);

    /**
     * Reads an Excel sheet and returns rows as a list of maps (Column Header -> Cell Value).
     * Automatically applies trimming on all string values to sanitize data.
     */
    public static List<Map<String, String>> readSheet(String filePath, String sheetName) {
        List<Map<String, String>> data = new ArrayList<>();
        File file = new File(filePath);

        if (!file.exists()) {
            logger.error("Excel file does not exist at path: {}", filePath);
            throw new IllegalArgumentException("Excel file not found at " + filePath);
        }

        try (FileInputStream fis = new FileInputStream(file);
             Workbook workbook = WorkbookFactory.create(fis)) {

            Sheet sheet = workbook.getSheet(sheetName);
            if (sheet == null) {
                logger.error("Sheet '{}' not found in file: {}", sheetName, filePath);
                throw new IllegalArgumentException("Sheet " + sheetName + " not found");
            }

            Row headerRow = sheet.getRow(0);
            if (headerRow == null) {
                return data;
            }

            int colCount = headerRow.getLastCellNum();
            List<String> headers = new ArrayList<>();
            for (int c = 0; c < colCount; c++) {
                Cell cell = headerRow.getCell(c, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                headers.add(sanitizeString(getCellValueAsString(cell)));
            }

            int rowCount = sheet.getLastRowNum();
            for (int r = 1; r <= rowCount; r++) {
                Row row = sheet.getRow(r);
                if (row == null) continue;

                Map<String, String> rowMap = new LinkedHashMap<>();
                boolean hasContent = false;
                for (int c = 0; c < colCount; c++) {
                    Cell cell = row.getCell(c, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                    String val = sanitizeString(getCellValueAsString(cell));
                    if (!val.isEmpty()) {
                        hasContent = true;
                    }
                    rowMap.put(headers.get(c), val);
                }
                if (hasContent) {
                    data.add(rowMap);
                }
            }

        } catch (IOException e) {
            logger.error("Error reading Excel file: {}", filePath, e);
            throw new RuntimeException("Error reading Excel file: " + filePath, e);
        }

        return data;
    }

    /**
     * Converts cell content to string.
     */
    private static String getCellValueAsString(Cell cell) {
        if (cell == null) return "";
        DataFormatter formatter = new DataFormatter();
        return formatter.formatCellValue(cell);
    }

    /**
     * Trims whitespace to prevent false negatives from trailing spaces.
     */
    public static String sanitizeString(String input) {
        if (input == null) return "";
        return input.trim();
    }

    /**
     * Normalizes a key or value for reliable assertions: trimmed, upper-case, spaces replaced with underscores.
     * e.g., " In Stock " -> "IN_STOCK"
     */
    public static String normalizeKey(String input) {
        if (input == null) return "";
        return input.trim().toUpperCase().replace(" ", "_");
    }
}
