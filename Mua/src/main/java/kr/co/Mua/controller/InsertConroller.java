package kr.co.Mua.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.Mua.bean.AlbumDTO;
import kr.co.Mua.bean.ArtistDTO;
import kr.co.Mua.bean.SearchResultDTO;
import kr.co.Mua.bean.SongDTO;
import kr.co.Mua.service.InsertDBService;

@Controller
@RequestMapping(value = "/insert", method = RequestMethod.GET)
public class InsertConroller {
	
	@Autowired
	InsertDBService insertDBService;

	@GetMapping("/main")
	public String main(HttpServletRequest res, Model model) {
		ArrayList<SearchResultDTO> arrayResultDTOs = new ArrayList<SearchResultDTO>();
		
		String search_value = res.getParameter("search_value");
		String search_where = res.getParameter("search_where").trim();
		String search_url_first = "https://www.melon.com/search/total/index.htm?q=";
		String search_url_last = "&section=&mwkLogType=T";

		Document doc;

		try {

			doc = Jsoup.connect(search_url_first + search_value + search_url_last).get();

			Elements elements = doc.select("form#" + search_where + " div table tr");

			for (Element ele : elements) {
				SearchResultDTO searchResultDTO = new SearchResultDTO();
				
				SongDTO songDTO = new SongDTO();
				ArtistDTO artistDTO = new ArtistDTO();
				AlbumDTO albumDTO = new AlbumDTO();

				Elements tempSong = ele.select("td:nth-child(3) div.ellipsis a").eq(1);
				Elements tempArtist = ele.select("td:nth-child(4) div#artistName a").eq(0);
				Elements tempAlbum = ele.select("td:nth-child(5) div.ellipsis a");

				if (tempSong.text().length() != 0) {

					//-----------------song-----------------------
					String hrefSong = tempSong.attr("href").trim();
					int song_id = Integer.parseInt(hrefSong.substring(hrefSong.lastIndexOf(",") + 1, hrefSong.lastIndexOf(")")));
					songDTO.setSong_name(tempSong.text());
					songDTO.setSong_id(song_id);

					//------------------artist-----------------
					artistDTO.setArtist_name(tempArtist.text());
					if(!artistDTO.getArtist_name().equals("")) {
						String hrefArtist = tempArtist.attr("href").trim();
						int artist_num = Integer.parseInt(
								hrefArtist.substring(hrefArtist.lastIndexOf("goArtistDetail") + 16, 
								hrefArtist.lastIndexOf("melon")-3));
						artistDTO.setArtist_id(artist_num);
					} else {
						artistDTO.setArtist_id(-1);
						artistDTO.setArtist_name("Various Artists");
					}
					
					//------------------album--------------------
					int album_num = Integer.parseInt(tempAlbum.toString().substring(
							tempAlbum.toString().lastIndexOf("goAlbumDetail('") + 15,
							tempAlbum.toString().lastIndexOf("title") - 5).trim());
					albumDTO.setAlbum_name(tempAlbum.text());
					albumDTO.setAlbum_id(album_num);
					
					searchResultDTO.setSongDTO(songDTO);
					searchResultDTO.setArtistDTO(artistDTO);
					searchResultDTO.setAlbumDTO(albumDTO);
					
					arrayResultDTOs.add(searchResultDTO);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("arrayResultDTOs", arrayResultDTOs);
		
		return "search/main";
	}
	
	@GetMapping("/songinfo")
	public String songInfo(@RequestParam("song_id") int song_id) {
		
		insertDBService.insertDB(song_id);
		
		return "/search/main";
	}

}
