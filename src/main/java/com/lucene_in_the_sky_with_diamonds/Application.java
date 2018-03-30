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
import org.apache.lucene.store.FSDirectory;
import com.lucene_in_the_sky_with_diamonds.analysis.CustomAnalyzer;
import com.lucene_in_the_sky_with_diamonds.document.fbis.FBISDocumentLoader;
import com.lucene_in_the_sky_with_diamonds.document.fr94.FR94DocumentLoader;
import com.lucene_in_the_sky_with_diamonds.document.ft.FTDocumentLoader;
import com.lucene_in_the_sky_with_diamonds.document.la.LADocumentLoader;
import com.lucene_in_the_sky_with_diamonds.query.QueryFieldsObject;
import com.lucene_in_the_sky_with_diamonds.query.QueryLoader;

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

  private static final String ITERATION_NUM = " 0 ";
  private static final int TOP_X_RESULTS = 1000; // Upper limit in Trec-Eval
  private static final String indexPath = String.format("%s/index", Constants.APPLICATION_PATH);
  private static final String queryResultsFileName =
      String.format("%s/output/results.txt", Constants.APPLICATION_PATH);
  private static String qrelsInputFileName = String.format("", Constants.APPLICATION_PATH);
  private static final String trecEvalOutputFileName =
      String.format("%s/output/trec-eval-results.txt", Constants.APPLICATION_PATH);

  public static void main(String[] args) {
    try {
      qrelsInputFileName = String.format("%s/%s", Constants.APPLICATION_PATH, args[0]);
      if (!(Paths.get(qrelsInputFileName) == null)) {
        Analyzer analyzer = new CustomAnalyzer(StandardAnalyzer.ENGLISH_STOP_WORDS_SET);
        Directory indexDirectory = FSDirectory.open(Paths.get(indexPath));

        // TODO FIXME: for now, sticking in BM25 scoring model
        indexDocumentCollection(indexDirectory, analyzer, Constants.BM25);
        executeQueries(indexDirectory, analyzer, Constants.BM25);
        evaluateResults(indexDirectory, analyzer);
      }
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
    print("Queries loaded from the topics file.");
  }

  private static void loadDocumentsForAllCollections() throws IOException {
    // Federal Register
    FR94DocumentLoader federalRegisterDocumentLoader = new FR94DocumentLoader();
    for (String fileName : fr94CollectionFilenames) {
      federalRegisterDocumentLoader.loadDocumentsFromFile(fileName);
      fr94CollectionDocuments.addAll(federalRegisterDocumentLoader.getCollectionDocuments());
      federalRegisterDocumentLoader.setCollectionDocuments(new ArrayList<Document>());
    }

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
    FBISDocumentLoader fbisLoader = new FBISDocumentLoader();
    for (String fileName : fbisCollectionFilenames) {
      fbisLoader.loadDocumentsFromFile(fileName);
      fbisCollectionDocuments.addAll(fbisLoader.getCollectionDocuments());
      fbisLoader.setCollectionDocuments(new ArrayList<Document>());
    }

    // Print how many docs loaded per collection
    print(String.format("%s Financial Times documents loaded, from %s filepaths.",
        ftCollectionDocuments.size(), ftCollectionFilenames.size()));
    print(String.format(
        "%s Foreign Broadcast Information Service documents loaded, from %s filepaths.",
        fbisCollectionDocuments.size(), fbisCollectionFilenames.size()));
    print(String.format("%s Federal Register documents loaded, from %s filepaths.",
        fr94CollectionDocuments.size(), fr94CollectionFilenames.size()));
    print(String.format("%s Los Angeles Times documents loaded, from %s filepaths.",
        latimesCollectionDocuments.size(), latimesCollectionFilenames.size()));
  }

  private static void indexDocumentCollection(Directory indexDirectory, Analyzer analyzer,
      String scoringModel) throws Exception {
    IndexWriterConfig config = defineWriterConfiguration(analyzer, scoringModel);
    IndexWriter indexWriter = null;
    loadDocumentCollection();

    try {
      indexWriter = new IndexWriter(indexDirectory, config);
      try {
        indexWriter.addDocuments(latimesCollectionDocuments);
        print("Added latimes documents to index");
        indexWriter.addDocuments(fbisCollectionDocuments);
        print("Added fbis documents to index");
        indexWriter.addDocuments(fr94CollectionDocuments);
        print("Added fr94 documents to index");
        indexWriter.addDocuments(ftCollectionDocuments);
        print("Added ft documents to index");
      } catch (Exception e) {
        print("Failed to add document to the indexWriter");
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      try {
        indexWriter.close();
      } catch (Exception e) {
        print("Failed to close index writer: " + e.getMessage());
      }
    }
  }

  private static void executeQueries(Directory indexDirectory, Analyzer analyzer,
      String scoringModel) {
    ScoreDoc[] hits = {};
    IndexReader reader = null;
    PrintWriter writer = null;

    loadQueries();
    Map<String, Float> boostsMap = getFieldBoosts();
    // TODO FIXME these fields aren't right
    QueryParser parser =
        new MultiFieldQueryParser(new String[] {"Headline", "Text"}, analyzer, boostsMap);

    try {
      writer = new PrintWriter(queryResultsFileName, "UTF-8");
      reader = DirectoryReader.open(indexDirectory);

      IndexSearcher searcher = defineCustomSearcher(reader, scoringModel);
      for (int queryIndex = 0; queryIndex < (queries.size() - 1); queryIndex++) {
        QueryFieldsObject query = queries.get(queryIndex);
        // TODO FIXME Using the title for now as the query
        String stringQuery = QueryParser.escape(query.getTitle().toString() + " " + query.getDescription().toString());
        Query queryContents = parser.parse(stringQuery);
        hits = searcher.search(queryContents, TOP_X_RESULTS).scoreDocs;

        for (int i = 0; i < hits.length; i++) {
          ScoreDoc hit = hits[i];
          Document hitDoc = searcher.doc(hit.doc);
          String docNo = hitDoc.get("DocNo");
          String topic = query.getNum();
          writer.println(topic + ITERATION_NUM + docNo + " " + i + " " + hit.score + ITERATION_NUM);
        }
      }
    } catch (Exception e) {
      print("An exception occurred: " + e.getMessage());
    } finally {
      try {
        reader.close();
        writer.close();
        indexDirectory.close();
        print(String.format("Scores written to %s", queryResultsFileName));
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  private static IndexSearcher defineCustomSearcher(IndexReader reader, String scoringModel)
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
      ProcessBuilder pb =
          new ProcessBuilder("./trec_eval/trec_eval", qrelsInputFileName, queryResultsFileName);
      Path workingDirectory = FileSystems.getDefault().getPath(".").toAbsolutePath();
      pb.directory(new File(workingDirectory.toString()));
      Process process = pb.start();

      BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

      String line;
      List<String> lines = new ArrayList<>();
      while ((line = reader.readLine()) != null) {
        lines.add(line);
      }

      Files.write(Paths.get(trecEvalOutputFileName), lines, StandardCharsets.UTF_8,
          StandardOpenOption.CREATE, StandardOpenOption.WRITE,
          StandardOpenOption.TRUNCATE_EXISTING);

      print(String.format("TrecEval results available in %s", trecEvalOutputFileName));
    } catch (IOException e) {
      print("An exception occurred whilst executing trec eval over the results");
      e.printStackTrace();
    }
  }

  private static IndexWriterConfig defineWriterConfiguration(Analyzer analyzer, String scoringModel)
      throws Exception {
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

  private static Map<String, Float> getFieldBoosts() {
    Map<String, Float> boostsMap = new HashMap<String, Float>();
    boostsMap.put("Headline", new Float(0.2));
    boostsMap.put("Text", new Float(0.8));
    return boostsMap;
  }

  private static void print(String message) {
    System.out.println(message);
  }


}
