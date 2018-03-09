package src.com.lucene_in_the_sky_with_diamonds.query;

public enum ValidQueryTag {

	ID(".I"), CONTENT(".W");
	
	String tag;

	ValidQueryTag(final String tag) {
		this.tag = tag;
	}
	
	public String getTag() {
		return this.tag;
	}
}
