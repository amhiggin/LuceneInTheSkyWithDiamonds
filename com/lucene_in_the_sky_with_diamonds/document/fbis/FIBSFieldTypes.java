package lucene_in_the_sky_with_diamonds.document.fbis;

public enum FIBSFieldTypes {
	TEXT_START("<TEXT>"), TEXT_END("</TEXT>"),
	HEADER_START("<HEADER>"), HEADER_END("</HEADER>"),
    DOC_NO_START("<DOCNO>"), DOC_NO_END("<DOCNO>"),
    DOC_START( "<DOC>"), DOC_END("</DOC>"),
    DATE1_START( "<DATE1>"), DATE1_END("</DATE1>"),
    TITLE_START( "<TI>"),TITLE_END("</TI>"),
    F_START( "<F[^>]*>"), F_END( "</F>")
    ,HT_START( "<HT>"),HT_END( "</HT>"),
    AU_START( "<AU>"),AU_END( "</AU>")
   
	;

	String fieldType;

	private FIBSFieldTypes(final String fieldType) {
		this.fieldType = fieldType;
	}

	public String getFieldType() {
		return this.fieldType;
	}

}
