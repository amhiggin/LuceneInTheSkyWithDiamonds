package com.lucene_in_the_sky_with_diamonds.document.ft;

public enum FTFieldTypes {
	TEXT_START("<TEXT>"), TEXT_END("</TEXT>"), HEADLINE_START("<HEADLINE>"), HEADLINE_END("</HEADLINE>"), BYLINE_START(
			"<BYLINE>"), BYLINE_END("</BYLINE>"), CORRECTION_START(
					"<CORRECTION>"), CORRECTION_END("</CORRECTION>"), CORRECTION_DATE_START(
							"<CORRECTION-DATE>"), CORRECTION_DATE_END("</CORRECTION_DATE>"), DOC_NO_START(
									"<DOCNO>"), DOC_ID_START("<DOCID>"), DOC_START("<DOC>"), DOC_END("</DOC>");

	String fieldType;

	private FTFieldTypes(final String fieldType) {
		this.fieldType = fieldType;
	}

	public String getFieldType() {
		return this.fieldType;
	}

}
