package testProject;

import java.io.File;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

public class createExcelFile {


    @Test
    public void createExcel() throws IOException {
        // Create the file output stream to write the Excel file
        FileOutputStream fos = new FileOutputStream(System.getProperty("user.dir") + "\\TestData\\test3.xlsx");

        // Create a new workbook and a sheet named "Data"
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Data");

        // Create the first row and populate it with data
        XSSFRow row1 = sheet.createRow(0);
        row1.createCell(0).setCellValue("Java");
        row1.createCell(1).setCellValue(123);
        row1.createCell(2).setCellValue("Automation");

        // Create the second row and populate it with data
        XSSFRow row2 = sheet.createRow(1);
        row2.createCell(0).setCellValue("Java1");
        row2.createCell(1).setCellValue(1234);
        row2.createCell(2).setCellValue("Automation1");

        // Write the workbook to the file
        workbook.write(fos);

        // Close the workbook and file output stream
        workbook.close();
        fos.close();

        // Print a confirmation message
        System.out.println("File Created");
    }
    
    public static void getScreenshot(String filePath, WebDriver driver) throws IOException {
//      File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
  	TakesScreenshot ts = (TakesScreenshot) driver;
  	//
//  	        // Call getScreenshotAs method to create an image file
  	        File screenshot = ts.getScreenshotAs(OutputType.FILE);
  	//
      File destination = new File(filePath);
      Files.copy(screenshot.toPath(), destination.toPath());
  }
}
