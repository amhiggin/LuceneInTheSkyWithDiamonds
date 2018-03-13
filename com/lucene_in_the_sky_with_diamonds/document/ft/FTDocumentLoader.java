package com.lucene_in_the_sky_with_diamonds.document.ft;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;

public class FTDocumentLoader {

	private static final String EMPTY_STRING = "";

	private String topLevelRegex = "<DOCNO>|</DOCNO>|" + "<HEADLINE>|</HEADLINE>|" + "<TEXT>|</TEXT>|" + "</DOC>|"
			+ "<DATE>|</DATE>|" + "<BYLINE>| </BYLINE>|" + "<PUB>|</PUB>|" + "";

	private List<Document> collectionDocuments;

	public FTDocumentLoader() {
		this.collectionDocuments = new ArrayList<Document>();
	}

	public void loadDocumentsFromFile(String fileName) {
		try {
			Scanner scan = new Scanner(new File(fileName));
			scan.useDelimiter(Pattern.compile("<DOC>"));
			while (scan.hasNext()) {

				String[] sections = scan.next().split(topLevelRegex);
				ArrayList<String> cleanedData = clearEmptySections(sections);
				print(String.format("%s", cleanedData.size()));
				for (String datum : cleanedData) {
					// print(datum);
				}

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

	private ArrayList<String> clearEmptySections(String[] sections) {
		ArrayList<String> cleanedData = new ArrayList<String>();
		for (String sec : sections) {
			if (sec.trim().equals(EMPTY_STRING)) { // there might be a lot empty strings after regex splitting
				;
			} else {
				cleanedData.add(sec.trim());
			}
		}
		return cleanedData;

	}

	private Document createDocument(ArrayList<String> sections) {

		Document document = new Document();

		document.add(new StringField("DocNo", sections.get(0), Field.Store.YES));
		document.add(new TextField("Headline", sections.get(2), Field.Store.YES));
		document.add(new TextField("Date", sections.get(3), Field.Store.YES));
		document.add(new TextField("Pub", sections.get(4), Field.Store.YES));
		document.add(new TextField("Title", sections.get(5), Field.Store.YES));
		document.add(new TextField("Text", sections.get(6), Field.Store.YES));

		return document;
	}

	public int getSizeOfCollection() {
		return collectionDocuments.size();
	}

	public List<Document> getCollectionDocuments() {
		return collectionDocuments;
	}

	public void print(String message) {
		System.out.println(message);
	}
}
