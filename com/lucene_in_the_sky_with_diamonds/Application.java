package com.lucene_in_the_sky_with_diamonds;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Application {

	private static final String ITERATION_NUM = " 0 ";
	private static final int TOP_X_RESULTS = 1000; // Upper limit in Trec-Eval
	private static final String APPLICATION_PATH = Paths.get("").toAbsolutePath().toString();
	private static final String FT_FILEPATH = String.format("%s/input_data/ft/", APPLICATION_PATH);
	private static String queriesFileName = String.format("%s/cran/cran.qry", APPLICATION_PATH);
	private static String relevancesInputFileName = String.format("%s/cran/cranqrel", APPLICATION_PATH);
	private static String computedResultsFileName = null;
	private static String trecEvalOutputFileName = null;
	private static String scoringModel = null;

	public static void main(String[] args) {
		try {
			walkDirTree(FT_FILEPATH);

			// FTDocumentLoader financialTimesDocumentLoader = new
			// FTDocumentLoader(FT_FILEPATH);
			// financialTimesDocumentLoader.loadDocumentsFromFile();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void walkDirTree(String rootFolder) throws Exception {
		Files.walk(Paths.get(rootFolder)).forEach(path -> {
			File file = new File(path.toString());
			if (file.isFile() && !file.getName().contains("read")) {
				System.out.println(path);
			}
		});
	}

}
