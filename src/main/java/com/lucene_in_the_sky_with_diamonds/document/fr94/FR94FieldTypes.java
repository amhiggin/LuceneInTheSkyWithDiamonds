package com.lucene_in_the_sky_with_diamonds.document.fr94;
	
	public enum FR94FieldTypes {
		DOC_START("<DOC>"), DOC_END("</DOC>"), 
		DOC_NO_START("<DOCNO>"), DOC_NO_END("</DOCNO>"), 
		PARENT_START("<PARENT>"), PARENT_END("</PARENT>"),
		TEXT_START("<TEXT>"), TEXT_END("</TEXT>"),
		DEPT_START("<USDEPT>"), DEPT_END("</USDEPT>"),
		BUREAU_START("<USBUREAU>"), BUREAU_END("</USBUREAU>"),
		CFR_START("<CFRNO>"), CFR_END("</CFRNO>"),
		RIN_START("<RINDOCK>"), RIN_END("</RINDOCK>"),
		AGENCY_START("<AGENCY>"), AGENCY_END("</AGENCY>"),
		ACTION_START("<ACTION>"), ACTION_END("</ACTION>"),
		SUMMARY_START("<SUMMARY>"), SUMMARY_END("</SUMMARY>"),
		DATE_START("<DATE>"), DATE_END("</DATE>"),
		FURTHER_START("<FURTHER>"), FURTHER_END("</FURTHER>"),
		SUPP_START("<SUPPLEM>"), SUPP_END("</SUPPLEM>"),
		SIGNER_START("<SIGNER>"), SIGNER_END("</SIGNER>"),
		SIGNJOB_START("<SIGNJOB>"), SIGNJOB_END("</SIGNJOB>"),
		FRFILING_START("<FRFILING>"), FRFILING_END("</FRFILING>"),
		BILLING_START("<BILLING>"), BILLING_END("</BILLING>")
	;
		
	String fieldType;

	private FR94FieldTypes(final String fieldType) {
	  this.fieldType = fieldType;
	}

	public String getFieldType() {
	  return this.fieldType;
	}
}


