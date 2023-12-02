package kr.co.Mua.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.Mua.Mapper.ChartMapper;

@Repository
public class ChartDao {

	@Autowired
	private ChartMapper chartMapper;
	
	//================ 데이터 베이스에 있는지 확인하기 위함 ==============
	public Integer chartSongMatch(String song_name, String artist_name, String album_name) {
		return chartMapper.chartSongMatch(song_name, artist_name, album_name);
	}
	
	public Integer chartArtistmatch(int song_id, String artist_name) {
		return chartMapper.chartArtistmatch(song_id, artist_name);
	}
	
	public Integer chartAlbumMatch(int song_id) {
		return chartMapper.chartAlbumMatch(song_id);
	}
	
	//================= 데이터 베이스에 있는 값을 가져옴 =================
	// 노래 썸네일
	public String getSong_thumbnail(int song_id) {
		return chartMapper.getSong_thumbnail(song_id);
	}
	// 아티스트 썸네일
	public String getArtist_thumbnail(int artist_id) {
		return chartMapper.getArtist_thumbnail(artist_id);
	}
	// 좋아요 갯수
	public int getCount_thumbup(int song_id) {
		return chartMapper.getCount_thumbup(song_id);
	}
}
