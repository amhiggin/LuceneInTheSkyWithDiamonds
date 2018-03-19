package lucene_in_the_sky_with_diamonds.document;

public enum CollectionType {
	FT("ft"), LA("latimes"), FBIS("fbis"), FR94("fr94");

	String collection;

	private CollectionType(final String collection) {
		this.collection = collection;
	}

	public String getCollection() {
		return this.collection;
	}
}
