package src.com.lucene_in_the_sky_with_diamonds.document;

/*
 * Documents are a collection of fields. Each field is treated differently. This intermediary bean
 * object allows for the separation and building of fields before being incorporated into a
 * document.
 */

public class DocumentFieldsObject {
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getAuthors() {
    return authors;
  }

  public void setAuthors(String authors) {
    this.authors = authors;
  }

  public String getBibliography() {
    return bibliography;
  }

  public void setBibliography(String bibliography) {
    this.bibliography = bibliography;
  }

  public StringBuilder getTitle() {
    return title;
  }

  public void setTitle(StringBuilder title) {
    this.title = title;
  }

  public StringBuilder getContent() {
    return content;
  }

  public void setContent(StringBuilder content) {
    this.content = content;
  }

  public String id;
  public String authors;
  public String bibliography;
  public StringBuilder title;
  public StringBuilder content;

  public DocumentFieldsObject() {
    this.id = "";
    this.authors = "";
    this.bibliography = "";
    this.title = new StringBuilder();
    this.content = new StringBuilder();
  }


}
