package src.com.lucene_in_the_sky_with_diamonds.query;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import src.com.lucene_in_the_sky_with_diamonds.document.ValidDocumentTag;

public class QueryLoader {
	private List<QueryFieldsObject> queryCollection;
	private String fileName;

	public QueryLoader(String fileName) {
		this.fileName = fileName;
	}

	public void loadQueriesFromFile() {
		QueryFieldsObject queryInfo = new QueryFieldsObject();

		String tempTag = ValidDocumentTag.ID.getTag();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(this.fileName));
			String line = null;

			while ((line = reader.readLine()) != null) {
				String tag = getTagFromCurrentLineIfExists(line);
				if (notNewQueryField(tempTag, tag)) {
					;
				} else {
					tempTag = tag;
					queryInfo = storeLastQueryIfAllFieldsPopulated(queryInfo, tempTag);
				}
				populateAppropriateQueryField(queryInfo, tempTag, line);
			}
			// Add the final query when we get to the end
			queryCollection.add(queryInfo);
			reader.close();

		} catch (Exception e) {
			System.out.println(String.format("An exception occurred when parsing the queries from %s", fileName));
			e.printStackTrace();
		}

	}

	private boolean notNewQueryField(String tempTag, String tag) {
		return !getValidTags().contains(tag) || tag == tempTag;
	}

	private void populateAppropriateQueryField(QueryFieldsObject queryInfo, String tempTag, String line) {
		if (tempTag.equals(ValidQueryTag.ID.getTag())) {
			queryInfo.setId(line.split(" ")[1]);
		} else if (tempTag.equals(ValidQueryTag.CONTENT.getTag()) && !line.contains(ValidQueryTag.CONTENT.getTag())) {
			queryInfo.setContent(queryInfo.getContent().append(" " + line));
		}
	}

	private QueryFieldsObject storeLastQueryIfAllFieldsPopulated(QueryFieldsObject queryInfo, String tempTag) {
		if (tempTag == ValidQueryTag.ID.getTag()) {
			// Store the previous document and reset
			queryCollection.add(queryInfo);
			queryInfo = new QueryFieldsObject();
		}
		return queryInfo;
	}

	private List<String> getValidTags() {
		List<String> validTags = new ArrayList<String>();
		validTags.add(ValidQueryTag.ID.tag);
		validTags.add(ValidQueryTag.CONTENT.tag);
		return validTags;
	}

	private String getTagFromCurrentLineIfExists(String line) {
		for (ValidQueryTag tag : ValidQueryTag.values()) {
			if (line.contains(tag.getTag())) {
				return tag.getTag();
			}
		}
		return null;
	}

	public int getNumberOfQueries() {
		return queryCollection.size();
	}

	public List<QueryFieldsObject> getParsedQueries() {
		return queryCollection;
	}

	public String getFileName() {
		return this.fileName;
	}
}
