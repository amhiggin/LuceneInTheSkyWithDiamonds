package com.lucene_in_the_sky_with_diamonds.document.la;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;

public class LADocumentLoader {

	private List<Document> collectionDocuments;

	public LADocumentLoader() {
		this.collectionDocuments = new ArrayList<Document>();
	}

	public void loadDocumentsFromFile(String fileName) throws IOException {
		InputStream in = Files.newInputStream(Paths.get(fileName));
		BufferedReader bufferedReader = null;
		String line;
		String headline = "";
		String body = "";
		String author = "";
		String docNo = "";
		String date = "";
		boolean pTag = false;
		boolean textTag = false;
		boolean headTag = false;
		boolean byTag = false;
		boolean dateTag = false;
		boolean found = false;
		try {
			bufferedReader = new BufferedReader(new InputStreamReader(in));
			bufferedReader.readLine();
			while ((line = bufferedReader.readLine()) != null) {
				if (line.startsWith(LAFieldTypes.P_START)) {
					pTag = true;
				} else if (line.startsWith(LAFieldTypes.DOCNO_START)) {
					docNo = line.replace(LAFieldTypes.DOCNO_START, "");
					docNo = docNo.replace(LAFieldTypes.DOCNO_END, "");
					docNo = docNo.replace(" ", "");
					// System.out.println(docNo);
				} else if (line.startsWith(LAFieldTypes.HEADLINE_START)) {
					headTag = true;
				} else if (line.startsWith(LAFieldTypes.TEXT_START)) {
					textTag = true;
				} else if (line.startsWith(LAFieldTypes.BYLINE_START)) {
					byTag = true;
				} else if (line.startsWith(LAFieldTypes.DATE_START)) {
					dateTag = true;
				} else if (line.startsWith(LAFieldTypes.DOC_END)) {
					Document doc = createDocument(docNo, body, author, headline, date);
					getCollectionDocuments().add(doc);
					body = "";
					docNo = "";
					headline = "";
					author = "";
					date = "";
				} else if (line.startsWith(LAFieldTypes.P_END)) {
					pTag = false;
				} else if (line.startsWith(LAFieldTypes.HEADLINE_END)) {
					headTag = false;
				} else if (line.startsWith(LAFieldTypes.TEXT_END)) {
					textTag = false;
				} else if (line.startsWith(LAFieldTypes.BYLINE_END)) {
					byTag = false;
					found = false;
				} else if (line.startsWith(LAFieldTypes.DATE_END)) {
					dateTag = false;
				} else if (pTag) {
					if (textTag) {
						body = body + " " + line;
					} else if (headTag) {
						headline = headline + " " + line;
					} else if (byTag) {
						// if(line.contains("Compiled by")) {
						// author = line.replace("Compiled by", "");
						// }
						if (!found) {
							if (line.contains("By")) {
								// author = line.replace("By", "");
								int index = line.lastIndexOf("By");
								index = index + 2;
								author = line.substring(index);
								found = true;
							} else if (line.contains("by")) {
								int index = line.lastIndexOf("by");
								index = index + 2;
								author = line.substring(index);
								found = true;
							}
							if (line.contains("From")) {
								// author = line.replace("From", "");
								int index = line.lastIndexOf("From");
								index = index + 4;
								author = line.substring(index);
								found = true;
							} else if (line.contains("From")) {
								// author = line.replace("From", "");
								int index = line.lastIndexOf("from");
								index = index + 4;
								author = line.substring(index);
								found = true;
							}
							if (author.contains(",")) {
								String[] split = author.split(",");
								author = split[0];
							}
							if (docNo.equals("LA081390-0104")) {
								// System.out.println(line);
							}
							if (author.contains(" "))
								author = author.substring(1);
							// System.out.println(author);
						}
					} else if (dateTag) {
						if (line.contains(",")) {
							String[] split = line.split(",");
							date = split[0] + split[1];
						}
					}
				}
			}
			bufferedReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private Document createDocument(String id, String body, String author, String title, String date) {
		Document doc = new Document();
		doc.add(new TextField("DocNo", id, Field.Store.YES));
		doc.add(new TextField("Text", body, Field.Store.YES));
		doc.add(new TextField("Author", author, Field.Store.YES));
		doc.add(new TextField("Headline", title, Field.Store.YES));
		doc.add(new TextField("Date", date, Field.Store.YES));
		return doc;
	}

	public int getSizeOfCollection() {
		return collectionDocuments.size();
	}

	public List<Document> getCollectionDocuments() {
		return collectionDocuments;
	}

	public void setCollectionDocuments(ArrayList<Document> documentList) {
		this.collectionDocuments = documentList;
	}

}
