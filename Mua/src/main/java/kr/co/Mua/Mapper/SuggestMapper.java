package kr.co.Mua.Mapper;

import kr.co.Mua.bean.SongDto;

import java.util.Date;

import org.apache.ibatis.annotations.Select;

public interface SuggestMapper {
	
    @Select("SELECT A.ARTIST_NAME " +
            "FROM ARTIST A " +
            "INNER JOIN (SELECT ARTIST_ID " +
                        "FROM THUMBUP_ARTIST " +
                        "WHERE USER_NUM = #{userNum} " +
                        "ORDER BY THUMBUP_DATE DESC " +
                        "FETCH FIRST ROW ONLY) T ON A.ARTIST_ID = T.ARTIST_ID")
    public String getMostRecentArtistName(int userNum);
    
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
    		+ "WHERE sa.song_id = #{song_id} ")
    public String getArtistName(int song_id);
    
    @Select("SELECT ta.thumbup_date "
    		+ "FROM artist a "
    		+ "LEFT JOIN thumbup_artist ta ON a.artist_id = ta.artist_id "
    		+ "WHERE a.artist_name = #{artist_name} ")
    public Date getArtistThumbupDate(String artist_name);

}

