package kr.co.Mua.controller;

import java.util.ArrayList;

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
import kr.co.Mua.bean.ReviewDto;
import kr.co.Mua.bean.SongDto;
import kr.co.Mua.bean.UserBean;
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
	
	@RequestMapping(value="/emptyNation")
	public String emptyNation() {
		return "/admin/search/emptyNation";
	}
	
	@RequestMapping(value = "/review_report")
	public String review_report(Model model) {
		
		ArrayList<ReviewDto> reviewList = adminService.getReviewReport();
		model.addAttribute("reviewList", reviewList);
		
		return "/admin/search/review_report";
	}
	
	@RequestMapping(value = "/deleteReview")
	public String deleteReview(@RequestParam("report_num") int report_num,
								@RequestParam("review_num") int review_num) {
		
		adminService.deleteUserReview( review_num);
		adminService.deleteReport(report_num);
		
		return "/admin/search/review_report";
	}
	
	@RequestMapping(value = "/passReview")
	public String passReview(@RequestParam("report_num") int report_num) {
		
		adminService.deleteReport(report_num);
		
		return "/admin/search/review_report";
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
			if(modifySongDto.getSong_thumbnail().equals("-")) {
				modifySongDto.setSong_thumbnail("default.jpg");
			}
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
			if(modifyArtistDto.getArtist_thumbnail().equals("-")) {
				modifyArtistDto.setArtist_thumbnail("default.jpg");
			}
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
			if(modifyAlbumDto.getAlbum_thumbnail().equals("-")) {
				modifyAlbumDto.setAlbum_thumbnail("default.jpg");
			}
			adminService.updateAlbum(modifyAlbumDto);
			return "/admin/search/success";
		}
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
		AdminModifyValidator adminModifyValidator = new AdminModifyValidator();
		binder.addValidators(adminModifyValidator);
	}
}
