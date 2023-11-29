package kr.co.Mua.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.Mua.bean.SongDTO;
import kr.co.Mua.dao.SearchDAO;

@Service
public class SearchService {

	@Autowired
	private SearchDAO searchDAO;
	
	public SongDTO getSong_info(int song_id) {
		return searchDAO.getSong_info(song_id);
	}
}
