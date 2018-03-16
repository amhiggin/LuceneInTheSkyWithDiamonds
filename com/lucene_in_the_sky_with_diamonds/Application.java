package com.lucene_in_the_sky_with_diamonds;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.document.Document;

import com.lucene_in_the_sky_with_diamonds.document.CollectionType;
import com.lucene_in_the_sky_with_diamonds.document.ft.FTDocumentLoader;
import com.lucene_in_the_sky_with_diamonds.query.QueryFieldsObject;
import com.lucene_in_the_sky_with_diamonds.query.QueryLoader;

public class Application {

	private static final String APPLICATION_PATH = Paths.get("").toAbsolutePath().toString();
	private static final String FT_FILEPATH = String.format("%s/input_data/ft/", APPLICATION_PATH);
	private static final String FBIS_FILEPATH = String.format("%s/input_data/fbis/", APPLICATION_PATH);
	private static final String FR94_FILEPATH = String.format("%s/input_data/fr94/", APPLICATION_PATH);
	private static final String LATIMES_FILEPATH = String.format("%s/input_data/latimes/", APPLICATION_PATH);
	private static final String TOPICS_FILEPATH = String.format("%s/input_data/topics.401-450", APPLICATION_PATH);

	private static List<String> ftCollectionFilenames = new ArrayList<String>();
	private static List<String> fbisCollectionFilenames = new ArrayList<String>();
	private static List<String> latimesCollectionFilenames = new ArrayList<String>();
	private static List<String> fr94CollectionFilenames = new ArrayList<String>();
	private static List<Document> ftCollectionDocuments = new ArrayList<Document>();
	private static List<Document> fbisCollectionDocuments = new ArrayList<Document>();
	private static List<Document> latimesCollectionDocuments = new ArrayList<Document>();
	private static List<Document> fr94CollectionDocuments = new ArrayList<Document>();
	private static List<QueryFieldsObject> queries = new ArrayList<QueryFieldsObject>();

	public static void main(String[] args) {
		try {
			loadDocumentCollection(CollectionType.FT); // TODO allow this to be passed as args[0]
			// loadDocumentCollection(CollectionType.LA);
			QueryLoader.loadQueriesFromFile(TOPICS_FILEPATH);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void loadDocumentCollection(CollectionType collection) throws Exception {
		switch (collection) {
		case FT:
			ftCollectionFilenames = walkDirTree(FT_FILEPATH);
			FTDocumentLoader financialTimesDocumentLoader = new FTDocumentLoader();
			for (String fileName : ftCollectionFilenames) {
				financialTimesDocumentLoader.loadDocumentsFromFile(fileName);
				ftCollectionDocuments.addAll(financialTimesDocumentLoader.getCollectionDocuments());
				financialTimesDocumentLoader.setCollectionDocuments(new ArrayList<Document>());
			}
			print(String.format("%s Financial Times documents loaded, from %s filepaths.", ftCollectionDocuments.size(),
					ftCollectionFilenames.size()));
		case FBIS:
			walkDirTree(FBIS_FILEPATH);
			print(String.format("%s Foreign Broadcast Information Service documents loaded, from %s filepaths.",
					fbisCollectionDocuments.size(), fbisCollectionFilenames.size()));
		case FR94:
			walkDirTree(FR94_FILEPATH);
			print(String.format("%s Federal Register documents loaded, from %s filepaths.",
					fr94CollectionDocuments.size(), fr94CollectionFilenames.size()));
			// case LA:
			// latimesCollectionFilenames = walkDirTree(LATIMES_FILEPATH);
			// LADocumentLoader laDocLoader = new LADocumentLoader();
			// for (String fileName : latimesCollectionFilenames) {
			// laDocLoader.loadDocumentsFromFile(fileName);
			// latimesCollectionDocuments.addAll(laDocLoader.getCollectionDocuments());
			// laDocLoader.setCollectionDocuments(new ArrayList<Document>());
			// }
			// print(String.format("%s Los Angeles Times documents loaded, from %s
			// filepaths.",
			// latimesCollectionDocuments.size(), latimesCollectionFilenames.size()));
		default:
			break;
		}
	}

	private static List<String> walkDirTree(String rootFolder) throws Exception {
		List<String> collectionFilesToIndex = new ArrayList<String>();
		Files.walk(Paths.get(rootFolder)).forEach(path -> {
			File file = new File(path.toString());
			if (file.isFile() && !file.getName().contains("read")) {
				collectionFilesToIndex.add(path.toString());
			}
		});
		return collectionFilesToIndex;
	}

	private static void print(String message) {
		System.out.println(message);
	}
}
