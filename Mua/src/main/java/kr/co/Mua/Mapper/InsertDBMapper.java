package kr.co.Mua.Mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.co.Mua.bean.AlbumDto;
import kr.co.Mua.bean.ArtistDto;
import kr.co.Mua.bean.SongDto;

public interface InsertDBMapper {
	
	//----------정보가 있는지 찾는 부분---------------
	@Select("select song_id from song "
			+ "where song_name=#{song_name} and song_genre=#{song_genre} "
			+ "and release_date=#{release_date}")
	public SongDto song_match(SongDto songDTO);
	
	@Select("select artist_id from artist where artist_name=#{artist_name} and "
			+ "artist_date=#{artist_date} and artist_type=#{artist_type} "
			+ "and artist_agency=#{artist_agency}")
	public ArtistDto artist_match(ArtistDto artistDTO);
	
	@Select("select album_id from album "
			+ "where album_name=#{album_name} and album_genre=#{album_genre} "
			+ "and release_date=#{release_date}")
	public AlbumDto album_match(AlbumDto albumDTO);
	
	@Select("select artist_id from song_artist where song_id=#{arg0} and artist_id=#{arg1}")
	public Integer song_artist_match(int song_id, int artist_id);
	
	@Select("select artist_id from album_artist where album_id=#{arg0} and artist_id=#{arg1}")
	public Integer album_artist_match(int album_id, int artist_id);
	
	@Select("select artist_id from member where artist_id=#{arg0} and member_name=#{arg1}")
	public Integer member_match(int artist_id, String member_name);
	
	//-----------정보를 저장하는 부분----------------
	@Insert("insert into song values(song_seq.nextval, #{song_name}, "
			+ "#{song_genre}, #{release_date}, #{lyrics}, 0, #{song_thumbnail}, #{album_id}, #{song_nation})")
	public void insert_song(SongDto songDTO);
	
	@Insert("insert into artist values(artist_seq.nextval, #{artist_name}, #{artist_date}, "
			+ "#{artist_type}, #{artist_thumbnail}, #{artist_agency}, #{artist_nation})")
	public void insert_artist(ArtistDto artistDTO);
	
	@Insert("insert into album values(album_seq.nextval, #{album_name}, #{release_date}, #{album_genre}, "
			+ "#{album_publisher}, #{album_agency}, #{album_thumbnail})")
	public void insert_album(AlbumDto albumDTO);
	
	@Insert("insert into song_artist values(#{arg0}, #{arg1})")
	public void insert_song_artist(int song_id, int artist_id);
	
	@Insert("insert into album_artist values(#{arg0}, #{arg1})")
	public void insert_album_artist(int album_id, int artist_id);
	
	@Insert("insert into member values(#{arg1}, #{arg0})")
	public void insert_member(int artist_id, String name);
	
	//=========== 썸네일 추가 ==============
	@Update("update song set song_thumbnail=#{arg1} where song_id=#{arg0}")
	public void insert_song_thumbnail(int song_id, String song_thumbnail);
	
	@Update("update artist set artist_thumbnail=#{arg1} where artist_id=#{arg0}")
	public void insert_artist_thumbnail(int song_id, String artist_thumbnail);
	
	@Update("update album set album_thumbnail=#{arg1} where album_id=#{arg0}")
	public void insert_album_thumbnail(int song_id, String album_thumbnail);
	
	//============ 가사 ==============
	@Update("update song set lyrics=#{arg1} where song_id=#{arg0}")
	public void insert_lyrics(int song_id, String lyrics);
	
}
