package kr.co.Mua.Mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import kr.co.Mua.bean.AlbumDto;
import kr.co.Mua.bean.ArtistDto;
import kr.co.Mua.bean.SongDto;

public interface SearchMapper {

	// 노래의 정보와 앨범 name, id를 가져옴
	@Select("select s.song_id, s.song_name, s.song_genre, s.release_date, s.lyrics, "
			+ "s.song_thumbnail, a.album_id, a.album_name, count(t.song_id) as song_thumbup "
			+ "from song s "
			+ "inner join album a ON a.album_id = s.album_id "
			+ "left join thumbup_song t ON t.song_id = s.song_id "
			+ "where s.song_id = #{song_id} "
			+ "group by s.song_id, s.song_name, s.song_genre, s.release_date, s.lyrics, "
			+ "s.song_thumbnail, a.album_id, a.album_name")
	public SongDto getSong_info(int song_id);
	
	// 해당 노래의 아티스트의 간략한 정보를 가져옴
	@Select("select artist_id, artist_name, artist_thumbnail from artist "
			+ "where artist_id in (select artist_id from song_artist where song_id=#{song_id})")
	public ArrayList<ArtistDto> getBriefArtist(int song_id);
	
	// 아티스트의 노래를 전부 가져옴
	@Select("select distinct song_id, song_name, song_thumbnail, album_name, "
			+ "ListAgg(artist_name,', ') within group (order by artist_name) as artist_name "
			+ "from artist, song s, album "
			+ "where s.album_id = album.album_id "
			+ "and song_id in (select song_id from song_artist where artist_id=#{artist_id}) "
			+ "and artist_id in (select artist_id from song_artist where song_id=s.song_id) "
			+ "group by song_id, song_name, song_thumbnail, album_name")
	public ArrayList<SongDto> getMoreSong_info(int artist_id);
	
	// 아티스트의 정보를 가져옴
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
	
	// 아티스트의 아이디를 이용해 앨범의 정보를 가져옴
	// album_id, album_name, album_thumbnailm, release_date, artist_name
	@Select("select al.album_name, al.album_id, al.album_thumbnail, al.release_date, "
			+ "listagg(ar.artist_name, ', ') as artist_name "
			+ "from album al "
			+ "inner join album_artist aa on aa.album_id=al.album_id "
			+ "inner join artist ar on ar.artist_id=aa.artist_id "
			+ "where al.album_id in (select album_id from album_artist aa2 where aa2.artist_id=#{artist_id}) "
			+ "group by al.album_name, al.album_id, al.album_thumbnail, al.release_date")
	public ArrayList<AlbumDto> getArtist_album_info(int artist_id);
	
	// 앨범의 정보를 가져옴
	@Select("select * from album where album_id = #{album_id}")
	public AlbumDto getAlbum_info(int album_id);
	
	// 앨범의 아이디를 통해 아티스트의 정보를 가져옴
	@Select("select ar.artist_id, ar.artist_name "
			+ "from artist ar "
			+ "inner join album_artist aa on aa.artist_id=ar.artist_id "
			+ "where aa.album_id=#{album_id}")
	public ArrayList<ArtistDto> getAlbum_Artist_info(int album_id);
	
	// 앨범의 아이디를 이용하여 노래들을 가져옴 - 페이징
	@Select("select * from( "
			+ "select s.song_id, s.song_name, s.song_thumbnail, al.album_id, al.album_name, "
			+ "row_number() over (order by s.song_name) as rn "
			+ "from song s "
			+ "inner join album al on al.album_id=s.album_id "
			+ "where al.album_id = #{arg0}) "
			+ "where rn between #{arg1} and #{arg2}")
	public ArrayList<SongDto> getAlbum_Song(int album_id, int index, int endIndex);
	
	// album_info.jsp의 페이징 처릴위한 count값
	@Select("select count(*) "
			+ "from song s "
			+ "inner join album al on al.album_id=s.album_id "
			+ "where al.album_id=#{album_id}")
	public Integer getAlbum_Song_MaxIndex(int album_id);
	
	// 문자열을 입력 받아 노래를 검색 - 페이징
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
	
	// 문자열을 입력 받아 아티스트를 검색 - 페이징
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
	
	// 문자열을 입력 받아 앨범을 검색 - 페이징
	@Select("select * from( "
			+ "select album_id, album_name, album_thumbnail, album_genre, release_date, "
			+ "row_number() over (order by instr(album_name, #{arg0}), album_name) as rn "
			+ "from album "
			+ "where lower(replace(album_name, ' ', '')) like lower(replace(#{arg1}, ' ', '')) "
			+ "order by rn) "
			+ "where rn between #{arg2} and #{arg3}")
	public ArrayList<AlbumDto> getSearch_album(String str, String replaceStr, int index, int endView);
	
	// 해당 앨범의 아티스트를 가져옴
	@Select("select artist_id, artist_name "
			+ "from artist "
			+ "where artist_id in (select artist_id from album_artist where album_id=#{album_id})")
	public ArrayList<ArtistDto> getAlbum_Artist(int album_id);
	
	// 검색 결과의 갯수를 가져옴
	@Select("select count(*) from ${arg1} "
			+ "where lower(replace(${arg1}_name, ' ','')) like lower(replace(#{arg0}, ' ', ''))")
	public int getMaxIndex(String str, String type);
	
	// 좋아요 했는지?
	@Select("select count(*) "
			+ "from thumbup_${arg2} "
			+ "where ${arg2}_id = #{arg0} "
			+ "and user_num=#{arg1}")
	public int getUserThumbup(int id, int user_num, String infoType);
	
	// 좋아요
	@Insert("insert into thumbup_${arg2} "
			+ "values(#{arg1}, #{arg0})")
	public void thumbup(int id, int user_num, String infoType);
	
	// 좋아요 취소
	@Delete("delete thumbup_${arg2} "
			+ "where user_num=#{arg1} and ${arg2}_id=#{arg0}")
	public void delThumbup(int id, int user_num, String infoType);
	
	// 좋아요 갯수
	@Select("select count(*) from thumbup_${arg1} "
			+ "where ${arg1}_id=#{arg0}")
	public int getThumbup(int id, String infoType);
}
