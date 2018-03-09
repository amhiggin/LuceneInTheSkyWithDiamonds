package src.com.lucene_in_the_sky_with_diamonds.relevance;

public class RelevanceObject {
  int queryNumber;
  int documentNumber;
  int relevanceCode;


  public RelevanceObject(int queryNumber,  int documentNumber,  int relevanceCode) {
    this.queryNumber = queryNumber;
    this.documentNumber = documentNumber;
    this.relevanceCode = relevanceCode;
  }
  
  public int getQueryNumber() {
    return this.queryNumber;
  }
  
  public int getRelevanceCode() {
    return this.relevanceCode;
  }
  
  public int getDocumentNumber() {
    return this.documentNumber;
  }
  
}
