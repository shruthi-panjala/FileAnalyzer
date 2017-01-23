package com.epam.project;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.CharBuffer;
import java.util.StringTokenizer;

import org.apache.catalina.connector.Request;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

public class test { 

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		File file=new File("C:\\Users\\Shruthi_Panjala\\Desktop\\textfiles\\one.docx");
		boolean str=file.getName().contains(".docx");
		System.out.println(str);
		System.out.println(file.getName());
		//out.println(str);
		XWPFDocument docx = new XWPFDocument(
				   new FileInputStream(file));
				   //using XWPFWordExtractor Class
				   XWPFWordExtractor we = new XWPFWordExtractor(docx);
				   System.out.println(we.getText());
				   System.out.println(we.getDocument());
		System.out.println(we.getCustomProperties().toString());
		System.out.println(we.getClass());
		//docx.par
		
	}
	}
		/*BufferedReader br1 = new BufferedReader(new FileReader(file.getAbsoluteFile()));
		
	String line1;int count=0;
	while ((line1 = br1.readLine()) != null) {
	String[] str=line1.split("^\\s+");
	StringTokenizer st=new StringTokenizer(line1," ");
	System.out.println(st.countTokens());
	if(st.countTokens()==0){
		count++;
	}
	}
	System.out.println("patas"+count);
		String str="hi this is shruthi i am working as a teacher in a school";
		Character ch='r';
		System.out.println(ch.isUpperCase('c'));
	      System.out.println(Character.isUpperCase('C'));
	      System.out.println(Character.isUpperCase('\n'));
	      System.out.println(Character.isUpperCase('\t'));
		
		}*/
	


