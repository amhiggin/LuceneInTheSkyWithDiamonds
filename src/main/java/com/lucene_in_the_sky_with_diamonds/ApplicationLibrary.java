package com.lucene_in_the_sky_with_diamonds;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.core.KeywordAnalyzer;
import org.apache.lucene.analysis.core.SimpleAnalyzer;
import org.apache.lucene.analysis.core.StopAnalyzer;
import org.apache.lucene.analysis.core.WhitespaceAnalyzer;
import org.apache.lucene.analysis.morfologik.MorfologikAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.misc.SweetSpotSimilarity;
import org.apache.lucene.search.similarities.BM25Similarity;
import org.apache.lucene.search.similarities.BooleanSimilarity;
import org.apache.lucene.search.similarities.ClassicSimilarity;
import org.apache.lucene.search.similarities.LMDirichletSimilarity;
import org.apache.lucene.search.similarities.LMJelinekMercerSimilarity;
import org.apache.lucene.search.similarities.MultiSimilarity;
import org.apache.lucene.search.similarities.Similarity;

import com.lucene_in_the_sky_with_diamonds.analysis.CustomAnalyzer;
import com.lucene_in_the_sky_with_diamonds.analysis.CustomAnalyzerB;
import com.lucene_in_the_sky_with_diamonds.analysis.CustomAnalyzerC;
import com.lucene_in_the_sky_with_diamonds.analysis.ShingleAnalyzer;

public class ApplicationLibrary {

	public ApplicationLibrary() {
	}

	Similarity determineScoringModel(String scoringModel) throws Exception {
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
		case Constants.LM_JELINEK_MERCER:
			similarityModel = new LMJelinekMercerSimilarity(0.49f);
			break;
		case Constants.MULTI:
			Similarity sims[] = { new LMDirichletSimilarity(), new LMJelinekMercerSimilarity(0.75f)};
			similarityModel = new MultiSimilarity(sims);
			break;
		default:
			throw new Exception(String.format("Invalid scoring model specified: %s", scoringModel));
		}
		return similarityModel;
	}

	Analyzer determineAnalyzer(String parsedAnalyzer) throws Exception {
		Analyzer analyzer = null;
		switch (parsedAnalyzer) {
		case Constants.KEYWORD_ANALYZER:
			analyzer = new KeywordAnalyzer();
			break;
		case Constants.MORFOLOGIK_ANALYZER:
			analyzer = new MorfologikAnalyzer();
			break;
		case Constants.SHINGLE_ANALYZER:
			analyzer = new ShingleAnalyzer(StandardAnalyzer.ENGLISH_STOP_WORDS_SET);
			break;
		case Constants.STANDARD_ANALYZER:
			analyzer = new StandardAnalyzer(StandardAnalyzer.ENGLISH_STOP_WORDS_SET);
			break;
		case Constants.SIMPLE_ANALYZER:
			analyzer = new SimpleAnalyzer();
			break;
		case Constants.STOP_ANALYZER:
			analyzer = new StopAnalyzer(StandardAnalyzer.ENGLISH_STOP_WORDS_SET);
			break;
		case Constants.WHITESPACE_ANALYZER:
			analyzer = new WhitespaceAnalyzer();
			break;
		case Constants.CUSTOM_ANALYZER_A:
			analyzer = new CustomAnalyzer();
			break;
		case Constants.CUSTOM_ANALYZER_B:
			analyzer = new CustomAnalyzerB(StandardAnalyzer.ENGLISH_STOP_WORDS_SET);
			break;
		case Constants.CUSTOM_ANALYZER_C:
			analyzer = new CustomAnalyzerC(StandardAnalyzer.ENGLISH_STOP_WORDS_SET);
			break;
		default:
			throw new Exception(String.format("Invalid analyzer specified: %s", analyzer));
		}
		return analyzer;

	}

}
