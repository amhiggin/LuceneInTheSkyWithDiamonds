package com.lucene_in_the_sky_with_diamonds.analysis;

import org.apache.lucene.analysis.CharArraySet;
import org.apache.lucene.analysis.LowerCaseFilter;
import org.apache.lucene.analysis.StopFilter;
import org.apache.lucene.analysis.StopwordAnalyzerBase;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.en.EnglishMinimalStemFilter;
import org.apache.lucene.analysis.en.EnglishPossessiveFilter;
import org.apache.lucene.analysis.en.KStemFilter;
import org.apache.lucene.analysis.en.PorterStemFilter;
import org.apache.lucene.analysis.Analyzer.TokenStreamComponents;
import org.apache.lucene.analysis.miscellaneous.ASCIIFoldingFilter;
import org.apache.lucene.analysis.miscellaneous.HyphenatedWordsFilter;
import org.apache.lucene.analysis.miscellaneous.HyphenatedWordsFilterFactory;
import org.apache.lucene.analysis.miscellaneous.TrimFilter;
import org.apache.lucene.analysis.snowball.SnowballFilter;
import org.apache.lucene.analysis.standard.StandardFilter;
import org.apache.lucene.analysis.standard.StandardTokenizer;
import org.tartarus.snowball.ext.EnglishStemmer;

public class CustomAnalyzerB extends StopwordAnalyzerBase {

	  public CustomAnalyzerB(CharArraySet stopWordSet) {
	    super(stopWordSet);
	  }
	  /*
	   *  EnglishMinimalStemFilter, HyphenatedWordsFilter, ASCIIFoldingFilter, EnglishPossessiveFilter
	   */
	  @Override
	  protected TokenStreamComponents createComponents(String arg0) {
	    final Tokenizer tokenizer = new StandardTokenizer();

	    // Add additional token filters: lowercase, English stopwords, porter
	    // stemming
	    TokenStream tokenStream = new StandardFilter(tokenizer);
	    tokenStream = new LowerCaseFilter(tokenStream);
	    tokenStream = new StopFilter(tokenStream, this.stopwords);
	    //tokenStream = new KStemFilter(tokenStream);
	    tokenStream = new EnglishMinimalStemFilter(tokenStream);
	    tokenStream = new EnglishPossessiveFilter(tokenStream);
	    tokenStream = new HyphenatedWordsFilter(tokenStream);
	    tokenStream = new ASCIIFoldingFilter(tokenStream);
	    //tokenStream = new PorterStemFilter(tokenStream);
	 
	    // tokenStream = KeywordRepeatFilter(tokenStream)
	    
	    tokenStream = new TrimFilter(tokenStream);

	    TokenStreamComponents tokenStreamComponents = new TokenStreamComponents(tokenizer, tokenStream);
	    return tokenStreamComponents;
	  }
	}
