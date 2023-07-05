package hello.spring;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;

public class Test {

    public static void main(String[] args) throws FileNotFoundException {
       //InputStream is = new FileInputStream("D:\\Test.xlsx");

        try (Workbook workbook = new SXSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("sheet1");

            // Write data to the Excel sheet
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("Name");
            headerRow.createCell(1).setCellValue("Age");
            headerRow.createCell(2).setCellValue("City");
            headerRow.createCell(3).setCellValue("road");
            headerRow.createCell(4).setCellValue("sex");
            headerRow.createCell(5).setCellValue("habby");
            headerRow.createCell(6).setCellValue("cntr_no");
            headerRow.createCell(7).setCellValue("cell_phone_no");
            headerRow.createCell(8).setCellValue("seq_no");
            int cnt = 1;
            while(cnt < 100001) {
                Row dataRow = sheet.createRow(cnt);
                dataRow.createCell(0).setCellValue("John"+cnt);
                dataRow.createCell(1).setCellValue(25);
                dataRow.createCell(2).setCellValue("New York");
                dataRow.createCell(3).setCellValue("11"+cnt);
                if(cnt % 2 == 0){
                    dataRow.createCell(4).setCellValue("man");
                } else {
                    dataRow.createCell(4).setCellValue("woman");
                }
                dataRow.createCell(5).setCellValue("play base ball");
                dataRow.createCell(6).setCellValue("56789345");
                dataRow.createCell(7).setCellValue("01083304136");
                dataRow.createCell(8).setCellValue(""+cnt);
                System.out.println("작업중 row number : "+cnt);
                cnt++;
            }

            //Row dataRow2 = sheet.createRow(2);
            //dataRow2.createCell(0).setCellValue("Alice");
            //dataRow2.createCell(1).setCellValue(30);
            //dataRow2.createCell(2).setCellValue("London");

            // Save the Excel file
//            FileOutputStream outputStream = new FileOutputStream("D:\\Test.xlsx");
//            workbook.write(outputStream);
//            outputStream.close();
            try (FileOutputStream outputStream = new FileOutputStream("D:\\Test.xlsx")) {
                workbook.write(outputStream);
            }

            System.out.println("Excel file created successfully.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
