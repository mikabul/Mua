package kr.co.Mua.bean;

import java.util.HashMap;
import java.util.Map;

public class ArtistDto {

	private int artist_id;
	private String artist_name;
	private String artist_date;
	private String artist_type;
	private String artist_thumbnail;
	private String artist_agency;
	private String artist_nation;
	
	private int artist_thumbup;
	private String thumbup_date;
	private String member;
	
	
	//------------기본 생성자------------
	public ArtistDto() {
		artist_id = 0;
		artist_name = "-";
		artist_date = "-";
		artist_type = "-";
		artist_thumbnail = "-";
		artist_agency = "-";
		artist_nation = "-";
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

	public String getArtist_nation() {
		return artist_nation;
	}

	public void setArtist_nation(String artist_nation) {
		this.artist_nation = artist_nation;
	}

	public int getArtist_thumbup() {
		return artist_thumbup;
	}

	public void setArtist_thumbup(int artist_thumbup) {
		this.artist_thumbup = artist_thumbup;
	}

	public String getMember() {
		return member;
	}

	public void setMember(String member) {
		this.member = member;
	}

	public String getArtist_thumbup_date() {
		return thumbup_date;
	}

	public void setArtist_thumbup_date(String thumbup_date) {
		this.thumbup_date = thumbup_date;
	}
	
	

}
