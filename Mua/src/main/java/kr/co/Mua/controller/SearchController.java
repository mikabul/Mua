package kr.co.Mua.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.Mua.bean.SongDTO;
import kr.co.Mua.service.SearchService;

@Controller
@RequestMapping(value="/search")
public class SearchController {
	
	@Autowired
	private SearchService searchService;
	
	@GetMapping("/song_info")
	public String song_info(@RequestParam("song_id") int song_id, Model model) {
		
		SongDTO infoSongDTO = searchService.getSong_info(song_id);
		model.addAttribute("infoSongDTO", infoSongDTO);
		
		return "/search/song_info";
	}
}
