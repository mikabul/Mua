package kr.co.Mua.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.Mua.bean.ArtistDto;
import kr.co.Mua.bean.SongDto;
import kr.co.Mua.service.SearchService;

@Controller
@RequestMapping(value="/search")
public class SearchController {
	
	@Autowired
	private SearchService searchService;
	
	private boolean isGetData;
	
	@GetMapping("/song_info")
	public String song_info(@RequestParam("song_id") int song_id, Model model) {
		
		SongDto infoSongDto = searchService.getSong_info(song_id);
		ArrayList<ArtistDto> artistList = searchService.getBriefArtist(song_id);
		List<SongDto> songList = new ArrayList<SongDto>();
		
		// 아티스트의 아이디를 통해
		Iterator<ArtistDto> artistListIt = artistList.iterator();
		while(artistListIt.hasNext()) {
			ArtistDto tempArtistDto = artistListIt.next();
			ArrayList<SongDto> tempSongList = searchService.getMoreSong_info(tempArtistDto.getArtist_id());
			Iterator<SongDto> tempSongListIt = tempSongList.iterator();
			while(tempSongListIt.hasNext()) {
				SongDto tempSongDto = tempSongListIt.next();
				isGetData = true;
				
				if(songList.size() == 0) {
					songList.add(tempSongDto);
				} else {
					int index = songList.size();
					for(int i = 0; i < index; i++) {
						
						if(songList.get(i).getSong_name().equals(tempSongDto.getSong_name())) {
							isGetData = false;
							break;
						}
					}
					if(isGetData) {
						songList.add(tempSongDto);
					}
				}
				
			}
			
		}
		
		model.addAttribute("infoSongDto", infoSongDto);
		model.addAttribute("artistList", artistList);
		model.addAttribute("songList", songList);
		model.addAttribute("song_id", song_id);
		
		return "/search/song_info";
	}
	
	@GetMapping("/artist_info")
	public String artist_info() {
		
		return "/search/song_info";
	}
	
	@GetMapping("/album_info")
	public String album_info() {
		
		return "/search/album_info";
	}
}
