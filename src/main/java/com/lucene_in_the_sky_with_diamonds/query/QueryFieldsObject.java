package com.lucene_in_the_sky_with_diamonds.query;

public class QueryFieldsObject {

	private String num;
	private StringBuilder title;
	private StringBuilder description;
	private StringBuilder narrative;

	public QueryFieldsObject() {
		this.setNum("");
		this.setTitle(new StringBuilder());
		this.setDescription(new StringBuilder());
		this.setNarrative(new StringBuilder());
	}

	public StringBuilder getTitle() {
		return this.title;
	}

	public void setTitle(StringBuilder title) {
		this.title = title;
	}

	public StringBuilder getDescription() {
		return this.description;
	}

	public void setDescription(StringBuilder description) {
		this.description = description;
	}

	public StringBuilder getNarrative() {
		return this.narrative;
	}

	public void setNarrative(StringBuilder narrative) {
		this.narrative = narrative;
	}

	public String getNum() {
		return this.num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(String.format("Num: %s\n", this.getNum()));
		builder.append(String.format("Title: %s\n", this.getTitle()));
		builder.append(String.format("Description: %s\n", this.getDescription()));
		builder.append(String.format("Num: %s\n", this.getNarrative()));
		return builder.toString();
	}

}
