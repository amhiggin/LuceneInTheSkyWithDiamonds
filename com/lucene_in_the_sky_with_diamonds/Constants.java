package com.lucene_in_the_sky_with_diamonds;

import java.nio.file.Paths;

public class Constants {

	// Filepaths for document collections
	static final String APPLICATION_PATH = Paths.get("").toAbsolutePath().toString();
	static final String FT_FILEPATH = String.format("%s/input_data/ft/", APPLICATION_PATH);
	static final String FBIS_FILEPATH = String.format("%s/input_data/fbis/", APPLICATION_PATH);
	static final String FR94_FILEPATH = String.format("%s/input_data/fr94/", APPLICATION_PATH);
	static final String LATIMES_FILEPATH = String.format("%s/input_data/latimes/", APPLICATION_PATH);
	static final String TOPICS_FILEPATH = String.format("%s/input_data/topics.401-450", APPLICATION_PATH);

	// Scoring models
	static final String VSM = "vsm";
	static final String BM25 = "bm25";
	static final String BOOLEAN = "boolean";
	static final String LM_DIRICHLET = "dirichlet";
	static final String[] ALL_SCORING_MODELS = { VSM, BM25, BOOLEAN, LM_DIRICHLET };

	// Analyzers
	static final String CUSTOM_ANALYZER = "custom";
	static final String STANDARD_ANALYZER = "standard";
	static final String SIMPLE_ANALYZER = "simple";
	static final String WHITESPACE_ANALYZER = "whitespace";
	static final String STOP_ANALYZER = "stop";
	static final String[] ALL_ANALYZERS = { CUSTOM_ANALYZER, STANDARD_ANALYZER, STOP_ANALYZER, SIMPLE_ANALYZER,
			WHITESPACE_ANALYZER };

	// Strings for string formatting
	static final String USAGE_EXCEPTION_STRING = "%s args passed.\nUsage: <SCORING_MODEL> <ANALYZER>\nOptions: [%s|%s|%s|%s], [%s|%s|%s|%s|%s].\nReceived: %s, %s";

	static String usageExceptionMessage(String[] args) {
		return String.format(USAGE_EXCEPTION_STRING, args.length, VSM, BM25, BOOLEAN, LM_DIRICHLET, SIMPLE_ANALYZER,
				STANDARD_ANALYZER, WHITESPACE_ANALYZER, STOP_ANALYZER, CUSTOM_ANALYZER, args[0], args[1]);
	}

}
