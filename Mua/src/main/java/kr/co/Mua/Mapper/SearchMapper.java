package kr.co.Mua.Mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.co.Mua.bean.AlbumDto;
import kr.co.Mua.bean.ArtistDto;
import kr.co.Mua.bean.ReviewDto;
import kr.co.Mua.bean.SongDto;
import kr.co.Mua.bean.ViewedSongDto;

public interface SearchMapper {

	// �끂�옒�쓽 �젙蹂댁� �븿踰� name, id瑜� 媛��졇�샂
	@Select("select s.song_id, s.song_name, s.song_genre, s.release_date, s.lyrics, "
			+ "s.song_thumbnail, a.album_id, a.album_name, count(t.song_id) as song_thumbup "
			+ "from song s "
			+ "inner join album a ON a.album_id = s.album_id "
			+ "left join thumbup_song t ON t.song_id = s.song_id "
			+ "where s.song_id = #{song_id} "
			+ "group by s.song_id, s.song_name, s.song_genre, s.release_date, s.lyrics, "
			+ "s.song_thumbnail, a.album_id, a.album_name")
	public SongDto getSong_info(int song_id);
	
	// �빐�떦 �끂�옒�쓽 �븘�떚�뒪�듃�쓽 媛꾨왂�븳 �젙蹂대�� 媛��졇�샂
	@Select("select artist_id, artist_name, artist_thumbnail from artist "
			+ "where artist_id in (select artist_id from song_artist where song_id=#{song_id})")
	public ArrayList<ArtistDto> getBriefArtist(int song_id);
	
	// �븘�떚�뒪�듃�쓽 �끂�옒瑜� �쟾遺� 媛��졇�샂
	@Select("select distinct song_id, song_name, song_thumbnail, album_name, "
			+ "ListAgg(artist_name,', ') within group (order by artist_name) as artist_name "
			+ "from artist, song s, album "
			+ "where s.album_id = album.album_id "
			+ "and song_id in (select song_id from song_artist where artist_id=#{artist_id}) "
			+ "and artist_id in (select artist_id from song_artist where song_id=s.song_id) "
			+ "group by song_id, song_name, song_thumbnail, album_name")
	public ArrayList<SongDto> getMoreSong_info(int artist_id);
	
	// �븘�떚�뒪�듃�쓽 �젙蹂대�� 媛��졇�샂
	@Select("select ar.artist_id, ar.artist_name, ar.artist_date, ar.artist_type, "
			+ "ar.artist_thumbnail, ar.artist_agency, ar.artist_nation, "
			+ "count(t.artist_id) artist_thumbup, listAgg(member_name, ', ') as member "
			+ "from artist ar "
			+ "left join thumbup_artist t on t.artist_id = ar.artist_id "
			+ "left join member m on m.artist_id = ar.artist_id "
			+ "where ar.artist_id=#{artist_id} "
			+ "group by ar.artist_id, ar.artist_name, ar.artist_date, ar.artist_type, "
			+ "ar.artist_thumbnail, ar.artist_agency, ar.artist_nation")
	public ArtistDto getArtist_info(int artist_id);
	
	// �븘�떚�뒪�듃�쓽 �븘�씠�뵒瑜� �씠�슜�빐 �븿踰붿쓽 �젙蹂대�� 媛��졇�샂
	// album_id, album_name, album_thumbnailm, release_date, artist_name
	@Select("select al.album_name, al.album_id, al.album_thumbnail, al.release_date, "
			+ "listagg(ar.artist_name, ', ') as artist_name "
			+ "from album al "
			+ "inner join album_artist aa on aa.album_id=al.album_id "
			+ "inner join artist ar on ar.artist_id=aa.artist_id "
			+ "where al.album_id in (select album_id from album_artist aa2 where aa2.artist_id=#{artist_id}) "
			+ "group by al.album_name, al.album_id, al.album_thumbnail, al.release_date")
	public ArrayList<AlbumDto> getArtist_album_info(int artist_id);
	
	// �븿踰붿쓽 �젙蹂대�� 媛��졇�샂
	@Select("select * from album where album_id = #{album_id}")
	public AlbumDto getAlbum_info(int album_id);
	
	// �븿踰붿쓽 �븘�씠�뵒瑜� �넻�빐 �븘�떚�뒪�듃�쓽 �젙蹂대�� 媛��졇�샂
	@Select("select ar.artist_id, ar.artist_name "
			+ "from artist ar "
			+ "inner join album_artist aa on aa.artist_id=ar.artist_id "
			+ "where aa.album_id=#{album_id}")
	public ArrayList<ArtistDto> getAlbum_Artist_info(int album_id);
	
