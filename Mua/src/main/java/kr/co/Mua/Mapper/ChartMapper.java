package kr.co.Mua.Mapper;

import org.apache.ibatis.annotations.Select;

public interface ChartMapper {

	// song_name, artist_name, album_name 이 모두 데이터 베이스에 있어야 값을 반환
	@Select("select song_id from song_artist, album "
			+ "where song_id in (select song_id from song where song_name=#{arg0}) "
			+ "and artist_id in (select artist_id from artist where artist_name=#{arg1} or "
			+ "trim(REGEXP_SUBSTR(artist_name,'[0-1a-zA-Z가-힣.-_, ]+'))=#{arg1}) "
			+ "and album_id in (select album_id from album where album_name=#{arg2})")
	public Integer chartSongMatch(String song_name, String artist_name, String album_name);

	@Select("select artist_id from song_artist "
			+ "where song_id=#{arg0} and artist_id in (select artist_id from artist "
			+ "where artist_name=#{arg1} or trim(REGEXP_SUBSTR(artist_name,'[0-1a-zA-Z가-힣.-_, ]+'))=#{arg1})")
	public Integer chartArtistmatch(int song_id, String artist_name);
	
	@Select("select album_id from album where album_id=(select album_id from song where song_id=#{song_id})")
	public Integer chartAlbumMatch(int song_id);
	
	@Select("select song_thumbnail from song where song_id=#{song_id}")
	public String getSong_thumbnail(int song_id);
	
	@Select("select artist_thumbnail from artist where artist_id=#{artist_id}")
	public String getArtist_thumbnail(int artist_id);
	
	@Select("select count(*) from thumbup_song where song_id=#{song_id}")
	public int getCount_thumbup(int song_id);
	
}
