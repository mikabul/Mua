package kr.co.Mua.bean;

public class SongDTO {

	private int song_id;
	private String song_name;
	private String song_genre;
	private String release_date;
	private String lyrics;
	private int views;
	private String song_thumbnail;
	private int album_id;

	// /search/song_info를 위한 변수
	private String artist_name;
	private int artist_id;
	private String album_name;

	// --------기본 생성자--------
	public SongDTO() {
		song_id = 0;
		song_name = "-";
		song_genre = "-";
		release_date = "-";
		lyrics = "-";
		views = 0;
		song_thumbnail = "-";
		album_id = 0;
	}

	// -----------get, set-----------
	public int getSong_id() {
		return song_id;
	}

	public void setSong_id(int song_id) {
		this.song_id = song_id;
	}

	public String getSong_name() {
		return song_name;
	}

	public void setSong_name(String song_name) {
		this.song_name = song_name;
	}

	public String getSong_genre() {
		return song_genre;
	}

	public void setSong_genre(String song_genre) {
		this.song_genre = song_genre;
	}

	public String getRelease_date() {
		return release_date;
	}

	public void setRelease_date(String release_date) {
		this.release_date = release_date;
	}

	public String getLyrics() {
		return lyrics;
	}

	public void setLyrics(String lyrics) {
		this.lyrics = lyrics;
	}

	public int getViews() {
		return views;
	}

	public void setViews(int views) {
		this.views = views;
	}

	public String getSong_thumbnail() {
		return song_thumbnail;
	}

	public void setSong_thumbnail(String song_thumbnail) {
		this.song_thumbnail = song_thumbnail;
	}

	public int getAlbum_id() {
		return album_id;
	}

	public void setAlbum_id(int album_id) {
		this.album_id = album_id;
	}

	public String getArtist_name() {
		return artist_name;
	}

	public void setArtist_name(String artist_name) {
		this.artist_name = artist_name;
	}

	public int getArtist_id() {
		return artist_id;
	}

	public void setArtist_id(int artist_id) {
		this.artist_id = artist_id;
	}

	public String getAlbum_name() {
		return album_name;
	}

	public void setAlbum_name(String album_name) {
		this.album_name = album_name;
	}

}