	// �븿踰붿쓽 �븘�씠�뵒瑜� �씠�슜�븯�뿬 �끂�옒�뱾�쓣 媛��졇�샂 - �럹�씠吏�
	@Select("select * from( "
			+ "select s.song_id, s.song_name, s.song_thumbnail, al.album_id, al.album_name, "
			+ "row_number() over (order by s.song_name) as rn "
			+ "from song s "
			+ "inner join album al on al.album_id=s.album_id "
			+ "where al.album_id = #{arg0}) "
			+ "where rn between #{arg1} and #{arg2}")
	public ArrayList<SongDto> getAlbum_Song(int album_id, int index, int endIndex);
	
	// album_info.jsp�쓽 �럹�씠吏� 泥섎┫�쐞�븳 count媛�
	@Select("select count(*) "
			+ "from song s "
			+ "inner join album al on al.album_id=s.album_id "
			+ "where al.album_id=#{album_id}")
	public Integer getAlbum_Song_MaxIndex(int album_id);
	
	// 臾몄옄�뿴�쓣 �엯�젰 諛쏆븘 �끂�옒瑜� 寃��깋 - �럹�씠吏�
	@Select("select * from ( "
			+ "select s.song_id, s.song_name, s.song_thumbnail, a.album_id, a.album_name, "
			+ "count(t.song_id) as song_thumbup, "
			+ "row_number() over (order by instr(s.song_name, #{arg0}), s.song_name) as rn "
			+ "from song s "
			+ "inner join album a on s.album_id=a.album_id "
			+ "left join thumbup_song t on s.song_id=t.song_id "
			+ "where lower(replace(s.song_name, ' ','')) like lower(replace(#{arg1}, ' ', '')) "
			+ "group by s.song_id, s.song_name, s.song_thumbnail, a.album_id, a.album_name "
			+ "order by rn) "
			+ "where rn between #{arg2} and #{arg3}")
	public ArrayList<SongDto> getSearch_song(String str, String replaceStr, int index, int endView);
	
	// �끂�옒瑜� �겢由��빐 �뱾�뼱媛덉떆 理쒓렐 蹂� �끂�옒 �뀒�씠釉붿뿉 �벑濡�
	@Insert("insert into viewed_song values(#{arg1}, #{arg0}, sysdate)")
	public void insertViewed_song(int song_id, int user_num);
	
	// 理쒓렐 蹂� �끂�옒�뀒�씠釉붿뿉 �젙蹂닿� 議댁옱�븯�뒗吏�
	@Select("select user_num, song_id, to_char(viewed_date, 'yyyy-MM-dd HH24:mi:ss') as viewed_date "
			+ "from viewed_song "
			+ "where song_id=#{arg0} and user_num=#{arg1}")
	public ViewedSongDto getViewed_song(int song_id, int user_num);
	
	// 理쒓렐 蹂� �끂�옒 �뀒�씠釉붿뿉 議댁옱�븷�떆 �뾽�뜲�씠�듃
	@Update("update viewed_song set viewed_date=sysdate "
			+ "where song_id=#{arg0} and user_num=#{arg1}")
	public void updateViewed_song(int song_id, int user_num);
	
	// 臾몄옄�뿴�쓣 �엯�젰 諛쏆븘 �븘�떚�뒪�듃瑜� 寃��깋 - �럹�씠吏�
	@Select("select * from ( "
			+ "select artist.artist_id, artist_name, artist_thumbnail, artist_type, "
			+ "listagg(member_name, ', ') as member, "
			+ "row_number() over (order by instr(artist_name, #{arg0}), artist_name) as rn "
			+ "from artist "
			+ "left join member on member.artist_id=artist.artist_id "
			+ "where lower(replace(artist_name, ' ', '')) like lower(replace(#{arg1}, ' ', '')) "
			+ "group by artist.artist_id, artist_name, artist_thumbnail, artist_type "
			+ "order by rn) "
			+ "where rn between #{arg2} and #{arg3}")
	public ArrayList<ArtistDto> getSearch_artist(String str, String replaceStr, int index, int endIndex);
	
	// 臾몄옄�뿴�쓣 �엯�젰 諛쏆븘 �븿踰붿쓣 寃��깋 - �럹�씠吏�
	@Select("select * from( "
			+ "select album_id, album_name, album_thumbnail, album_genre, release_date, "
			+ "row_number() over (order by instr(album_name, #{arg0}), album_name) as rn "
			+ "from album "
			+ "where lower(replace(album_name, ' ', '')) like lower(replace(#{arg1}, ' ', '')) "
			+ "order by rn) "
			+ "where rn between #{arg2} and #{arg3}")
	public ArrayList<AlbumDto> getSearch_album(String str, String replaceStr, int index, int endView);
	
