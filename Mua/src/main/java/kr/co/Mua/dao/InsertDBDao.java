package kr.co.Mua.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.Mua.Mapper.InsertDBMapper;
import kr.co.Mua.bean.AlbumDTO;
import kr.co.Mua.bean.ArtistDTO;
import kr.co.Mua.bean.SongDTO;

@Repository
public class InsertDBDao {

	@Autowired
	private InsertDBMapper insertDBMapper;
	// 데이터베이스에 노래가 있는지?
	public SongDTO song_match(SongDTO songDTO) {
		return insertDBMapper.song_match(songDTO);
	}
	// 데이터베이스에 아티스트가 있는지?
	public ArtistDTO artist_match(ArtistDTO artistDTO) {
		return insertDBMapper.artist_match(artistDTO);
	}
	// 데이터베이스에 엘범이 있는지?
	public AlbumDTO album_match(AlbumDTO albumDTO) {
		return insertDBMapper.album_match(albumDTO);
	}
	
	public Integer song_artist_match(int song_id, int artist_id) {
		return insertDBMapper.song_artist_match(song_id, artist_id);
	}
	
	public Integer album_artist_match(int album_id, int artist_id) {
		return insertDBMapper.album_artist_match(album_id, artist_id);
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
