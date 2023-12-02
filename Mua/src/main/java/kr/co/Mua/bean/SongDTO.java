package kr.co.Mua.bean;

public class SongDTO {

	private int song_id;
	private String song_name;
	private String sing_genre;
	private String release_date;
	private String lyrics;
	private int views;
	private int artist_num;
	private int album_num;

	//-----------get, set----------------
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

	public String getSing_genre() {
		return sing_genre;
	}

	public void setSing_genre(String sing_genre) {
		this.sing_genre = sing_genre;
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

	public int getArtist_num() {
		return artist_num;
	}

	public void setArtist_num(int artist_num) {
		this.artist_num = artist_num;
	}

	public int getAlbum_num() {
		return album_num;
	}

	public void setAlbum_num(int album_num) {
		this.album_num = album_num;
	}
}
