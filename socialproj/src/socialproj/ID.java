//get names of all authors in database and set id number of each author

package socialproj;

import java.io.*;
import java.util.*;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ID{
	
	public static void main(String args[])throws Exception
	{
	
			FileInputStream fbcon = new FileInputStream(new File("data.xlsx"));
			XSSFWorkbook wb= new XSSFWorkbook(fbcon);
			XSSFSheet newsheet =wb.createSheet("ids");
			String s="name";
			int value=0;
		   
			HashMap hm = new HashMap();
			BufferedReader br = new BufferedReader(new FileReader("filename.txt"));
	        	String line = br.readLine();
	        	while(line !=null)
	        	{
	     		   String parts[]=line.split(" : ");
	     	   	   if(parts[0].equals("author"))
	     	 	   {	
	     			 if(hm.get(parts[1])==null)
	     			 hm.put(value++,parts[1]);
	     		   }
		     	   line=br.readLine();
	     	   
		        }
	        
			 Row inputrow=newsheet.createRow(0);
			 Cell idcell;
			 Cell namecell;
			 int rnum=0,c=0,c1=1;
			 int id=1;
			 int count=0;
		     	for(int i=0;i<value;i++) 
		     	{
		        	 s=(String) hm.get(i);
			         System.out.println(s);
			         inputrow=newsheet.createRow(rnum++);
				idcell=inputrow.createCell(c);
				idcell.setCellValue(id++);
				namecell=inputrow.createCell(c1);
				namecell.setCellValue(s);
				count++;
				if(count==500000)break;       
		      }
		   
			FileOutputStream out = new FileOutputStream(new File("data.xlsx"));
	      		wb.write(out);
		        out.flush();
	                out.close();
			wb.close();
			fbcon.close();
			
	}
	
	
}
