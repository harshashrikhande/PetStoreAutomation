package api.utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XLUtils {
	
	public FileInputStream fi;
	public FileOutputStream fo;
	public XSSFWorkbook workbook;
	public XSSFSheet sheet;
	public XSSFRow row;
	public XSSFCell cell;
	public CellStyle style;
	String path;
	
	public XLUtils(String path) {
		this.path = path;
	}

	public int getRowCount(String sheetName) throws IOException 
	{
		fi=new FileInputStream(path);
		workbook=new XSSFWorkbook(fi);
		sheet=workbook.getSheet(sheetName);
		int rowcount=sheet.getLastRowNum();
		workbook.close();
		fi.close();
		return rowcount;		
	}
	
	
	public int getCellCount(String sheetName,int rownum) throws IOException
	{
		fi=new FileInputStream(path);
		workbook=new XSSFWorkbook(fi);
		sheet=workbook.getSheet(sheetName);
		row=sheet.getRow(rownum);
		int cellcount=row.getLastCellNum();
		workbook.close();
		fi.close();
		return cellcount;
	}
	
	
	public String getCellData(String sheetName,int rownum,int colnum) throws IOException
	{
		fi=new FileInputStream(path);
		workbook=new XSSFWorkbook(fi);
		sheet=workbook.getSheet(sheetName);
		row=sheet.getRow(rownum);
		cell=row.getCell(colnum);
		
		String data;
		DataFormatter formatter = new DataFormatter();
		try 
		{
			
            data = formatter.formatCellValue(cell);
    
		}
		catch (Exception e) 
		{
			data="";
		}
		workbook.close();
		fi.close();
		return data;
	}
	
//	public static void setCellData(String xlfile,String xlsheet,int rownum,int colnum,String data) throws IOException
//	{
//		fi=new FileInputStream(xlfile);
//		wb=new XSSFWorkbook(fi);
//		ws=wb.getSheet(xlsheet);
//		row=ws.getRow(rownum);
//		cell=row.createCell(colnum);
//		cell.setCellValue(data);
//		fo=new FileOutputStream(xlfile);
//		wb.write(fo);		
//		wb.close();
//		fi.close();
//		fo.close();
//	}
	
	
}
