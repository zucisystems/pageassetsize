package core;

import java.lang.reflect.Field;
import java.util.Hashtable;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;

public class StoreExcelData {

	public String sNo = null;
	public String workSheetName = null;
	public String URL = null;
	public String workBookName = null;
	public String DeviceType = null;
	public String UserAgent = null;
	public Hashtable<String, Integer> excelHeaders = new Hashtable<String, Integer>();
	public Hashtable<String, Integer> excelrRowColumnCount = new Hashtable<String, Integer>();

	public String toString() {
		
        StringBuffer listOfValues  = new StringBuffer();
        @SuppressWarnings("rawtypes")
		Class cls = this.getClass();
        Field[] fields = cls.getDeclaredFields( );
        Field field = null;
        
        try {
            for (int i=0; i < fields.length; i++) {
                field = fields[i];
                Object subObj = field.get(this);
                if (subObj != null && !field.getName().equals("logger") ) {
                    listOfValues.append(":");
                    listOfValues.append(field.getName());
                    listOfValues.append("=");
                    listOfValues.append(subObj.toString());
                }
            }
        }catch (RuntimeException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
        	e.printStackTrace();
		}   
        return listOfValues.toString();
    }

	
	public boolean fetchHomeTestData(){
			
		ReadFromExcel readTestData = new ReadFromExcel();
		boolean isDataFound = false;
		sNo = sNo != null ? sNo.trim() : "";
		
		try{
			
			HSSFSheet sheet = null;	
			// function call to initiate a connection to an excel sheet
			sheet = readTestData.initiateExcelConnection(workSheetName, workBookName);

			// function to find number of rows and columns
			excelrRowColumnCount = readTestData.findRowColumnCount(sheet, excelrRowColumnCount);

			// function call to find excel header fields
			excelHeaders = readTestData.readExcelHeaders(sheet, excelHeaders, excelrRowColumnCount);

			HSSFRow row = null;
			HSSFCell cell = null;
			String tempSNo = null;
			for(int r = 0; r < excelrRowColumnCount.get("RowCount"); r++) {
				row = sheet.getRow(r);
				if(row != null) {
					for(int c = 0; c < excelrRowColumnCount.get("ColumnCount"); ) {		
						cell = row.getCell(excelHeaders.get("S.No"));
						if(cell != null){
							tempSNo = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("S.No")));
							if(tempSNo.equals(sNo)){
								isDataFound = true;
									URL = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("URL")));
									DeviceType = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("Device Type")));
									UserAgent = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("User Agent")));
                                    break;
							}else {
								break;
							}							
						}else {
							break;
						}
					}
				}if(isDataFound){
					break;
				}
			}if(!isDataFound){
				URL = null;
				System.out.println("No Further URLs to validate");					
			}
		}catch (RuntimeException e) {
			e.printStackTrace();
		}
		return isDataFound;
	}

	@SuppressWarnings("unused")
	public boolean writeOutputToExcel(){
	
		ReadFromExcel readTestData = new ReadFromExcel();
		boolean isRecordFound = false;
		sNo = sNo != null ? sNo.trim() : "";
		
		try{

			HSSFSheet sheet = null;
			// function call to initiate a connection to an excel sheet
			sheet = readTestData.initiateExcelConnection(workSheetName, workBookName);

			// function to find number of rows and columns
			excelrRowColumnCount = readTestData.findRowColumnCount(sheet, excelrRowColumnCount);

			// function call to find excel header fields
			excelHeaders = readTestData.readExcelHeaders(sheet, excelHeaders, excelrRowColumnCount);

			HSSFRow row = null;
			HSSFCell cell = null;
			String tempSNo = null;
			String tempURL = null;

			for(int r = 0; r < excelrRowColumnCount.get("RowCount"); r++){
				row = sheet.getRow(r);
				if(row != null) {
					for(int c = 0; c < excelrRowColumnCount.get("ColumnCount"); ) {		
						cell = row.getCell(excelHeaders.get("S.No"));
						if(cell != null){
							tempSNo = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("S.No")));
							tempSNo = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("URL")));
							tempSNo = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("Browser")));
							if(tempSNo.equals(sNo) && tempSNo.equals(URL)){
								isRecordFound = true;
									URL = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("URL")));
									DeviceType = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("Device Type")));
									UserAgent = readTestData.convertHSSFCellToString(row.getCell(excelHeaders.get("User Agent")));
                                    break;
							}else {
								break;
							}
						}else {
							break;
						}
					}
				}if(isRecordFound){
					break;
				}
			}if(!isRecordFound){
				URL = null;
				System.out.println("Something is wrong in writing to Excel");					
			}
		}catch (RuntimeException e){
			e.printStackTrace();
		}
		return isRecordFound;
	}
}