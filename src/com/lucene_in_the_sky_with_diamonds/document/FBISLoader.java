package com.lucene_in_the_sky_with_diamonds.document;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;


public class FBISLoader {
	private String fileName;
	private List<Document> collectionDocuments;

	public FBISLoader(String fileName) {
		this.fileName = fileName;
		this.collectionDocuments = new ArrayList<Document>();
	}
	private String topLevelRegex = "<DOCNO>|</DOCNO>|"
									+ "<HT>|</HT>|"
									+ "<HEADER>|</HEADER>|"
									+ "<TEXT>|</TEXT>|"
									+ "</DOC>|"
									+ "<DATE1>|</DATE1>|"
									+ "<H2>| </H2>|"
									+ "<H3>|</H3>|"
									+ "<TI>|</TI>";
	
	private String headerLevelRegex = "<H2>|<DATE1>|<H3>";
	public void loadDocumentsFromFile() {
		 
		try { 
			Scanner scan = new Scanner(new File(this.fileName));
			scan.useDelimiter(Pattern.compile("<DOC>"));
			while (scan.hasNext()) {
				
				 
				String[] sections = scan.next().split(topLevelRegex);
				ArrayList<String> cleanedData = clearEmptySections(sections);
				System.out.println(cleanedData.size());
				System.out.println(cleanedData);
				
				Document doc = createDocument(cleanedData);
				
				this.collectionDocuments.add(doc);
				
				 
				 
				break;
			}	
			scan.close();
			 
		} catch (Exception e) {
			System.out.println(String.format("An exception occurred when parsing the documents from %s", fileName));
			e.printStackTrace();
		}
	}
	private ArrayList<String> clearEmptySections(String [] sections){
		ArrayList<String> cleanedData = new ArrayList<String>();
		for (String sec: sections) {
			if (sec.trim().equals("" )) { // there might be a lot empty strings after regex splitting
				;
			}else {
				cleanedData.add(sec.trim());
			}
		}
		return cleanedData;
		
	}
	
	private Document createDocument(ArrayList<String> sections) {
		
		/*
		 *  Ignoring HT, dont know what on earth that is.
		 *  There is a summary section might be a good idea to use it (index 6 of sections)
		 */
		
		Document document = new Document();
		
		document.add(new StringField("docNo",sections.get(0), Field.Store.YES));
		
		document.add(new TextField("H2", sections.get(2), Field.Store.YES));
		document.add(new TextField("Date", sections.get(3), Field.Store.YES));
		document.add(new TextField("DocumentType", sections.get(4), Field.Store.YES));
		document.add(new TextField("Title", sections.get(5), Field.Store.YES));
	 
		 
		document.add(new TextField("Text", sections.get(6), Field.Store.YES));
		 
		


		return document;
	}
	public int getSizeOfCollection() {
		return collectionDocuments.size();
	}

	public String getFileName() {
		return fileName;
	}

	public List<Document> getCollectionDocuments() {
		return collectionDocuments;
	}
}
