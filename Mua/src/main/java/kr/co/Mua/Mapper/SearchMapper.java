package kr.co.Mua.Mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Select;

import kr.co.Mua.bean.ArtistDto;
import kr.co.Mua.bean.SongDto;

public interface SearchMapper {

	// 노래의 정보와 앨범 name, id를 가져옴
	@Select("select song_id, song_name, song_genre, song.release_date, lyrics, "
			+ "song_thumbnail, album.album_id, album_name "
			+ "from song, album "
			+ "where song_id = #{song_id} "
			+ "and album.album_id = song.album_id")
	public SongDto getSong_info(int song_id);
	
	// 해당 노래의 아티스트의 간략한 정보를 가져옴
	@Select("select artist_id, artist_name from artist "
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
	
}
