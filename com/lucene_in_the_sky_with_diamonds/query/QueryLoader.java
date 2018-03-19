package lucene_in_the_sky_with_diamonds.query;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class QueryLoader {
	private static List<QueryFieldsObject> queryCollection;

	public QueryLoader() {
		this.queryCollection = new ArrayList<QueryFieldsObject>();
	}

	public void loadQueriesFromFile(String fileName) {
		QueryFieldsObject queryInfo = new QueryFieldsObject();

		String tempTag = ValidQueryTag.TOP_START.getTag();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(fileName));
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
			this.queryCollection.add(queryInfo);
			queryInfo = new QueryFieldsObject();
			reader.close();

		} catch (Exception e) {
			System.out.println(String.format("An exception occurred when parsing the topics from %s", fileName));
			e.printStackTrace();
		}

	}

	private boolean notNewQueryField(String tempTag, String tag) {
		return !getValidQueryFieldTags().contains(tag) || tag == tempTag || tag == null;
	}

	private void populateAppropriateQueryField(QueryFieldsObject queryInfo, String tempTag, String line) {
		if (line.equals(null)) {
			return;
		}

		if (tempTag.equals(ValidQueryTag.NUM.getTag())) {
			queryInfo.setNum(line.split(ValidQueryTag.NUM.getTag())[1]);
		} else if (tempTag.equals(ValidQueryTag.TITLE.getTag())) {
			if (line.contains(ValidQueryTag.TITLE.getTag())) {
				queryInfo.setTitle(queryInfo.getTitle().append(line.split(ValidQueryTag.TITLE.getTag())[1]));
			} else {
				queryInfo.setTitle(queryInfo.getTitle().append(line + " "));
			}
		} else if (tempTag.equals(ValidQueryTag.NARRATIVE.getTag())) {
			if (line.contains(ValidQueryTag.NARRATIVE.getTag())) {
				return;
			} else {
				queryInfo.setNarrative(queryInfo.getNarrative().append(line + " "));
			}
		} else if (tempTag.equals(ValidQueryTag.DESCRIPTION.getTag())) {
			if (line.contains(ValidQueryTag.DESCRIPTION.getTag())) {
				return;
			} else {
				queryInfo.setDescription(queryInfo.getDescription().append(line + " "));
			}
		}
	}

	private QueryFieldsObject storeLastQueryIfAllFieldsPopulated(QueryFieldsObject queryInfo, String tempTag) {
		if (tempTag == ValidQueryTag.TOP_END.getTag()) {
			// Store the previous query and reset
			queryCollection.add(queryInfo);
			queryInfo = new QueryFieldsObject();
		}
		return queryInfo;
	}

	private List<String> getValidQueryFieldTags() {
		List<String> validTags = new ArrayList<String>();
		validTags.add(ValidQueryTag.NUM.tag);
		validTags.add(ValidQueryTag.TITLE.tag);
		validTags.add(ValidQueryTag.NARRATIVE.tag);
		validTags.add(ValidQueryTag.DESCRIPTION.tag);
		validTags.add(ValidQueryTag.TOP_END.tag);
		validTags.add(ValidQueryTag.TOP_START.tag);
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

}
