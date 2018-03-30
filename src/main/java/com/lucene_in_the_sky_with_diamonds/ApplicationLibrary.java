package com.lucene_in_the_sky_with_diamonds;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.misc.SweetSpotSimilarity;
import org.apache.lucene.search.similarities.BM25Similarity;
import org.apache.lucene.search.similarities.BooleanSimilarity;
import org.apache.lucene.search.similarities.ClassicSimilarity;
import org.apache.lucene.search.similarities.LMDirichletSimilarity;
import org.apache.lucene.search.similarities.Similarity;

public class ApplicationLibrary {

  public ApplicationLibrary() {}

  Similarity determineScoringModel(String scoringModel) {
    Similarity similarityModel = null;
    switch (scoringModel) {
      case Constants.BM25:
        similarityModel = new BM25Similarity();
        break;
      case Constants.VSM:
        similarityModel = new ClassicSimilarity();
        break;
      case Constants.BOOLEAN:
        similarityModel = new BooleanSimilarity();
        break;
      case Constants.LM_DIRICHLET:
        similarityModel = new LMDirichletSimilarity();
        break;
      case Constants.SWEET_SPOT:
        similarityModel = new SweetSpotSimilarity();
        break;
    }
    return similarityModel;
  }

  Analyzer determineAnalyzer(String analyzer) {
    return null;

  }

}
