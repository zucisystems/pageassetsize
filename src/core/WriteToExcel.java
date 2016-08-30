package core;

import java.io.File;
import java.io.FileOutputStream;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;

public class WriteToExcel {
	
	public void writeToExcel(HSSFSheet sheet1,HSSFSheet sheet2, long[] a, String tempSlNo, String tempURL, int writeExcelCounter){
        
        // Create Heading
        boolean headerCreated = false;
        Row rowhead = null;
        if(!headerCreated){
	        rowhead = sheet1.createRow(0);
	        rowhead.createCell(0).setCellValue("S.No");
	        rowhead.createCell(1).setCellValue("URL");
	        rowhead.createCell(2).setCellValue("HTML Size (in KB)");
	        rowhead.createCell(3).setCellValue("CSS Size (in KB)");
	        rowhead.createCell(4).setCellValue("JS Size (in KB)");
	        rowhead.createCell(5).setCellValue("XHR Size (in KB)");
	        rowhead.createCell(6).setCellValue("Image Size (in KB)");
	        rowhead.createCell(7).setCellValue("Media Size (in KB)");
	        rowhead.createCell(8).setCellValue("Font Size (in KB)");
	        rowhead.createCell(9).setCellValue("Other Size (in KB)");
	        headerCreated = true;
        }
        	rowhead = sheet1.createRow(writeExcelCounter);
        	rowhead.createCell(0).setCellValue(tempSlNo);
        	rowhead.createCell(1).setCellValue(tempURL);
        	rowhead.createCell(2).setCellValue(a[0]);
        	rowhead.createCell(3).setCellValue(a[1]);
        	rowhead.createCell(4).setCellValue(a[2]);
        	rowhead.createCell(5).setCellValue(a[3]); 
        	rowhead.createCell(6).setCellValue(a[4]);  
        	rowhead.createCell(7).setCellValue(a[5]);  
        	rowhead.createCell(8).setCellValue(a[6]);  
        	rowhead.createCell(9).setCellValue(a[7]);
	boolean headerCreated1 = false;
    Row rowhead1 = null;
	if(!headerCreated1){
        	rowhead1 = sheet2.createRow(0);
        	rowhead1.createCell(0).setCellValue("S.No");
        	rowhead1.createCell(1).setCellValue("URL");
        	rowhead1.createCell(2).setCellValue("HTML Time (in Seconds)");
        	rowhead1.createCell(3).setCellValue("CSS Time (in Seconds)");
        	rowhead1.createCell(4).setCellValue("JS Time (in Seconds)");
        	rowhead1.createCell(5).setCellValue("XHR Time (in Seconds)");
        	rowhead1.createCell(6).setCellValue("Image Time (in Seconds)");
        	rowhead1.createCell(7).setCellValue("Media Time (in Seconds)");
        	rowhead1.createCell(8).setCellValue("Font Time (in Seconds)");
        	rowhead1.createCell(9).setCellValue("Other Time (in Seconds)");
        	headerCreated1 = true;
		}
			rowhead1 = sheet2.createRow(writeExcelCounter);
			rowhead1.createCell(0).setCellValue(tempSlNo);
			rowhead1.createCell(1).setCellValue(tempURL);
			rowhead1.createCell(2).setCellValue(a[8]);
			rowhead1.createCell(3).setCellValue(a[9]);
			rowhead1.createCell(4).setCellValue(a[10]);
			rowhead1.createCell(5).setCellValue(a[11]); 
			rowhead1.createCell(6).setCellValue(a[12]);  
			rowhead1.createCell(7).setCellValue(a[13]);  
			rowhead1.createCell(8).setCellValue(a[14]);  
			rowhead1.createCell(9).setCellValue(a[15]);
	}
	public void writeExcel(String str, HSSFWorkbook wb, String path){
		String path1 = null;
		try {
			path1=path+File.separator+"Page Assets.xls";
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