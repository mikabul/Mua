package kr.co.Mua.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.Mua.Mapper.SuggestMapper;
import kr.co.Mua.bean.SongDto;

@Repository
public class SuggestDao {
	
	@Autowired
	private SuggestMapper suggestMapper;

    public String getMostRecentArtistName(int userNum) {
		return suggestMapper.getMostRecentArtistName(userNum);
	}
    
    public String getMostGenreName(int userNum) {
    	return suggestMapper.getMostGenreName(userNum);
    }
    
    public String getMostNationName(int userNum) {
    	return suggestMapper.getMostNationName(userNum);
    }
    
    public int getSongId(int song_id) {
    	return suggestMapper.getSongId(song_id);
    }
    
    public String getSongGenre(int song_id) {
    	return suggestMapper.getSongGenre(song_id);
    }
    
    public String getSongNation(int song_id) {
    	return suggestMapper.getSongNation(song_id);
    }
}
