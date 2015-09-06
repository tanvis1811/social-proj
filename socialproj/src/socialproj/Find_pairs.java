//compare names with mathgeneology database
package socialproj;

import java.io.BufferedReader;
import java.util.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Find_pairs {
	public static void main(String args[])throws Exception
	{
		FileInputStream fbcon = new FileInputStream(new File("data.xlsx"));
		XSSFWorkbook wb= new XSSFWorkbook(fbcon);
		XSSFSheet sheet= wb.getSheet("ids");
		XSSFSheet sheet1= wb.getSheet("mathgeneology");
		XSSFSheet sheet2= wb.getSheet("authordata");
       		 Iterator<Row> ri=sheet.iterator();
        	
		//store auhtor names 
        	HashMap hm = new HashMap();
        	int value=0;
   		String s;
  		 int i=0;
        	while(ri.hasNext())
        	{
        		Row row =ri.next();
        		Cell cell=row.getCell(1);
        		s=cell.getStringCellValue();
     		   	//System.out.print(i++ + "  ");
        		hm.put(s, value);
        	}
        
        	//compare with mathgenelogy database
		Iterator<Row> ri1=sheet1.iterator();
       		 i=0;
        	while(ri1.hasNext())
        	{
        		Row row =ri1.next();
        		Cell cell=row.getCell(0);
        		s=cell.getStringCellValue();
      	  	
        	      	if(hm.get(s)!=null)
        		{
        			System.out.println(s);
        			Cell cell1=row.createCell(1);
     	       			cell1.setCellValue(1);
		            	hm.put(s,1);
        		}
        	}
        	
		//store results of comparison for each pair
		Iterator<Row> ri2=sheet2.iterator();
	        i=0;
	        while(ri2.hasNext())
	        {
        		Row row =ri2.next();
        		Cell cell=row.getCell(0);
        		s=cell.getStringCellValue();
        		Cell cell1=row.createCell(6);
        		int count=0;
				
	        	if((hm.get(s)!=null)&&((int)hm.get(s)==1))
        		{
        			System.out.println(s);
        			count++;
            	       	}
        		
			Cell cell2=row.getCell(1);
        		s=cell2.getStringCellValue();
        	
        	       	if((hm.get(s)!=null)&&((int)hm.get(s)==1))
        		{
        			System.out.println(s);
		        	    count++;   
            		}
     		   	cell1.setCellValue(count);
        	}
        
        	FileOutputStream out = new FileOutputStream(new File("data.xlsx"));
        	wb.write(out);
        	out.flush();
        	out.close();
		wb.close();
		fbcon.close();
	}

}
