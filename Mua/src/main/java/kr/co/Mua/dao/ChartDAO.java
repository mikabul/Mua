package kr.co.Mua.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.Mua.Mapper.ChartMapper;

@Repository
public class ChartDAO {

	@Autowired
	private ChartMapper chartMapper;
	
	public Integer chartSongMatch(String song_name, String artist_name, String album_name) {
		return chartMapper.chartSongMatch(song_name, artist_name, album_name);
	}
	
	public Integer chartArtistmatch(int song_id, String artist_name) {
		return chartMapper.chartArtistmatch(song_id, artist_name);
	}
	
	public Integer chartAlbumMatch(int song_id) {
		return chartMapper.chartAlbumMatch(song_id);
	}
	
}
