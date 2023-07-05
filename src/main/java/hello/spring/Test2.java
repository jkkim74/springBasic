package hello.spring;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class Test2 {
    public static void main(String[] args) throws IOException, InvalidFormatException {
        String fileName = "D:\\Test.xlsx";
//        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
//            while (br.readLine() != null) {
//                // do something with each line
//                System.out.println(br.readLine());
//            }
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }

//        try (Stream<String> lines = java.nio.file.Files.lines(Paths.get(fileName))) {
//            // do something with each line
//            lines.forEach(System.out::println);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        File file = new File(fileName);
//        Workbook wb = WorkbookFactory.create(file);
//        LineIterator it = FileUtils.lineIterator(file, "UTF-8");
//        try {
//            while (it.hasNext()) {
//                String line = it.nextLine();
//                // do something with line
//                System.out.println(line);
//            }
//        } finally {
//            LineIterator.closeQuietly(it);
//        }

        String excelFullPath = "D:\\Test.xlsx";//"C:/DevLog/20230221/devlog_202302210817056.xlsx";

        File file = new File(excelFullPath);

//workbook 생성
        XSSFWorkbook workbook = new XSSFWorkbook(file);
//sheet 가지고오기
        XSSFSheet sheet = workbook.getSheetAt(0);
//row 개수
        int nMaxRowCnt = sheet.getPhysicalNumberOfRows();
        for(int nRow = 0 ; nRow < nMaxRowCnt ; nRow++) {
            //row Object
            XSSFRow row = sheet.getRow(nRow);
            //col 개수
            int nMaxColCnt = row.getPhysicalNumberOfCells();

            for(int nCol = 0 ; nCol < nMaxColCnt ; nCol++) {
                XSSFCell cell = row.getCell(nCol);
                System.out.println(cell.toString());
            }
            System.out.println("-------------------------------------");
        }
    }
}
