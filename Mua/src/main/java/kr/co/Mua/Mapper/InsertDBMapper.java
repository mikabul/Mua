package kr.co.Mua.Mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import kr.co.Mua.bean.AlbumDTO;
import kr.co.Mua.bean.ArtistDTO;
import kr.co.Mua.bean.SongDTO;

public interface InsertDBMapper {
	
	@Select("select song_name from song where song_name=#{arg0.song_name} " + 
			"and artist_num=(select artist_num from artist " + 
			"where artist_name=#{arg1.artist_name} and artist_date=to_date(#{arg1.artist_date}) " + 
			"and artist_type=#{arg1.artist_type} and agency=#{arg1.agency})")
	public SongDTO song_search(SongDTO songDTO, ArtistDTO artistDTO);
	
	@Select("select artist_num from artist where artist_name=#{artist_name} and "
			+ "artist_date=to_date(#{artist_date}) and artist_type=#{artist_type} "
			+ "and agency=#{agency}")
	public ArtistDTO artist_search(ArtistDTO artistDTO);
	
	@Select("select album_num from album where album_name=#{arg0.album_name} " + 
			"and artist_num=(select artist_num from artist " + 
			"where artist_name=#{arg1.artist_name} and artist_date=#{arg1.artist_date} " +
			"and artist_type=#{arg1.artist_type} and agency=#{arg1.agency})")
	public AlbumDTO album_search(AlbumDTO albumDTO, ArtistDTO artistDTO);
	
	@Insert("insert into song values(song_seq.nextval, #{song_name}, #{sing_genre}, to_date(#{release_date}), null, 0, #{artist_num}, #{album_num})")
	public void insert_song(SongDTO songDTO);
	
	@Insert("insert into artist values(artist_seq.nextval, #{artist_name}, to_date(#{artist_date}), #{artist_type}, #{agency})")
	public void insert_artist(ArtistDTO artistDTO);
	
	@Insert("insert into albul values(album_seq.nextval, #{album_name}, to_date(#{release_date}), #{album_genre}, #{album_agency}, #{artist_num})")
	public void insert_albem(AlbumDTO albumDTO);
	
}
