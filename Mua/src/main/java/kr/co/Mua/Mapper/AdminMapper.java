package kr.co.Mua.Mapper;

import java.util.ArrayList;
import java.util.Date;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.co.Mua.bean.AdminDto;
import kr.co.Mua.bean.AlbumDto;
import kr.co.Mua.bean.ArtistDto;
import kr.co.Mua.bean.ReviewDto;
import kr.co.Mua.bean.SongDto;
import kr.co.Mua.bean.UserBean;
import kr.co.Mua.bean.notAcceptUserBean;

public interface AdminMapper {

	// 어드민 로그인
	@Select("select admin_num, admin_id from admin where admin_id=#{admin_id} and admin_pw=#{admin_pw}")
	public AdminDto getLogin(AdminDto tempAdminDto);
	
	//=========== 노래 =============
	// 노래 이름으로 검색
	@Select("select * from ( "
			+ "select s.song_id, s.song_name, s.song_thumbnail, a.album_id, a.album_name, "
			+ "s.release_date, "
			+ "row_number() over (order by instr(s.song_name, #{arg0}), s.song_name) as rn "
			+ "from song s "
			+ "inner join album a on s.album_id=a.album_id "
			+ "left join thumbup_song t on s.song_id=t.song_id "
			+ "where lower(replace(s.song_name, ' ','')) like lower(replace(#{arg1}, ' ', '')) "
			+ "order by rn) "
			+ "where rn between #{arg2} and #{arg3}")
	public ArrayList<SongDto> getSearchSongName(String str, String replaceStr, int index, int endView);
	
	// song_id로 검색
	@Select("select * from song where song_id=#{song_id}")
	public SongDto getSearchSongId(int song_id);
	
	//---------- 업데이트 ------------
	// 이름, 장르, 발매일, 가사파일이름, 썸네일파일이름, 국내(해외)
	@Update("update song set song_name=#{song_name}, song_genre=#{song_genre}, "
			+ "release_date=#{release_date}, lyrics=#{lyrics}, "
			+ "song_thumbnail=#{song_thumbnail}, song_nation=#{song_nation} "
			+ "where song_id=#{song_id}")
	public void updateSong(SongDto songDto);
	
	//=========== 아티스트 ============
	// 아티스트 이름으로 검색
	@Select("select * from ( "
			+ "select artist.artist_id, artist_name, artist_thumbnail, artist_type, "
			+ "artist_date, artist_nation, "
			+ "row_number() over (order by instr(artist_name, #{arg0}), artist_name) as rn "
			+ "from artist "
			+ "where lower(replace(artist_name, ' ', '')) like lower(replace(#{arg1}, ' ', '')) "
			+ "order by rn) "
			+ "where rn between #{arg2} and #{arg3}")
	public ArrayList<ArtistDto> getSearchArtistName(String str, String replaceStr, int index, int endIndex);
	
	// artist_id로 검색
	@Select("select * from artist where artist_id=#{artist_id}")
	public ArtistDto getSearchArtistId(int artist_id);
	
	//------------ 업데이트 --------------
	// 이름, 데뷔일, 유형, 썸네일파일이름, 기획사, 국내(해외)
	@Update("update artist set artist_name=#{artist_name}, artist_date=#{artist_date}, "
			+ "artist_type=#{artist_type}, artist_thumbnail=#{artist_thumbnail}, "
			+ "artist_agency=#{artist_agency}, artist_nation=#{artist_nation} "
			+ "where artist_id=#{artist_id}")
	public void updateArtist(ArtistDto artistDto);
	
	//=========== 앨범 =============
	// 앨범 이름으로 검색
	@Select("select * from( "
			+ "select album_id, album_name, album_thumbnail, album_genre, release_date, "
			+ "row_number() over (order by instr(album_name, #{arg0}), album_name) as rn "
			+ "from album "
			+ "where lower(replace(album_name, ' ', '')) like lower(replace(#{arg1}, ' ', '')) "
			+ "order by rn) "
			+ "where rn between #{arg2} and #{arg3}")
	public ArrayList<AlbumDto> getSearchalbumName(String str, String replaceStr, int index, int endView);
	
