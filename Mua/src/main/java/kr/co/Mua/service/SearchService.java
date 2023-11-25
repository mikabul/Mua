package kr.co.Mua.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.Mua.bean.AlbumDTO;
import kr.co.Mua.bean.ArtistDTO;
import kr.co.Mua.bean.SongDTO;
import kr.co.Mua.dao.InserDBDao;

@Service
public class SearchService {
	
	@Autowired
	private InserDBDao insertDBDao;

	private SongDTO tempSongDTO;
	private ArtistDTO tempArtsitDTO;
	private AlbumDTO tempAlbumDTO;

	private boolean song_has;
	private boolean artist_has;
	private boolean album_has;

	private int artist_num;
	private int album_num;

	public void insertDB(int song_id) {

		tempArtsitDTO = new ArtistDTO();
		tempAlbumDTO = new AlbumDTO();
		
		getSong(song_id);
		getArtist();
		getAlbum();

		if(insertDBDao.getSongDTO(tempSongDTO, tempArtsitDTO) == null) {
			System.out.println("SONG성공이다!");
		}
		
		if(insertDBDao.getArtistDTO(tempArtsitDTO) == null) {
			System.out.println("ARTIST성공이다!");
		}
		
		if(insertDBDao.getAlbumDTO(tempAlbumDTO, tempArtsitDTO) == null) {
			System.out.println("ALBUM성공이다!");
		}
	}

	// 노래 정보를 가져옴
	private void getSong(int song_id) {

		tempSongDTO = new SongDTO();

		String url = "https://www.melon.com/song/detail.htm?songId=" + song_id;

		Document doc;

		// 노래 정보를 크롤링
		try {
			doc = Jsoup.connect(url).get();

			Elements elements = doc.select("div.entry");

			// 노래의 이름을 가져옴
			Elements name_elements = elements.select("div.song_name").not("strong");
			String Song_name = name_elements.text().substring(2).trim();
			tempSongDTO.setSong_name(Song_name);
			
			// 노래의 발매일을 가져옴
			Elements release_data_elements = elements.select("div.meta dl.list dd").eq(1);
			String release_date = release_data_elements.text().trim();
			tempSongDTO.setRelease_date(release_date);
			
			// 노래의 장르를 가져옴
			Elements genre_elements = elements.select("div.meta dl.list dd").eq(2);
			String song_genre = genre_elements.text().trim();
			tempSongDTO.setSong_genre(song_genre);

			// 아티스트 번호 가져오기
			// 아티스트 이름이 "Various Artists" 일경우 아티스트 페이지가 존재하지 않음
			// 예외처리
			if(elements.select("div.artist").text().equals("Various Artists")) {
				artist_num = -1;
			} else {
				Elements artist_num_elements = elements.select("div.artist a");
				// href의 속성값을 가져오기
				String hrefArtist_num = artist_num_elements.attr("href");
				artist_num = Integer.parseInt(hrefArtist_num .substring(
								hrefArtist_num.lastIndexOf("('") + 2, 
								hrefArtist_num.lastIndexOf("')")).trim());
			}
			// 앨범 번호 가져오기
			Elements album_num_elements = elements.select("div.meta dl.list dd a");

			String hrefAlbum_num = album_num_elements.attr("href");
			album_num = Integer.parseInt(hrefAlbum_num
					.substring(hrefAlbum_num.lastIndexOf("('") + 2, hrefAlbum_num.lastIndexOf("')")).trim());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// 아티스트의 정보를 가져오기
	private void getArtist() {
		
		tempArtsitDTO = new ArtistDTO();
		if(artist_num != -1) {
			String url = "https://www.melon.com/artist/timeline.htm?artistId=" + artist_num;
			Document doc;
			
			try {
				
				doc = Jsoup.connect(url).get();
				
				Elements elements = doc.select("div.wrap_atist_info");
				Elements info_elements = elements.select("dl.atist_info.clfix");
				
				//아티스트 이름 데뷔일 활동유형 소속사를 가져옴
				
				Elements name_elements = elements.select("p.title_atist");
				Elements date_elements = info_elements.select("span.gubun");
				Elements type_elements = info_elements.select("dd").eq(2);
				Elements agency_elements = info_elements.select("dd").eq(3);
				
				tempArtsitDTO.setArtist_name(name_elements.text().substring(5));
				tempArtsitDTO.setArtist_date(date_elements.text());
				tempArtsitDTO.setArtist_type(type_elements.text());
				tempArtsitDTO.setAgency(agency_elements.text());
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			tempArtsitDTO.setArtist_name("Various Artist");
			tempArtsitDTO.setArtist_date("-");
			tempArtsitDTO.setArtist_type("-");
			tempArtsitDTO.setAgency("-");
		}
	}
	
	// 앨범의 정보를 가져오기
	private void getAlbum() {
		
		tempAlbumDTO = new AlbumDTO();
		String url = "https://www.melon.com/album/detail.htm?albumId=" + album_num;
		Document doc;
		
		try {
			doc = Jsoup.connect(url).get();
			
			Elements elements = doc.select("div.entry");
			
			// 앨범의 이름, 발매일, 장르, 기획사를 가져옴
			Elements info_elements = elements.select("div.meta dl.list");
			
			Elements name_elements = elements.select("div.song_name");
			Elements date_elements = info_elements.select("dd").eq(0);
			Elements genre_elements = info_elements.select("dd").eq(1);
			Elements publisher_elements = info_elements.select("dd").eq(2);
			Elements agency_elements = info_elements.select("dd").eq(3);
			
			tempAlbumDTO.setAlbum_name(name_elements.text().substring(4));
			tempAlbumDTO.setRelease_date(date_elements.text());
			tempAlbumDTO.setAlbum_genre(genre_elements.text());
			tempAlbumDTO.setAlbum_publisher(publisher_elements.text());
			tempAlbumDTO.setAlbum_agency(agency_elements.text());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
