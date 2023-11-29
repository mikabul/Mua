package kr.co.Mua.Mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import kr.co.Mua.bean.AlbumDTO;
import kr.co.Mua.bean.ArtistDTO;
import kr.co.Mua.bean.SongDTO;

public interface InsertDBMapper {
	
	//----------정보가 있는지 찾는 부분---------------
	@Select("select song_id from song "
			+ "where song_name=#{song_name} and song_genre=#{song_genre} "
			+ "and release_date=#{release_date}")
	public SongDTO song_match(SongDTO songDTO);
	
	@Select("select artist_id from artist where artist_name=#{artist_name} and "
			+ "artist_date=#{artist_date} and artist_type=#{artist_type} "
			+ "and artist_agency=#{artist_agency}")
	public ArtistDTO artist_match(ArtistDTO artistDTO);
	
	@Select("select album_id from album "
			+ "where album_name=#{album_name} and album_genre=#{album_genre} "
			+ "and release_date=#{release_date}")
	public AlbumDTO album_match(AlbumDTO albumDTO);
	
	@Select("select artist_id from song_artist where song_id=#{arg0} and artist_id=#{arg1}")
	public Integer song_artist_match(int song_id, int artist_id);
	
	@Select("select artist_id from album_artist where album_id=#{arg0} and artist_id=#{arg1}")
	public Integer album_artist_match(int album_id, int artist_id);
	
	//-----------정보를 저장하는 부분----------------
	@Insert("insert into song values(song_seq.nextval, #{song_name}, #{song_genre}, #{release_date}, #{lyrics}, 0, #{song_thumbnail}, #{album_id})")
	public void insert_song(SongDTO songDTO);
	
	@Insert("insert into artist values(artist_seq.nextval, #{artist_name}, #{artist_date}, #{artist_type}, #{artist_thumbnail}, #{artist_agency})")
	public void insert_artist(ArtistDTO artistDTO);
	
	@Insert("insert into album values(album_seq.nextval, #{album_name}, #{release_date}, #{album_genre}, #{album_publisher}, #{album_agency}, #{album_thumbnail})")
	public void insert_album(AlbumDTO albumDTO);
	
	@Insert("insert into song_artist values(#{arg0}, #{arg1})")
	public void insert_song_artist(int song_id, int artist_id);
	
	@Insert("insert into album_artist values(#{arg0}, #{arg1})")
	public void insert_album_artist(int album_id, int artist_id);
	
}
