package com.lucene_in_the_sky_with_diamonds;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;

 

public class FBISLoader {
	/*
	 * fb496178 is an example  stops in the middle for some reason for text
	 */
	private List<Document> collectionDocuments;

	public FBISLoader() {

		this.collectionDocuments = new ArrayList<Document>();
	}
 

	public void loadDocumentsFromFile(String fileName) {

		try { 
			InputStream stream = Files.newInputStream(Paths.get(fileName));
			BufferedReader reader = new BufferedReader(new InputStreamReader(stream, StandardCharsets.UTF_8));
			 
			String line;
		    while (!(line = getNextDoc(reader)).equals("")) {
		         
				ArrayList <String>sections =applyRegex(line);; 
  
				FBISFieldsObject docInfo = createFBISFieldsObject(sections);  
				Document doc = createDocument(docInfo);
//				printFBISFieldsObject(docInfo);
				this.collectionDocuments.add(doc); 
				 
		    }
			
 
			
			System.out.println(fileName+" Doc size (running total)"+ this.collectionDocuments.size());
		 

		} catch (Exception e) {
			System.out.println(String.format("An exception occurred when parsing the documents from %s", fileName));
			e.printStackTrace();
		}
	}
	 

	private ArrayList<String> applyRegex(String str) {
		/*
		 * 
		 */
		String topDocRegx = buildToplevelRegex();
		String nestedRegx = nestedRegex();
		ArrayList<String> result = new ArrayList<String>();
		
		String [] sections = str.split(topDocRegx);
		for( String sec: sections) { 
			String [] nestedSplit = null;
			if(! sec.trim().isEmpty()) {
				nestedSplit = sec.split(nestedRegx); 
			} 
			if(nestedSplit == null ) {
				if  (!sec.trim().equals("")){
					result.add(sec);
				} 
			}
			else {
				 
				for(String nSec :nestedSplit ) {
					if(!sec.trim().equals("")) {
						result.add(nSec);
					}
				}
				
			}
			
			
		}
		
		 
		
		
		return result;
		
	}
	private String nestedRegex() {
		/*
		 * TO be used after doing toplevel
		 */
		String regex =   FIBSFieldTypes.F_START.fieldType ;
		return regex;
	}
	private String buildToplevelRegex() {
		String regex = FIBSFieldTypes.DOC_NO_START.fieldType 
				+ "|" + FIBSFieldTypes.HEADER_START.fieldType
				+ "|" + FIBSFieldTypes.TEXT_START.fieldType 
				+ "|" + FIBSFieldTypes.TITLE_START.fieldType
				
				+ "|" + FIBSFieldTypes.HT_START.fieldType 
				+ "|" + FIBSFieldTypes.DATE1_START.fieldType;

			//+ "|" + FIBSFieldTypes.F_START.fieldType 
		return regex;
	}

	private String getNextDoc(BufferedReader reader) throws IOException {
		/**
		 * Reads in each doc at a time
		 */
		StringBuilder doc = new StringBuilder();
		String line = null;
		while ((line = reader.readLine()) != null) {
			doc.append(line);
			if(line.equals(FIBSFieldTypes.DOC_END.fieldType)) {
				doc.append(line);
				break;
			}
			
			
		}
		return doc.toString();
		
	}

	public static String stripTags(String buf) {

		return buf.replaceAll("<[^>]*>", " ");
	}

	private FBISFieldsObject createFBISFieldsObject(ArrayList<String> sections) {
		/*
		 * Date is in fb496178 Sometimes date appears inside the header
		 * 
		 * Whatever is left after parsing is the  unknownSect.
		 * Need to do research into what to do with
		 */
		FBISFieldsObject obj = new FBISFieldsObject();
	 
		for (String sec : sections) {

			if (sec.trim().contains("</DATE1>")) {

				obj.setDate((sec.split("</DATE1>")[0].trim()));
			}  
			else if (sec.trim().contains("</DOCNO>")) {
				
				obj.setDocNo(sec.split("</DOCNO>")[0].trim());
				
			}  else if (sec.trim().contains("</TI>")) {

				obj.setTitle((sec.split("</TI>")[0].trim()));
				
			}  else if (sec.trim().contains("</TEXT>")) {
				// remove all the other headers
		 
				String cleanData = stripTags(sec.split("</TEXT>")[0].trim());
				
				obj.setText(cleanData);
			}else if (sec.trim().contains("</F>")) {
				// append all the f tags and
			 
				 String [] splitData = sec.split("</F>");
				 if(splitData.length>0) {
					 obj.setFtags((sec.split("</F>")[0].trim()));
					 
				 }
					 
				
			 

			} else {
				/// keeps all the uninteresting tags into this
				String cleanData = stripTags(sec.trim());

				obj.setUnknownSect(cleanData);
				 
			}
		}
		return obj;

	}
	private void printFBISFieldsObject(FBISFieldsObject sections) {
		System.out.println("DocNo "+ sections.getDocNo() + "\n\n***************");
		System.out.println("Date "+ sections.getDate() + "\n\n***************");
		System.out.println("Title "+ sections.getTitle() + "\n\n***************");
		System.out.println("Text "+ sections.getText() + "\n\n***************");
		System.out.println("Ftags "+ sections.getFtags() + "\n\n***************");
		System.out.println("unknownSect "+ sections.getFtags() + "\n\n***************");
	}
	private Document createDocument(FBISFieldsObject sections) throws Exception {

		/*
		 * Ignoring HT, dont know what on earth that is. There is a summary section
		 * might be a good idea to use it (index 6 of sections)
		 * 
		 *  
		 */
		if (sections==null) {
			throw new Exception("Cannot add empty sections to a document!");
		}

		Document document = new Document();
		
		
		document.add(new StringField("DocNo", sections.getDocNo(), Field.Store.YES));

 
		document.add(new TextField("Date", sections.getDate(), Field.Store.YES)); 
		document.add(new TextField("Title",sections.getTitle(), Field.Store.YES));

		document.add(new TextField("Text", sections.getText(), Field.Store.YES));
		document.add(new TextField("Ftags", sections.getFtags(), Field.Store.YES));
		document.add(new TextField("unknownSect", sections.getUnknownSect(), Field.Store.YES));
		

		return document;
	}

	public int getSizeOfCollection() {
		return collectionDocuments.size();
	}

	public List<Document> getCollectionDocuments() {
		return collectionDocuments;
	}
}
