package testProject;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class dataDriven {
	
//	public ArrayList<String> getData(String testcaseName) throws IOException
	public static void main(String[] args) throws IOException
	{
		
		
		// Set the path to the ChromeDriver executable
//   	 System.setProperty("webdriver.chrome.driver", "C:\\Driver\\chrome\\chromedriver.exe");
//
//       // Initialize WebDriver with ChromeDriver
//       WebDriver driver = new ChromeDriver();
//       System.out.println("Testing");
//
//       // Close the browser
//       driver.quit();
//	}
		//fileInputStream argument
		String excelFilePath = System.getProperty("user.dir") + "\\TestData\\TestDataTest.xlsx";

        // Create a FileInputStream from the Excel file
        FileInputStream fis = new FileInputStream(excelFilePath);

        // Create a workbook instance from the Excel file
        XSSFWorkbook workbook = new XSSFWorkbook(fis);

        // Get the first sheet from the workbook
        XSSFSheet sheet = workbook.getSheetAt(0);

        // Iterate through each row in the sheet
        Iterator<Row> rowIterator = sheet.iterator();

        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();

            // Iterate through each cell in the row
            Iterator<Cell> cellIterator = row.cellIterator();

            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();

                // Check the cell type and process accordingly
                switch (cell.getCellType()) {
                    case STRING:
                        System.out.print(cell.getStringCellValue() + "\t");
                        break;
                    case NUMERIC:
                        System.out.print(cell.getNumericCellValue() + "\t");
                        break;
                    case BOOLEAN:
                        System.out.print(cell.getBooleanCellValue() + "\t");
                        break;
                    default:
                        System.out.print("Unknown Cell Type");
                }
            }
            System.out.println();
        }

        // Close the workbook and file input stream
        workbook.close();
        fis.close();
}
}
