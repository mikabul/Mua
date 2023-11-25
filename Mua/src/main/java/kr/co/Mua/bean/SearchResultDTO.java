package kr.co.Mua.bean;

public class SearchResultDTO {

	private SongDTO songDTO;
	private AlbumDTO albumDTO;
	private ArtistDTO artistDTO;
	
	//----------------get, set---------------
	public SongDTO getSongDTO() {
		return songDTO;
	}
	public void setSongDTO(SongDTO songDTO) {
		this.songDTO = songDTO;
	}
	public AlbumDTO getAlbumDTO() {
		return albumDTO;
	}
	public void setAlbumDTO(AlbumDTO albumDTO) {
		this.albumDTO = albumDTO;
	}
	public ArtistDTO getArtistDTO() {
		return artistDTO;
	}
	public void setArtistDTO(ArtistDTO artistDTO) {
		this.artistDTO = artistDTO;
	}
	
}
