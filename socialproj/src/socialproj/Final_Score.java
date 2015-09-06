//Calculate score of relationship

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

public class Final_Score {
	
	public static void main(String args[])throws Exception
	{
		FileInputStream fbcon = new FileInputStream(new File("data.xlsx"));
		XSSFWorkbook wb= new XSSFWorkbook(fbcon);
		XSSFSheet sheet= wb.getSheet("ids");
		XSSFSheet data= wb.getSheet("authordata");
		BufferedReader br = new BufferedReader(new FileReader("filename.txt"));
       		String line = br.readLine();
	        Iterator<Row> ri=sheet.iterator();
        	String s;
  		int w1,w2,w3,score;
        	w1=w3=10; 
	        w2=100;
        	int value=0;
        
		//get  starting year and number of papers published 
	        HashMap hm = new HashMap();
        	HashMap yhm= new HashMap(); 
        	while(ri.hasNext())
        	{
        		Row row=ri.next();
	        	Cell c1=row.getCell(1);
        		Cell c2=row.getCell(3);
        		Cell c3=row.getCell(2);
    		    	s=c1.getStringCellValue();
        		yhm.put(s, (int)c2.getNumericCellValue());
        		hm.put(s, (int)c3.getNumericCellValue());
        	        	
        	}
		
		//calculate score of relationship
	
	        Iterator<Row> ri1=data.iterator();
	       double i=0,j=0;
	       double var;
	       int p1,p2;
	       String s1, s2;
	       int diff1=0,diff2=0,diff=0;
	       int count=0;
	        while(ri1.hasNext())
	       {	
        	   System.out.println(count++);
	    	   Row row=ri1.next();
	    	   Cell a1=row.getCell(0);
	    	   s1=a1.getStringCellValue();
	    	   Cell c1=row.getCell(2);
	    	   i=c1.getNumericCellValue();
	    	   p1=(int)hm.get(s1);
	    	   diff1=(int)yhm.get(s1);
	    	   Cell a2=row.getCell(1);
	    	   s2=a2.getStringCellValue();
	    	   Cell c2=row.getCell(3);	
         	   j=c2.getNumericCellValue();
     		   diff2=(int)yhm.get(s2);
     	           p2=(int)hm.get(s2);
	    	   diff=diff1-diff2;
    		   //var=i-j;
    		   var=0;
    		   if(10<Math.abs(diff))
    		   {
    			   if(var>=0)var+=2000;
    			   else var-=2000;
    		   }
    	   	   if(10<Math.abs(p1-p2))
    	   	  {
    		  	 if(var>=0)var+=1000;
    		   	else var-=1000;
    		  }
	
	    	   if(i>=j)
    		   {
    			   var=(var/(i+j))*100;
    		   }
    	   	   else
    	   	  {
    		  	 var=(-var/(i+j))*100;
    	   	  }
    	   
    		   Cell c3=row.createCell(5);
    		   c3.setCellValue(var);
    	   
    	   }
        
		FileOutputStream out = new FileOutputStream(new File("data.xlsx"));
       		wb.write(out);
       		out.flush();
	        out.close();
		wb.close();
		fbcon.close();
		
		
	}

}
