//get number of collaboration per author
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

public class No_of_partnerships {
	
	public static void main(String args[])throws Exception
	{
		FileInputStream fbcon = new FileInputStream(new File("data.xlsx"));
		XSSFWorkbook wb= new XSSFWorkbook(fbcon);
		XSSFSheet sheet= wb.getSheet("ids");
		BufferedReader br = new BufferedReader(new FileReader("filename.txt"));
	        String line = br.readLine();
	        Iterator<Row> ri=sheet.iterator();
	        String s;
		//get all names and set number of collaborations initially to zero
	        HashMap hm = new HashMap();

	        int value=0;
        	while(ri.hasNext())
        	{
	        	Row row =ri.next();
        		Cell cell=row.getCell(1);
        		s=cell.getStringCellValue();
        		hm.put(s, value);
        	}
        	
		//get number of collaboration for each author
		int nauth=0,flag=0,rnum=0,count=0;
	           while(line !=null)
       		{
        	   if(line.equals("paper!"))
        	   {   nauth=0;
        	   		flag+=1;
        	   		HashMap auth=new HashMap();
        	   		line=br.readLine();
        	   		String parts[]=line.split(" : ");
        	
    	
    	   		while(parts[0].equals("author"))
    	   		{
    	   			auth.put(nauth,parts[1]);
    	   			nauth++;
    	   			line=br.readLine();
 
    	   			
    	   			parts=line.split(" : ");
    	   		}	 
    	   		System.out.println(nauth);
    	   		for(int i=0;i<nauth;i++)
    	   		{
    	   			String str=(String)auth.get(i);
    	   			if(hm.get(str)!=null)
    	   			{
    	   				value=(int)hm.get(str)+nauth-1;
    	   				hm.put(str,value);
    	   			}
    	   		}
    	   			
 	   	   }
 	   	   line=br.readLine();
 	      }
		//save in excel      
           Iterator<Row> ri1=sheet.iterator();
           while(ri1.hasNext())
           {
           	Row row =ri1.next();
           	Cell cell=row.getCell(1);
           	s=cell.getStringCellValue();
           	value=(int)hm.get(s);
           	Cell countcell=row.createCell(6);
           	countcell.setCellValue(value);
           }
       
		FileOutputStream out = new FileOutputStream(new File("data.xlsx"));
       		 wb.write(out);
	        out.flush();
	        out.close();
		wb.close();
		fbcon.close();
		
		
	}

}
