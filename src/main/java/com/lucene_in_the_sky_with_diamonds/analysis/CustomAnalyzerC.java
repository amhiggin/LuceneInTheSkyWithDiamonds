package com.lucene_in_the_sky_with_diamonds.analysis;

import org.apache.lucene.analysis.CharArraySet;
import org.apache.lucene.analysis.LowerCaseFilter;
import org.apache.lucene.analysis.StopFilter;
import org.apache.lucene.analysis.StopwordAnalyzerBase;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.Analyzer.TokenStreamComponents;
import org.apache.lucene.analysis.en.EnglishPossessiveFilter;
import org.apache.lucene.analysis.en.KStemFilter;
import org.apache.lucene.analysis.miscellaneous.ASCIIFoldingFilter;
import org.apache.lucene.analysis.miscellaneous.HyphenatedWordsFilter;
import org.apache.lucene.analysis.miscellaneous.KeywordRepeatFilter;
import org.apache.lucene.analysis.miscellaneous.TrimFilter;
import org.apache.lucene.analysis.standard.StandardFilter;
import org.apache.lucene.analysis.standard.StandardTokenizer;

public class CustomAnalyzerC extends StopwordAnalyzerBase  {
	 public CustomAnalyzerC(CharArraySet stopWordSet) {
		    super(stopWordSet);
		  }
	 	/*
	 	 *  KStemFilter, EnglishPossessiveFilter, HyphenatedWordsFilter, ASCIIFoldingFilter
	 	 */

		  @Override
		  protected TokenStreamComponents createComponents(String arg0) {
		    final Tokenizer tokenizer = new StandardTokenizer();

		    // Add additional token filters: lowercase, English stopwords, porter
		    // stemming
		    TokenStream tokenStream = new StandardFilter(tokenizer);
		    tokenStream = new LowerCaseFilter(tokenStream);
		    tokenStream = new StopFilter(tokenStream, this.stopwords);
		    tokenStream = new KStemFilter(tokenStream);
		    tokenStream = new EnglishPossessiveFilter(tokenStream);
		    tokenStream = new HyphenatedWordsFilter(tokenStream);
		    tokenStream = new ASCIIFoldingFilter(tokenStream);
		    tokenStream = new KeywordRepeatFilter(tokenStream);
		    //tokenStream = new PorterStemFilter(tokenStream);
		    //tokenStream = new EnglishMinimalStemFilter(tokenStream)
		    
		    tokenStream = new TrimFilter(tokenStream);

		    TokenStreamComponents tokenStreamComponents = new TokenStreamComponents(tokenizer, tokenStream);
		    return tokenStreamComponents;
		  }
}
