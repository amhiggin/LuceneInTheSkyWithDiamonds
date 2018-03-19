package lucene_in_the_sky_with_diamonds.query;

public enum ValidQueryTag {

	TOP_START("<top>"), TOP_END("</top>"), NUM("<num> Number: "), TITLE("<title>"), DESCRIPTION(
			"<desc> Description: "), NARRATIVE("<narr> Narrative: ");

	String tag;

	ValidQueryTag(final String tag) {
		this.tag = tag;
	}

	public String getTag() {
		return this.tag;
	}
}
