package kr.co.Mua.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fasterxml.jackson.databind.ObjectMapper;

import kr.co.Mua.bean.AlbumDto;
import kr.co.Mua.bean.ArtistDto;
import kr.co.Mua.bean.ReviewDto;
import kr.co.Mua.bean.SearchResultDto;
import kr.co.Mua.bean.SongDto;
import kr.co.Mua.bean.UserBean;
import kr.co.Mua.service.SearchService;

@Controller
@RequestMapping(value="/search")
public class SearchController {
	
	@Autowired
	private SearchService searchService;
	
	@Resource(name="loginUserBean")
	private UserBean loginUserBean;
	
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
		
		if(maxPage <= 10) { // 理쒕� �럹�씠吏� 10媛쒖씠�븯
			loadPage = new int[maxPage];
			for(int i = 1; i <= maxPage; i++) {
				loadPage[i - 1] = i;
			}
		} else { // 理쒕� �럹�씠吏� 10媛� 珥덇낵
			loadPage = new int[10];
			if(page < 6) { // 6�럹�씠吏� 誘몃쭔
				for(int i = 1; i <= 10; i++) {
					loadPage[i - 1] = i;
				}
			} else if(page + 6 > maxPage) { // 理쒕��럹�씠吏��쓽 -5
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
		model.addAttribute("maxPage", maxPage);
		model.addAttribute("page", page);
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
	public String song_info(@ModelAttribute("userReviewDto") ReviewDto userReviewDto,
							@RequestParam("song_id") int song_id, Model model) {
		
		SongDto infoSongDto = searchService.getSong_info(song_id);
		ArrayList<ArtistDto> artistList = searchService.getBriefArtist(song_id);
		List<SongDto> songList = new ArrayList<SongDto>();
		
		if(infoSongDto == null) {
			return "/NotAccept";
		}
		
		// �븘�떚�뒪�듃�쓽 �븘�씠�뵒瑜� �넻�빐
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
		
		// 리뷰
		ReviewDto tempReviewDto = searchService.getUserReview("song", song_id, loginUserBean.getUser_num());
		if(tempReviewDto != null) {
			userReviewDto.setReview_num(tempReviewDto.getReview_num());
			userReviewDto.setType_id(tempReviewDto.getType_id());
			userReviewDto.setUser_num(tempReviewDto.getUser_num());
			userReviewDto.setUser_name(tempReviewDto.getUser_name());
			userReviewDto.setFlag(tempReviewDto.getFlag());
			userReviewDto.setReview_content(tempReviewDto.getReview_content());
			userReviewDto.setReview_date(tempReviewDto.getReview_date());
			userReviewDto.setReview_point(tempReviewDto.getReview_point());
			userReviewDto.setSuggestion(tempReviewDto.getSuggestion());
		}
		
		// 理쒓렐 蹂� �끂�옒
		if(loginUserBean.isUserLogin()) {
			if(searchService.getViewed_song(song_id, loginUserBean.getUser_num()) == null){
				searchService.insertViewed_song(song_id, loginUserBean.getUser_num());
			} else {
				searchService.updateViewed_song(song_id, loginUserBean.getUser_num());
			}
		}
		
		return "/search/song_info";
	}
	
	@GetMapping("/artist_info")
	public String artist_info(@ModelAttribute("userReviewDto") ReviewDto userReviewDto,
							@RequestParam("artist_id") int artist_id, Model model) {
		
		ArtistDto artistDto = searchService.getArtist_info(artist_id);
		ArrayList<AlbumDto> albumList = searchService.getArtist_album_info(artist_id);
		
		if(artistDto == null) {
			return "/NotAccept";
		}
		
		model.addAttribute("artistDto", artistDto);
		model.addAttribute("albumList", albumList);
		
		// 由щ럭
		ReviewDto tempReviewDto = searchService.getUserReview("artist", artist_id, loginUserBean.getUser_num());
		if(tempReviewDto != null) {
			userReviewDto.setReview_num(tempReviewDto.getReview_num());
			userReviewDto.setType_id(tempReviewDto.getType_id());
			userReviewDto.setUser_num(tempReviewDto.getUser_num());
			userReviewDto.setUser_name(tempReviewDto.getUser_name());
			userReviewDto.setFlag(tempReviewDto.getFlag());
			
			userReviewDto.setReview_content(tempReviewDto.getReview_content().replace("<br>", ""));
			userReviewDto.setReview_date(tempReviewDto.getReview_date());
			userReviewDto.setReview_point(tempReviewDto.getReview_point());
			userReviewDto.setSuggestion(tempReviewDto.getSuggestion());
		}
		return "/search/artist_info";
	}
	
	@GetMapping("/album_info")
	public String album_info(@ModelAttribute("userReviewDto") ReviewDto userReviewDto,
							@RequestParam("album_id") int album_id, Model model) {
		
		int index = 1;
		int maxView = 10;
		// 寃��깋寃곌낵�쓽 理쒕� 媛��닔
		int maxIndex = searchService.getAlbum_Song_MaxIndex(album_id);
		// 理쒕� �럹�씠吏�
		int maxPage = maxIndex / maxView + 1;
		// �쁽�옱 �럹�씠吏�
		int page = index / maxView + 1;
		// �럹�씠吏� 踰꾪듉 諛곗뿴
		int loadPage[];
		// �럹�씠吏� 泥섎━瑜� �쐞�븳 荑쇰━媛�
		int endView = maxView * page;
		
		if(maxIndex % 20 == 0) {
			maxPage -= 1;
		}
		
		if(maxPage <= 10) { // 理쒕� �럹�씠吏� 10媛쒖씠�븯
			loadPage = new int[maxPage];
			for(int i = 1; i <= maxPage; i++) {
				loadPage[i - 1] = i;
			}
		} else { // 理쒕� �럹�씠吏� 10媛� 珥덇낵
			loadPage = new int[10];
			if(page < 6) { // 6�럹�씠吏� 誘몃쭔
				for(int i = 1; i <= 10; i++) {
					loadPage[i - 1] = i;
				}
			} else if(page + 6 > maxPage) { // 理쒕��럹�씠吏��쓽 -5
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
		
		if(albumDto == null) {
			return "/NotAccept";
		}
		
		// �옄諛� �뒪�겕由쏀듃�뿉�꽌 �궗�슜�븯湲� �쐞�븳 JSON
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
		// �럹�씠吏� 泥섎━
		model.addAttribute("maxView", maxView);
		model.addAttribute("loadPage", loadPage);
		
		// 由щ럭
		ReviewDto tempReviewDto = searchService.getUserReview("album", album_id, loginUserBean.getUser_num());
		if(tempReviewDto != null) {
			userReviewDto.setReview_num(tempReviewDto.getReview_num());
			userReviewDto.setType_id(tempReviewDto.getType_id());
			userReviewDto.setUser_num(tempReviewDto.getUser_num());
			userReviewDto.setUser_name(tempReviewDto.getUser_name());
			userReviewDto.setFlag(tempReviewDto.getFlag());
			userReviewDto.setReview_content(tempReviewDto.getReview_content());
			userReviewDto.setReview_date(tempReviewDto.getReview_date());
			userReviewDto.setReview_point(tempReviewDto.getReview_point());
			userReviewDto.setSuggestion(tempReviewDto.getSuggestion());
		}
		return "/search/album_info";
	}
	
	@PostMapping("/insertReview")
	public String insertReview(@ModelAttribute("userRrviewDto") ReviewDto userReviewDto) {
		
		String review_content = userReviewDto.getReview_content();
		int subIndex = 50;
		
		if(review_content.length() > subIndex) {
			String subString = "";
			for(int i = 0; i < review_content.length(); i += subIndex) {
				try {
					subString += review_content.substring(i, i + subIndex) + "<br>";
				} catch (Exception e) {
					subString += review_content.substring(i);
				}
			}
			review_content = subString;
		}
		userReviewDto.setReview_content(review_content);
		searchService.insertUserReview(userReviewDto);
		
		switch(userReviewDto.getFlag()) {
		case "song":
			return "redirect:/search/song_info?song_id=" + userReviewDto.getType_id();
		case "artist":
			return "redirect:/search/artist_info?artist_id=" + userReviewDto.getType_id();
		case "album":
			return "redirect:/search/album_info?album_id=" + userReviewDto.getType_id();
		default:
			return"redirect:/main";
		}
	}
	
	@PostMapping("rewriteReview")
	public String rewriteReview(@ModelAttribute("userRrviewDto") ReviewDto userReviewDto) {
		
		String review_content = userReviewDto.getReview_content();
		int subIndex = 50;
		
		if(review_content.length() > subIndex) {
			String subString = "";
			for(int i = 0; i < review_content.length(); i += subIndex) {
				try {
					subString += review_content.substring(i, i + subIndex) + "<br>";
				} catch (Exception e) {
					subString += review_content.substring(i);
				}
			}
			review_content = subString;
		}
		userReviewDto.setReview_content(review_content);
		searchService.rewriteUserReview(userReviewDto);
		
		switch(userReviewDto.getFlag()) {
		case "song":
			return "redirect:/search/song_info?song_id=" + userReviewDto.getType_id();
		case "artist":
			return "redirect:/search/artist_info?artist_id=" + userReviewDto.getType_id();
		case "album":
			return "redirect:/search/album_info?album_id=" + userReviewDto.getType_id();
		default:
			return"redirect:/main";
		}
	}
}
