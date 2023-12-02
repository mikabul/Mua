package kr.co.Mua.service;

<<<<<<< HEAD
public class SearchService {

	public void insertDB() {
		
=======
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.Mua.bean.ArtistDto;
import kr.co.Mua.bean.SongDto;
import kr.co.Mua.dao.SearchDao;

@Service
public class SearchService {

	@Autowired
	private SearchDao searchDAO;
	
	public SongDto getSong_info(int song_id) {
		return searchDAO.getSong_info(song_id);
	}
	
	public ArrayList<ArtistDto> getBriefArtist(int song_id){
		return searchDAO.getBriefArtist(song_id);
	}
	
	public ArrayList<SongDto> getMoreSong_info(int artist_id){
		return searchDAO.getMoreSong_info(artist_id);
>>>>>>> refs/remotes/origin/이영민
	}
}
