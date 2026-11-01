package com.company.sqa.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Utility to export Cucumber JSON execution results into a formatted Excel report
 * matching the standardized Test Case Template (Test Case ID, Test Scenario, Test Steps,
 * Test Data, Expected Result, Actual Result, Status).
 */
public class TestReportExcelExporter {

    public static void main(String[] args) {
        String jsonPath = "target/cucumber-reports/cucumber-ui-report.json";
        String outputPath = "reports/Test_Execution_Report_Login.xlsx";

        if (args.length > 0) jsonPath = args[0];
        if (args.length > 1) outputPath = args[1];

        try {
            exportReport(jsonPath, outputPath);
            System.out.println("Excel test report successfully generated at: " + outputPath);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public static void exportReport(String cucumberJsonPath, String outputExcelPath) throws IOException {
        File jsonFile = new File(cucumberJsonPath);
        if (!jsonFile.exists()) {
            throw new IllegalArgumentException("Cucumber report JSON not found at: " + cucumberJsonPath);
        }

        File outFile = new File(outputExcelPath);
        if (outFile.getParentFile() != null) {
            outFile.getParentFile().mkdirs();
        }

        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(jsonFile);

        try (XSSFWorkbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Test Results");
            sheet.setDisplayGridlines(true);

            // Palette colors (RGB) matching the template image
            byte[] colorIdHeader = new byte[]{(byte) 217, (byte) 225, (byte) 242};       // #D9E1F2 (Soft Blue)
            byte[] colorScenarioHeader = new byte[]{(byte) 226, (byte) 239, (byte) 218}; // #E2EFDA (Soft Green)
            byte[] colorStepsHeader = new byte[]{(byte) 255, (byte) 242, (byte) 204};    // #FFF2CC (Soft Peach)
            byte[] colorDataHeader = new byte[]{(byte) 232, (byte) 215, (byte) 241};     // #E8D7F1 (Soft Lavender)
            byte[] colorExpectedHeader = new byte[]{(byte) 252, (byte) 228, (byte) 214}; // #FCE4D6 (Soft Rose)
            byte[] colorActualHeader = new byte[]{(byte) 221, (byte) 235, (byte) 247};   // #DDEBF7 (Soft Cyan)
            byte[] colorStatusHeader = new byte[]{(byte) 249, (byte) 231, (byte) 159};   // #F9E79F (Soft Gold)

            // Pass / Fail styles
            byte[] colorPassFill = new byte[]{(byte) 198, (byte) 239, (byte) 206};       // #C6EFCE
            byte[] colorPassText = new byte[]{(byte) 0, (byte) 97, (byte) 0};            // #006100
            byte[] colorFailFill = new byte[]{(byte) 255, (byte) 199, (byte) 206};       // #FFC7CE
            byte[] colorFailText = new byte[]{(byte) 156, (byte) 0, (byte) 6};           // #9C0006

            // Font definitions
            XSSFFont titleFont = workbook.createFont();
            titleFont.setFontName("Calibri");
            titleFont.setFontHeightInPoints((short) 18);
            titleFont.setBold(true);
            titleFont.setColor(new XSSFColor(new byte[]{(byte) 27, (byte) 54, (byte) 93}, null));

            XSSFFont subFont = workbook.createFont();
            subFont.setFontName("Calibri");
            subFont.setFontHeightInPoints((short) 10);
            subFont.setColor(new XSSFColor(new byte[]{(byte) 100, (byte) 100, (byte) 100}, null));

            XSSFFont headerFont = workbook.createFont();
            headerFont.setFontName("Calibri");
            headerFont.setFontHeightInPoints((short) 11);
            headerFont.setBold(true);

            XSSFFont boldFont = workbook.createFont();
            boldFont.setFontName("Calibri");
            boldFont.setFontHeightInPoints((short) 10);
            boldFont.setBold(true);

            XSSFFont regularFont = workbook.createFont();
            regularFont.setFontName("Calibri");
            regularFont.setFontHeightInPoints((short) 10);

            XSSFFont passFont = workbook.createFont();
            passFont.setFontName("Calibri");
            passFont.setFontHeightInPoints((short) 11);
            passFont.setBold(true);
            passFont.setColor(new XSSFColor(colorPassText, null));

            XSSFFont failFont = workbook.createFont();
            failFont.setFontName("Calibri");
            failFont.setFontHeightInPoints((short) 11);
            failFont.setBold(true);
            failFont.setColor(new XSSFColor(colorFailText, null));

            // Create styles for headers
            XSSFCellStyle styleHeaderId = createHeaderStyle(workbook, headerFont, colorIdHeader);
            XSSFCellStyle styleHeaderScenario = createHeaderStyle(workbook, headerFont, colorScenarioHeader);
            XSSFCellStyle styleHeaderSteps = createHeaderStyle(workbook, headerFont, colorStepsHeader);
            XSSFCellStyle styleHeaderData = createHeaderStyle(workbook, headerFont, colorDataHeader);
            XSSFCellStyle styleHeaderExpected = createHeaderStyle(workbook, headerFont, colorExpectedHeader);
            XSSFCellStyle styleHeaderActual = createHeaderStyle(workbook, headerFont, colorActualHeader);
            XSSFCellStyle styleHeaderStatus = createHeaderStyle(workbook, headerFont, colorStatusHeader);

            // Cell data styles
            XSSFCellStyle styleCellId = createBorderedStyle(workbook, boldFont, HorizontalAlignment.CENTER, VerticalAlignment.CENTER, false);
            XSSFCellStyle styleCellScenario = createBorderedStyle(workbook, regularFont, HorizontalAlignment.LEFT, VerticalAlignment.TOP, true);
            XSSFCellStyle styleCellSteps = createBorderedStyle(workbook, regularFont, HorizontalAlignment.LEFT, VerticalAlignment.TOP, true);
            XSSFCellStyle styleCellData = createBorderedStyle(workbook, regularFont, HorizontalAlignment.LEFT, VerticalAlignment.TOP, true);
            XSSFCellStyle styleCellExpected = createBorderedStyle(workbook, regularFont, HorizontalAlignment.LEFT, VerticalAlignment.TOP, true);
            XSSFCellStyle styleCellActual = createBorderedStyle(workbook, regularFont, HorizontalAlignment.LEFT, VerticalAlignment.TOP, true);

            XSSFCellStyle styleCellPass = createStatusStyle(workbook, passFont, colorPassFill);
            XSSFCellStyle styleCellFail = createStatusStyle(workbook, failFont, colorFailFill);

            // Title block
            Row rowTitle = sheet.createRow(1);
            rowTitle.setHeightInPoints(28);
            Cell cellTitle = rowTitle.createCell(0);
            cellTitle.setCellValue("📋  TEST CASE TEMPLATE & EXECUTION REPORT");
            XSSFCellStyle styleTitle = workbook.createCellStyle();
            styleTitle.setFont(titleFont);
            styleTitle.setVerticalAlignment(VerticalAlignment.CENTER);
            cellTitle.setCellStyle(styleTitle);

            Row rowSubtitle = sheet.createRow(2);
            Cell cellSub = rowSubtitle.createCell(0);
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            cellSub.setCellValue("Target: https://magang.dikahadir.com/authentication/login | Modul: Login | Waktu Eksekusi: " + timestamp);
            XSSFCellStyle styleSub = workbook.createCellStyle();
            styleSub.setFont(subFont);
            cellSub.setCellStyle(styleSub);

            // Table Header row (Row 4)
            int headerRowIndex = 4;
            Row rowHeader = sheet.createRow(headerRowIndex);
            rowHeader.setHeightInPoints(26);

            String[] headers = {
                    "Test Case ID",
                    "Test Scenario",
                    "Test Steps",
                    "Test Data",
                    "Expected Result",
                    "Actual Result",
                    "Status"
            };
            XSSFCellStyle[] headerStyles = {
                    styleHeaderId,
                    styleHeaderScenario,
                    styleHeaderSteps,
                    styleHeaderData,
                    styleHeaderExpected,
                    styleHeaderActual,
                    styleHeaderStatus
            };

            for (int i = 0; i < headers.length; i++) {
                Cell c = rowHeader.createCell(i);
                c.setCellValue(headers[i]);
                c.setCellStyle(headerStyles[i]);
            }

            // Populate rows from cucumber json
            int currentRowIndex = headerRowIndex + 1;
            int totalScenarios = 0;
            int totalPassed = 0;
            int totalFailed = 0;
            Pattern quotePattern = Pattern.compile("\"([^\"]+)\"");

            for (JsonNode featureNode : rootNode) {
                JsonNode elements = featureNode.get("elements");
                if (elements == null || !elements.isArray()) continue;

                for (JsonNode scenarioNode : elements) {
                    String type = scenarioNode.has("type") ? scenarioNode.get("type").asText() : "";
                    if (!"scenario".equalsIgnoreCase(type)) continue;

                    totalScenarios++;
                    String scenarioName = scenarioNode.has("name") ? scenarioNode.get("name").asText() : "Scenario " + totalScenarios;

                    // Extract ID from tags
                    String testCaseId = "TC_" + String.format("%03d", totalScenarios);
                    JsonNode tagsNode = scenarioNode.get("tags");
                    if (tagsNode != null && tagsNode.isArray()) {
                        for (JsonNode tag : tagsNode) {
                            String t = tag.get("name").asText().replaceAll("^@", "");
                            String lower = t.toLowerCase();
                            if (lower.contains("log-") || lower.contains("sec-") || lower.contains("ux-") || lower.contains("a11y-") || lower.contains("res-")) {
                                testCaseId = t.toUpperCase();
                                break;
                            }
                        }
                    }

                    // Extract steps, test data, expected result, actual result, status
                    StringBuilder stepsBuilder = new StringBuilder();
                    StringBuilder testDataBuilder = new StringBuilder();
                    StringBuilder expectedBuilder = new StringBuilder();
                    String actualResult = "Sesuai ekspektasi: Seluruh langkah berhasil dieksekusi tanpa galat.";
                    boolean isPassed = true;

                    int stepNumber = 1;
                    stepsBuilder.append("1. Buka halaman login aplikasi");

                    JsonNode stepsNode = scenarioNode.get("steps");
                    if (stepsNode != null && stepsNode.isArray()) {
                        for (JsonNode step : stepsNode) {
                            String stepName = step.has("name") ? step.get("name").asText() : "";
                            String keyword = step.has("keyword") ? step.get("keyword").asText().trim() : "";
                            JsonNode resultNode = step.get("result");
                            String stepStatus = resultNode != null && resultNode.has("status") ? resultNode.get("status").asText() : "";

                            if ("failed".equalsIgnoreCase(stepStatus)) {
                                isPassed = false;
                                String err = resultNode.has("error_message") ? resultNode.get("error_message").asText().split("\n")[0] : "Assertion failed";
                                actualResult = "Gagal pada verifikasi \"" + keyword + " " + stepName + "\": " + err;
                            }

                            // Extract test data from quotes
                            Matcher matcher = quotePattern.matcher(stepName);
                            if (stepName.toLowerCase().contains("email") && matcher.find()) {
                                if (testDataBuilder.length() > 0) testDataBuilder.append("\n");
                                testDataBuilder.append("Email: ").append(matcher.group(1));
                            } else if (stepName.toLowerCase().contains("password") && matcher.find()) {
                                if (testDataBuilder.length() > 0) testDataBuilder.append("\n");
                                testDataBuilder.append("Password: [PROTECTED]");
                            } else if (stepName.toLowerCase().contains("resolusi") && matcher.find()) {
                                if (testDataBuilder.length() > 0) testDataBuilder.append("\n");
                                testDataBuilder.append("Resolusi: ").append(matcher.group(1));
                            }

                            // Classify step vs expectation
                            boolean isVerification = keyword.equalsIgnoreCase("Maka") ||
                                    stepName.toLowerCase().startsWith("sistem menampilkan") ||
                                    stepName.toLowerCase().startsWith("field ") ||
                                    stepName.toLowerCase().startsWith("ikon ") ||
                                    stepName.toLowerCase().contains("terlihat");

                            if (!isVerification) {
                                stepNumber++;
                                stepsBuilder.append("\n").append(stepNumber).append(". ").append(stepName);
                            } else {
                                if (expectedBuilder.length() > 0) expectedBuilder.append("\n");
                                expectedBuilder.append(stepName);
                            }
                        }
                    }

                    if (isPassed) {
                        totalPassed++;
                    } else {
                        totalFailed++;
                    }

                    String testDataStr = testDataBuilder.length() > 0 ? testDataBuilder.toString() : "-";
                    String expectedStr = expectedBuilder.length() > 0 ? expectedBuilder.toString() : "-";

                    // Write row
                    Row row = sheet.createRow(currentRowIndex);
                    row.setHeightInPoints(48); // generous height for wrapped text

                    Cell c0 = row.createCell(0);
                    c0.setCellValue(testCaseId);
                    c0.setCellStyle(styleCellId);

                    Cell c1 = row.createCell(1);
                    c1.setCellValue(scenarioName);
                    c1.setCellStyle(styleCellScenario);

                    Cell c2 = row.createCell(2);
                    c2.setCellValue(stepsBuilder.toString());
                    c2.setCellStyle(styleCellSteps);

                    Cell c3 = row.createCell(3);
                    c3.setCellValue(testDataStr);
                    c3.setCellStyle(styleCellData);

                    Cell c4 = row.createCell(4);
                    c4.setCellValue(expectedStr);
                    c4.setCellStyle(styleCellExpected);

                    Cell c5 = row.createCell(5);
                    c5.setCellValue(actualResult);
                    c5.setCellStyle(styleCellActual);

                    Cell c6 = row.createCell(6);
                    c6.setCellValue(isPassed ? "Pass" : "Fail");
                    c6.setCellStyle(isPassed ? styleCellPass : styleCellFail);

                    currentRowIndex++;
                }
            }

            // Write stats badge summary on row 3
            Row rowStats = sheet.createRow(3);
            Cell cellStats = rowStats.createCell(0);
            double passRate = totalScenarios > 0 ? ((double) totalPassed / totalScenarios) * 100.0 : 0.0;
            cellStats.setCellValue(String.format("Ringkasan: Total Kasus: %d  |  Passed: %d  |  Failed: %d  |  Pass Rate: %.2f%%",
                    totalScenarios, totalPassed, totalFailed, passRate));
            XSSFCellStyle styleStats = workbook.createCellStyle();
            styleStats.setFont(boldFont);
            cellStats.setCellStyle(styleStats);

            // ==========================================
            // FOOTER: IMPORTANT COLUMNS EXPLAINED
            // ==========================================
            currentRowIndex += 2;
            Row rowFooterTitle = sheet.createRow(currentRowIndex++);
            rowFooterTitle.setHeightInPoints(24);
            Cell cellFooterTitle = rowFooterTitle.createCell(0);
            cellFooterTitle.setCellValue("📘  IMPORTANT COLUMNS EXPLAINED");
            XSSFCellStyle styleFooterTitle = workbook.createCellStyle();
            styleFooterTitle.setFont(boldFont);
            styleFooterTitle.setFillForegroundColor(new XSSFColor(new byte[]{(byte) 41, (byte) 128, (byte) 185}, null));
            styleFooterTitle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            XSSFFont fontFooterWhite = workbook.createFont();
            fontFooterWhite.setBold(true);
            fontFooterWhite.setColor(new XSSFColor(new byte[]{(byte) 255, (byte) 255, (byte) 255}, null));
            styleFooterTitle.setFont(fontFooterWhite);
            cellFooterTitle.setCellStyle(styleFooterTitle);
            sheet.addMergedRegion(new CellRangeAddress(rowFooterTitle.getRowNum(), rowFooterTitle.getRowNum(), 0, 6));

            String[][] explanations = {
                    {"Test Case ID", "Unique ID given to each test case for easy tracking."},
                    {"Test Scenario", "High level scenario or functionality to be tested."},
                    {"Test Steps", "Step-by-step procedure to execute the test."},
                    {"Test Data", "Input values used for testing."},
                    {"Expected Result", "The expected output or behavior from the application."},
                    {"Actual Result", "The actual output observed during test execution."},
                    {"Status", "Final result of the test case: Pass or Fail."}
            };

            for (String[] exp : explanations) {
                Row r = sheet.createRow(currentRowIndex++);
                r.setHeightInPoints(20);

                Cell cKey = r.createCell(0);
                cKey.setCellValue(exp[0]);
                cKey.setCellStyle(styleCellId);

                Cell cDesc = r.createCell(1);
                cDesc.setCellValue(exp[1]);
                cDesc.setCellStyle(styleCellScenario);
                sheet.addMergedRegion(new CellRangeAddress(r.getRowNum(), r.getRowNum(), 1, 6));
            }

            currentRowIndex++;
            Row rowTip = sheet.createRow(currentRowIndex++);
            rowTip.setHeightInPoints(22);
            Cell cTip = rowTip.createCell(0);
            cTip.setCellValue("💡 Tip: A well-written test case improves test coverage, helps in identifying defects early, and ensures the quality of the application.");
            cTip.setCellStyle(styleCellScenario);
            sheet.addMergedRegion(new CellRangeAddress(rowTip.getRowNum(), rowTip.getRowNum(), 0, 6));

            Row rowRemember = sheet.createRow(currentRowIndex++);
            rowRemember.setHeightInPoints(22);
            Cell cRemember = rowRemember.createCell(0);
            cRemember.setCellValue("🎯 Remember: Good Test Cases = Better Quality. Better Quality = Happy Users 😊");
            cRemember.setCellStyle(styleCellScenario);
            sheet.addMergedRegion(new CellRangeAddress(rowRemember.getRowNum(), rowRemember.getRowNum(), 0, 6));

            // Set column widths
            sheet.setColumnWidth(0, 16 * 256); // Test Case ID
            sheet.setColumnWidth(1, 38 * 256); // Test Scenario
            sheet.setColumnWidth(2, 45 * 256); // Test Steps
            sheet.setColumnWidth(3, 28 * 256); // Test Data
            sheet.setColumnWidth(4, 40 * 256); // Expected Result
            sheet.setColumnWidth(5, 45 * 256); // Actual Result
            sheet.setColumnWidth(6, 14 * 256); // Status

            // Save file
            try (FileOutputStream fos = new FileOutputStream(outFile)) {
                workbook.write(fos);
            }
        }
    }

    private static XSSFCellStyle createHeaderStyle(XSSFWorkbook wb, XSSFFont font, byte[] rgbColor) {
        XSSFCellStyle style = wb.createCellStyle();
        style.setFont(font);
        style.setFillForegroundColor(new XSSFColor(rgbColor, null));
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        setBorders(style);
        return style;
    }

    private static XSSFCellStyle createBorderedStyle(XSSFWorkbook wb, XSSFFont font,
                                                    HorizontalAlignment ha, VerticalAlignment va, boolean wrap) {
        XSSFCellStyle style = wb.createCellStyle();
        style.setFont(font);
        style.setAlignment(ha);
        style.setVerticalAlignment(va);
        style.setWrapText(wrap);
        setBorders(style);
        return style;
    }

    private static XSSFCellStyle createStatusStyle(XSSFWorkbook wb, XSSFFont font, byte[] rgbFill) {
        XSSFCellStyle style = wb.createCellStyle();
        style.setFont(font);
        style.setFillForegroundColor(new XSSFColor(rgbFill, null));
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        setBorders(style);
        return style;
    }

    private static void setBorders(XSSFCellStyle style) {
        style.setBorderTop(BorderStyle.THIN);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
    }
}

