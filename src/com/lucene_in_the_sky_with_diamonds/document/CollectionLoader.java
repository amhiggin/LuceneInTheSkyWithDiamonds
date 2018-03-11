package com.lucene_in_the_sky_with_diamonds.document;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;

public class CollectionLoader {

	private String fileName;
	private List<Document> collectionDocuments;

	public CollectionLoader(String fileName) {
		this.fileName = fileName;
		this.collectionDocuments = new ArrayList<Document>();
	}

	public void loadDocumentsFromFile() {
		DocumentFieldsObject documentInfo = new DocumentFieldsObject();
		// Start with ".I" tag, which will always be first
		String tempTag = ValidDocumentTag.ID.getTag();

		try {
			BufferedReader reader = new BufferedReader(new FileReader(this.fileName));
			String line = null;

			while ((line = reader.readLine()) != null) {
				String tag = getTagFromCurrentLineIfExists(line);
				if (notNewDocumentField(tempTag, tag)) {
					;
				} else {
					tempTag = tag;
					documentInfo = storePreviousDocumentIfAllFieldsPopulated(documentInfo, tempTag);
				}
				populateAppropriateDocumentField(documentInfo, tempTag, line);
			}
			// Add the final document when we get to the end
			Document document = createDocument(documentInfo);
			collectionDocuments.add(document);
			document = new Document();
			reader.close();
		} catch (Exception e) {
			System.out.println(String.format("An exception occurred when parsing the documents from %s", fileName));
			e.printStackTrace();
		}
	}

	private boolean notNewDocumentField(String tempTag, String tag) {
		return !getValidTags().contains(tag) || tag == tempTag;
	}

	private void populateAppropriateDocumentField(DocumentFieldsObject documentInfo, String tempTag, String line) {
		if (tempTag.equals(ValidDocumentTag.ID.getTag())) {
			documentInfo.setId(line.split(" ")[1]);
		} else if (tempTag.equals(ValidDocumentTag.AUTHORS.getTag())
				&& !line.contains(ValidDocumentTag.AUTHORS.getTag())) {
			documentInfo.setAuthors(documentInfo.getAuthors() + line);
		} else if (tempTag.equals(ValidDocumentTag.TITLE.getTag()) && !line.contains(ValidDocumentTag.TITLE.getTag())) {
			documentInfo.setTitle(documentInfo.getTitle().append(" " + line));
		} else if (tempTag.equals(ValidDocumentTag.CONTENT.getTag())
				&& !line.contains(ValidDocumentTag.CONTENT.getTag())) {
			documentInfo.setContent(documentInfo.getContent().append(" " + line));
		} else if (tempTag.equals(ValidDocumentTag.BIBLIOGRAPHY.getTag())
				&& !line.contains(ValidDocumentTag.BIBLIOGRAPHY.getTag())) {
			documentInfo.setBibliography(documentInfo.getBibliography() + line);
		}
	}

	private DocumentFieldsObject storePreviousDocumentIfAllFieldsPopulated(DocumentFieldsObject documentInfo,
			String tempTag) {
		if (tempTag == ValidDocumentTag.ID.getTag()) {
			// Store the previous document and reset
			Document document = createDocument(documentInfo);
			collectionDocuments.add(document);
			documentInfo = new DocumentFieldsObject();
		}
		return documentInfo;
	}

	private List<String> getValidTags() {
		List<String> validTags = new ArrayList<String>();
		validTags.add(ValidDocumentTag.AUTHORS.tag);
		validTags.add(ValidDocumentTag.BIBLIOGRAPHY.tag);
		validTags.add(ValidDocumentTag.TITLE.tag);
		validTags.add(ValidDocumentTag.ID.tag);
		validTags.add(ValidDocumentTag.CONTENT.tag);
		return validTags;
	}

	private Document createDocument(DocumentFieldsObject documentInfo) {
		Document document = new Document();

		// NB: TextFields are tokenised: StringFields are not
		document.add(new StringField("id", documentInfo.getId(), Field.Store.YES));
		document.add(new TextField("title", documentInfo.getTitle().toString(), Field.Store.YES));
		document.add(new TextField("authors", documentInfo.getAuthors(), Field.Store.YES));
		document.add(new TextField("bibliography", documentInfo.getBibliography(), Field.Store.YES));
		document.add(new TextField("content", documentInfo.getContent().toString(), Field.Store.YES));

		return document;
	}

	private String getTagFromCurrentLineIfExists(String line) {
		for (ValidDocumentTag tag : ValidDocumentTag.values()) {
			if (line.contains(tag.getTag())) {
				return tag.getTag();
			}
		}
		return null;
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
