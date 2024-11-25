package dataSources;

import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.util.NumberToTextConverter;

public class Excel {

    public static String readDataFromExcel(String filePath, String sheetName, String cardName, String expectedValue) throws IOException {
        try (FileInputStream fis = new FileInputStream(filePath); XSSFWorkbook workbook = new XSSFWorkbook(fis)) {
            XSSFSheet sheet = workbook.getSheet(sheetName);
            if (sheet == null) return null;

            int columnIdx = findColumnIndex(sheet, expectedValue);
            if (columnIdx == -1) return null;

            // Iterate over rows starting from the second row
            for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
                Row row = sheet.getRow(i);
                if (row == null) continue;

                Cell cardCell = row.getCell(0);
                if (cardCell != null && cardCell.getStringCellValue().equalsIgnoreCase(cardName)) {
                    Cell targetCell = row.getCell(columnIdx);
                    return targetCell != null ? getCellValue(targetCell) : null;
                }
            }
        }
        return null;
    }

    // Helper method to find the index of a column based on header name
    @SuppressWarnings("unused")
	private static int findColumnIndex(Sheet sheet, String headerName) {
        Row headerRow = sheet.getRow(0);
        if (headerRow == null) return -1;

        for (int j = 0; j < headerRow.getLastCellNum(); j++) {
            Cell headerCell = headerRow.getCell(j);
            if (headerCell != null && headerCell.getStringCellValue().equalsIgnoreCase(headerName)) {
                return j;
            }
        }
        return -1;
    }

    // Helper method to get the cell value as a string
    private static String getCellValue(Cell cell) {
        return cell.getCellType() == CellType.STRING ? cell.getStringCellValue()
                : cell.getCellType() == CellType.NUMERIC ? NumberToTextConverter.toText(cell.getNumericCellValue())
                : null;
    }
}
