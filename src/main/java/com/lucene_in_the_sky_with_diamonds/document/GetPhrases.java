package com.lucene_in_the_sky_with_diamonds.document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.en.EnglishAnalyzer;
import org.apache.lucene.analysis.shingle.ShingleFilter;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;

import com.lucene_in_the_sky_with_diamonds.analysis.ShingleAnalyzer;

public class GetPhrases {
	public List<String> getPhrases(String body) throws IOException {
		List<String> phrases = new ArrayList<String>();
		Analyzer analyze = new ShingleAnalyzer(StandardAnalyzer.ENGLISH_STOP_WORDS_SET);
		TokenStream t = analyze.tokenStream("Text", body);
		t = new ShingleFilter(t, 2, 3);
		CharTermAttribute charTerm = t.getAttribute(CharTermAttribute.class);
		t.reset();
		while (t.incrementToken()) {
			String x = charTerm.toString();
			if (x.indexOf(' ') >= 0 && x.indexOf('_') == -1)
				if(!phrases.contains(body)) {
					phrases.add(x);
					//System.out.println(x);
				}
		}
		t.end();
		t.close();
		return phrases;
	}
}
