package kr.co.Mua.bean;

public class ViewedSongDto {

	private int user_num;
	private int song_id;
	private String viewed_date;
	
	//--------- get,set ----------
	public int getUser_num() {
		return user_num;
	}
	public void setUser_num(int user_num) {
		this.user_num = user_num;
	}
	public int getSong_id() {
		return song_id;
	}
	public void setSong_id(int song_id) {
		this.song_id = song_id;
	}
	public String getViewed_date() {
		return viewed_date;
	}
	public void setViewed_date(String viewed_date) {
		this.viewed_date = viewed_date;
	}
	
}
