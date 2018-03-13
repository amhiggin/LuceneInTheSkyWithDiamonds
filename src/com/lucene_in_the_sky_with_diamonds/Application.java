package com.lucene_in_the_sky_with_diamonds;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.core.SimpleAnalyzer;
import org.apache.lucene.analysis.core.StopAnalyzer;
import org.apache.lucene.analysis.core.WhitespaceAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.similarities.BM25Similarity;
import org.apache.lucene.search.similarities.BooleanSimilarity;
import org.apache.lucene.search.similarities.ClassicSimilarity;
import org.apache.lucene.search.similarities.LMDirichletSimilarity;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;

import com.lucene_in_the_sky_with_diamonds.analysis.CustomAnalyzer;
import com.lucene_in_the_sky_with_diamonds.document.CollectionLoader;
import com.lucene_in_the_sky_with_diamonds.document.FBISLoader;
import com.lucene_in_the_sky_with_diamonds.query.QueryFieldsObject;
import com.lucene_in_the_sky_with_diamonds.query.QueryLoader;

public class Application {

	private static final String ITERATION_NUM = " 0 ";
	private static final int TOP_X_RESULTS = 1000; // Upper limit in Trec-Eval
	private static String applicationPath = Paths.get("").toAbsolutePath().toString();
	private static String documentsFileName = String.format("%s/cran/cran.all.1400", applicationPath);
	private static String queriesFileName = String.format("%s/cran/cran.qry", applicationPath);
	private static String relevancesInputFileName = String.format("%s/cran/cranqrel", applicationPath);
	private static String computedResultsFileName = null;
	private static String trecEvalOutputFileName = null;
	private static String scoringModel = null;

