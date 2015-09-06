//Find all collaborations in dblp dataset
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

public class Coauthors {
	
	public static void main(String args[])throws Exception
	{
		FileInputStream fbcon = new FileInputStream(new File("data.xlsx"));
		XSSFWorkbook wb= new XSSFWorkbook(fbcon);
		XSSFSheet sheet= wb.getSheet("ids");
		XSSFSheet data= wb.createSheet("authordata");
		BufferedReader br = new BufferedReader(new FileReader("filename.txt"));
	        String line = br.readLine();
       		 Iterator<Row> ri=sheet.iterator();
        	String s;
        
		//Store names of authors in hashmap
		HashMap hm = new HashMap();
	        int value=0;
       		while(ri.hasNext())
        	{
        		Row row =ri.next();
        		Cell cell=row.getCell(1);
        		s=cell.getStringCellValue();
        		hm.put(s, value);
        	}
        
		int nauth=0,flag=0,rnum=0,count=0;
        	
		//for each paper published find all authors and store their pairs in excel sheet "authordata"
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
    	   			for(int j=i+1;j<nauth;j++)
    	   			{
    	   				Row row=data.createRow(rnum++);
    	   				Cell cell1=row.createCell(0);
    	   				Cell cell2=row.createCell(1);
    	   				cell1.setCellValue((String)auth.get(i));
    	   				cell2.setCellValue((String)auth.get(j));
    	   				count++;
    	   				System.out.println(count);
    	   				if(count==500000)break;
    	   				System.out.println(auth.get(i)+" : "+auth.get(j));
    	   			}
    	   		
    		   }
    		   if(count==500000)break;
    		   line=br.readLine();
   	
    	  }
      
      
       
		FileOutputStream out = new FileOutputStream(new File("data.xlsx"));
	        wb.write(out);
        	out.flush();
        	out.close();
		wb.close();
		fbcon.close();
		
		
	}

}
