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
	
	// �끂�옒 寃��깋
	public ArrayList<SearchResultDto> getSearch_song(String str, int index, int endView){
		
		String replaceStr = "%" + str + "%";
		ArrayList<SongDto> songList = searchDAO.getSearch_song(str, replaceStr, index, endView);
		
		// 寃��깋寃곌낵瑜� �떞湲곗쐞�븳 媛앹껜
		SearchResultDto tempSerchResult;
		// 紐⑤뱺 寃��깋寃곌낵瑜� �떞湲곗쐞�븳 由ъ뒪�듃
		searchResultList = new ArrayList<SearchResultDto>();
		
		// �끂�옒 蹂꾨줈 �븘�떚�뒪�듃�쓽 �젙蹂대�� 諛쏆븘�삤湲곗쐞�빐 Iterator �궗�슜
		Iterator<SongDto> songIt = songList.iterator();
		while(songIt.hasNext()) {
			
			tempSerchResult = new SearchResultDto();
			SongDto tempSongDto = songIt.next();
			// song_id瑜� �궗�슜�븯�뿬 �븘�떚�뒪�듃�뱾�쓽 �젙蹂대�� 媛��졇�샂
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
	
	// �븘�떚�뒪�듃 寃��깋
	public ArrayList<ArtistDto> getSearch_artist(String str, int index, int endView) {
		String replaceStr = "%" + str + "%";
		return searchDAO.getSearch_artist(str, replaceStr, index, endView);
		
	}
	
	// �븿踰� 寃��깋
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
			
			// �븿踰붿쓽 �븘�씠�뵒瑜� �씠�슜�븯�뿬 �븘�떚�뒪�듃�쓽 �젙蹂대�� 媛��졇�샂
			tempArtistDto = searchDAO.getAlbum_Artist(tempAlbumDto.getAlbum_id());
			// 媛��졇�삩 �뜲�씠�꽣瑜� tempSearchResult �뿉 ���옣
			tempSearchResult.setAlbumDto(tempAlbumDto);
			tempSearchResult.setArtistList(tempArtistDto);
			
			// tempSearchResult瑜� searchResultListdp 異붽�
			searchResultList.add(tempSearchResult);
			
		}
		return searchResultList;
	}
	
	// 寃��깋寃곌낵�쓽 理쒕� 媛��닔
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
		// �븘�떚�뒪�듃 �븘�씠�뵒瑜� �씠�슜�븯�뿬 �끂�옒�쓽 �젙蹂대�� 媛��졇�샂
		ArrayList<SongDto> songList = searchDAO.getAlbum_Song(album_id, index, endIndex);
		// 理쒖쥌 寃곌낵臾쇱쓣 �떞湲곗쐞�븳 ArrayList媛앹껜 �깮�꽦
		searchResultList = new ArrayList<SearchResultDto>();
		// SongDto, ArtistDto瑜� �떞湲곗쐞�븳 媛앹껜
		SearchResultDto tempSearchResultDto;
		
		Iterator<SongDto> songDtoIt = songList.iterator();
		while(songDtoIt.hasNext()) {
			// SongDto, ArtistDto瑜� �떞湲곗쐞�븳 媛앹껜 �깮�꽦
			tempSearchResultDto = new SearchResultDto();
			// 由ъ뒪�듃�뿉 �떞寃⑥엳�뒗 SongDto瑜� �븯�굹�뵫 媛��졇�샂
			SongDto tempSongDto = songDtoIt.next();
			// song_id瑜� �씠�슜�븯�뿬 �븘�떚�뒪�듃�쓽 id, name�쓣 媛��졇�샂
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
	
	//================ 醫뗭븘�슂 ==================
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
	
	//=============== 由щ럭 ===============
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
