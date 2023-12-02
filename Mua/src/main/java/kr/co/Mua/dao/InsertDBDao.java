package kr.co.Mua.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.Mua.Mapper.InsertDBMapper;
import kr.co.Mua.bean.AlbumDto;
import kr.co.Mua.bean.ArtistDto;
import kr.co.Mua.bean.SongDto;

@Repository
public class InsertDBDao {

	@Autowired
	private InsertDBMapper insertDBMapper;
	
	//================= 존재 여부 =====================
	// 데이터베이스에 노래가 있는지?
	public SongDto song_match(SongDto songDTO) {
		return insertDBMapper.song_match(songDTO);
	}
	// 데이터베이스에 아티스트가 있는지?
	public ArtistDto artist_match(ArtistDto artistDTO) {
		return insertDBMapper.artist_match(artistDTO);
	}
	// 데이터베이스에 앨범이 있는지?
	public AlbumDto album_match(AlbumDto albumDTO) {
		return insertDBMapper.album_match(albumDTO);
	}
	// 데이터베이스에 노래와 아티스트가 연결되어있는지
	public Integer song_artist_match(int song_id, int artist_id) {
		return insertDBMapper.song_artist_match(song_id, artist_id);
	}
	// 데이터베이스에 앨범과 아티스트가 연결되어있는지
	public Integer album_artist_match(int album_id, int artist_id) {
		return insertDBMapper.album_artist_match(album_id, artist_id);
	}
	
	//================= 저장 ==================
	// 데이터베이스에 노래 저장
	public void insert_song(SongDto songDTO) {
		insertDBMapper.insert_song(songDTO);
	}
	// 데이터베이스에 아티스트 저장
	public void insert_artist(ArtistDto artistDTO) {
		insertDBMapper.insert_artist(artistDTO);
	}
	// 데이터베이스에 앨범 저장
	public void insert_album(AlbumDto albumDTO) {
		insertDBMapper.insert_album(albumDTO);
	}
	// 데이터베이스 노래 아티스트 연결
	public void insert_song_artist(int song_id, int artist_id) {
		insertDBMapper.insert_song_artist(song_id, artist_id);
	}
	// 데이터베이스 앨범 아티스트 연결
	public void insert_album_artist(int album_id, int artist_id) {
		insertDBMapper.insert_album_artist(album_id, artist_id);
	}
	
	
	//================ 썸네일 ===============
	public void insert_song_thumbnail(int song_id, String song_thumbnail) {
		insertDBMapper.insert_song_thumbnail(song_id, song_thumbnail);
	}
	
	public void insert_artist_thumbnail(int artist_id, String artist_thumbnail) {
		insertDBMapper.insert_artist_thumbnail(artist_id, artist_thumbnail);
	}
	
	public void insert_album_thumbnail(int album_id, String album_thumbnail) {
		insertDBMapper.insert_album_thumbnail(album_id, album_thumbnail);
	}
	
	//================ 가사 =================
	public void insert_lyrics(int song_id, String lyrics) {
		insertDBMapper.insert_lyrics(song_id, lyrics);
	}
}
