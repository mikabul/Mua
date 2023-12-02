package kr.co.Mua.dao;

import java.util.ArrayList;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.Mua.Mapper.SearchMapper;
import kr.co.Mua.bean.ArtistDto;
import kr.co.Mua.bean.SongDto;

@Repository
public class SearchDao {

	@Autowired
	private SearchMapper searchMapper;
	
	public SongDto getSong_info(int song_id) {
		return searchMapper.getSong_info(song_id);
	}
	
	public ArrayList<ArtistDto> getBriefArtist(int song_id){
		return searchMapper.getBriefArtist(song_id);
	}
	
	public ArrayList<SongDto> getMoreSong_info(int artist_id){
		return searchMapper.getMoreSong_info(artist_id);
	}
}
