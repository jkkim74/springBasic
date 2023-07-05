package hello.spring;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;

public class LargeExcelReader {
    public static void main(String[] args) {
        String inputFile = "D:\\Test.xlsx";
        int batchSize = 30000; // Number of rows to read per batch
        // Set a higher override value for ByteArrayMaxOverride
        IOUtils.setByteArrayMaxOverride(200000000); // Set to a value greater than the record size

        try (Workbook workbook = new XSSFWorkbook(new FileInputStream(inputFile))) {
            Sheet sheet = workbook.getSheetAt(0); // Assuming you want to read the first sheet

            int rowCount = sheet.getLastRowNum();
            int batchCount = (int) Math.ceil((double) rowCount / batchSize);

            for (int batchIndex = 0; batchIndex < batchCount; batchIndex++) {
                int startRow = batchIndex * batchSize;
                int endRow = Math.min(startRow + batchSize - 1, rowCount);

                for (int rowIndex = startRow; rowIndex <= endRow; rowIndex++) {
                    Row row = sheet.getRow(rowIndex);
                    if (row != null) {
                        // Process the row data here
                        for (Cell cell : row) {
                            // Access cell values as needed
                            CellType cellType = cell.getCellType();
                            switch (cellType) {
                                case STRING:
                                    System.out.print(cell.getStringCellValue() + "\t");
                                    break;
                                case NUMERIC:
                                    System.out.print(cell.getNumericCellValue() + "\t");
                                    break;
                                // Handle other cell types as needed
                            }
                        }
                        System.out.println(); // Move to the next line after processing a row
                    }
                }

                System.out.println("Processed rows: " + (startRow + 1) + " to " + (endRow + 1));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

