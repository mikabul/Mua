package kr.co.Mua.dao;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.Mua.Mapper.ChartMapper;
import kr.co.Mua.bean.AlbumDto;
import kr.co.Mua.bean.ArtistDto;
import kr.co.Mua.bean.SongDto;

@Repository
public class ChartDao {

	@Autowired
	private ChartMapper chartMapper;
	
	//================ 데이터 베이스에 있는지 확인하기 위함 ==============
	public SongDto chartSongMatch(String song_name, String release_date, String song_genre) {
		return chartMapper.chartSongMatch(song_name, release_date, song_genre);
	}
	
	public SongDto chartSongMatch_fast(String song_name, String artist_name, String album_name) {
		return chartMapper.chartSongMatch_fast(song_name, artist_name, album_name);
	}
	
	public ArrayList<ArtistDto> chartArtistmatch(int song_id) {
		return chartMapper.chartArtistmatch(song_id);
	}
	
	public AlbumDto chartAlbumMatch(int album_id) {
		return chartMapper.chartAlbumMatch(album_id);
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
	
	// ============== 장르별 음악 =================
	public ArrayList<SongDto> getGenreSong(String replaceSTabValue, String fTabValue, int index, int endIndex) {
		return chartMapper.getGenreSong(replaceSTabValue, fTabValue, index, endIndex);
	}

	public int getGenreSongMaxIndex(String replaceSTabValue, String fTabValue) {
		return chartMapper.getGenreSongMaxIndex(replaceSTabValue, fTabValue);
	}

	public ArrayList<SongDto> getGenreSongOST(int index, int endIndex) {
		return chartMapper.getGenreSongOST(index, endIndex);
	}

	public int getGenreSongOSTMaxIndex() {
		return chartMapper.getGenreSongOSTMaxIndex();
	}

	public ArrayList<SongDto> getOtherGenreSong(String replaceSTabValue, int index, int endIndex) {
		return chartMapper.getOtherGenreSong(replaceSTabValue, index, endIndex);
	}

	public int getOtherGenreSongMaxIndex(String replaceSTabValue) {
		return chartMapper.getOtherGenreSongMaxIndex(replaceSTabValue);
	}
}
