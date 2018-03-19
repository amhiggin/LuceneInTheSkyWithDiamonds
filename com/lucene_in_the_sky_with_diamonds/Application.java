package lucene_in_the_sky_with_diamonds;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.document.Document;

import lucene_in_the_sky_with_diamonds.document.ft.FTDocumentLoader;
import lucene_in_the_sky_with_diamonds.document.la.LADocumentLoader;
import lucene_in_the_sky_with_diamonds.document.fbis.FBISDocumentLoader;
import lucene_in_the_sky_with_diamonds.query.QueryFieldsObject;
import lucene_in_the_sky_with_diamonds.query.QueryLoader;

public class Application {

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
			loadDocumentCollection();
			loadQueries();

			print(String.format("%s queries loaded.", queries.size()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void loadDocumentCollection() throws Exception {
		loadFileTreesForAllCollections();
		loadDocumentsForAllCollections();
	}

	private static void loadQueries() {
		QueryLoader queryLoader = new QueryLoader();
		queryLoader.loadQueriesFromFile(Constants.TOPICS_FILEPATH);
		queries = queryLoader.getParsedQueries();
	}

	private static void loadDocumentsForAllCollections() throws IOException {
		// TODO add @"Ringo" O'Rourke and @"Paul" Xavier's file loading for FBIS, FR94

		// Financial Times
		FTDocumentLoader financialTimesDocumentLoader = new FTDocumentLoader();
		for (String fileName : ftCollectionFilenames) {
			financialTimesDocumentLoader.loadDocumentsFromFile(fileName);
			ftCollectionDocuments.addAll(financialTimesDocumentLoader.getCollectionDocuments());
			financialTimesDocumentLoader.setCollectionDocuments(new ArrayList<Document>());
		}
		// LA Times
		LADocumentLoader laDocLoader = new LADocumentLoader();
		for (String fileName : latimesCollectionFilenames) {
			laDocLoader.loadDocumentsFromFile(fileName);
			latimesCollectionDocuments.addAll(laDocLoader.getCollectionDocuments());
			laDocLoader.setCollectionDocuments(new ArrayList<Document>());
		}
		// FBIS
		
		FBISDocumentLoader fbisDocLoader = new FBISDocumentLoader();
		for (String fileName : fbisCollectionFilenames) {
			fbisDocLoader.loadDocumentsFromFile(fileName);
			fbisCollectionDocuments.addAll(fbisDocLoader.getCollectionDocuments());
			fbisDocLoader.setCollectionDocuments(new ArrayList<Document>());
		}
		// Print how many docs loaded per collection
		print(String.format("%s Financial Times documents loaded, from %s filepaths.", ftCollectionDocuments.size(),
				ftCollectionFilenames.size()));
		print(String.format("%s Foreign Broadcast Information Service documents loaded, from %s filepaths.",
				fbisCollectionDocuments.size(), fbisCollectionFilenames.size()));
		print(String.format("%s Federal Register documents loaded, from %s filepaths.", fr94CollectionDocuments.size(),
				fr94CollectionFilenames.size()));
		print(String.format("%s Los Angeles Times documents loaded, from %s filepaths.",
				latimesCollectionDocuments.size(), latimesCollectionFilenames.size()));
	}

	private static void loadFileTreesForAllCollections() throws Exception {
		ftCollectionFilenames = walkDirTree(Constants.FT_FILEPATH);
		latimesCollectionFilenames = walkDirTree(Constants.LATIMES_FILEPATH);
		fr94CollectionFilenames = walkDirTree(Constants.FR94_FILEPATH);
		fbisCollectionFilenames = walkDirTree(Constants.FBIS_FILEPATH);
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
