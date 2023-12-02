package kr.co.Mua.controller;

<<<<<<< HEAD
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import kr.co.Mua.bean.ArtistDto;
import kr.co.Mua.bean.SongDto;
import kr.co.Mua.service.SearchService;

@RestController
@PropertySource("/WEB-INF/properties/option.properties")
public class RestApiController {

	@Value("${resources.path}")
	private String path;
	
	@Autowired
	private SearchService searchService;
	
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
	
	@RequestMapping(value = "/changeSong_info", produces = "application/json; charset=UTF-8")
	@ResponseBody
	public String chageSong_info(@RequestParam("song_id") int song_id) {
	    SongDto infoSongDto = searchService.getSong_info(song_id);
	    ArrayList<ArtistDto> artistList = searchService.getBriefArtist(song_id);
	    String lyric = getLyric(infoSongDto.getLyrics());
	    
	    // 데이터를 JSON 문자열로 변환
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
	        jsonString = ""; // 에러 처리 필요
	    }
	    
	    return jsonString;
	}
	
=======
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import kr.co.Mua.service.UserService;

@RestController
public class RestApiController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/user/checkUserIdExist/{user_id}")
	public String checkUserIdExit(@PathVariable String user_id) {
		
		boolean check = userService.checkUserIDExit(user_id);
		
		return check+"";
		
	}
>>>>>>> refs/heads/김진욱
}
