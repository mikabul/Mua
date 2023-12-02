package kr.co.Mua.bean;

public class AlbumDTO {

	private int album_id;
	private String album_name;
	private String release_date;
	private String album_genre;
	private String album_agency;
	private int artist_num;
	
	//----------get,set------------
	public int getAlbum_id() {
		return album_id;
	}
	public void setAlbum_id(int album_id) {
		this.album_id = album_id;
	}
	public String getAlbum_name() {
		return album_name;
	}
	public void setAlbum_name(String album_name) {
		this.album_name = album_name;
	}
	public String getRelease_date() {
		return release_date;
	}
	public void setRelease_date(String release_date) {
		this.release_date = release_date;
	}
	public String getAlbum_genre() {
		return album_genre;
	}
	public void setAlbum_genre(String album_genre) {
		this.album_genre = album_genre;
	}
	public String getAlbum_agency() {
		return album_agency;
	}
	public void setAlbum_agency(String album_agency) {
		this.album_agency = album_agency;
	}
	public int getArtist_num() {
		return artist_num;
	}
	public void setArtist_num(int artist_num) {
		this.artist_num = artist_num;
	}
	
}
