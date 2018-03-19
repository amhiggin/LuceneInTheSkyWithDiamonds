package lucene_in_the_sky_with_diamonds.document.fbis;

public class FBISFieldsObject {
	
	private String docNo = "";
	private String text = "";
	private String date = "";
	private String title ="";
	private String ftags = "";
	private String unknownSect = "";
	
	
 
	public String getUnknownSect() {
		return unknownSect;
	}
	public void setUnknownSect(String unknownSect) {
		this.unknownSect = this.unknownSect + unknownSect;
	}
	public String getFtags() {
		return ftags;
	}
	public void setFtags(String ftags) {
		// appends all the f tags into one
		this.ftags = this.ftags +ftags ;
	}
	public String getDocNo() {
		return docNo;
	}
	public void setDocNo(String docNo) {
		this.docNo = docNo;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	 
 

}
