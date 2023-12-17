package kr.co.Mua.dao;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.Mua.Mapper.AdminMapper;
import kr.co.Mua.bean.AdminDto;
import kr.co.Mua.bean.AlbumDto;
import kr.co.Mua.bean.ArtistDto;
import kr.co.Mua.bean.ReviewDto;
import kr.co.Mua.bean.SongDto;
import kr.co.Mua.bean.UserBean;

@Repository
public class AdminDao {
	
	@Autowired
	private AdminMapper adminMapper;

	public AdminDto getLogin(AdminDto tempAdminDto) {
		return adminMapper.getLogin(tempAdminDto);
	}
	
	//============= 노래 =============
	public ArrayList<SongDto> getSearchSongName(String str, String replaceStr, int index, int endView){
		return adminMapper.getSearchSongName(str, replaceStr, index, endView);
	}
	
	public SongDto getSearchSongId(int song_id) {
		return adminMapper.getSearchSongId(song_id);
	}
	
	public ArrayList<SongDto> getEmptySongNation(int index, int maxIndex){
		return adminMapper.getEmptySongNation(index, maxIndex);
	}
	
	public int getEmptySongNationMaxIndex() {
		return adminMapper.getEmptySongNationMaxIndex();
	}
	
	public void updateSong(SongDto songDto) {
		adminMapper.updateSong(songDto);
	}
	
	//========== 아티스트 ===========
	public ArrayList<ArtistDto> getSearchArtistName(String str, String replaceStr, int index, int endIndex){
		return adminMapper.getSearchArtistName(str, replaceStr, index, endIndex);
	}
	
	public ArtistDto getSearchArtistId(int artist_id) {
		return adminMapper.getSearchArtistId(artist_id);
	}
	
	public void updateArtist(ArtistDto artistDto) {
		adminMapper.updateArtist(artistDto);
	}
	
	//============ 앨범 ===========
	public ArrayList<AlbumDto> getSearchalbumName(String str, String replaceStr, int index, int endView){
		return adminMapper.getSearchalbumName(str, replaceStr, index, endView);
	}
	
	public AlbumDto getSearchAlbumId(int album_id) {
		return adminMapper.getSearchAlbumId(album_id);
	}
	
	public void updateAlbum(AlbumDto albumDto) {
		adminMapper.updateAlbum(albumDto);
	}
	
	// 검색결과의 최대 갯수
	public int getMaxIndex(String str, String type) {
		return adminMapper.getMaxIndex(str, type);
	}
	
	//============= 유저 =============
	public ArrayList<UserBean> searchUserName(String str, String replaceStr, int index, int endView){
		return adminMapper.searchUserName(str, replaceStr, index, endView);
	}
	
	public void insertNotAccepteUser(int user_num, int admin_num, String end_date) {
		adminMapper.insertNotAccepteUser(user_num, admin_num, end_date);
	}
	
	public void deleteNotAccepteUser(int user_num) {
		adminMapper.deleteNotAccepteUser(user_num);
	}
	
	//============== 리뷰 ===============
	public ArrayList<ReviewDto> getReviewReport(){
		return adminMapper.getReviewReport();
	}
	
	public void deleteUserReview(String flag, int type_id, int user_num, int review_num) {
		adminMapper.deleteUserReview(flag, type_id, user_num, review_num);
	}
	
	public void deleteReport(int report_num) {
		adminMapper.deleteReport(report_num);
	}
	
}