	public static void main(String[] args) {
	 
		FBISLoader collectionLoader = new FBISLoader( "/home/jibin/Desktop/Assignment_Two/fbis/fb396001");
		collectionLoader.loadDocumentsFromFile();
//		try {
//			if (args.length != 2 || !validRankingModelSpecified(args[0]) || !validAnalyzerSpecified(args[1])) {
//				throw new RuntimeException(Constants.usageExceptionMessage(args));
//			} else {
//				setScoringModel(args[0]);
//				Analyzer analyzer = setAnalyzer(args[1]);
//				updateOutputFilePaths(args[0], args[1]);
//
//				Directory indexDirectory = new RAMDirectory();
//
//				indexDocumentCollection(indexDirectory, analyzer);
//				executeQueries(indexDirectory, analyzer);
//				evaluateResults(indexDirectory, analyzer);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}

	private static void updateOutputFilePaths(String _scoringModel, String _analyzer) {
		computedResultsFileName = String.format("%s/output/my-qrel-%s-%s.txt", applicationPath, _scoringModel,
				_analyzer);
		trecEvalOutputFileName = String.format("%s/output/trec-eval-results-%s-%s.txt", applicationPath, _scoringModel,
				_analyzer);

	}

	private static void indexDocumentCollection(Directory indexDirectory, Analyzer analyzer) throws Exception {
		List<Document> documents = loadCranfieldDocumentCollection();
		IndexWriterConfig config = defineWriterConfiguration(analyzer);
		IndexWriter indexWriter = null;

		try {
			indexWriter = new IndexWriter(indexDirectory, config);
			try {
				indexWriter.addDocuments(documents);
			} catch (Exception e) {
				print("Failed to add document to the indexWriter");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				indexWriter.close();
			} catch (Exception e) {
				print("Failed to close index writer/index directory: " + e.getMessage());
			}
		}
	}

	private static void executeQueries(Directory indexDirectory, Analyzer analyzer) {
		ScoreDoc[] hits = {};
		IndexReader reader = null;
		PrintWriter writer = null;

		Map<String, Float> boostsMap = getFieldBoosts();
		QueryParser parser = new MultiFieldQueryParser(new String[] { "title", "author", "bibliography", "text" },
				analyzer, boostsMap);
		List<QueryFieldsObject> queryCollection = loadCranfieldQueryCollection();

		try {
			writer = new PrintWriter(computedResultsFileName, "UTF-8");
			reader = DirectoryReader.open(indexDirectory);

			IndexSearcher searcher = defineCustomSearcher(reader, analyzer, scoringModel);
			for (int queryIndex = 1; queryIndex < queryCollection.size(); queryIndex++) {
				QueryFieldsObject query = queryCollection.get(queryIndex);
				String stringQuery = QueryParser.escape(query.getContent().toString());
				Query queryContents = parser.parse(stringQuery);
				hits = searcher.search(queryContents, TOP_X_RESULTS).scoreDocs;

				for (int i = 0; i < hits.length; i++) {
					ScoreDoc hit = hits[i];
					writer.println(queryIndex + ITERATION_NUM + (hit.doc + 1) + " " + i + " " + Math.round(hit.score)
							+ ITERATION_NUM);
				}
			}
		} catch (Exception e) {
			print("An exception occurred: " + e.getMessage());
		} finally {
			try {
				reader.close();
				writer.close();
				indexDirectory.close();
				print(String.format("Scores written to %s", computedResultsFileName));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private static Map<String, Float> getFieldBoosts() {
		Map<String, Float> boostsMap = new HashMap<String, Float>();
		boostsMap.put("title", new Float(0.5));
		boostsMap.put("author", new Float(0.1));
		boostsMap.put("bibliography", new Float(0.1));
		boostsMap.put("content", new Float(0.3));
		return boostsMap;
	}

	private static IndexSearcher defineCustomSearcher(IndexReader reader, Analyzer analyzer, String scoringModel)
			throws Exception {
		IndexSearcher searcher = new IndexSearcher(reader);
		switch (scoringModel) {
		case Constants.BM25:
			searcher.setSimilarity(new BM25Similarity());
			break;
		case Constants.VSM:
			searcher.setSimilarity(new ClassicSimilarity());
			break;
		case Constants.BOOLEAN:
			searcher.setSimilarity(new BooleanSimilarity());
			break;
		case Constants.LM_DIRICHLET:
			searcher.setSimilarity(new LMDirichletSimilarity());
		}
		return searcher;
	}

	private static void evaluateResults(Directory indexDirectory, Analyzer analyzer) {
		try {
			// Run trec eval over the two files
			ProcessBuilder pb = new ProcessBuilder("./trec_eval/trec_eval", "-l", "3", "-m", "all_trec",
					relevancesInputFileName, computedResultsFileName);
			Path workingDirectory = FileSystems.getDefault().getPath(".").toAbsolutePath();
			pb.directory(new File(workingDirectory.toString()));
			Process process = pb.start();

			BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

			String line;
			List<String> lines = new ArrayList<>();
			while ((line = reader.readLine()) != null) {
				lines.add(line);
			}

			Files.write(Paths.get(trecEvalOutputFileName), lines, StandardCharsets.UTF_8, StandardOpenOption.CREATE,
					StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.WRITE);

			print(String.format("TrecEval results available in %s", trecEvalOutputFileName));
		} catch (IOException e) {
			print("An exception occurred whilst executing trec eval over the results");
			e.printStackTrace();
		}
	}

	private static IndexWriterConfig defineWriterConfiguration(Analyzer analyzer) throws Exception {
		IndexWriterConfig config = new IndexWriterConfig(analyzer);
		switch (scoringModel) {
		case Constants.BM25:
			config.setSimilarity(new BM25Similarity());
			break;
		case Constants.VSM:
			config.setSimilarity(new ClassicSimilarity());
			break;
		case Constants.BOOLEAN:
			config.setSimilarity(new BooleanSimilarity());
			break;
		case Constants.LM_DIRICHLET:
			config.setSimilarity(new LMDirichletSimilarity());
		}
		config.setOpenMode(IndexWriterConfig.OpenMode.CREATE);
		return config;
	}

	private static List<Document> loadCranfieldDocumentCollection() {
		CollectionLoader collectionLoader = new CollectionLoader(documentsFileName);
		collectionLoader.loadDocumentsFromFile();
		print(String.format("%s documents loaded", collectionLoader.getSizeOfCollection()));
		return collectionLoader.getCollectionDocuments();
	}

	private static List<QueryFieldsObject> loadCranfieldQueryCollection() {
		QueryLoader queryLoader = new QueryLoader(queriesFileName);
		queryLoader.loadQueriesFromFile();
		print(String.format("%s queries loaded", queryLoader.getNumberOfQueries()));
		return queryLoader.getParsedQueries();
	}

	private static boolean validRankingModelSpecified(String arg0) {
		for (String scoringModel : Constants.ALL_SCORING_MODELS) {
			if (scoringModel.equals(arg0)) {
				return true;
			}
		}
		return false;
	}

	private static boolean validAnalyzerSpecified(String arg1) {
		for (String analyzer : Constants.ALL_ANALYZERS) {
			if (analyzer.equals(arg1)) {
				return true;
			}
		}
		return false;
	}

	private static Analyzer setAnalyzer(String arg1) throws Exception {
		print(String.format("Analyzer: %s", arg1));
		switch (arg1) {
		case Constants.CUSTOM_ANALYZER:
			return new CustomAnalyzer(StandardAnalyzer.ENGLISH_STOP_WORDS_SET);
		case Constants.STANDARD_ANALYZER:
			return new StandardAnalyzer(StandardAnalyzer.ENGLISH_STOP_WORDS_SET);
		case Constants.SIMPLE_ANALYZER:
			return new SimpleAnalyzer();
		case Constants.WHITESPACE_ANALYZER:
			return new WhitespaceAnalyzer();
		case Constants.STOP_ANALYZER:
			return new StopAnalyzer();
		default:
			throw new Exception(String.format("Invalid analyzer specified: %s", arg1));
		}

	}

	private static void setScoringModel(String arg0) {
		scoringModel = arg0;
		print(String.format("Ranking model: %s", scoringModel));
	}

	private static void print(String message) {
		System.out.println(message);
	}
}
