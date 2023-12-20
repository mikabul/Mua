package kr.co.Mua.dao;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.Mua.Mapper.SuggestMapper;
import kr.co.Mua.bean.ArtistDto;
import kr.co.Mua.bean.SongDto;

@Repository
public class SuggestDao {
	
	@Autowired
	private SuggestMapper suggestMapper;

    public List<ArtistDto> getMostRecentArtistInfo(int userNum) {
    	return suggestMapper.getRecentArtistInfo(userNum);
	}
    
    public String getMostGenreName(int userNum) {
    	return suggestMapper.getMostGenreName(userNum);
    }
    
    public int getMostGenreCount(int userNum) {
    	return suggestMapper.getMostGenreCount(userNum);
    }
    
    public String getMostNationName(int userNum) {
    	return suggestMapper.getMostNationName(userNum);
    }

    public int getMostNationCount(int userNum) {
    	return suggestMapper.getMostNationCount(userNum);
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
    
    public List<String> getArtistNames(int song_id) {
    	return suggestMapper.getArtistNames(song_id);
    }

    

}
