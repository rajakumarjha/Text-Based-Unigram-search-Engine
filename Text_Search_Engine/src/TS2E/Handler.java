package TS2E;

import java.util.HashSet;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

public class Handler extends DefaultHandler {

	boolean bName=false;
	boolean bAge=false;
	boolean bAbout=false;
	static HashSet<Students_Class>student_set;
	public Handler(){
		student_set=new HashSet<>();
	}
	static Students_Class stu;
	
	public void startElement(String uri, String localName, String qName, Attributes atts){
		if(qName.equalsIgnoreCase("Student")){
			stu=new Students_Class(atts.getValue("id"));
		}else if(qName.equalsIgnoreCase("Name")){
			bName=true;
		}
		else if(qName.equalsIgnoreCase("Age")){
			bAge=true;
		}
		else if(qName.equalsIgnoreCase("About")){
			bAbout=true;
		}
	}
	public void endElement(String uri, String localName,String qName){
		student_set.add(stu);
	}
	public void characters(char[] ch, int start, int length){
		if(bName==true){
			stu.name.append(ch,start,length);
			bName=false;
		}else if(bAge==true){
			stu.age=Integer.parseInt(new String(ch,start,length));
			bAge=false;
		}else if(bAbout==true){
			stu.about.append(ch,start,length);;
			bAge=false;
		}
	}

	public static void main(String[] args) {
		

	}

}

