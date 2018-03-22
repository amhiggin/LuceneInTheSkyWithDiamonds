package com.lucene_in_the_sky_with_diamonds.document.fr94;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;

public class FR94DocumentLoader {

	private List<Document> collectionDocuments;

	public FR94DocumentLoader() {
		this.collectionDocuments = new ArrayList<Document>();
	}

	public void loadDocumentsFromFile(String fileName) {

		try (InputStream stream = Files.newInputStream(Paths.get(fileName))) {
			BufferedReader br = new BufferedReader(new InputStreamReader(stream, StandardCharsets.UTF_8));
			String line = null;
			String docNO = null, docID = null, parent = null;
			StringBuilder headlineStringBuilder = new StringBuilder(), textStringBuilder = new StringBuilder();
			;
			boolean docFound = false, headlineFound = false, textFound = false;
			ArrayList<String> sections = new ArrayList<String>();

			while ((line = br.readLine()) != null) {
				line = line.trim();

				if (line.equals(FR94FieldTypes.DOC_START.fieldType)) {
					// We are at the start of the document: we keep going until we get to the end
					docFound = true;
					continue;
				} else if (line.equals(FR94FieldTypes.DOC_END.fieldType)) {
					// Save the document and reset the fields
					docFound = false;
					try {
						getCollectionDocuments().add(createDocument(sections));
					} catch (Exception e) {
						continue;
					}
					sections = new ArrayList<String>();
					continue;
				} else if (line.contains(FR94FieldTypes.DOC_NO_START.fieldType)) {
					docNO = parseDocNO(line);
					sections.add(docNO);
				} else if (line.contains(FR94FieldTypes.PARENT_START.fieldType)) {
					parent = parseParent(line);
					sections.add(parent);
				} else if (line.equals(FR94FieldTypes.TEXT_START.fieldType)) {
					textFound = true;
					continue;
				} else if (line.equals(FR94FieldTypes.TEXT_END.fieldType)) {
					textFound = false;
					sections.add(textStringBuilder.toString());
					textStringBuilder = new StringBuilder();
					continue;
				}

				if (docFound == true) {
					if (textFound == true) {
						line = line.replaceAll( "(?s)<!--.*?-->", "" ); //remove xml comments
						textStringBuilder.append(line + " ");
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private String parseDocNO(String line) {
		String docNo = "";
		line = line.replaceAll(FR94FieldTypes.DOC_NO_START.fieldType, "");
		docNo = line.replaceAll(FR94FieldTypes.DOC_NO_END.fieldType, "");
		return docNo;
	}

	private String parseParent(String line) {
		String parent = "";
		line = line.replaceAll(FR94FieldTypes.PARENT_START.fieldType, "");
		parent = line.replaceAll(FR94FieldTypes.PARENT_END.fieldType, "");
		return parent;
	}

	protected static String removeComments(String line) {
		String toRemove = "";
		int beginIndex = line.indexOf('<');
		int endIndex = line.lastIndexOf('>');
		if (beginIndex == -1 || endIndex == -1 || beginIndex > endIndex) {
			//print(line);
			return null;
		}
		return line.replaceAll( "(?s)<!--.*?-->", "" );
	}

	private Document createDocument(ArrayList<String> sections) throws Exception {
		if (sections.isEmpty()) {
			throw new Exception("Cannot add empty sections to a document!");
		}
		Document document = new Document();
		document.add(new TextField("DocNo", sections.get(0), Field.Store.YES));
		document.add(new TextField("Parent", sections.get(1), Field.Store.YES));
		document.add(new TextField("Text", sections.get(2), Field.Store.YES));

		return document;
	}

	public int getSizeOfCollection() {
		return collectionDocuments.size();
	}

	public List<Document> getCollectionDocuments() {
		return collectionDocuments;
	}

	public static void print(String message) {
		System.out.println(message);
	}

	public void setCollectionDocuments(ArrayList<Document> documentList) {
		this.collectionDocuments = documentList;
	}
}


