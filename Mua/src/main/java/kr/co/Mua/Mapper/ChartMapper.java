package kr.co.Mua.Mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Select;

import kr.co.Mua.bean.AlbumDto;
import kr.co.Mua.bean.ArtistDto;
import kr.co.Mua.bean.SongDto;

public interface ChartMapper {

	// song_name, release_date, song_genre �씠 紐⑤몢 �뜲�씠�꽣 踰좎씠�뒪�뿉 �엳�뼱�빞 媛믪쓣 諛섑솚
	@Select("select * from song "
			+ "where song_name=#{arg0} and release_date=#{arg1} "
			+ "and song_genre=#{arg2}")
	public SongDto chartSongMatch(String song_name, String release_date, String song_genre);
	
	// song_name, artist_name, album_name 이 모두 데이터 베이스에 있어야 값을 반환
    @Select("select s.song_id, s.song_name, s.song_thumbnail, s.album_id, ar.artist_name "
    		+ "from song s "
    		+ "inner join song_artist sa on sa.song_id=s.song_id "
    		+ "inner join artist ar on ar.artist_id=sa.artist_id "
    		+ "inner join album al on al.album_id=s.album_id "
    		+ "where s.song_name=#{arg0} "
    		+ "and (ar.artist_name=#{arg1} or "
    		+ "trim(REGEXP_SUBSTR(ar.artist_name,'[0-9a-zA-Z가-힣., \\-]+'))=trim(REGEXP_SUBSTR(#{arg1},'[0-9a-zA-Z가-힣., \\-]+'))) "
    		+ "and al.album_name=#{arg2}")
    public SongDto chartSongMatch_fast(String song_name, String artist_name, String album_name);
	
	// song_id �� �븘�떚�뒪�듃�씠由꾩씠 �씪移섑빐�빞 媛믪쓣 諛섑솚
	@Select("select ar.artist_name, ar.artist_id, ar.artist_thumbnail "
			+ "from song_artist sa "
			+ "inner join artist ar on sa.artist_id=ar.artist_id "
			+ "where sa.song_id=#{song_id}")
	public ArrayList<ArtistDto> chartArtistmatch(int song_id);
	
	// song_id 媛� �씪移섑븯硫� 媛믪쓣 諛섑솚
	@Select("select album_id, album_name "
			+ "from album where album_id=#{album_id}")
	public AlbumDto chartAlbumMatch(int album_id);
	
	@Select("select song_thumbnail from song where song_id=#{song_id}")
	public String getSong_thumbnail(int song_id);
	
	@Select("select artist_thumbnail from artist where artist_id=#{artist_id}")
	public String getArtist_thumbnail(int artist_id);
	
	@Select("select count(*) from thumbup_song where song_id=#{song_id}")
	public int getCount_thumbup(int song_id);
	
	//================ �옣瑜대퀎 �쓬�븙 ==================
	// 援�媛�蹂� �궗�슜 �옣瑜대퀎 寃��깋
	@Select("select * from ( " + "select s.song_name, s.song_id, al.album_id, al.album_name, song_genre, "
			+ "s.song_thumbnail, al.album_thumbnail, row_number() over (order by s.release_date, s.song_name) as rn "
			+ "from song s " + "inner join album al on al.album_id=s.album_id "
			+ "left join thumbup_song ts on ts.song_id=s.song_id "
			+ "where s.song_genre like #{arg0} and s.song_nation=#{arg1}) " + "where rn between #{arg2} and #{arg3}")
	public ArrayList<SongDto> getGenreSong(String replaceSTabValue, String fTabValue, int index, int endIndex);

	// 援�媛�蹂� �궗�슜 �옣瑜대퀎 寃��깋 理쒕�媛쒖닔
	@Select("select count(*) from song " + "where song_genre like #{arg0} and song_nation=#{arg1}")
	public int getGenreSongMaxIndex(String replaceSTabValue, String fTabValue);

	// OST�쟾�슜
	@Select("select * from ( " + "select s.song_name, s.song_id, al.album_id, al.album_name, song_genre, "
			+ "s.song_thumbnail, al.album_thumbnail, row_number() over (order by s.release_date, s.song_name) as rn "
			+ "from song s " + "inner join album al on al.album_id=s.album_id "
			+ "left join thumbup_song ts on ts.song_id=s.song_id "
			+ "where s.song_genre like '%�븷�땲硫붿씠�뀡%' or s.song_genre like '%�뱶�씪留�%' "
			+ "or s.song_genre like '%�쁺�솕%' or s.song_genre like '%裕ㅼ�而�%' " + "or s.song_genre like '%寃뚯엫%') "
			+ "where rn BETWEEN #{arg0} and #{arg1}")
	public ArrayList<SongDto> getGenreSongOST(int index, int endIndex);

	// OST�쟾�슜 寃��깋寃곌낵�쓽 理쒕�媛��닔瑜� 媛����샂
	@Select("select count(*) from song " + "where song_genre like '%�븷�땲硫붿씠�뀡%' or song_genre like '%�뱶�씪留�%' "
			+ "or song_genre like '%�쁺�솕%' or song_genre like '%裕ㅼ�而�%' " + "or song_genre like '%寃뚯엫%'")
	public int getGenreSongOSTMaxIndex();

	// 洹몄쇅
	@Select("select * from ( " + "select s.song_name, s.song_id, al.album_id, al.album_name, song_genre, "
			+ "s.song_thumbnail, al.album_thumbnail, row_number() over (order by s.release_date, s.song_name) as rn "
			+ "from song s " + "Inner join album al on s.album_id=al.album_id " + "where s.song_genre like #{arg0}) "
			+ "where rn BETWEEN #{arg1} and #{arg2}")
	public ArrayList<SongDto> getOtherGenreSong(String replaceSTabValue, int index, int endIndex);

	@Select("select count(*) from song " + "where song_genre like #{replaceSTabValue}")
	public int getOtherGenreSongMaxIndex(String replaceSTabValue);
	
}
