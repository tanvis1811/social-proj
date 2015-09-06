/* Get number of papers published by each author */
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

public class Author_data {
	
	public static void main(String args[])throws Exception
	{
		FileInputStream fbcon = new FileInputStream(new File("data.xlsx"));
		XSSFWorkbook wb= new XSSFWorkbook(con);
		XSSFSheet sheet= wb.getSheet("ids");
		BufferedReader br = new BufferedReader(new FileReader("filename.txt"));
	        String line = br.readLine();
	        Iterator<Row> ri=sheet.iterator();
	        String s;

		// store all author names in hashmap
	        HashMap hm = new HashMap();
	        int value=0;
	        while(ri.hasNext())
	        {
	        	Row row =ri.next();
	        	Cell cell=row.getCell(1);
	        	s=cell.getStringCellValue();
	        	hm.put(s, value);
	        }
	       
		//get number of papers published by each author 
		while(line !=null)
	       {
	    	   	String parts[]=line.split(" : ");
    	   		if(parts[0].equals("author"))
    	   		{
    				 value=hm.get(parts[1])==null?-1:(int)hm.get(parts[1]);
    				 if(value!=-1)
    				  {
    					  value+=1;
    			 		 hm.put(parts[1], value);
    			  		if(value!=0)System.out.println(value);
    		  		  }
    	   		}
    	   		line=br.readLine();
    		}

		//store above data in excel sheet "ids" 
	      Iterator<Row> ri1=sheet.iterator();
       	      while(ri1.hasNext())
       		{
       			Row row =ri1.next();
		       	Cell cell=row.getCell(1);
       			s=cell.getStringCellValue();
		       	value=(int)hm.get(s);
		       	Cell countcell=row.createCell(2);
       			countcell.setCellValue(value);
      		 }
      
       
		FileOutputStream out = new FileOutputStream(new File("data.xlsx"));
        	wb.write(out);
       		out.flush();
       		out.close();
		wb.close();
		con.close();
		
		
	}

}
