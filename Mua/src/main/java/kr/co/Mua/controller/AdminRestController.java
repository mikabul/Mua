package kr.co.Mua.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.co.Mua.bean.AlbumDto;
import kr.co.Mua.bean.ArtistDto;
import kr.co.Mua.bean.SongDto;
import kr.co.Mua.bean.UserBean;
import kr.co.Mua.bean.notAcceptUserBean;
import kr.co.Mua.service.AdminService;

@RestController
@RequestMapping(value="/admin/search/ajax")
public class AdminRestController {
	
	@Autowired
	private AdminService adminService;
	
	int maxView = 20;
	int maxIndex;
	int maxPage;
	int page;
	int loadPage[];
	int endView;

	@RequestMapping(value="/song")
	@ResponseBody
	public String ajaxSong(@RequestParam("str") String str,
							@RequestParam(value="index", defaultValue = "1") int index,
							@RequestParam(value="endIndex", defaultValue = "20") int endIndex) {
		
		String jsonString = "";
		ObjectMapper objectMapper = new ObjectMapper();
		Map<String, Object> map = new HashMap<String, Object>();
		
		ArrayList<SongDto> songList = adminService.getSearchSongName(str, index, endIndex);
		getLoadPage(index, endIndex, str, "song");
		
		map.put("songList", songList);
		map.put("loadPage", loadPage);
		map.put("maxView", maxView);
		map.put("page", page);
		
		try {
			jsonString = objectMapper.writeValueAsString(map);
		} catch (Exception e) {
			System.out.println("AdminAjaxSong 문제 발생");
			System.out.println(e);
		}
		return jsonString;
	}
	
	@RequestMapping(value="/artist")
	@ResponseBody
	public String ajaxArtist(@RequestParam("str") String str,
							@RequestParam(value="index", defaultValue = "1") int index,
							@RequestParam(value="endIndex", defaultValue = "20") int endIndex) {
		
		String jsonString = "";
		ObjectMapper objectMapper = new ObjectMapper();
		Map<String, Object> map = new HashMap<String, Object>();
		
		ArrayList<ArtistDto> artistList = adminService.getSearchArtistName(str, index, endIndex);
		getLoadPage(index, endIndex, str, "artist");
		
		map.put("artistList", artistList);
		map.put("loadPage", loadPage);
		map.put("maxView", maxView);
		map.put("page", page);
		
		try {
			jsonString = objectMapper.writeValueAsString(map);
		} catch (Exception e) {
			System.out.println("AdminAjaxArtist 문제 발생");
			System.out.println(e);
		}
		return jsonString;
	}
	
	@RequestMapping(value="/album")
	@ResponseBody
	public String ajaxAlbum(@RequestParam("str") String str,
							@RequestParam(value="index", defaultValue = "1") int index,
							@RequestParam(value="endIndex", defaultValue = "20") int endIndex) {
		
		String jsonString = "";
		ObjectMapper objectMapper = new ObjectMapper();
		Map<String, Object> map = new HashMap<String, Object>();
		
		ArrayList<AlbumDto> albumList = adminService.getSearchalbumName(str, index, endIndex);
		getLoadPage(index, endIndex, str, "album");
		
		map.put("albumList", albumList);
		map.put("loadPage", loadPage);
		map.put("maxView", maxView);
		map.put("page", page);
		
		try {
			jsonString = objectMapper.writeValueAsString(map);
		} catch (Exception e) {
			System.out.println("AdminAjaxAlbum 문제 발생");
			System.out.println(e);
		}
		return jsonString;
	}
	
	@RequestMapping(value="/user")
	@ResponseBody
	public String ajaxUser(@RequestParam("str") String str,
							@RequestParam(value="index", defaultValue = "1") int index,
							@RequestParam(value="endIndex", defaultValue = "20") int endIndex) {
		
		String jsonString = "";
		ObjectMapper objectMapper = new ObjectMapper();
		Map<String, Object> map = new HashMap<String, Object>();
		
		ArrayList<UserBean> userList = adminService.searchUserName(str, index, endIndex);
		getLoadPage(index, endIndex, str, "user_info");
		
		map.put("userList", userList);
		map.put("loadPage", loadPage);
		map.put("maxView", maxView);
		map.put("page", page);
		
		try {
			jsonString = objectMapper.writeValueAsString(map);
		} catch (Exception e) {
			System.out.println("AdminAjaxUser 문제 발생");
			System.out.println(e);
		}
		return jsonString;
	}
	
	@RequestMapping(value ="/checkBaned")
	public String checkBaned(@RequestParam("user_num") int user_num) {
		notAcceptUserBean tempNotAcceptUserBean = adminService.getBanishedUser(user_num);
		
		if(tempNotAcceptUserBean == null) {
			return true+"";
		}else {
			return false+"";
		}
		
	}
	
	private void getLoadPage(int index, int endIndex, String str, String type) {
		if(type.equals("user_info")) {
			maxIndex = adminService.getUserMaxIndex(str, type);
		}else {
			maxIndex = adminService.getMaxIndex(str, type);			
		}
		maxPage = maxIndex / maxView + 1;
		page = index / maxView;
		
		if(maxIndex % 20 == 0) {
			maxPage -= 1;
		}
		
		if(maxPage <= 10) { // 최대 페이지 10개이하
			loadPage = new int[maxPage];
			for(int i = 1; i <= maxPage; i++) {
				loadPage[i - 1] = i;
			}
		} else { // 최대 페이지 10개 초과
			loadPage = new int[10];
			if(page < 6) { // 6페이지 미만
				for(int i = 1; i <= 10; i++) {
					loadPage[i - 1] = i;
				}
			} else if(page + 6 > maxPage) { // 최대페이지의 -5
				int j = 9;
				for(int i = maxPage; i > maxPage - 10; i-- ) {
					loadPage[j] = i;
					j--;
				}
			} else {
				int j = 0;
				for(int i = page; i <= 10; i++) {
					loadPage[j] = i;
					j++;
				}
			}
		}
	}
}