	// album_id로 검색
	@Select("select * from album where album_id=#{album_id}")
	public AlbumDto getSearchAlbumId(int album_id);
	
	// 검색 결과의 갯수를 가져옴
	@Select("select count(*) from ${arg1} "
			+ "where lower(replace(${arg1}_name, ' ','')) like lower(replace(#{arg0}, ' ', ''))")
	public int getMaxIndex(String str, String type);
	
	//------------- 업데이트 --------------
	// 이름, 발매일, 장르, 기획사, 발매사, 썸네일 파일 이름
	@Update("update album set album_name=#{album_name}, release_date=#{release_date}, "
			+ "album_genre=#{album_genre}, album_agency=#{album_agency}, "
			+ "album_publisher=#{album_publisher}, album_thumbnail=#{album_thumbnail} "
			+ "where album_id=#{album_id}")
	public void updateAlbum(AlbumDto albumDto);
	
	//============= 유저 =============
	//유저 넘을 통한 유저 상세정보 가져옴
	//이름 이메일 전화번호 생성일 번호
	@Select("select user_name, user_email, user_tel, user_registdate, user_num from user_info where user_num = ${user_num}")
	public UserBean getUserNum(int user_num);
	
	//유저 검색 결과 갯수 가져옴
	@Select("select count(*) from ${arg1} "
			+ "where lower(replace(user_name,' ','')) like lower(replace(#{arg0},' ',''))")
	public int getUserMaxIndex(String searchedValue, String table_name);
	// 유저의 정보 조회 - user_num, user_id, user_name, user_registdate
	@Select("select * from( "
			+ "select user_num, user_id, user_name, "
			+ "to_char(user_registdate, 'yyyy-mm-dd hh24:mi:ss') as user_registdate, "
			+ "row_number() over (order by instr(user_id, #{arg0}), user_num) as rn "
			+ "from user_info "
			+ "where lower(user_id) like lower(#{arg1})) "
			+ "where rn between #{arg2} and #{arg3}")
	public ArrayList<UserBean> searchUserName(String str, String replaceStr, int index, int endView);
	
	// 유저의 차단
	@Insert("insert into notacceptuser values( #{arg0}, #{arg1}, sysdate, to_date(#{arg2}))")
	public void insertNotAccepteUser(int user_num, int admin_num, String end_date);
	
	// 유저의 차단 해제
	@Delete("delete notacceptuser where user_num=#{user_num}")
	public void deleteNotAccepteUser(int user_num);
	
	// 차단 유저 불러오기
	@Select("select user_num, start_date, end_date from notacceptuser where user_num = #{arg0}")
	public notAcceptUserBean getBanishedUser(int user_num);
	//============== 리뷰 ===============
	// 신고된 전체 리뷰
	@Select("select us.user_num, us.user_id, us.user_name, "
			+ "rv.review_num, rv.review_content, rv.flag, "
			+ "to_char(rr.report_date, 'yyyy-mm-dd hh24:mi:ss') as report_date, "
			+ "rr.report_num "
			+ "from review_report rr "
			+ "inner join user_info us on us.user_num=rr.report_user_num "
			+ "inner join review rv on rv.review_num=rr.review_num "
			+ "order by rr.report_date desc")
	public ArrayList<ReviewDto> getReviewReport();
	
	// 리뷰 삭제
	@Delete("delete review where flag=#{arg0} "
			+ "and type_id=#{arg1} and user_num=#{arg2} "
			+ "and review_num=${arg3}")
	public void deleteUserReview(String flag, int type_id, int user_num, int review_num);
	
	// 문제가 없는 리뷰
	@Delete("delete review_report where report_num=#{report_num}")
	public void deleteReport(int report_num);
}
