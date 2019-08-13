package TS2E;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;


public class SAXParser {

	public static void main(String[] args) {
		SAXParserFactory spfac = SAXParserFactory.newInstance();
		javax.xml.parsers.SAXParser sp = null;
		try {
			 sp = spfac.newSAXParser();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
         Handler handler=new Handler();
		 try {
			sp.parse("/Users/Temp/workspace/Text_Search_Engine/src/TS2E/database.xml", handler);
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 HashMap<String, Set<TextInfo>>outerMap=new HashMap<>();
		 HashSet<Students_Class>student_set;
		 student_set= handler.student_set;
		 java.util.Iterator<Students_Class> itr = student_set.iterator();
		 HashSet<String>stopWords=new HashSet<>();
			BufferedReader br = null;
			try {
				br = new BufferedReader(new FileReader(new File(StopWord.STOP_WORD_FILE)));
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			String line;
			try {
				while( (line = br.readLine()) != null){
					  String tokens[] = line.toLowerCase().split(StopWord.STOP_WORD_DELIMITER);
					  for(String token:tokens){
						  stopWords.add(token);
					  }
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 PorterStemmer s = new PorterStemmer();
			 //updating hashmap of each objects....
		 while(itr.hasNext()){
			 Students_Class stu=itr.next();
			 String name=stu.name.toString();
			 String nameArr[]=name.split(" ");
			 String about=stu.about.toString();
			 String AboutArr[]=about.split(" ");//white space tokenizer
		//	for(int i=0;i<AboutArr.length;i++){System.out.println(AboutArr[i]+" ");}
			 for(int i=0;i<nameArr.length;i++){
				 String input=nameArr[i].toLowerCase();
				 if(!stopWords.contains(input)){
					input= s.stemWord(input);
				 if(stu.object_detail.containsKey(input)){
					 int val1[]=stu.object_detail.get(input);
					 val1[0]=val1[0]+1;
					 stu.object_detail.put(input, val1);
				 }else{
					 int []val=new int[2];
					 val[0]=1;
					 stu.object_detail.put(input, val);
					
				 }
				
			 }
			 }
			 
			 for(int i=0;i<AboutArr.length;i++){
				 String input=AboutArr[i].toLowerCase();
				 if(!stopWords.contains(input)){
						input= s.stemWord(input);
				 if(stu.object_detail.containsKey(input)){
					 int val1[]=stu.object_detail.get(input);
					 val1[1]=val1[1]+1;
					 stu.object_detail.put(input, val1);
					 //if(input.equals("java")){System.out.println(stu.id+" : "+stu.name+":");}
					
				 }else{
					 int []val=new int[2];
					 val[1]=1;
					 stu.object_detail.put(input, val);
					 //if(input.equals("java")){System.out.println(stu.id+" : "+stu.name+":");}
				 }
			 }
			 }
			 
			 
		 }
		 //Till here I have updated the hashmap of each objects.
		 java.util.Iterator<Students_Class> itr1 = student_set.iterator();
		 while(itr1.hasNext()){
			 Students_Class stu=itr1.next();
			 String name=stu.name.toString();
			 String nameArr[]=name.split(" ");
			 String about=stu.about.toString();
			 String AboutArr[]=about.split(" ");//white space tokenizer
			 int val[]=new int[2];
			 for(int i=0;i<nameArr.length;i++){
				 String input=nameArr[i].toLowerCase();
				 if(!stopWords.contains(input)){
					 input= s.stemWord(input);
					 if(!outerMap.containsKey(input)){
						 Set<TextInfo>set=new HashSet<>();
						 TextInfo info=new TextInfo();
						 info.id=Integer.parseInt(stu.id);
						 info.name=stu.name;
						 int arr[]=stu.object_detail.get(input);
						 info.Term_Frequency+=(1000*arr[0]+50*arr[1]);
						 set.add(info);
						 outerMap.put(input, set);
						 
					 }else{
						 Set<TextInfo>set=outerMap.get(input);
						 TextInfo info=new TextInfo();
						 info.id=Integer.parseInt(stu.id);
						 info.name=stu.name;
						 int arr[]=stu.object_detail.get(input);
						 info.Term_Frequency+=(1000*arr[0]+50*arr[1]);
						 set.add(info);
						 set.add(info);
						 outerMap.put(input, set);
						 
					 }
				
			 }
			 }
			 for(int i=0;i<AboutArr.length;i++){
				 String input=AboutArr[i].toLowerCase();
				 if(!stopWords.contains(input)){
						input= s.stemWord(input);
						 if(!outerMap.containsKey(input)){
							 Set<TextInfo>set=new HashSet<>();
							 TextInfo info=new TextInfo();
							 info.id=Integer.parseInt(stu.id);
							 info.name=stu.name;
							 int arr[]=stu.object_detail.get(input);
							 info.Term_Frequency+=(1000*arr[0]+50*arr[1]);// term frequency cant be calculted here I have to modify this. 
							 set.add(info);
							 outerMap.put(input,set);
						 }else{
							 Set<TextInfo>set=outerMap.get(input);
							 TextInfo info=new TextInfo();
							 info.id=Integer.parseInt(stu.id);
							 info.name=stu.name;
							 int arr[]=stu.object_detail.get(input);
							 info.Term_Frequency+=(1000*arr[0]+50*arr[1]);
							 set.add(info);
							 outerMap.put(input,set);
						 }
			 }
			 }
			 
			 
		 }
		 Scanner sc=new Scanner(System.in);
		 System.out.println("Enter a string: ");
		 String str=sc.next();
		 str=str.toLowerCase();
		 if(!stopWords.contains(str)){
		  str=s.stemWord(str);
		  Set<TextInfo>set = null;
		  if(outerMap.containsKey(str)){
		     set=outerMap.get(str);
		   java.util.Iterator<TextInfo> it=set.iterator();
		 
		   ArrayList<TextInfo>list=new ArrayList<>();
		   HashSet<Integer>IntInfo=new HashSet<>();
		   HashSet<TextInfo>setInfo=new HashSet<>();
		   while(it.hasNext()){
			   TextInfo text=it.next();
			   if(!IntInfo.contains(text.id)){
				   setInfo.add(text);
				   IntInfo.add(text.id);
				   System.out.println(text.name+" : "+text.Term_Frequency);
			   }
			   
		   }
		   list.addAll(setInfo);
		   sort_list(list);
		  }else{
			  System.out.println("Not Found!!");
		  }
	       }else{
	    	   System.out.println("This is a stop words!!");
	       }
		 }
	
	public static void sort_list(ArrayList<TextInfo>list){
		Collections.sort(list, new Comparator<TextInfo>(){
			
			@Override
			public int compare(TextInfo o1, TextInfo o2) {
				// TODO Auto-generated method stub
				return o1.Term_Frequency>o2.Term_Frequency?-1:1;
			}
		});
		System.out.println("Found at: ");
		for(int i=0;i<list.size();i++){
			TextInfo text1=list.get(i);
			System.out.print(text1.id+" : "+text1.name);
			System.out.println();
		}
	}

}
