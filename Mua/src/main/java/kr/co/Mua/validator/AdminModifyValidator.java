package kr.co.Mua.validator;

import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import kr.co.Mua.bean.AlbumDto;
import kr.co.Mua.bean.ArtistDto;
import kr.co.Mua.bean.SongDto;

public class AdminModifyValidator implements Validator {

	private final String REGEXP_PATTERN_DATE = "^[\\d]{4}\\.(0[1-9]|1[012])\\.(0[1-9]|[12][0-9]|3[01])$|^-$";
	private final String REGEXP_PATTERN_LYRICS = "^[a-z|A-Z|0-9|_|-]+\\.txt$|^-$";
	private final String REGEXP_PATTERN_THUMBNAIL = "^[a-z|A-Z|0-9|_|-]+\\.(jpg|jpeg|png)$|^-$";

	@Override
	public boolean supports(Class<?> clazz) {
		return SongDto.class.isAssignableFrom(clazz) || 
				ArtistDto.class.isAssignableFrom(clazz) ||
				AlbumDto.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		
		String release_date;

		switch(errors.getObjectName()) {
		// song
		case "modifySongDto":
			
			SongDto songDto = (SongDto) target;
			
			String song_name = songDto.getSong_name();
			String song_genre = songDto.getSong_genre();
			release_date = songDto.getRelease_date();
			String lyrics = songDto.getLyrics();
			String song_thumbnail = songDto.getSong_thumbnail();
			
			System.out.println("song_name : " + song_name.length());
			// song_name
			if (song_name == null || song_name.isEmpty()) {
				errors.rejectValue("song_name", "sNameEmpty");
			} else if (song_name.getBytes().length > 300) {
				errors.rejectValue("song_name", "sNameTooLong");
			}
			
			// song_genre
			if (song_genre.isEmpty() || song_genre == null) {
				errors.rejectValue("song_genre", "sGenreEmpty");
			} else if (song_genre.getBytes().length > 100) {
				errors.rejectValue("song_genre", "sGenreTooLong");
			}
			
			// release_date
			if (release_date.isEmpty() || release_date == null) {
				errors.rejectValue("release_date", "sDateEmpty");
			} else if (!Pattern.matches(REGEXP_PATTERN_DATE, release_date)) {
				errors.rejectValue("release_date", "sDatePattern");
			}
			
			// lyrics
			if (lyrics.isEmpty() || lyrics == null) {
				errors.rejectValue("lyrics", "sLyricsEmpty");
			} else if (lyrics.getBytes().length > 200) {
				errors.rejectValue("lyrics", "sLyricsTooLong");
			} else if (!Pattern.matches(REGEXP_PATTERN_LYRICS, lyrics)) {
				errors.rejectValue("lyrics", "sLyricsPattern");
			}
			
			// song_thumbnail
			if (song_thumbnail.isEmpty() || song_thumbnail == null) {
				errors.rejectValue("song_thumbnail", "sThumbnailEmpty");
			} else if (song_thumbnail.getBytes().length > 200) {
				errors.rejectValue("song_thumbnail", "sThumbnailTooLong");
			} else if (!Pattern.matches(REGEXP_PATTERN_THUMBNAIL, song_thumbnail)) {
				errors.rejectValue("song_thumbnail", "sThumbnailPattern");
			}
			break;
			
		//artist
		case "modifyArtistDto":
			
			ArtistDto artistDto = (ArtistDto) target;

			String artist_name = artistDto.getArtist_name();
			String artist_date = artistDto.getArtist_date();
			String artist_thumbnail = artistDto.getArtist_thumbnail();
			String artist_agency = artistDto.getArtist_agency();
			String artist_nation = artistDto.getArtist_nation();

			if (artist_name == null || artist_name.isEmpty()) {
				errors.rejectValue("artist_name", "arNameEmpty");
			} else if (artist_name.getBytes().length > 200) {
				errors.rejectValue("artist_name", "arNameTooLong");
			}

			if (artist_date == null || artist_date.isEmpty()) {
				errors.rejectValue("artist_date", "arDateEmpty");
			} else if (!Pattern.matches(REGEXP_PATTERN_DATE, artist_date)) {
				errors.rejectValue("artist_date", "arDatePattern");
			}

			if (artist_thumbnail == null || artist_thumbnail.isEmpty()) {
				errors.rejectValue("artist_thumbnail", "arThumbnailEmpty");
			} else if (artist_thumbnail.getBytes().length > 200) {
				errors.rejectValue("artist_thumbnail", "arThumbnailTooLong");
			} else if (!Pattern.matches(REGEXP_PATTERN_THUMBNAIL, artist_thumbnail)) {
				errors.rejectValue("artist_thumbnail", "arThumbnailPattern");
			}

			if (artist_agency == null || artist_agency.isEmpty()) {
				errors.rejectValue("artist_agency", "arAgencyEmpty");
			} else if (artist_agency.getBytes().length > 100) {
				errors.rejectValue("artist_agency", "arAgencyTooLong");
			}

			if (artist_nation == null || artist_nation.isEmpty()) {
				errors.rejectValue("artist_nation", "arNationEmpty");
			} else if (artist_nation.getBytes().length > 100) {
				errors.rejectValue("artist_nation", "arNationTooLong");
			}
			break;
			
		//album
		case "modifyAlbumDto":
			
			AlbumDto albumDto = (AlbumDto) target;

			String album_name = albumDto.getAlbum_name();
			release_date = albumDto.getRelease_date();
			String album_genre = albumDto.getAlbum_genre();
			String album_publisher = albumDto.getAlbum_publisher();
			String album_agency = albumDto.getAlbum_agency();
			String album_thumbnail = albumDto.getAlbum_thumbnail();

			if (album_name == null || album_name.isEmpty()) {
				errors.rejectValue("album_name", "alNameEmpty");
			} else if (album_name.getBytes().length > 300) {
				errors.rejectValue("album_name", "alNameTooLong");
			}

			if (release_date == null || release_date.isEmpty()) {
				errors.rejectValue("release_date", "alDateEmpty");
			} else if (!Pattern.matches(REGEXP_PATTERN_DATE, release_date)) {
				errors.rejectValue("release_date", "alDatePattern");
			}

			if (album_genre == null || album_genre.isEmpty()) {
				errors.rejectValue("album_genre", "alGenreEmpty");
			} else if (album_genre.getBytes().length > 100) {
				errors.rejectValue("album_genre", "alGenreTooLong");
			}

			if (album_publisher == null || album_publisher.isEmpty()) {
				errors.rejectValue("album_publisher", "alPublisherEmpty");
			} else if (album_publisher.getBytes().length > 100) {
				errors.rejectValue("album_publisher", "alPublisherTooLong");
			}

			if (album_agency == null || album_agency.isEmpty()) {
				errors.rejectValue("album_agency", "alAgencyEmpty");
			} else if (album_agency.getBytes().length > 100) {
				errors.rejectValue("album_agency", "alAgencyTooLong");
			}

			if (album_thumbnail == null || album_thumbnail.isEmpty()) {
				errors.rejectValue("album_thumbnail", "alThumbnailEmpty");
			} else if (album_thumbnail.getBytes().length > 200) {
				errors.rejectValue("album_thumbnail", "alThumbnailTooLong");
			} else if (!Pattern.matches(REGEXP_PATTERN_THUMBNAIL, album_thumbnail)) {
				errors.rejectValue("album_thumbnail", "alThumbnailPattern");
			}
			break;
			
		}
		

	}
}
