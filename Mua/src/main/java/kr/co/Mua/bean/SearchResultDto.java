package kr.co.Mua.bean;

public class SearchResultDto {

	private SongDto songDTO;
	private AlbumDto albumDTO;
	private ArtistDto artistDTO;
	
	//----------------get, set---------------
	public SongDto getSongDTO() {
		return songDTO;
	}
	public void setSongDTO(SongDto songDTO) {
		this.songDTO = songDTO;
	}
	public AlbumDto getAlbumDTO() {
		return albumDTO;
	}
	public void setAlbumDTO(AlbumDto albumDTO) {
		this.albumDTO = albumDTO;
	}
	public ArtistDto getArtistDTO() {
		return artistDTO;
	}
	public void setArtistDTO(ArtistDto artistDTO) {
		this.artistDTO = artistDTO;
	}
	
}
