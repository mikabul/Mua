package kr.co.Mua.service;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import kr.co.Mua.bean.AlbumDTO;
import kr.co.Mua.bean.ArtistDTO;
import kr.co.Mua.bean.SongDTO;
import kr.co.Mua.dao.InserDBDao;

@Service
@PropertySource("/WEB-INF/properties/option.properties")
public class SearchService {

	@Value("${resources.path}")
	private String path;

	@Autowired
	private InserDBDao insertDBDao;

	private SongDTO tempSongDTO;
	private ArtistDTO tempArtsitDTO;
	private ArrayList<ArtistDTO> artistList;
	private AlbumDTO tempAlbumDTO;

	private int artist_id[];
	private int album_id;
	private String lyric;
	private String thumbnail;

	private int insert_artist_id[];

	public void insertDB(int song_id) {

		// 노래 아티스트 앨범 정보를 가져옴
		getSong(song_id);
		getArtist();
		getAlbum();
		
		// album insert
		if(insertDBDao.album_match(tempAlbumDTO) == null) {
			insertDBDao.insert_album(tempAlbumDTO);
		}
		int temp_album_id = insertDBDao.album_match(tempAlbumDTO).getAlbum_id();
		
		// song insert
		// 앨범 아이디 입력
		tempSongDTO.setAlbum_id(temp_album_id);
		
		if(insertDBDao.song_match(tempSongDTO) == null) {
			insertDBDao.insert_song(tempSongDTO);
		}
		int temp_song_id = insertDBDao.song_match(tempSongDTO).getSong_id();
		
		//테스트용------------------------------------------
		int count = 1;
		//-----------------------------------------------
		// artist insert
		Iterator<ArtistDTO> it = artistList.iterator();
		while(it.hasNext()) {
			ArtistDTO artistDTO = it.next();
			if(insertDBDao.artist_match(artistDTO) == null) {
				insertDBDao.insert_artist(artistDTO);
			}
			int temp_artist_id = insertDBDao.artist_match(artistDTO).getArtist_id();
			insertDBDao.insert_song_artist(temp_song_id, temp_artist_id);
			insertDBDao.insert_album_artist(temp_album_id, temp_artist_id);
			
			System.out.println(artistList.size() + "개 중 " + count + "개 완료");
			count++;
		}

	}

	// 노래 정보를 가져옴
	private void getSong(int song_id) {

		tempSongDTO = new SongDTO();

		String url = "https://www.melon.com/song/detail.htm?songId=" + song_id;

		Document doc;

		// 노래 정보를 크롤링
		try {
			// 정보를 찾았는지 판별
			int index[] = { -1, -1 };

			doc = Jsoup.connect(url).get();
			Elements elements = doc.select("div.entry");

			// 노래의 이름을 가져옴
			Elements name_elements = elements.select("div.song_name").not("strong");
			String Song_name = name_elements.text().substring(2).trim();
			tempSongDTO.setSong_name(Song_name);

			// 노래의 정보를 가져옴
			// 노래의 정보를 구분하기위한 Elements
			Elements song_info_elements = elements.select("div.meta dl.list dt");
			// 노래의 정보를 저장하기위한 Elements
			Elements song_info = elements.select("div.meta dl.list dd");
			// 발매일과 장르의 위치를 찾음
			for (int i = 0; i < song_info_elements.size(); i++) {
				if (song_info_elements.get(i).text().equals("발매일")) {
					tempSongDTO.setRelease_date(song_info.get(i).text());
				}

				if (song_info_elements.get(i).text().equals("장르")) {
					tempSongDTO.setSong_genre(song_info.get(i).text());
				}
			}

			// 노래의 가사를 가져옴
			Elements lyric_elements = doc.select("div.lyric");
			String str_lyric = lyric_elements.toString();
			lyric = str_lyric.substring(str_lyric.lastIndexOf("-->") + 4);

			// 노래 이미지의 url을 가져옴
			Elements thumbnail_elements = doc.select("div.thumb#d_song_org img");
			thumbnail = thumbnail_elements.attr("src");

			// 아티스트 이름이 "Various Artists" 일경우 아티스트 페이지가 존재하지 않음
			// 예외처리
			if (elements.select("div.artist").text().equals("Various Artists")) {
				artist_id[0] = -1;
			} else {
				// 아티스트가 매우 많은 경우를 위한 Elements
				Elements more_artist_elements = elements.select("div.info div.wrap_atist ul").eq(0);
				Elements dropdown_artist_elements = more_artist_elements.select("li a");
				
				if (dropdown_artist_elements.size() > 0) {// 아티스트가 매우 많다면
					
					artist_id = new int[dropdown_artist_elements.size()];
					
					for(int i = 0; i < artist_id.length; i++) {
						String temp_artist_id = dropdown_artist_elements.get(i).attr("href");
						System.out.println(temp_artist_id);
						String substring_artist_id = temp_artist_id.substring(temp_artist_id.lastIndexOf("('") + 2, temp_artist_id.lastIndexOf("')")).trim();
						artist_id[i] = Integer.parseInt(substring_artist_id);
					}
					
				} else {
					Elements artist_num_elements = elements.select("div.info div.artist a");
					artist_id = new int[artist_num_elements.size()];

					for (int i = 0; i < artist_num_elements.size(); i++) {
						String hrefArtist_num = artist_num_elements.get(i).attr("href");
						artist_id[i] = Integer.parseInt(hrefArtist_num
								.substring(hrefArtist_num.lastIndexOf("('") + 2, hrefArtist_num.lastIndexOf("')"))
								.trim());
					}
				}

			}

			// 앨범 번호 가져오기
			Elements album_num_elements = elements.select("div.meta dl.list dd a");

			String hrefAlbum_num = album_num_elements.attr("href");
			album_id = Integer.parseInt(hrefAlbum_num
					.substring(hrefAlbum_num.lastIndexOf("('") + 2, hrefAlbum_num.lastIndexOf("')")).trim());

		} catch (Exception e) {
			System.out.println("노래 못가져옴");
		}
	}

