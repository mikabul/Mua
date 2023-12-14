package kr.co.Mua.dao;

import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.Mua.Mapper.SearchMapper;
import kr.co.Mua.bean.AlbumDto;
import kr.co.Mua.bean.ArtistDto;
import kr.co.Mua.bean.SongDto;

@Repository
public class SearchDao {

	@Autowired
	private SearchMapper searchMapper;
	
	public SongDto getSong_info(int song_id) {
		return searchMapper.getSong_info(song_id);
	}
	
	public ArrayList<ArtistDto> getBriefArtist(int song_id){
		return searchMapper.getBriefArtist(song_id);
	}
	
	public ArrayList<SongDto> getMoreSong_info(int artist_id){
		return searchMapper.getMoreSong_info(artist_id);
	}
	
	public ArrayList<SongDto> getSearch_song(String str, String replaceStr, int index, int endView){
		return searchMapper.getSearch_song(str, replaceStr, index, endView);
	}
	
	public ArrayList<ArtistDto> getSearch_artist(String str, String replaceStr, int index, int endView){
		return searchMapper.getSearch_artist(str, replaceStr, index, endView);
	}
	
	public ArrayList<AlbumDto> getSearch_album(String str, String replaceStr, int index, int endView){
		return searchMapper.getSearch_album(str, replaceStr, index, endView);
	}
	
	public ArrayList<ArtistDto> getAlbum_Artist(int album_id){
		return searchMapper.getAlbum_Artist(album_id);
	}
	
	public int getMaxIndex(String str, String type) {
		return searchMapper.getMaxIndex(str, type);
	}
	
	public ArtistDto getArtist_info(int artist_id) {
		return searchMapper.getArtist_info(artist_id);
	}
	
	public ArrayList<AlbumDto> getArtist_album_info(int artist_id) {
		return searchMapper.getArtist_album_info(artist_id);
	}
	
	public AlbumDto getAlbum_info(int album_id) {
		return searchMapper.getAlbum_info(album_id);
	}
	
	public ArrayList<ArtistDto> getAlbum_Artist_info(int album_id){
		return searchMapper.getAlbum_Artist_info(album_id);
	}
	
	public ArrayList<SongDto> getAlbum_Song(int album_id, int index, int endIndex){
		return searchMapper.getAlbum_Song(album_id, index, endIndex);
	}
	
	public Integer getAlbum_Song_MaxIndex(int album_id) {
		return searchMapper.getAlbum_Song_MaxIndex(album_id);
	}
	
    public int getUserThumbup(int id, int user_num, String infoType) {
        return searchMapper.getUserThumbup(id, user_num, infoType);
    }

    public void thumbup(int id, int user_num, String infoType) {
        searchMapper.thumbup(id, user_num, infoType);
    }

    public void delThumbup(int id, int user_num, String infoType) {
        searchMapper.delThumbup(id, user_num, infoType);
    }

	public int getThumbup(int id, String infoType) {
		return searchMapper.getThumbup(id, infoType);
	}
}
