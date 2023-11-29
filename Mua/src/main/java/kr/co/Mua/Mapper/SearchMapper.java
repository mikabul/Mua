package kr.co.Mua.Mapper;

import org.apache.ibatis.annotations.Select;

import kr.co.Mua.bean.SongDTO;

public interface SearchMapper {

	@Select("select song_id, song_name, song_genre, song.release_date, lyrics, "
			+ "song_thumbnail, artist_id, artist_name, album.album_id, album_name "
			+ "from song, artist, album "
			+ "where song_id = #{song_id} and artist_id=(select artist_id from song_artist where song_id = #{song_id}) "
			+ "and album.album_id = song.album_id")
	public SongDTO getSong_info(int song_id);
}
