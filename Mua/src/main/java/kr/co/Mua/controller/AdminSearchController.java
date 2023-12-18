package kr.co.Mua.controller;

import java.util.Date;

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
import kr.co.Mua.bean.UserBean;
import kr.co.Mua.bean.notAcceptUserBean;
import kr.co.Mua.service.AdminService;
import kr.co.Mua.service.UserService;
import kr.co.Mua.validator.AdminModifyValidator;

@Controller
@RequestMapping(value="/admin/search/")
public class AdminSearchController {
	
	@Autowired
	private AdminService adminService;
	
	@Autowired
	private UserService userService;
	
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
	
	@RequestMapping(value="/userId")
	public String userID() {
		return "/admin/search/userId";
	}
	
	@RequestMapping(value = "/userInfo")
	public String userInfo(@RequestParam("user_num") int user_num,@ModelAttribute("infoUserBean") UserBean userBean) {
		UserBean tempUserBean = adminService.getUserNum(user_num);
		if(tempUserBean == null) {
			return "/admin/InvalidValue";
		}
		userBean.setUser_name(tempUserBean.getUser_name());
		userBean.setUser_email(tempUserBean.getUser_email());
		userBean.setUser_registdate(tempUserBean.getUser_registdate());
		userBean.setUser_tel(tempUserBean.getUser_tel());
		userBean.setUser_num(tempUserBean.getUser_num());
		
		return "/admin/search/userInfo";
	}
	
	@RequestMapping(value = "/modifyUser")
	public String userModify(@ModelAttribute("infoUserBean") UserBean userBean) {
		String name = userBean.getUser_name();
		int num = userBean.getUser_num();
		userService.modifyUserName(name, num);
		return "/admin/search/success";
	}
	
	@RequestMapping(value = "/banish")
	public String userBan(@RequestParam("user_num1") int user_num1,
						  @RequestParam("admin_num1") int admin_num1,
						  @RequestParam("end_date") String end_date) {
		adminService.insertNotAccepteUser(user_num1, admin_num1, end_date);
		return "/admin/search/success";
	}
	
	@RequestMapping(value = "/banish-deny")
	public String userBan_deny(@RequestParam("user_num2")int user_num1) {
		adminService.deleteNotAccepteUser(user_num1);
		return "/admin/search/success";
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		AdminModifyValidator songModifyValidator = new AdminModifyValidator();
		binder.addValidators(songModifyValidator);
	}
}
