package com.lucene_in_the_sky_with_diamonds.document.ft;

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

public class FTDocumentLoader {

	private static final String EMPTY_STRING = "";
	// private static final Set<String> documentTags = new
	// HashSet<String>(Arrays.asList("<p>", "</p>", "<ABS>", "<AU>",
	// "<DATE1>", "<H1>", "<HEADER>", "<HT>", "<TEXT>", "<TR>", "<BYLINE>",
	// "<CORRECTION>", "<CORRECTION-DATE>",
	// "<DATE>", "<DATELINE>", "<GRAPHIC>", "<LENGTH>", "<SECTION>", "<SUBJECT>",
	// "<TYPE>"));

	private List<Document> collectionDocuments;

	public FTDocumentLoader() {
		this.collectionDocuments = new ArrayList<Document>();
	}

	public void loadDocumentsFromFile(String fileName) {

		try (InputStream stream = Files.newInputStream(Paths.get(fileName))) {
			BufferedReader br = new BufferedReader(new InputStreamReader(stream, StandardCharsets.UTF_8));
			String line = null;
			String docNO = null, docID = null, s;
			StringBuilder sb = new StringBuilder();
			boolean docFound = false, headlineFound = false, titleFound = false;
			ArrayList<String> sections = new ArrayList<String>();

			while ((line = br.readLine()) != null) {
				line = line.trim();

				if (line.equals(FTFieldTypes.DOC_START.fieldType)) {
					// We are at the start of the document: we keep going until we get to the end
					docFound = true;
					continue;
				} else if (line.equals(FTFieldTypes.DOC_END.fieldType)) {
					docFound = false;
					// Save the document and reset the fields
					try {
						getCollectionDocuments().add(createDocument(sections));
					} catch (Exception e) {
						continue;
					}
					sections = new ArrayList<String>();
					continue;
				} else if (line.contains(FTFieldTypes.DOC_NO_START.fieldType)) {
					docNO = parseDocNO(line);
					sections.add(docNO);
				} else if (line.equals(FTFieldTypes.HEADLINE_START.fieldType)) {
					// Go through the other lines of the document
					headlineFound = true;
					continue;
				} else if (line.equals(FTFieldTypes.HEADLINE_END.fieldType)) {
					headlineFound = false;
					sections.add(sb.toString());
					sb = new StringBuilder();
					continue;
				}

				if (docFound == true) {
					if (headlineFound == true) {
						sb.append(line);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private String parseDocNO(String line) {
		String docNo = "";
		line = line.replaceAll(FTFieldTypes.DOC_NO_START.fieldType, "");
		docNo = line.replaceAll(FTFieldTypes.DOC_NO_END.fieldType, "");
		return docNo;
	}

	protected static String removeTags(String line) {
		int beginIndex = line.indexOf('>');
		int endIndex = line.lastIndexOf('<');
		if (beginIndex == -1 || endIndex == -1 || beginIndex > endIndex) {
			print(line);
			return null;
		}
		return line.substring(beginIndex + 1, endIndex).trim();
	}

	private Document createDocument(ArrayList<String> sections) throws Exception {
		if (sections.isEmpty()) {
			throw new Exception("Cannot add empty sections to a document!");
		}

		Document document = new Document();
		document.add(new TextField("DocNo", sections.get(0), Field.Store.YES));
		document.add(new TextField("Headline", sections.get(1), Field.Store.YES));

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
