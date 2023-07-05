package hello.spring;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.eventusermodel.ReadOnlySharedStringsTable;
import org.apache.poi.xssf.eventusermodel.XSSFReader;
import org.apache.xmlbeans.impl.common.IOUtil;
import org.xml.sax.*;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.FileInputStream;
import java.io.InputStream;

public class ExcelReader {

    public static void main(String[] args) {
        String excelFilePath = "D:\\Test.xlsx";
        IOUtils.setByteArrayMaxOverride(100000000);
        try (InputStream inputStream = new FileInputStream(excelFilePath);
             OPCPackage opcPackage = OPCPackage.open(inputStream)) {

            XSSFReader xssfReader = new XSSFReader(opcPackage);
            ReadOnlySharedStringsTable sharedStringsTable = new ReadOnlySharedStringsTable(opcPackage);

            XMLReader xmlReader = XMLReaderFactory.createXMLReader();

            // Create a custom event handler for parsing the sheet data
            SheetHandler sheetHandler = new SheetHandler(sharedStringsTable);

            // Set the custom event handler as the content handler for the XMLReader
            xmlReader.setContentHandler(sheetHandler);

            // Get the sheet iterator and process each sheet
            XSSFReader.SheetIterator sheetIterator = (XSSFReader.SheetIterator) xssfReader.getSheetsData();
            while (sheetIterator.hasNext()) {
                try (InputStream sheetInputStream = sheetIterator.next()) {
                    InputSource inputSource = new InputSource(sheetInputStream);
                    xmlReader.parse(inputSource);
                }
            }

            System.out.println("Excel file read successfully.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static class SheetHandler extends DefaultHandler {

        private ReadOnlySharedStringsTable sharedStringsTable;
        private boolean isCell;
        private StringBuilder cellValue;

        public SheetHandler(ReadOnlySharedStringsTable sharedStringsTable) {
            this.sharedStringsTable = sharedStringsTable;
            this.isCell = false;
            this.cellValue = new StringBuilder();
        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            if (qName.equals("c")) {
                isCell = true;
                cellValue.setLength(0);
            }
        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            if (isCell) {
                cellValue.append(ch, start, length);
            }
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            if (qName.equals("c")) {
                isCell = false;
                String cellData = cellValue.toString().trim();
                if (!cellData.isEmpty()) {
                    if (sharedStringsTable.getCount() > 0) {
                        int cellIndex = Integer.parseInt(cellData);
                        cellData = sharedStringsTable.getItemAt(cellIndex).getString();
                    }
                    System.out.println("Cell value: " + cellData);
                }
            }
        }
    }
}

