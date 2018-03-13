package com.lucene_in_the_sky_with_diamonds.document;

public enum ValidDocumentTag {
	
	ID(".I"), AUTHORS(".A"), BIBLIOGRAPHY(".B"), CONTENT(".W"), TITLE(".T");
	
	String tag;

	ValidDocumentTag(final String tag) {
		this.tag = tag;
	}
	
	public String getTag() {
		return this.tag;
	}
}