	// 아티스트의 정보를 가져오기
	private void getArtist() {

		artistList = new ArrayList<ArtistDTO>();

		if (artist_id[0] != -1) {

			try {

				for (int i = 0; i < artist_id.length; i++) {

					tempArtsitDTO = new ArtistDTO();

					String url = "https://www.melon.com/artist/timeline.htm?artistId=" + artist_id[i];
					Document doc;
					doc = Jsoup.connect(url).get();

					// 아티스트의 이름을 저장
					Elements elements = doc.select("div.wrap_atist_info");
					Elements name_elements = elements.select("p.title_atist");
					tempArtsitDTO.setArtist_name(name_elements.text().substring(5));

					// 아티스트의 데뷔일, 활동유형, 소속사를 저장
					// 데뷔일, 활동유형, 소속사를 구분하기위한 Elements
					Elements info_elements = elements.select("dl.atist_info.clfix dt");
					// 데부일, 활동유형, 소속사를 저장하기위한 Elements
					Elements artist_info_elements = elements.select("dl.atist_info.clfix dd");
					for (int j = 0; j < info_elements.size(); j++) {
						if (info_elements.get(j).text().equals("데뷔")) {
							String str = artist_info_elements.get(j).select("span").text();
							tempArtsitDTO.setArtist_date(str.substring(0, str.indexOf(" ", 2)));
						} else if (info_elements.get(j).text().equals("활동유형")) {
							tempArtsitDTO.setArtist_type(artist_info_elements.get(j).text());
						} else if (info_elements.get(j).text().equals("소속사")) {
							tempArtsitDTO.setArtist_agency(artist_info_elements.get(j).text());
						}
					}
					
					if(i % 5 == 0 && i > 0) {
						Thread.sleep(5000);
						System.out.println("쉬어갑시다");
					}

					artistList.add(tempArtsitDTO);
				}
			} catch (Exception e) {
				System.out.println("아티스트 못가져옴");
			}
		} else {
			tempArtsitDTO.setArtist_name("Various Artists");
			artistList.add(tempArtsitDTO);
		}
	}

	// 앨범의 정보를 가져오기
	private void getAlbum() {

		int index[] = { -1, -1, -1, -1 };
		tempAlbumDTO = new AlbumDTO();
		String url = "https://www.melon.com/album/detail.htm?albumId=" + album_id;
		Document doc;

		try {
			doc = Jsoup.connect(url).get();

			Elements elements = doc.select("div.entry");

			// 앨범의 이름을 저장
			Elements name_elements = elements.select("div.song_name");
			tempAlbumDTO.setAlbum_name(name_elements.text().substring(4));

			// 앨범의 발매일, 장르, 발매사, 기획사를 저장
			// 앨범의 발매일, 장르, 발매사, 기획사를 구분하기위한 Elements
			Elements info_elements = elements.select("div.meta dl.list dt");
			// 앨범의 발매일, 장르, 발매사, 기획사를 저장하기위한 Elements
			Elements album_info_elements = elements.select("div.meta dl.list dd");
			for (int i = 0; i < info_elements.size(); i++) {
				if (info_elements.get(i).text().equals("발매일")) {
					tempAlbumDTO.setRelease_date(album_info_elements.get(i).text());
				} else if (info_elements.get(i).text().equals("장르")) {
					tempAlbumDTO.setAlbum_genre(album_info_elements.get(i).text());
				} else if (info_elements.get(i).text().equals("발매사")) {
					tempAlbumDTO.setAlbum_publisher(album_info_elements.get(i).text());
				} else if (info_elements.get(i).text().equals("기획사")) {
					tempAlbumDTO.setAlbum_agency(album_info_elements.get(i).text());
				}
			}

		} catch (Exception e) {
			System.out.println("앨범 못가져옴");
		}
	}

	// 노래 가사 저장
	private void lyric_thumbnail(int song_id, String song_name) {

		String file_name = song_id + song_name + ".lyric";

		try {

			FileOutputStream fos = new FileOutputStream(path + "/lyric/" + file_name);
			DataOutputStream dos = new DataOutputStream(fos);

			dos.writeUTF(lyric);

			dos.close();
			fos.close();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("노래 가사 저장 이상발생");
		}

	}
}
