package lucene_in_the_sky_with_diamonds.relevance;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class RelevanceLoader {

  private ArrayList<RelevanceObject> relevanceData;
  private String inputFilePath;
  private String outputFilePath;

  public RelevanceLoader(String inputfilePath, String outputFilePath) {
    this.inputFilePath = inputfilePath;
    this.outputFilePath = outputFilePath;
  }

  public void loadRelevanceDataFromFile() {
    try {
      BufferedReader reader = new BufferedReader(new FileReader(this.inputFilePath));
      PrintWriter writer = new PrintWriter(this.outputFilePath, "UTF-8");
      String line = null;

      while ((line = reader.readLine()) != null) {
        int[] values = getRelevanceValuesForSingleLine(line);
        RelevanceObject relevanceDatum = new RelevanceObject(values[0], values[1], values[2]);
        writer.println(relevanceDatum.queryNumber + " 0 " + relevanceDatum.getDocumentNumber() + " "
            + relevanceDatum.relevanceCode);
      }
      reader.close();
      writer.close();
    } catch (Exception e) {
      System.out.println(String.format("An exception occurred when parsing the relevances from %s",
          this.inputFilePath));
      e.printStackTrace();
    }
  }

  private int[] getRelevanceValuesForSingleLine(String line) throws IOException {
    String[] numIntegersPerLine = line.split("\\s+");
    int values[] = new int[numIntegersPerLine.length];
    for (int i = 0; i < numIntegersPerLine.length; i++) {
      values[i] = Integer.parseInt(numIntegersPerLine[i]);
    }
    return values;
  }


  public List<RelevanceObject> getParsedRelevances() {
    return relevanceData;
  }

  public int numberOfParsedRelevances() {
    return relevanceData.size();
  }

}
