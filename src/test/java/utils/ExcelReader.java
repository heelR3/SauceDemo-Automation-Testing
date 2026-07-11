package utils;
 
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
 
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
 
public class ExcelReader {
 
    public static synchronized Map<String, String> getTestData(String sheetName, String testCaseId) {
 
        Map<String, String> data = new HashMap<>();
 
        try {
 
            FileInputStream fis =
                    new FileInputStream(ConfigReader.getProperty("excelPath"));
 
            Workbook workbook = new XSSFWorkbook(fis);
 
            Sheet sheet = workbook.getSheet(sheetName);
 
            DataFormatter formatter = new DataFormatter();
 
            Row headerRow = sheet.getRow(0);
 
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
 
                Row row = sheet.getRow(i);
 
                String tcId = formatter.formatCellValue(row.getCell(0));
 
                if (tcId.equalsIgnoreCase(testCaseId)) {
 
                    for (int j = 0; j < headerRow.getLastCellNum(); j++) {
 
                        String key = formatter.formatCellValue(headerRow.getCell(j));
 
                        String value = formatter.formatCellValue(row.getCell(j));
 
                        data.put(key, value);
 
                    }
 
                    break;
                }
 
            }
 
            workbook.close();
            fis.close();
 
        }
 
        catch(IOException e) {
 
            e.printStackTrace();
 
        }
 
        return data;
 
    }
 
    
}