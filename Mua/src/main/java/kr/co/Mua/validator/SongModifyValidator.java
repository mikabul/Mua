package kr.co.Mua.validator;

import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import kr.co.Mua.bean.SongDto;

public class SongModifyValidator implements Validator{
	
	private final String REGEXP_PATTERN_DATE = "^[\\d]{4}\\.(0[1-9]|1[012])\\.(0[1-9]|[12][0-9]|3[01])$";
	private final String REGEXP_PATTERN_LYRICS = "^[a-z|A-Z|0-9|_|-]+\\.txt$";
	private final String REGEXP_PATTERN_THUMBNAIL = "^[a-z|A-Z|0-9|_|-]+\\.(jpg|jpeg|png)$";
	
	@Override
	public boolean supports(Class<?> clazz) {
		return SongDto.class.isAssignableFrom(clazz);
	}
	
	@Override
	public void validate(Object target, Errors errors) {
		
			
		if(errors.getObjectName().equals("modifySongDto")) {
			SongDto songDto = (SongDto)target;
			
			String song_name = songDto.getSong_name();
			String song_genre = songDto.getSong_genre();
			String release_date = songDto.getRelease_date();
			String lyrics = songDto.getLyrics();
			String song_thumbnail = songDto.getSong_thumbnail();
			
			System.out.println("song_name : " + song_name.length());
			// song_name
			if(song_name == null || song_name.isEmpty()) {
				errors.rejectValue("song_name", "sNameEmpty");
			} else if(song_name.getBytes().length > 300) {
				errors.rejectValue("song_name", "sNameTooLong");
			}
			
			// song_genre
			if(song_genre.isEmpty() || song_genre == null) {
				errors.rejectValue("song_genre", "sGenreEmpty");
			} else if(song_genre.getBytes().length > 100) {
				errors.rejectValue("song_genre", "sGenreTooLong");
			}
			
			// release_date
			if(release_date.isEmpty() || release_date == null) {
				errors.rejectValue("release_date", "sDateEmpty");
			} else if(!Pattern.matches(REGEXP_PATTERN_DATE, release_date)) {
				errors.rejectValue("release_date", "sDatePattern");
			}
			
			// lyrics
			if(lyrics.isEmpty() || lyrics == null) {
				errors.rejectValue("lyrics", "sLyricsEmpty");
			} else if(lyrics.getBytes().length > 200) {
				errors.rejectValue("lyrics", "sLyricsTooLong");
			} else if(!Pattern.matches(REGEXP_PATTERN_LYRICS, lyrics)) {
				errors.rejectValue("lyrics", "sLyricsPattern");
			}
			
			// song_thumbnail
			if(song_thumbnail.isEmpty() || song_thumbnail == null) {
				errors.rejectValue("song_thumbnail", "sThumbnailEmpty");
			}else if(song_thumbnail.getBytes().length > 200) {
				errors.rejectValue("song_thumbnail", "sThumbnailTooLong");
			}else if(!Pattern.matches(REGEXP_PATTERN_THUMBNAIL, song_thumbnail)) {
				errors.rejectValue("song_thumbnail", "sThumbnailPattern");
			}
		}
		
	}
}
