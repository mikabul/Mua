package kr.co.Mua.bean;

public class AlbumDTO {

	private int album_id;
	private String album_name;
	private String release_date;
	private String album_genre;
	private String album_publisher;
	private String album_agency;
	private String album_thumbnail;
	
	//----------기본 생성자-------------
	public AlbumDTO() {
		album_id = 0;
		album_name = "-";
		release_date = "-";
		album_genre = "-";
		album_publisher = "-";
		album_agency = "-";
		album_thumbnail = "-";
	}
	
	//-----------get, set------------
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
	public String getAlbum_publisher() {
		return album_publisher;
	}
	public void setAlbum_publisher(String album_publisher) {
		this.album_publisher = album_publisher;
	}
	public String getAlbum_agency() {
		return album_agency;
	}
	public void setAlbum_agency(String album_agency) {
		this.album_agency = album_agency;
	}
	public String getAlbum_thumbnail() {
		return album_thumbnail;
	}
	public void setAlbum_thumbnail(String album_thumbnail) {
		this.album_thumbnail = album_thumbnail;
	}

}
