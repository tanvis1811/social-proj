package socialpackage;

/*In order to parse DBLP database*/
import java.io.PrintWriter;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
 
public class DOMParserDemo {
 
	
   public static void main(String argv[]) {
 
	 
	   //writer.println("The first line");
	   //writer.println("The second line");
	  // writer.close();
    try {
    	 PrintWriter writer = new PrintWriter("filename.txt", "UTF-8");
	SAXParserFactory factory = SAXParserFactory.newInstance();
	SAXParser saxParser = factory.newSAXParser();
 
	DefaultHandler handler = new DefaultHandler() {
 
	boolean author = false;
	boolean title = false;
	boolean year = false;
	boolean journal = false;
	boolean number = false;
 int flag=1;
	public void startElement(String uri, String localName,String qName, 
                Attributes attributes) throws SAXException {
 
		//System.out.println("Start Element :" + qName);
 
		if (qName.equalsIgnoreCase("Author")) {
			author = true;
		}
 
		if (qName.equalsIgnoreCase("title")) {
			title = true;
		}
 
		if (qName.equalsIgnoreCase("year")) {
			year = true;
		}
 
		if (qName.equalsIgnoreCase("journal")) {
			journal = true;
		}
		if (qName.equalsIgnoreCase("number")) {
			number = true;
		}
 
	}
 
	public void endElement(String uri, String localName,
		String qName) throws SAXException {
 
		//System.out.println("End Element :" + qName);
 
	}
 
	public void characters(char ch[], int start, int length) throws SAXException {
 
		if (author) {
			if(flag==1)
			{
				//System.out.println("paper!");
				writer.println("paper!");
				flag=0;
			}
			//System.out.println("author : " + new String(ch, start, length));
			writer.println("author : " + new String(ch, start, length));
			author = false;
		}
 
		if (title) {
			//System.out.println("title : " + new String(ch, start, length));
			writer.println("title : " + new String(ch, start, length));
			title = false;
			flag=1;
		}
 
		if (year) {
			//System.out.println("year : " + new String(ch, start, length));
			writer.println("year : " + new String(ch, start, length));
			year = false;
		}
 
		if (journal) {
			//System.out.println("journal : " + new String(ch, start, length));
			writer.println("journal : " + new String(ch, start, length));
			journal = false;
		}
		if (number) {
			//System.out.println("number : " + new String(ch, start, length));
			writer.println("number : " + new String(ch, start, length));
			number = false;
		}
 
	}
 
     };
 
       saxParser.parse("C:/Users/Tanvi/workspace/example/src/dblp.xml", handler);
      // writer.close();
     } catch (Exception e) {
       e.printStackTrace();
     }
 
   }
 
}



