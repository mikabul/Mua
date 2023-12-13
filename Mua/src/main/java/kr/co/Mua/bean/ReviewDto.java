package kr.co.Mua.bean;

public class ReviewDto {

    private int review_num;
    private int user_num;
    private int type_id;
    private double review_point;
    private String review_content;
    private String review_date;
    private String suggestion;
    private String flag;

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
    public String getSuggestion() {
        return suggestion;
    }
    public void setSuggestion(String suggestion) {
        this.suggestion = suggestion;
    }
    public String getFlag() {
        return flag;
    }
    public void setFlag(String flag) {
        this.flag = flag;
    }


}