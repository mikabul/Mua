package kr.co.Mua.bean;

public class ArtistDTO {

	private int artist_num;
	private String artist_name;
	private String artist_date;
	private String artist_type;
	private String agency;
	
	//----------get, set----------
	public int getArtist_num() {
		return artist_num;
	}
	public void setArtist_num(int artist_num) {
		this.artist_num = artist_num;
	}
	public String getArtist_name() {
		return artist_name;
	}
	public void setArtist_name(String artist_name) {
		this.artist_name = artist_name;
	}
	public String getArtist_date() {
		return artist_date;
	}
	public void setArtist_date(String artist_date) {
		this.artist_date = artist_date;
	}
	public String getArtist_type() {
		return artist_type;
	}
	public void setArtist_type(String artist_type) {
		this.artist_type = artist_type;
	}
	public String getAgency() {
		return agency;
	}
	public void setAgency(String agency) {
		this.agency = agency;
	}
	
}
