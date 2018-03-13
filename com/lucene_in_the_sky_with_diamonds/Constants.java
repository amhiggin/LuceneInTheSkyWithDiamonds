package com.lucene_in_the_sky_with_diamonds;

public class Constants {

	// Scoring models
	public static final String VSM = "vsm";
	public static final String BM25 = "bm25";
	public static final String BOOLEAN = "boolean";
	public static final String LM_DIRICHLET = "dirichlet";
	public static final String[] ALL_SCORING_MODELS = { VSM, BM25, BOOLEAN, LM_DIRICHLET };

	// Analyzers
	public static final String CUSTOM_ANALYZER = "custom";
	public static final String STANDARD_ANALYZER = "standard";
	public static final String SIMPLE_ANALYZER = "simple";
	public static final String WHITESPACE_ANALYZER = "whitespace";
	public static final String STOP_ANALYZER = "stop";
	public static final String[] ALL_ANALYZERS = { CUSTOM_ANALYZER, STANDARD_ANALYZER, STOP_ANALYZER, SIMPLE_ANALYZER,
			WHITESPACE_ANALYZER };

	// Strings for string formatting
	public static final String USAGE_EXCEPTION_STRING = "%s args passed.\nUsage: <SCORING_MODEL> <ANALYZER>\nOptions: [%s|%s|%s|%s], [%s|%s|%s|%s|%s].\nReceived: %s, %s";

	public static String usageExceptionMessage(String[] args) {
		return String.format(USAGE_EXCEPTION_STRING, args.length, VSM, BM25, BOOLEAN, LM_DIRICHLET, SIMPLE_ANALYZER,
				STANDARD_ANALYZER, WHITESPACE_ANALYZER, STOP_ANALYZER, CUSTOM_ANALYZER, args[0], args[1]);
	}
}
