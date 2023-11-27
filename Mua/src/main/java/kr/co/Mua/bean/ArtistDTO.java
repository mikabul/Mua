package kr.co.Mua.bean;

public class ArtistDTO {

	private int artist_id;
	private String artist_name;
	private String artist_date;
	private String artist_type;
	private String artist_thumbnail;
	private String artist_agency;
	
	//------------기본 생성자------------
	public ArtistDTO() {
		artist_id = 0;
		artist_name = "-";
		artist_date = "-";
		artist_type = "-";
		artist_thumbnail = "-";
		artist_agency = "-";
	}
	
	//-----------get, set------------
	public int getArtist_id() {
		return artist_id;
	}
	public void setArtist_id(int artist_id) {
		this.artist_id = artist_id;
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
	public String getArtist_thumbnail() {
		return artist_thumbnail;
	}
	public void setArtist_thumbnail(String artist_thumbnail) {
		this.artist_thumbnail = artist_thumbnail;
	}
	public String getArtist_agency() {
		return artist_agency;
	}
	public void setArtist_agency(String artist_agency) {
		this.artist_agency = artist_agency;
	}

}
