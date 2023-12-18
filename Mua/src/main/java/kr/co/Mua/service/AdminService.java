package kr.co.Mua.service;

import java.util.ArrayList;
import java.util.Date;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.Mua.bean.AdminDto;
import kr.co.Mua.bean.AlbumDto;
import kr.co.Mua.bean.ArtistDto;
import kr.co.Mua.bean.ReviewDto;
import kr.co.Mua.bean.SongDto;
import kr.co.Mua.bean.UserBean;
import kr.co.Mua.bean.notAcceptUserBean;
import kr.co.Mua.dao.AdminDao;

@Service
public class AdminService {

	@Autowired
	private AdminDao adminDao;
	
	@Resource(name="loginAdminDto")
	private AdminDto loginAdminDto;
	
	public boolean getLogin(AdminDto tempAdminDto) {
		AdminDto adminDto = adminDao.getLogin(tempAdminDto);
		System.out.println(adminDto);
		if(adminDto == null) {
			return false;
		} else {
			loginAdminDto.setAdmin_num(adminDto.getAdmin_num());
			loginAdminDto.setAdmin_id(adminDto.getAdmin_id());
			loginAdminDto.setLoginState(true);
			return true;
		}
	}
	
	//=========== 노래 ============
	public ArrayList<SongDto> getSearchSongName(String str, int index, int endView){
		String replaceStr = "%" + str + "%";
		return adminDao.getSearchSongName(str, replaceStr, index, endView);
	}
	
	public SongDto getSearchSongId(int song_id) {
		return adminDao.getSearchSongId(song_id);
	}
	
	public void updateSong(SongDto songDto) {
		adminDao.updateSong(songDto);
	}
	
	//========== 아티스트 ===========
	public ArrayList<ArtistDto> getSearchArtistName(String str, int index, int endIndex){
		String replaceStr = "%" + str + "%";
		return adminDao.getSearchArtistName(str, replaceStr, index, endIndex);
	}
	
	public ArtistDto getSearchArtistId(int artist_id) {
		return adminDao.getSearchArtistId(artist_id);
	}
	
	public void updateArtist(ArtistDto artistDto) {
		adminDao.updateArtist(artistDto);
	}
	
	//============ 앨범 ===========
	public ArrayList<AlbumDto> getSearchalbumName(String str, int index, int endView){
		String replaceStr = "%" + str + "%";
		return adminDao.getSearchalbumName(str, replaceStr, index, endView);
	}
	
	public AlbumDto getSearchAlbumId(int album_id) {
		return adminDao.getSearchAlbumId(album_id);
	}
	
	public void updateAlbum(AlbumDto albumDto) {
		adminDao.updateAlbum(albumDto);
	}
	
	public int getMaxIndex(String str, String type) {
		str = "%" + str + "%";
		return adminDao.getMaxIndex(str, type);
	}
	
	//============= 유저 =============
	public notAcceptUserBean getBanishedUser(int user_num) {
		return adminDao.getBanishedUser(user_num);
	}
	
	public UserBean getUserNum(int user_num) {
		return adminDao.getUserNum(user_num);
	}
	
	public ArrayList<UserBean> searchUserName(String str, int index, int endView){
		String replaceStr = "%" + str + "%";
		return adminDao.searchUserName(str, replaceStr, index, endView);
	}
	
	public void insertNotAccepteUser(int user_num, int admin_num, String end_date) {
		adminDao.insertNotAccepteUser(user_num, admin_num, end_date);
	}
	
	public void deleteNotAccepteUser(int user_num) {
		adminDao.deleteNotAccepteUser(user_num);
	}
	
	public int getUserMaxIndex(String searchedValue, String table_name) {
		return adminDao.getUserMaxIndex(searchedValue, table_name);
	}
	
	//============== 리뷰 ===============
	public ArrayList<ReviewDto> getReviewReport(){
		return adminDao.getReviewReport();
	}
	
	public void deleteUserReview(String flag, int type_id, int user_num, int review_num) {
		adminDao.deleteUserReview(flag, type_id, user_num, review_num);
	}
	
	public void deleteReport(int report_num) {
		adminDao.deleteReport(report_num);
	}
	
}
