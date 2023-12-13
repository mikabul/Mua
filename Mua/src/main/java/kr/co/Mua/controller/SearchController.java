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

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.co.Mua.bean.AlbumDto;
import kr.co.Mua.bean.ArtistDto;
import kr.co.Mua.bean.SearchResultDto;
import kr.co.Mua.bean.SongDto;
import kr.co.Mua.service.SearchService;

@Controller
@RequestMapping(value="/search")
public class SearchController {
	
	@Autowired
	private SearchService searchService;
	
	private boolean isGetData;
	
	@GetMapping("/main")
	public String main(@RequestParam("type") String type,
						@RequestParam("str") String str,
						@RequestParam("index") int index,
						@RequestParam("maxView") int maxView,Model model) {
		
		int maxIndex = searchService.getMaxIndex(str, type);
		int maxPage = maxIndex / maxView + 1;
		int page = index / maxView + 1;
		int loadPage[];
		int endView = maxView * page;
		if(type.equals("album") || type.equals("artist")) {
			endView += 1;
		}
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
		
		model.addAttribute("type", type);
		model.addAttribute("index", index);
		model.addAttribute("maxView", maxView);
		model.addAttribute("loadPage", loadPage);
		model.addAttribute("str", str);
		
		switch (type) {
		case "song":
			ArrayList<SearchResultDto> songList = searchService.getSearch_song(str, index, endView);
			model.addAttribute("songList", songList);
			return "/search/song_main";
		case "artist":
			ArrayList<ArtistDto> artistList = searchService.getSearch_artist(str, index, endView);
			model.addAttribute("artistList", artistList);
			return "/search/artist_main";
		case "album":
			ArrayList<SearchResultDto> albumList = searchService.getSearch_album(str, index, endView);
			model.addAttribute("albumList", albumList);
			return "/search/album_main";
		}
		
		return "/NotAccept";
	}
	
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
		
		// 스크립트에서 이용하기위해 JSON으로 변환
		ObjectMapper objectMapper = new ObjectMapper();
		
		try {
			String artistListJson = objectMapper.writeValueAsString(artistList);
			model.addAttribute("artistListJson", artistListJson);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		model.addAttribute("infoSongDto", infoSongDto);
		model.addAttribute("artistList", artistList);
		model.addAttribute("songList", songList);
		model.addAttribute("song_id", song_id);
		
		return "/search/song_info";
	}
	
	@GetMapping("/artist_info")
	public String artist_info(@RequestParam("artist_id") int artist_id, Model model) {
		
		ArtistDto artistDto = searchService.getArtist_info(artist_id);
		ArrayList<AlbumDto> albumList = searchService.getArtist_album_info(artist_id);
		
		model.addAttribute("artistDto", artistDto);
		model.addAttribute("albumList", albumList);
		
		return "/search/artist_info";
	}
	
	@GetMapping("/album_info")
	public String album_info(@RequestParam("album_id") int album_id, Model model) {
		
		int index = 1;
		int maxView = 10;
		// 검색결과의 최대 갯수
		int maxIndex = searchService.getAlbum_Song_MaxIndex(album_id);
		// 최대 페이지
		int maxPage = maxIndex / maxView + 1;
		// 현재 페이지
		int page = index / maxView + 1;
		// 페이지 버튼 배열
		int loadPage[];
		// 페이징 처리를 위한 쿼리값
		int endView = maxView * page;
		
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
		
		AlbumDto albumDto = searchService.getAlbum_info(album_id);
		ArrayList<ArtistDto> artistList = searchService.getAlbum_Artist_info(album_id);
		ArrayList<SearchResultDto> searchResultList = searchService.getAlbum_Song(album_id, index, endView);
		
		// 자바 스크립트에서 사용하기 위한 JSON
		ObjectMapper objectMapper = new ObjectMapper();
		
		try {
			String artistListJson = objectMapper.writeValueAsString(artistList);
			model.addAttribute("artistListJson", artistListJson);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		model.addAttribute("albumDto", albumDto);
		model.addAttribute("artistList", artistList);
		model.addAttribute("searchResultList", searchResultList);
		// 페이징 처리
		model.addAttribute("maxView", maxView);
		model.addAttribute("loadPage", loadPage);
		
		return "/search/album_info";
	}
	
	
}
