package kr.co.Mua.controller;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import kr.co.Mua.bean.ArtistDto;
import kr.co.Mua.bean.SearchResultDto;
import kr.co.Mua.bean.SongDto;
import kr.co.Mua.service.MailSendService;
import kr.co.Mua.service.SearchService;
import kr.co.Mua.service.UserService;

@RestController
public class RestApiController {
	
	@Value("${resources.path}")
	private String path;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private SearchService searchService;
	
	@Autowired
	private MailSendService mailService;
	
	//유저 중복체크
	@GetMapping("/user/checkUserIdExist/{user_id}")
	public String checkUserIdExit(@PathVariable String user_id) {
		
		boolean check = userService.checkUserIDExit(user_id);
		
		return check+"";
		
	}
	
	//이메일 중복체크
	@GetMapping("/user/checkUserEmailExit/{user_email}")
	public String checkUserEmailExit(@PathVariable String user_email) {
		
		boolean check = userService.checkUserEmailExit(user_email);
		
		return check+"";
		
	}
	
	// 이메일 중복체크후 인증요청 발송
	@GetMapping("/user/checkCertificationCode/{user_email}")
	public String mailCheck(@PathVariable String user_email) {
		System.out.println("이메일 인증 요청 들어옴.");
		System.out.println("이메일 인증 이메일 : "+user_email);
		return mailService.joinEmail(user_email);
	}
	
	@RequestMapping(value = "/getLyric", produces = "application/text; charset=UTF-8")
	public String getLyric(@RequestParam("file_name") String file_name) {
		
		String url = path + "lyric/" + file_name;
		String lyric = "";
		
		try {
			FileInputStream fis = new FileInputStream(url);
			InputStreamReader isr = new InputStreamReader(fis, StandardCharsets.UTF_8);
			BufferedReader br = new BufferedReader(isr);
			
			while(br.ready()) {
				lyric += br.readLine();
			}
			
			br.close();
			isr.close();
			fis.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(lyric);
		return lyric;
	}
	
	// 노래정보 변경
	@RequestMapping(value = "/changeSong_info", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public String chageSong_info(@RequestParam("song_id") int song_id) {
	    SongDto infoSongDto = searchService.getSong_info(song_id);
	    ArrayList<ArtistDto> artistList = searchService.getBriefArtist(song_id);
	    
	    String lyric;
	    if(!infoSongDto.getLyrics().equals("-")) {
	    	lyric = getLyric(infoSongDto.getLyrics());
	    } else {
	    	lyric = "-";
	    }
	    
	    // 받아온 정보를 JSON형식으로 변환
	    ObjectMapper objectMapper = new ObjectMapper();
	    Map<String, Object> responseData = new HashMap<>();
	    responseData.put("infoSongDto", infoSongDto);
	    responseData.put("artistList", artistList);
	    responseData.put("lyric", lyric);
	    String jsonString;
	    try {
	        jsonString = objectMapper.writeValueAsString(responseData);
	    } catch (JsonProcessingException e) {
	        e.printStackTrace();
	        jsonString = "";
	    }
	    
	    return jsonString;
	}
	
	// 앨범의 노래리스트
	@RequestMapping(value = "/changeSongList", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public String changeSongList(@RequestParam("index") int index,
								@RequestParam("maxView") int maxView,
								@RequestParam("album_id") int album_id) {
		ArrayList<SearchResultDto> searchResultList = searchService.getAlbum_Song(album_id, index, maxView);
		
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonString = "";
		 try {
			 jsonString = objectMapper.writeValueAsString(searchResultList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return jsonString;
	}
	
	// 좋아요
	@RequestMapping(value = "/thumbup")
	@ResponseBody
	public String thumbup(@RequestParam("user_num") int user_num,
							@RequestParam("id") int id,
							@RequestParam("infoType") String infoType) {
		String likeIcon = "heart-solid.png";
		String disLikeIcon = "heart-regula.png";
		String resultJson = "";
		
		ObjectMapper objectMapper = new ObjectMapper();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			if(searchService.getUserThumbup(id, user_num, infoType) == 0) {
				searchService.thumbup(id, user_num, infoType);
				int thumbupCount = searchService.getThumbup(id, infoType);
				
				map.put("icon", likeIcon);
				map.put("thumbupCount", thumbupCount);
				
				resultJson = objectMapper.writeValueAsString(map);
			} else {
				searchService.delThumbup(id, user_num, infoType);
				int thumbupCount = searchService.getThumbup(id, infoType);
				
				map.put("icon", disLikeIcon);
				map.put("thumbupCount", thumbupCount);
				
				resultJson = objectMapper.writeValueAsString(map);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultJson;
	}
	
	// 좋아요 여부 판단
	@RequestMapping(value = "/getThumbup")
	public String getThumbup(@RequestParam("user_num") int user_num,
							@RequestParam("id") int id,
							@RequestParam("infoType") String infoType) {
		
		String likeIcon = "heart-solid.png";
		String disLikeIcon = "heart-regula.png";
		
		if(searchService.getUserThumbup(id, user_num, infoType) == 0) {
			return disLikeIcon;
		} else {
			return likeIcon;
		}
	}
}
