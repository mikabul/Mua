package kr.co.Mua.service;

import java.util.ArrayList;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.Mua.bean.AlbumDto;
import kr.co.Mua.bean.ArtistDto;
import kr.co.Mua.bean.ReviewDto;
import kr.co.Mua.bean.SearchResultDto;
import kr.co.Mua.bean.SongDto;
import kr.co.Mua.bean.ViewedSongDto;
import kr.co.Mua.dao.SearchDao;

@Service
public class SearchService {

	@Autowired
	private SearchDao searchDAO;
	
	ArrayList<SearchResultDto> searchResultList;
	
	public SongDto getSong_info(int song_id) {
		return searchDAO.getSong_info(song_id);
	}
	
	public ArrayList<ArtistDto> getBriefArtist(int song_id){
		return searchDAO.getBriefArtist(song_id);
	}
	
	public ArrayList<SongDto> getMoreSong_info(int artist_id){
		return searchDAO.getMoreSong_info(artist_id);
	}
	
	// 노래 검색
	public ArrayList<SearchResultDto> getSearch_song(String str, int index, int endView){
		
		String replaceStr = "%" + str + "%";
		ArrayList<SongDto> songList = searchDAO.getSearch_song(str, replaceStr, index, endView);
		
		// 검색결과를 담기위한 객체
		SearchResultDto tempSerchResult;
		// 모든 검색결과를 담기위한 리스트
		searchResultList = new ArrayList<SearchResultDto>();
		
		// 노래 별로 아티스트의 정보를 받아오기위해 Iterator 사용
		Iterator<SongDto> songIt = songList.iterator();
		while(songIt.hasNext()) {
			
			tempSerchResult = new SearchResultDto();
			SongDto tempSongDto = songIt.next();
			// song_id를 사용하여 아티스트들의 정보를 가져옴
			ArrayList<ArtistDto> artistList = searchDAO.getBriefArtist(tempSongDto.getSong_id());
			
			tempSerchResult.setSongDto(tempSongDto);
			tempSerchResult.setArtistList(artistList);
			searchResultList.add(tempSerchResult);
		}
		
		return searchResultList;
	}
	
	public void insertViewed_song(int song_id, int user_num) {
		searchDAO.insertViewed_song(song_id, user_num);
	}
	
	public ViewedSongDto getViewed_song(int song_id, int user_num) {
		return searchDAO.getViewed_song(song_id, user_num);
	}
	
	public void updateViewed_song(int song_id, int user_num) {
		searchDAO.updateViewed_song(song_id, user_num);
	}
	
	// 아티스트 검색
	public ArrayList<ArtistDto> getSearch_artist(String str, int index, int endView) {
		String replaceStr = "%" + str + "%";
		return searchDAO.getSearch_artist(str, replaceStr, index, endView);
		
	}
	
	// 앨범 검색
	public ArrayList<SearchResultDto> getSearch_album(String str, int index, int endView) {
		
		String replaceStr = "%" + str + "%";
		
		ArrayList<AlbumDto> albumDto = searchDAO.getSearch_album(str, replaceStr, index, endView);
		ArrayList<ArtistDto> tempArtistDto;
		searchResultList = new ArrayList<SearchResultDto>();
		SearchResultDto tempSearchResult;
		
		Iterator<AlbumDto> albumIt = albumDto.iterator();
		while(albumIt.hasNext()) {
			
			AlbumDto tempAlbumDto = albumIt.next();
			tempSearchResult = new SearchResultDto();
			
			// 앨범의 아이디를 이용하여 아티스트의 정보를 가져옴
			tempArtistDto = searchDAO.getAlbum_Artist(tempAlbumDto.getAlbum_id());
			// 가져온 데이터를 tempSearchResult 에 저장
			tempSearchResult.setAlbumDto(tempAlbumDto);
			tempSearchResult.setArtistList(tempArtistDto);
			
			// tempSearchResult를 searchResultListdp 추가
			searchResultList.add(tempSearchResult);
			
		}
		return searchResultList;
	}
	
