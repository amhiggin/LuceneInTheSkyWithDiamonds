package com.lucene_in_the_sky_with_diamonds.analysis;

import org.apache.lucene.analysis.CharArraySet;
import org.apache.lucene.analysis.LowerCaseFilter;
import org.apache.lucene.analysis.StopFilter;
import org.apache.lucene.analysis.StopwordAnalyzerBase;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.en.PorterStemFilter;
import org.apache.lucene.analysis.miscellaneous.TrimFilter;
import org.apache.lucene.analysis.standard.StandardFilter;
import org.apache.lucene.analysis.standard.StandardTokenizer;
import org.apache.lucene.analysis.snowball.SnowballFilter;
import org.tartarus.snowball.ext.EnglishStemmer;
public class CustomAnalyzer extends StopwordAnalyzerBase {

  public CustomAnalyzer(CharArraySet stopWordSet) {
    super(stopWordSet);
  }

  @Override
  protected TokenStreamComponents createComponents(String arg0) {
    final Tokenizer tokenizer = new StandardTokenizer();

    // Add additional token filters: lowercase, English stopwords, porter
    // stemming
    TokenStream tokenStream = new StandardFilter(tokenizer);
    tokenStream = new LowerCaseFilter(tokenStream);
    tokenStream = new StopFilter(tokenStream, this.stopwords);
    //tokenStream = new SnowballFilter(tokenStream, new EnglishStemmer());
    tokenStream = new PorterStemFilter(tokenStream);
    tokenStream = new TrimFilter(tokenStream);

    TokenStreamComponents tokenStreamComponents = new TokenStreamComponents(tokenizer, tokenStream);
    return tokenStreamComponents;
  }
}
