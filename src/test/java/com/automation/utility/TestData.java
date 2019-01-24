package com.automation.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class TestData {

	public static int randBetween(int start, int end) {
		return start + (int) Math.round(Math.random() * (end - start));
	}

	public static Sheet getExcelSheet(int sheetIndex) {
		String dataFilePath = "Resources/Credential.xlsx";
		File datafile = new File(dataFilePath);
		String fullpath = datafile.getAbsolutePath();
		Sheet firstSheet = null;

		try {
					
			System.out.println("full path " + datafile.getAbsolutePath() + " con " + datafile.getCanonicalPath());

			FileInputStream inputStream = new FileInputStream(new File(fullpath));

			Workbook workbook = new XSSFWorkbook(inputStream);
			firstSheet = workbook.getSheetAt(sheetIndex);

			workbook.close();
			inputStream.close();

		} catch (Exception e) {

		}
		return firstSheet;
	}

	public static Sheet upload(int sheetIndex) {
		String dataFilePath = "Resources/firstname.txt";
		File datafile = new File(dataFilePath);
		String fullpath = datafile.getAbsolutePath();
		Sheet firstSheet = null;

		try {

			System.out.println("full path " + datafile.getAbsolutePath() + " con " + datafile.getCanonicalPath());

			FileInputStream inputStream = new FileInputStream(new File(fullpath));

			Workbook workbook = new XSSFWorkbook(inputStream);
			firstSheet = workbook.getSheetAt(sheetIndex);

			workbook.close();
			inputStream.close();

		} catch (Exception e) {

		}
		return firstSheet;
	}

	
	//this function is used to change the value of the Excel sheet cell as per Test Result
	public static void SheetResultcellupdate(int row_no,int cell_no,String value) {
		
		
		/*FileInputStream fsIP= new FileInputStream(new File("C:\\TechartifactExcel.xls")); //Read the spreadsheet that needs to be updated
        
        HSSFWorkbook wb = new HSSFWorkbook(fsIP); //Access the workbook
          
        HSSFSheet worksheet = wb.getSheetAt(0); //Access the worksheet, so that we can update / modify it.
          
        Cell cell = null; // declare a Cell object
        
        cell = worksheet.getRow(2).getCell(2);   // Access the second cell in second row to update the value
          
        cell.setCellValue("OverRide Last Name");  // Get current cell value value and overwrite the value
          
        fsIP.close(); //Close the InputStream
         
        FileOutputStream output_file =new FileOutputStream(new File("C:\\TechartifactExcel.xls"));  //Open FileOutputStream to write updates
          
        wb.write(output_file); //write changes
          
        output_file.close();  //close the stream    
*/

		String dataFilePath = "Resources/[3DBroadCastSales]-Script_Result.xlsx";
		File datafile = new File(dataFilePath);
		String fullpath = datafile.getAbsolutePath();
		Sheet firstSheet = null;

		try {
					
			System.out.println("full path " + datafile.getAbsolutePath() + " con " + datafile.getCanonicalPath());

			FileInputStream inputStream = new FileInputStream(new File(fullpath));

			Workbook workbook = new XSSFWorkbook(inputStream);
			firstSheet = workbook.getSheetAt(1);
			
			Cell cell2Update = firstSheet.getRow(row_no).getCell(cell_no);
			cell2Update.setCellValue(value);
			inputStream.close();			
			FileOutputStream output_file =new FileOutputStream(new File(fullpath)); 
			workbook.write(output_file);
			workbook.close();
			output_file.close();
			System.out.println("Sheet Cell Value is Updated");

		} catch (Exception e) {

		}
		
	}
	
	
	//this function set the all Test result to skipped before updating the Test Result Sheet
	
public static void SheetResultcellupdatetoskipped(int row) {
		
		String dataFilePath = "Resources/[3DBroadCastSales]-Script_Result.xlsx";
		File datafile = new File(dataFilePath);
		String fullpath = datafile.getAbsolutePath();
		Sheet firstSheet = null;

		try {
					
			//System.out.println("full path " + datafile.getAbsolutePath() + " con " + datafile.getCanonicalPath());

			FileInputStream inputStream = new FileInputStream(new File(fullpath));
			Workbook workbook = new XSSFWorkbook(inputStream);
			firstSheet = workbook.getSheetAt(1);
			
			 Cell cell2Update = firstSheet.getRow(row).getCell(3);
			 cell2Update.setCellValue("Skipped");
			
			inputStream.close();			
			
			FileOutputStream output_file =new FileOutputStream(new File(fullpath)); 
			workbook.write(output_file);
			workbook.close();
			output_file.close();
			
			System.out.println("All Sheet Cells Updated to Skipped Status");

		} catch (Exception e) {

		}
		
	}
	
//this function is calling the SheetResultcellupdatetoskipped function as per number of row
public static void getnumberofrow() {
	
	String dataFilePath = "Resources/[3DBroadCastSales]-Script_Result.xlsx";
	File datafile = new File(dataFilePath);
	String fullpath = datafile.getAbsolutePath();
	Sheet firstSheet = null;

	try {
				
		System.out.println("full path " + datafile.getAbsolutePath() + " con " + datafile.getCanonicalPath());

		FileInputStream inputStream = new FileInputStream(new File(fullpath));
		Workbook workbook = new XSSFWorkbook(inputStream);
		firstSheet = workbook.getSheetAt(1);
		int noofrow = firstSheet.getLastRowNum();
		System.out.println("Number of rows :" +noofrow);
		inputStream.close();	
		for(int i =1;i<noofrow;i++) {
			SheetResultcellupdatetoskipped(i);
		}
		
		
	} catch (Exception e) {

	}
	
}
	
	
	
	
	public static String getURL() {

		System.out.println("Call getURL.......");
		return getExcelSheet(0).getRow(0).getCell(1).getStringCellValue();
	}

	public static String getUserName() {

		return getExcelSheet(0).getRow(1).getCell(1).getStringCellValue();
	}

	public static String getPassword() {

		return getExcelSheet(0).getRow(2).getCell(1).getStringCellValue();
	}

	public static String getSenderName() {

		return getExcelSheet(0).getRow(5).getCell(1).getStringCellValue();
	}
	
	public static String getRandomDate() {

		Calendar cal = Calendar.getInstance();

		cal.add(Calendar.DAY_OF_MONTH, 1);

		int fromday = cal.get(Calendar.DAY_OF_MONTH);
		int fromyear = cal.get(Calendar.YEAR);

		cal.add(Calendar.MONTH, 2);

		String day = String.valueOf(getDate(fromday));
		String month = TestData.getMonthD(cal.get(Calendar.MONTH));
		String year = String.valueOf(fromyear);

		return day + " " + month + " " + year;
	}

	public static int getDate(int fromday) {

		if (fromday < 10) {

			return 10 + (int) Math.round(Math.random() * (30 - 10));
		} else {
			return fromday;
		}
	}

	public static String getMonthD(int month) {
		switch (month) {
		case 1:
			return "Jan";

		case 2:
			return "Feb";

		case 3:
			return "Mar";

		case 4:
			return "Apr";

		case 5:
			return "May";

		case 6:
			return "Jun";

		case 7:
			return "Jul";

		case 8:
			return "Aug";

		case 9:
			return "Sep";

		case 10:
			return "Oct";

		case 11:
			return "Nov";

		case 12:
			return "Dec";

		default:
			return "Invalid month";

		}
	}

	public static String getDateConverted(String dateString) throws ParseException {
		SimpleDateFormat format1 = new SimpleDateFormat("dd-MM-yyyy");
		SimpleDateFormat format2 = new SimpleDateFormat("dd-MMM-yyyy");
		Date date = format1.parse(dateString);
		System.out.println(format2.format(date));
		return format2.format(date);
	}

	public static String getHeadless() {
		// TODO Auto-generated method stub
		try
		{
			return getExcelSheet(0).getRow(4).getCell(1).getStringCellValue();
		}
		catch(Exception e)
		{
			return "No";
		}	
	}
	
	//Vipul
	public static String ts_firstname() {

		  System.out.println("Call getURL.......");
		  return getExcelSheet(1).getRow(0).getCell(1).getStringCellValue();
		}
	
	public static String ts_lastname() {

		  System.out.println("Call getURL.......");
		  return getExcelSheet(1).getRow(1).getCell(1).getStringCellValue();
		}
	
	public static String ts_emailaddress() {

		  System.out.println("Call getURL.......");
		  return getExcelSheet(1).getRow(2).getCell(1).getStringCellValue();
		}
	
	public static String ts_phone() {

		  System.out.println("Call getURL.......");
		  getExcelSheet(1).getRow(3).getCell(1).setCellType(Cell.CELL_TYPE_STRING);
		  
		  return getExcelSheet(1).getRow(3).getCell(1).getStringCellValue();
		}
	
	public static String ts_address() {

		  System.out.println("Call getURL.......");
		  return getExcelSheet(1).getRow(4).getCell(1).getStringCellValue();
		}
	
	public static String ts_city() {

		  System.out.println("Call getURL.......");
		  return getExcelSheet(1).getRow(5).getCell(1).getStringCellValue();
		}
	public static String ts_country() {

		  System.out.println("Call getURL.......");
		  return getExcelSheet(1).getRow(6).getCell(1).getStringCellValue();
		}
	
	public static String ts_state() {

		  System.out.println("Call getURL.......");
		  return getExcelSheet(1).getRow(7).getCell(1).getStringCellValue();
		}
	
	public static String ts_postalcode() {

		  System.out.println("Call getURL.......");
		  getExcelSheet(1).getRow(8).getCell(1).setCellType(Cell.CELL_TYPE_STRING);
		  return getExcelSheet(1).getRow(8).getCell(1).getStringCellValue();
		}
	
	//E-Signature
	
	public static String esign_username() {

		  System.out.println("Call getURL.......");
		  return getExcelSheet(2).getRow(0).getCell(1).getStringCellValue();
		}
	
	public static String esign_password() {

		  System.out.println("Call getURL.......");
		  return getExcelSheet(2).getRow(1).getCell(1).getStringCellValue();
		}
	
	/*public static String esign_firstname() {

		  System.out.println("Call getURL.......");
		  return getExcelSheet(2).getRow(2).getCell(1).getStringCellValue();
		}
	
	public static String esign_lastname() {

		  System.out.println("Call getURL.......");
		  return getExcelSheet(2).getRow(3).getCell(1).getStringCellValue();
		}
	
	public static String esign_emailaddress() {

		  System.out.println("Call getURL.......");
		  return getExcelSheet(2).getRow(4).getCell(1).getStringCellValue();
		}
	
	public static String esign_phonenumber() {

		  System.out.println("Call getURL.......");
		  return getExcelSheet(2).getRow(5).getCell(1).getStringCellValue();
		}*/
	
	private static final String ALPHA_NUMERIC_STRING = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	public static String randomAlphaNumeric(int count) {
		StringBuilder builder = new StringBuilder();
		while (count-- != 0) {
		int character = (int)(Math.random()*ALPHA_NUMERIC_STRING.length());
		builder.append(ALPHA_NUMERIC_STRING.charAt(character));
		}
		return builder.toString();
		}

	private static final String ALPHA_STRING = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	public static String randomAlpha(int count) {
		StringBuilder builder = new StringBuilder();
		while (count-- != 0) {
		int character = (int)(Math.random()*ALPHA_STRING.length());
		builder.append(ALPHA_STRING.charAt(character));
		}
		return builder.toString();
		}
	

	private static final String NUMERIC_STRING = "01234567890123456789";
	public static String randomNumeric(int count) {
		StringBuilder builder = new StringBuilder();
		while (count-- != 0) {
		int character = (int)(Math.random()*NUMERIC_STRING.length());
		builder.append(NUMERIC_STRING.charAt(character));
		}
		return builder.toString();
		}
	
	private static final String numberofdocuments = "54321";
	
	public static String numberofducuments() {
		int count=1;
		StringBuilder builder = new StringBuilder();
		while (count-- != 0) {
		int character = (int)(Math.random()*numberofdocuments.length());
		builder.append(numberofdocuments.charAt(character));
		}
		/*StringBuilder builder = new StringBuilder();
		int character = (int)(Math.random()*numberofdocuments.length());
		builder.append(numberofdocuments.charAt(character));*/
		return builder.toString();
		}
	
	
	
}
