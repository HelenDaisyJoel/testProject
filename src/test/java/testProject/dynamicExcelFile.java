package testProject;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class dynamicExcelFile {
	
	public static void main(String[] args) throws IOException
	{
	FileOutputStream fos=new FileOutputStream(System.getProperty("user.dir")+"\\TestData\\testdynamic.xlsx");
	XSSFWorkbook workbook=new XSSFWorkbook();
	
	XSSFSheet sheet=workbook.createSheet("Data");
	
	
	Scanner sc=new Scanner(System.in);
	System.out.println("Enter number of rows");
	int noOfRows=sc.nextInt();
	
	
	System.out.println("Enter number of columns");
	int noOfColumns=sc.nextInt();
	
	for(int r=0;r<noOfRows; r++)
	{
		XSSFRow currentRow=sheet.createRow(r);
		for(int c=0;c<noOfColumns;c++)
		{
			XSSFCell cell=currentRow.createCell(c);
			cell.setCellValue(sc.next());
			
		}
		
	}
	workbook.write(fos);
	workbook.close();
	fos.close();
	System.out.println("File Created");
	
	
	
			
	
	
	
	}
	
	

}
