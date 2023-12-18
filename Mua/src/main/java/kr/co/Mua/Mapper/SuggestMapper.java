package kr.co.Mua.Mapper;

import kr.co.Mua.bean.ArtistDto;
import kr.co.Mua.bean.SongDto;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Select;

public interface SuggestMapper {
	
    @Select("select artist_name, to_char(thumbup_date, 'yyyy-mm-dd hh24:mm') as thumbup_date"
    		+ " from artist ar"
    		+ " inner join user_info u on u.user_num=${userNum}"
    		+ " inner join thumbup_artist ta on ta.user_num=u.user_num"
    		+ " where ta.artist_id=ar.artist_id"
    		+ " order by thumbup_date desc")
    public List<ArtistDto> getRecentArtistInfo(int userNum);
    
    
    @Select("SELECT most_common_genre " +
            "FROM ( " +
            "    SELECT song_genre AS most_common_genre, COUNT(*) AS genre_count " +
            "    FROM ( " +
            "        SELECT s.song_genre " +
            "        FROM song s " +
            "        INNER JOIN thumbup_song t ON s.song_id = t.song_id " +
            "        WHERE t.user_num = #{userNum} " +
            "    ) " +
            "    GROUP BY song_genre " +
            "    ORDER BY COUNT(*) DESC " +
            ") " +
            "WHERE ROWNUM = 1")
    public String getMostGenreName(int userNum);
    
    @Select("SELECT genre_count " +
            "FROM ( " +
            "    SELECT song_genre AS most_common_genre, COUNT(*) AS genre_count " +
            "    FROM ( " +
            "        SELECT s.song_genre " +
            "        FROM song s " +
            "        INNER JOIN thumbup_song t ON s.song_id = t.song_id " +
            "        WHERE t.user_num = #{userNum} " +
            "    ) " +
            "    GROUP BY song_genre " +
            "    ORDER BY COUNT(*) DESC " +
            ") " +
            "WHERE ROWNUM = 1")
    public int getMostGenreCount(int userNum);
    
    @Select("SELECT most_common_nation " +
            "FROM ( " +
            "    SELECT song_nation AS most_common_nation, COUNT(*) AS nation_count " +
            "    FROM ( " +
            "        SELECT s.song_nation " +
            "        FROM song s " +
            "        INNER JOIN thumbup_song t ON s.song_id = t.song_id " +
            "        WHERE t.user_num = #{userNum} " +
            "    ) " +
            "    GROUP BY song_nation " +
            "    ORDER BY COUNT(*) DESC " +
            ") " +
            "WHERE ROWNUM = 1")
    public String getMostNationName(int userNum);
    
    @Select("SELECT nation_count " +
            "FROM ( " +
            "    SELECT song_nation AS most_common_nation, COUNT(*) AS nation_count " +
            "    FROM ( " +
            "        SELECT s.song_nation " +
            "        FROM song s " +
            "        INNER JOIN thumbup_song t ON s.song_id = t.song_id " +
            "        WHERE t.user_num = #{userNum} " +
            "    ) " +
            "    GROUP BY song_nation " +
            "    ORDER BY COUNT(*) DESC " +
            ") " +
            "WHERE ROWNUM = 1")
    public int getMostNationCount(int userNum);
    
    @Select("select song_id from song where song_id = #{song_id}")
    public int getSongId(int song_id);
    
    @Select("select song_genre from song where song_id = #{song_id}")
    public String getSongGenre(int song_id);

    @Select("select song_nation from song where song_id = #{song_id}")
    public String getSongNation(int song_id);
    
    @Select("SELECT a.artist_name "
    	    + "FROM artist a "
    	    + "INNER JOIN song_artist sa ON a.artist_id = sa.artist_id "
    	    + "WHERE sa.song_id = #{song_id}")
    public List<String> getArtistNames(int song_id);


}

