package TS2E;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.StringTokenizer;

public class StopWord {
	public static final String STOP_WORD_FILE = "../Text_Search_Engine/src/TS2E/stopword.txt";
    public static final String STOP_WORD_DELIMITER=",";
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		HashSet<String>stopWords=new HashSet<>();
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(new File(STOP_WORD_FILE)));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String line;
		try {
			while( (line = br.readLine()) != null){
				  String tokens[] = line.toLowerCase().split(STOP_WORD_DELIMITER);
				  for(String token:tokens){
					  stopWords.add(token);
				  }
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        System.out.println("Enter the one line of string: ");
        String userInput=sc.nextLine();
        ArrayList<String>tokens=new ArrayList<>();
        StringTokenizer st = new StringTokenizer(userInput);
		 while (st.hasMoreTokens()) {
			 String token=st.nextToken();
			 if(!stopWords.contains(token)){tokens.add(token);}
	     }
		 System.out.println("This the our sentence after removing stopwords: ");
        for(int i=0;i<tokens.size();i++){
        	System.out.print(tokens.get(i)+" ");
        }
        
        
	}

}

