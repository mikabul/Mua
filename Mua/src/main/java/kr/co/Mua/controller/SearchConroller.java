package kr.co.Mua.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.co.Mua.bean.AlbumDTO;
import kr.co.Mua.bean.ArtistDTO;
import kr.co.Mua.bean.SearchResultDTO;
import kr.co.Mua.bean.SongDTO;

@Controller
@RequestMapping(value = "/search", method = RequestMethod.GET)
public class SearchConroller {

	@GetMapping("/main")
	public String main(HttpServletRequest res, Model model) {
		ArrayList<SearchResultDTO> arrayResultDTOs = new ArrayList<SearchResultDTO>();
		
		String search_value = res.getParameter("search_value");
		String search_where = res.getParameter("search_where").trim();
		String search_url_first = "https://www.melon.com/search/total/index.htm?q=";
		String search_url_last = "&section=&mwkLogType=T";

		System.out.println(search_value);
		System.out.println(search_where);

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
					String hrefArtist = tempArtist.attr("href").trim();
					System.out.println(tempArtist);
					int artist_num = Integer.parseInt(
							hrefArtist.substring(hrefArtist.lastIndexOf("goArtistDetail") + 16, 
							hrefArtist.lastIndexOf("melon")-3));
					artistDTO.setArtist_name(tempArtist.text());
					artistDTO.setArtist_num(artist_num);

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
		
		for(int i = 0; i < arrayResultDTOs.size(); i++) {
			System.out.println(arrayResultDTOs.get(i).getSongDTO().getSong_name());
		}

		return "search/main";
	}
	
//	@GetMapping("/SongInfo")
//	public String songInfo(HttpServletRequest res) {
//		
//	}

}
