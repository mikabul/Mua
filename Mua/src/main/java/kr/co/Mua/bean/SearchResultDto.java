package kr.co.Mua.bean;

import java.util.ArrayList;

public class SearchResultDto {

	private SongDto songDto;
	private ArrayList<ArtistDto> artistList;
	private ArtistDto artistDto;
	private AlbumDto albumDto;
	
	//----------------get, set---------------
	public SongDto getSongDto() {
		return songDto;
	}
	public void setSongDto(SongDto songDto) {
		this.songDto = songDto;
	}
	public ArrayList<ArtistDto> getArtistList() {
		return artistList;
	}
	public void setArtistList(ArrayList<ArtistDto> artistList) {
		this.artistList = artistList;
	}
	public ArtistDto getArtistDto() {
		return artistDto;
	}
	public void setArtistDto(ArtistDto artistDto) {
		this.artistDto = artistDto;
	}
	public AlbumDto getAlbumDto() {
		return albumDto;
	}
	public void setAlbumDto(AlbumDto albumDto) {
		this.albumDto = albumDto;
	}
	
}
