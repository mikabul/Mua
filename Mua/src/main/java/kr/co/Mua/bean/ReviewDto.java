package kr.co.Mua.bean;

public class ReviewDto {

	private int review_num;
	private int user_num;
	private int type_id;
	private double review_point;
	private String review_content;
	private String review_date;
	private int suggestion;
	private String flag;
	
	private String user_name;
	private String user_id;
	private String report_date;
	private int report_num;
	
	//------------ get, set ---------------
	public int getReview_num() {
		return review_num;
	}
	public void setReview_num(int review_num) {
		this.review_num = review_num;
	}
	public int getUser_num() {
		return user_num;
	}
	public void setUser_num(int user_num) {
		this.user_num = user_num;
	}
	public int getType_id() {
		return type_id;
	}
	public void setType_id(int type_id) {
		this.type_id = type_id;
	}
	public double getReview_point() {
		return review_point;
	}
	public void setReview_point(double review_point) {
		this.review_point = review_point;
	}
	public String getReview_content() {
		return review_content;
	}
	public void setReview_content(String review_content) {
		this.review_content = review_content;
	}
	public String getReview_date() {
		return review_date;
	}
	public void setReview_date(String review_date) {
		this.review_date = review_date;
	}
	public int getSuggestion() {
		return suggestion;
	}
	public void setSuggestion(int suggestion) {
		this.suggestion = suggestion;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getReport_date() {
		return report_date;
	}
	public void setReport_date(String report_date) {
		this.report_date = report_date;
	}
	public int getReport_num() {
		return report_num;
	}
	public void setReport_num(int report_num) {
		this.report_num = report_num;
	}
	
	
}
