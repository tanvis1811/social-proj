//calculate individual author score
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

public class Score {
	
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
        	//weightage of parameters
		int w1,w2,w3,score;
        	w1=w3=10; 
        	w2=100;
        	int value=0;
        
		//calculate individual score
       		 HashMap hm = new HashMap();
        
        	while(ri.hasNext())
        	{	
        		Row row=ri.next();
        		Cell auth=row.getCell(1);
        		Cell c1=row.getCell(2);
        		Cell c2=row.getCell(3);
        		Cell c3=row.getCell(6);
        		score=(w1*(int)c1.getNumericCellValue())+(w2*(2015-(int)c2.getNumericCellValue()))+(w3*(int)c3.getNumericCellValue());
        		Cell cell=row.createCell(7);
        		cell.setCellValue(score);
        		s=auth.getStringCellValue();
     			System.out.println(s);
        		hm.put(s, score);
                }

        	//store in excel
		Iterator<Row> ri1=data.iterator();
	       int i=0,j=0,count=0;
      		int diff1=0,diff2=0,diff=0;
   	    	 while(ri1.hasNext())
       		{
        		count++;
		    	Row row=ri1.next();
		    	Cell a1=row.getCell(0);
	         	s=a1.getStringCellValue();
		    	System.out.println(s);
             	  	Cell c1=row.createCell(2);
		    	if(hm.get(s)!=null)
    			{
	    		   c1.setCellValue((int)hm.get(s));
	    		   i=(int)hm.get(s);
    	    		}
    		        Cell a2=row.getCell(1);
	         	s=a2.getStringCellValue();
	         	Cell c2=row.createCell(3);
	         	if(hm.get(s)!=null)
    	   		{
    				c2.setCellValue((int)hm.get(s));
		    		j=(int)hm.get(s);
    	
    			}
		
		    	   diff=(i-j);
		    	   Cell c3=row.createCell(4);
    			   c3.setCellValue(diff);    	   
       }
        
		FileOutputStream out = new FileOutputStream(new File("data.xlsx"));
        	wb.write(out);
		out.flush();
	        out.close();
		wb.close();
		fbcon.close();
	
		
	}


}
