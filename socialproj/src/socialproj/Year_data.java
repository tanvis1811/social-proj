//find year that author started publishing 
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

public class Year_data {
	
	public static void main(String args[])throws Exception
	{
		FileInputStream fbcon = new FileInputStream(new File("data.xlsx"));
		XSSFWorkbook wb= new XSSFWorkbook(fbcon);
		XSSFSheet sheet= wb.getSheet("ids");
		BufferedReader br = new BufferedReader(new FileReader("filename.txt"));
       		 String line = br.readLine();
	        Iterator<Row> ri=sheet.iterator();
        	String s;
        	HashMap hm = new HashMap();
        	int value=0;
        
		//start year of author
        	while(ri.hasNext())
        	{
        		Row row =ri.next();
        		Cell cell=row.getCell(1);
        		s=cell.getStringCellValue();
        		hm.put(s, value);
        	}
	        int year=0,nauth=0;
        
        	while(line !=null)
        	{
    		   if(line.equals("paper!"))
    		   {
    			   line=br.readLine();nauth=0;/*System.out.println(year);*/
    		
    		  	 String parts[]=line.split(" : ");
    			   HashMap auth=new HashMap();
    	   
	   			while(parts[0].equals("author"))
	   			{
	   				auth.put(nauth,parts[1]);
	   				nauth++;
	   				line=br.readLine();   			
	   				parts=line.split(" : ");
	   			}	
    	   
	   			line=br.readLine();
	   			if(line!=null)parts=line.split(" : ");
	   			else break;
	   			if(parts[0].equals("year"))
	   			{
	   				year=Integer.parseInt(parts[1]);
	   			}
	   			System.out.println(year);
	   			for(int i=0;i<nauth;i++)
	   			{
	   				String str=(String)auth.get(i);
	   				value=hm.get(str)==null?-1:(int)hm.get(str);
	   				if(value!=-1)
	   				{
	   					if(value==0)  hm.put(str, year);
	   					if(value>year)
	   					hm.put(str, year);
	   					//System.out.println(value);
	   				}
	   			}
    	   }
    	   line=br.readLine();
    	   
       }
       Iterator<Row> ri1=sheet.iterator();
       int j=0;
       while(ri1.hasNext())
       {
       	Row row =ri1.next();
       	Cell cell=row.getCell(1);
       	s=cell.getStringCellValue();
       	value=(int)hm.get(s);
       	Cell countcell=row.createCell(3);
       	countcell.setCellValue(value);
       	System.out.println(j++);
       }
       
       
		FileOutputStream out = new FileOutputStream(new File("data.xlsx"));
        wb.write(out);
        out.flush();
        out.close();
		wb.close();
		fbcon.close();
		
		
	}		

}