	// �빐�떦 �븿踰붿쓽 �븘�떚�뒪�듃瑜� 媛��졇�샂
	@Select("select artist_id, artist_name "
			+ "from artist "
			+ "where artist_id in (select artist_id from album_artist where album_id=#{album_id})")
	public ArrayList<ArtistDto> getAlbum_Artist(int album_id);
	
	// 寃��깋 寃곌낵�쓽 媛��닔瑜� 媛��졇�샂
	@Select("select count(*) from ${arg1} "
			+ "where lower(replace(${arg1}_name, ' ','')) like lower(replace(#{arg0}, ' ', ''))")
	public int getMaxIndex(String str, String type);
	
	// 醫뗭븘�슂 �뻽�뒗吏�?
	@Select("select count(*) "
			+ "from thumbup_${arg2} "
			+ "where ${arg2}_id = #{arg0} "
			+ "and user_num=#{arg1}")
	public int getUserThumbup(int id, int user_num, String infoType);
	
	// 醫뗭븘�슂
	@Insert("insert into thumbup_${arg2} "
			+ "values(#{arg1}, #{arg0}, sysdate)")
	public void thumbup(int id, int user_num, String infoType);
	
	// 醫뗭븘�슂 痍⑥냼
	@Delete("delete thumbup_${arg2} "
			+ "where user_num=#{arg1} and ${arg2}_id=#{arg0}")
	public void delThumbup(int id, int user_num, String infoType);
	
	// 醫뗭븘�슂 媛��닔
	@Select("select count(*) from thumbup_${arg1} "
			+ "where ${arg1}_id=#{arg0}")
	public int getThumbup(int id, String infoType);
	
	//============== 由щ럭 ================
	// 由щ럭�쓽 �젙蹂대�� 媛��졇�샂
	@Select("select * from( "
			+ "select rv.review_num, rv.user_num, us.user_name, rv.type_id, rv.review_point, rv.review_content, "
			+ "to_char(rv.review_date, 'yyyy.MM.dd HH:mm') as review_date, rv.suggestion, rv.flag, "
			+ "row_number() over (order by rv.review_num desc) as rn "
			+ "from review rv "
			+ "Inner join user_info us on us.user_num = rv.user_num "
			+ "where flag=#{arg0} and type_id=#{arg1}) "
			+ "where rn between #{arg2} and #{arg3}")
	public ArrayList<ReviewDto> getReview(String flag, int id, int index, int endIndex);
	
	// 由щ럭�쓽 理쒕� 媛��닔瑜� 媛��졇�샂
	@Select("select count(*) from review "
			+ "where flag=#{arg0} and type_id=#{arg1}")
	public int getReviewCount(String flag, int id);
	
	// �궗�슜�옄 蹂몄씤�쓽 由щ럭瑜� 媛��졇�샂
	@Select("select * from review "
			+ "where flag=#{arg0} and type_id=#{arg1} "
			+ "and user_num=#{arg2}")
	public ReviewDto getUserReview(String flag, int type_id, int user_num);
	
	// 由щ럭 �옉�꽦
	@Insert("insert into review values(\r\n"
			+ "review_seq.nextval, #{user_num}, #{type_id}, #{review_point}, #{review_content}, sysdate, 0, #{flag})")
	public void insertUserReview(ReviewDto userReview);
	
	// 由щ럭 �닔�젙
	@Update("update review set review_point=#{review_point}, "
			+ "review_content=#{review_content}, review_date=sysdate "
			+ "where flag=#{flag} and type_id=#{type_id} and user_num=#{user_num}")
	public void rewriteUserReview(ReviewDto userReview);
	
	// 由щ럭 �궘�젣
	@Delete("delete review where flag=#{arg0} "
			+ "and type_id=#{arg1} and user_num=#{arg2} "
			+ "and review_num=${arg3}")
	public void deleteUserReview(String flag, int type_id, int user_num, int review_num);
	
	// 由щ럭 �떊怨� �쟾 �솗�씤
	@Select("select report_num from review_report "
			+ "where user_num=#{arg0} and review_num=#{arg1}")
	public Integer checkReport(int user_num, int review_num);
	
	// 由щ럭 �떊怨�
	@Insert("insert into review_report "
			+ "values(report_seq.nextval, #{arg0}, "
			+ "#{arg1}, #{arg2}, sysdate)")
	public void reviewReport(int review_num, int report_user_num, int user_num);
}
