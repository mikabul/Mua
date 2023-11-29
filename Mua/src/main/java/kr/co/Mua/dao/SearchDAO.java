package kr.co.Mua.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.Mua.Mapper.SearchMapper;
import kr.co.Mua.bean.SongDTO;

@Repository
public class SearchDAO {

	@Autowired
	private SearchMapper searchMapper;
	
	public SongDTO getSong_info(int song_id) {
		
		return searchMapper.getSong_info(song_id);
		
	}
}
