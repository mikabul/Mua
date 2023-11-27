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

	public SongDTO song_match(SongDTO songDTO) {
		return insertDBMapper.song_match(songDTO);
	}

	public ArtistDTO artist_match(ArtistDTO artistDTO) {
		return insertDBMapper.artist_match(artistDTO);
	}

	public AlbumDTO album_match(AlbumDTO albumDTO) {
		return insertDBMapper.album_match(albumDTO);
	}

	public void insert_song(SongDTO songDTO) {
		insertDBMapper.insert_song(songDTO);
	}

	public void insert_artist(ArtistDTO artistDTO) {
		insertDBMapper.insert_artist(artistDTO);
	}

	public void insert_album(AlbumDTO albumDTO) {
		insertDBMapper.insert_album(albumDTO);
	}
	
	public void insert_song_artist(int song_id, int artist_id) {
		insertDBMapper.insert_song_artist(song_id, artist_id);
	}
	
	public void insert_album_artist(int album_id, int artist_id) {
		insertDBMapper.insert_album_artist(album_id, artist_id);
	}
}
