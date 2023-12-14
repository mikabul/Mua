package kr.co.Mua.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.Mua.bean.AlbumDto;
import kr.co.Mua.bean.ArtistDto;
import kr.co.Mua.bean.SongDto;
import kr.co.Mua.service.AdminService;
import kr.co.Mua.validator.AdminModifyValidator;

@Controller
@RequestMapping(value="/admin/search/")
public class AdminSearchController {
	
	@Autowired
	private AdminService adminService;
	
	//========== 검색 ===========
	@RequestMapping(value="/songName")
	public String songName() {
		return "/admin/search/songName";
	}
	
	@RequestMapping(value="/artistName")
	public String artistName() {
		return "/admin/search/artistName";
	}
	
	@RequestMapping(value="/albumName")
	public String albumName() {
		return "/admin/search/albumName";
	}
	
	//============ 정보 ===========
	@RequestMapping(value="/songInfo")
	public String songInfo(@RequestParam("song_id") int song_id,
							@ModelAttribute("modifySongDto") SongDto modifySongDto) {
		SongDto tempSongDto = adminService.getSearchSongId(song_id);
		if(tempSongDto == null) {
			return "/admin/InvalidValue";
		}
		
		modifySongDto.setSong_id(tempSongDto.getSong_id());
		modifySongDto.setAlbum_id(tempSongDto.getAlbum_id());
		modifySongDto.setRelease_date(tempSongDto.getRelease_date());
		modifySongDto.setLyrics(tempSongDto.getLyrics());
		modifySongDto.setSong_genre(tempSongDto.getSong_genre());
		modifySongDto.setSong_name(tempSongDto.getSong_name());
		modifySongDto.setSong_nation(tempSongDto.getSong_nation());
		modifySongDto.setSong_thumbnail(tempSongDto.getSong_thumbnail());
		
		return "/admin/search/songInfo";
	}
	
	@RequestMapping(value="/modifySong")
	public String modifySong(@Valid @ModelAttribute("modifySongDto") SongDto modifySongDto,
							BindingResult result) {
		
		if(result.hasErrors()) {
			return "/admin/search/songInfo";
		} else {
			adminService.updateSong(modifySongDto);
			return "/admin/search/success";
		}
	}
	
	@RequestMapping(value="/artistInfo")
	public String artistInfo(@RequestParam("artist_id") int song_id,
							@ModelAttribute("modifyArtistDto") ArtistDto modifyArtistDto) {
		ArtistDto tempArtistDto = adminService.getSearchArtistId(song_id);
		
		if(tempArtistDto == null) {
			return "/admin/InvalidValue";
		}else {
			
			modifyArtistDto.setArtist_agency(tempArtistDto.getArtist_agency());
			modifyArtistDto.setArtist_date(tempArtistDto.getArtist_date());
			modifyArtistDto.setArtist_id(tempArtistDto.getArtist_id());
			modifyArtistDto.setArtist_name(tempArtistDto.getArtist_name());
			modifyArtistDto.setArtist_nation(tempArtistDto.getArtist_nation());
			modifyArtistDto.setArtist_thumbnail(tempArtistDto.getArtist_thumbnail());
			modifyArtistDto.setArtist_type(tempArtistDto.getArtist_type());
			
			return "/admin/search/artistInfo";
		}
		
	}
	
	@RequestMapping(value="/modifyArtist")
	public String modifyArtist(@Valid @ModelAttribute("modifyArtistDto") ArtistDto modifyArtistDto,
								BindingResult result) {
		
		if(result.hasErrors()) {
			return "/admin/search/artistInfo";
		} else {
			adminService.updateArtist(modifyArtistDto);
			return "/admin/search/success";
		}
	}
	
	@RequestMapping(value="/albumInfo")
	public String albumInfo(@RequestParam("album_id") int song_id,
							@ModelAttribute("modifyAlbumDto") AlbumDto modifyAlbumDto) {
		AlbumDto tempAlbumDto = adminService.getSearchAlbumId(song_id);
		
		if(tempAlbumDto == null) {
			return "/admin/InvalidValue";
		} else {
			
			modifyAlbumDto.setAlbum_agency(tempAlbumDto.getAlbum_agency());
			modifyAlbumDto.setAlbum_genre(tempAlbumDto.getAlbum_genre());
			modifyAlbumDto.setAlbum_id(tempAlbumDto.getAlbum_id());
			modifyAlbumDto.setAlbum_name(tempAlbumDto.getAlbum_name());
			modifyAlbumDto.setAlbum_publisher(tempAlbumDto.getAlbum_publisher());
			modifyAlbumDto.setAlbum_thumbnail(tempAlbumDto.getAlbum_thumbnail());
			modifyAlbumDto.setRelease_date(tempAlbumDto.getRelease_date());
			
			return "/admin/search/albumInfo";
		}
	}
	
	@RequestMapping(value="/modifyAlbum")
	public String modifyAlbum(@Valid @ModelAttribute("modifyAlbumDto") AlbumDto modifyAlbumDto,
								BindingResult result) {
		
		if(result.hasErrors()) {
			return "/admin/search/albumInfo";
		} else {
			adminService.updateAlbum(modifyAlbumDto);
			return "/admin/search/success";
		}
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		AdminModifyValidator adminModifyValidator = new AdminModifyValidator();
		binder.addValidators(adminModifyValidator);
	}
}
