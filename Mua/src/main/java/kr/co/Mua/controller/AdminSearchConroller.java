package kr.co.Mua.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
import kr.co.Mua.validator.SongModifyValidator;

@Controller
@RequestMapping(value="/admin/search/")
public class AdminSearchConroller {
	
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
	public String artistInfo(@RequestParam("artist_id") int song_id, Model model) {
		ArtistDto artistDto = adminService.getSearchArtistId(song_id);
		model.addAttribute("artistDto", artistDto);
		return "/admin/search/artistInfo";
	}
	
	@RequestMapping(value="/albumInfo")
	public String albumInfo(@RequestParam("album_id") int song_id, Model model) {
		AlbumDto albumDto = adminService.getSearchAlbumId(song_id);
		model.addAttribute("albumDto", albumDto);
		return "/admin/search/albumInfo";
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SongModifyValidator songModifyValidator = new SongModifyValidator();
		binder.addValidators(songModifyValidator);
	}
}
