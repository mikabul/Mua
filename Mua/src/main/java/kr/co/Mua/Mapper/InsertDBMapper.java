package kr.co.Mua.Mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import kr.co.Mua.bean.AlbumDTO;
import kr.co.Mua.bean.ArtistDTO;
import kr.co.Mua.bean.SongDTO;

public interface InsertDBMapper {
	
	@Select("select song_id, song_name from song where song_name=#{song_name} and artist_num=(select artist_num from artist where artist_name=#{artist_name})")
	public SongDTO song_search(SongDTO songDTO, ArtistDTO artistDTO);
	
	@Select("select artsit_num, artist_name from artist where artist_name=#{artist_name}")
	public ArtistDTO artist_search(ArtistDTO artistDTO);
	
	@Select("select album_num, album_name from album where artist_num=(select artist_num from artist where artist_name=#{artist_name}) and album_name=#{album_name}")
	public AlbumDTO album_search(AlbumDTO albumDTO, ArtistDTO artistDTO);
	
	@Insert("insert into song values(song_seq.nextval, #{song_name}, #{sing_genre}, to_date(#{release_date}), null, 0, #{artist_num}, #{album_num})")
	public void insert_song(SongDTO songDTO);
	
	@Insert("")
	public void insert_artist(SongDTO songDTO);
	
	@Insert("")
	public void insert_albem(AlbumDTO albumDTO);
	
}