	// 검색결과의 최대 갯수
	public int getMaxIndex(String str, String type) {
		str = "%" + str + "%";
		return searchDAO.getMaxIndex(str, type);
	}
	
	public ArtistDto getArtist_info(int artist_id) {
		return searchDAO.getArtist_info(artist_id);
	}
	
	public ArrayList<AlbumDto> getArtist_album_info(int artist_id){
		return searchDAO.getArtist_album_info(artist_id);
	}
	
	public AlbumDto getAlbum_info(int album_id) {
		return searchDAO.getAlbum_info(album_id);
	}
	
	public ArrayList<ArtistDto> getAlbum_Artist_info(int album_id){
		return searchDAO.getAlbum_Artist_info(album_id);
	}
	
	public ArrayList<SearchResultDto> getAlbum_Song(int album_id, int index, int endIndex){
		// 아티스트 아이디를 이용하여 노래의 정보를 가져옴
		ArrayList<SongDto> songList = searchDAO.getAlbum_Song(album_id, index, endIndex);
		// 최종 결과물을 담기위한 ArrayList객체 생성
		searchResultList = new ArrayList<SearchResultDto>();
		// SongDto, ArtistDto를 담기위한 객체
		SearchResultDto tempSearchResultDto;
		
		Iterator<SongDto> songDtoIt = songList.iterator();
		while(songDtoIt.hasNext()) {
			// SongDto, ArtistDto를 담기위한 객체 생성
			tempSearchResultDto = new SearchResultDto();
			// 리스트에 담겨있는 SongDto를 하나씩 가져옴
			SongDto tempSongDto = songDtoIt.next();
			// song_id를 이용하여 아티스트의 id, name을 가져옴
			ArrayList<ArtistDto> tempArtistList = searchDAO.getBriefArtist(tempSongDto.getSong_id());
			
			tempSearchResultDto.setSongDto(tempSongDto);
			tempSearchResultDto.setArtistList(tempArtistList);
			
			searchResultList.add(tempSearchResultDto);
		}
		return searchResultList;
	}
	
	public Integer getAlbum_Song_MaxIndex(int album_id) {
		return searchDAO.getAlbum_Song_MaxIndex(album_id);
	}
	
	//================ 좋아요 ==================
	public int getUserThumbup(int id, int user_num, String infoType) {
		return searchDAO.getUserThumbup(id, user_num, infoType);
	}
	
	public void thumbup(int id, int user_num, String infoType) {
		searchDAO.thumbup(id, user_num, infoType);
	}
	
	public void delThumbup(int id, int user_num, String infoType) {
		searchDAO.delThumbup(id, user_num, infoType);
	}
	
	public int getThumbup(int id, String infoType) {
		return searchDAO.getThumbup(id, infoType);
	}
	
	//=============== 리뷰 ===============
	public ArrayList<ReviewDto> getReview(String flag, int id, int index, int endIndex) {
		return searchDAO.getReview(flag, id, index, endIndex);
	}
	
	public int getReviewCount(String flag, int id) {
		return searchDAO.getReviewCount(flag, id);
	}
	
	public ReviewDto getUserReview(String flag, int type_id, int user_num) {
		return searchDAO.getUserReview(flag, type_id, user_num);
	}
	
	public void insertUserReview(ReviewDto userReview) {
		searchDAO.insertUserReview(userReview);
	}
	
	public void rewriteUserReview(ReviewDto userReview) {
		searchDAO.rewriteUserReview(userReview);
	}
	
	public void deleteUserReview(String flag, int type_id, int user_num, int review_num) {
		searchDAO.deleteUserReview(flag, type_id, user_num, review_num);
	}
	
	public Boolean checkReport(int review_num, int report_user_num, int user_num) {
		
		if(searchDAO.checkReport(user_num, review_num) != null) {
			return false;
		} else {
			searchDAO.reviewReport(review_num, report_user_num, user_num);
			return true;
		}
	}
	
}
