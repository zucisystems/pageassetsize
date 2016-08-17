package core;

import java.io.File;
import java.io.FileOutputStream;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;

public class WriteToExcel {
	
	public void writeToExcel(HSSFSheet sheet1, long[] a, String tempSlNo, String tempURL, int writeExcelCounter){
        
        // Create Heading
        boolean headerCreated = false;
        Row rowhead = null;
        if(!headerCreated){
	        rowhead = sheet1.createRow(0);
	        rowhead.createCell(0).setCellValue("S.No");
	        rowhead.createCell(1).setCellValue("URL");
	        rowhead.createCell(2).setCellValue("HTML Size (in KB)");
	        rowhead.createCell(3).setCellValue("Image Size (in KB)");
	        rowhead.createCell(4).setCellValue("JS Size (in KB)");
	        rowhead.createCell(5).setCellValue("CSS Size (in KB)");
	        headerCreated = true;
        }
        	rowhead = sheet1.createRow(writeExcelCounter);
        	rowhead.createCell(0).setCellValue(tempSlNo);
        	rowhead.createCell(1).setCellValue(tempURL);
        	rowhead.createCell(2).setCellValue(a[0]);
        	rowhead.createCell(3).setCellValue(a[1]);
        	rowhead.createCell(4).setCellValue(a[2]);
        	rowhead.createCell(5).setCellValue(a[3]);  
	     }
	public void writeExcel(String str, HSSFWorkbook wb, String path){
		String path1 = null;
		try {
			path1=path+File.separator+"Core1.xls";
    		File file = new File(path1);
 
    		file.createNewFile();
    		FileOutputStream out = new FileOutputStream(file); 
    		wb.write(out);
    		wb.close();
        } catch (Exception e) {
            e.printStackTrace();
		}	
	}
}