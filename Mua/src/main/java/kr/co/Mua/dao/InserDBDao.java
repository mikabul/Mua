package kr.co.Mua.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.Mua.Mapper.InsertDBMapper;
import kr.co.Mua.bean.AlbumDTO;
import kr.co.Mua.bean.ArtistDTO;
import kr.co.Mua.bean.SongDTO;

@Repository
public class InserDBDao {

	@Autowired
	private InsertDBMapper insertDBMapper;

	public SongDTO getSongDTO(SongDTO songDTO, ArtistDTO artistDTO) {
		return insertDBMapper.song_search(songDTO, artistDTO);
	}

	public ArtistDTO getArtistDTO(ArtistDTO artistDTO) {
		return insertDBMapper.artist_search(artistDTO);
	}

	public AlbumDTO getAlbumDTO(AlbumDTO albumDTO, ArtistDTO artistDTO) {
		return insertDBMapper.album_search(albumDTO, artistDTO);
	}

	public void insertSong(SongDTO songDTO) {
		insertDBMapper.insert_song(songDTO);
	}

	public void insertArtist(ArtistDTO artistDTO) {
		insertDBMapper.insert_artist(artistDTO);
	}

	public void insertAlbum(AlbumDTO albumDTO) {
		insertDBMapper.insert_albem(albumDTO);
	}
}
