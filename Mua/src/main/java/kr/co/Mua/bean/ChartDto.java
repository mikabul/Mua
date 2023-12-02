package kr.co.Mua.bean;

import java.io.Serializable;

public class ChartDto implements Serializable {
	static final long serialVersionUID = 1L;

	private String song_name;
	private int song_id;
	private String artist_name[];
	private int artist_id[];
	private String album_name;
	private int album_id;
	private String song_thumbnail;
	private String artist_thumbnail[];
	private int song_thumbup;
	
	//-----------get, set------------
	public String getSong_name() {
		return song_name;
	}
	public void setSong_name(String song_name) {
		this.song_name = song_name;
	}
	public int getSong_id() {
		return song_id;
	}
	public void setSong_id(int song_id) {
		this.song_id = song_id;
	}
	public String[] getArtist_name() {
		return artist_name;
	}
	public void setArtist_name(String[] artist_name) {
		this.artist_name = artist_name;
	}
	public int[] getArtist_id() {
		return artist_id;
	}
	public void setArtist_id(int[] artist_id) {
		this.artist_id = artist_id;
	}
	public String getAlbum_name() {
		return album_name;
	}
	public void setAlbum_name(String album_name) {
		this.album_name = album_name;
	}
	public int getAlbum_id() {
		return album_id;
	}
	public void setAlbum_id(int album_id) {
		this.album_id = album_id;
	}
	public String getSong_thumbnail() {
		return song_thumbnail;
	}
	public void setSong_thumbnail(String song_thumbnail) {
		this.song_thumbnail = song_thumbnail;
	}
	public int getSong_thumbup() {
		return song_thumbup;
	}
	public void setSong_thumbup(int song_thumbup) {
		this.song_thumbup = song_thumbup;
	}
	public String[] getArtist_thumbnail() {
		return artist_thumbnail;
	}
	public void setArtist_thumbnail(String[] artist_thumbnail) {
		this.artist_thumbnail = artist_thumbnail;
	}
	
}
