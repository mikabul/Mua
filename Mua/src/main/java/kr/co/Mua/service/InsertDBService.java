package kr.co.Mua.service;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import kr.co.Mua.bean.AlbumDTO;
import kr.co.Mua.bean.ArtistDTO;
import kr.co.Mua.bean.SongDTO;
import kr.co.Mua.dao.InsertDBDao;

@Service
@PropertySource("/WEB-INF/properties/option.properties")
public class InsertDBService {

	@Value("${resources.path}")
	private String path;

	@Autowired
	private InsertDBDao insertDBDao;

	private SongDTO tempSongDTO;
	private ArtistDTO tempArtsitDTO;
	private ArrayList<ArtistDTO> artistList;
	private AlbumDTO tempAlbumDTO;

	private int artist_id[];
	private int album_id;
	private String lyric;
	private String song_thumbnail;
	private String artist_thumbnail[];
	private String album_thumbnail;

	public void insertDB(int song_id) {

		// 노래 아티스트 앨범 정보를 가져옴
		getSong(song_id);
		getArtist();
		getAlbum();

		// album insert
		if (insertDBDao.album_match(tempAlbumDTO) == null) {
			// 썸네일
			tempAlbumDTO.setAlbum_thumbnail(thumbnailSave(tempAlbumDTO.getAlbum_name(), "album", 0));
			insertDBDao.insert_album(tempAlbumDTO);
		}
		int temp_album_id = insertDBDao.album_match(tempAlbumDTO).getAlbum_id();

		// song insert
		// 앨범 아이디 입력
		tempSongDTO.setAlbum_id(temp_album_id);
		if (insertDBDao.song_match(tempSongDTO) == null) {
			// 썸네일
			tempSongDTO.setSong_thumbnail(thumbnailSave(tempSongDTO.getSong_name(), "song", 0));
			// 가사
			tempSongDTO.setLyrics(lyricSave(tempSongDTO.getSong_name()));
			insertDBDao.insert_song(tempSongDTO);
		}
		int temp_song_id = insertDBDao.song_match(tempSongDTO).getSong_id();

		// artist insert
		int count = 0;
		Iterator<ArtistDTO> it = artistList.iterator();
		while (it.hasNext()) {

			ArtistDTO artistDTO = it.next();
			System.out.println(artistDTO.getArtist_name());
			if (insertDBDao.artist_match(artistDTO) == null) {
				artistDTO.setArtist_thumbnail(thumbnailSave(artistDTO.getArtist_name(), "artist", count));
				insertDBDao.insert_artist(artistDTO);
			}
			count++;
			int temp_artist_id = insertDBDao.artist_match(artistDTO).getArtist_id();
			// 중복 방지
			if (insertDBDao.song_artist_match(temp_song_id, temp_artist_id) == null) {
				insertDBDao.insert_song_artist(temp_song_id, temp_artist_id);
			}
			// 중복 방지
			if (insertDBDao.album_artist_match(temp_album_id, temp_artist_id) == null) {
				insertDBDao.insert_album_artist(temp_album_id, temp_artist_id);
			}

			System.out.println(artistList.size() + "개 중 " + count + "개 완료");

		}

	}

	// ===================노래 정보를 가져옴=======================
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
			String span_name = name_elements.select("span span").text();
			String song_name = name_elements.text().substring(3);
			if(span_name.length() > 0) {
				song_name = song_name.substring(song_name.lastIndexOf(span_name) + span_name.length() + 1);
			}
			tempSongDTO.setSong_name(song_name);

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
			if (str_lyric.length() > 0) {
				lyric = str_lyric.substring(str_lyric.lastIndexOf("-->") + 4);
			}

			// 노래 이미지의 url을 가져옴
			Elements thumbnail_elements = doc.select("div.thumb#d_song_org img");
			song_thumbnail = thumbnail_elements.attr("src");

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
					artist_thumbnail = new String[dropdown_artist_elements.size()];

					for (int i = 0; i < artist_id.length; i++) {
						String temp_artist_id = dropdown_artist_elements.get(i).attr("href");
						String substring_artist_id = temp_artist_id
								.substring(temp_artist_id.lastIndexOf("('") + 2, temp_artist_id.lastIndexOf("')"))
								.trim();
						artist_id[i] = Integer.parseInt(substring_artist_id);
					}

				} else {
					Elements artist_num_elements = elements.select("div.info div.artist a");
					artist_id = new int[artist_num_elements.size()];
					artist_thumbnail = new String[artist_num_elements.size()];
					
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
			e.printStackTrace();
			System.out.println("노래 못가져옴");
		}
	}

	// ===================아티스트의 정보를 가져오기=======================
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
					String subName = name_elements.text();
					tempArtsitDTO.setArtist_name(subName.substring(5));

					// 아티스트의 데뷔일, 활동유형, 소속사를 저장
					// 데뷔일, 활동유형, 소속사를 구분하기위한 Elements
					Elements info_elements = elements.select("dl.atist_info.clfix dt");
					// 데부일, 활동유형, 소속사를 저장하기위한 Elements
					Elements artist_info_elements = elements.select("dl.atist_info.clfix dd");
					for (int j = 0; j < info_elements.size(); j++) {
						if (info_elements.get(j).text().equals("데뷔")) {
							String str = artist_info_elements.get(j).select("span").text();
							try {
								tempArtsitDTO.setArtist_date(str.substring(0, str.indexOf(" ", 2)));
							} catch (Exception e) {
								tempArtsitDTO.setArtist_date(str);
							}
						} else if (info_elements.get(j).text().equals("활동유형")) {
							tempArtsitDTO.setArtist_type(artist_info_elements.get(j).text());
						} else if (info_elements.get(j).text().equals("소속사")) {
							tempArtsitDTO.setArtist_agency(artist_info_elements.get(j).text());
						}
					}

					// 썸네일 경로
					Elements thumbnail_elements = doc.select("div.dtl_atist.clfix span#artistImgArea img");
					artist_thumbnail[i] = thumbnail_elements.attr("src");

					artistList.add(tempArtsitDTO);
				}
			} catch (Exception e) {
				System.out.println("아티스트 못불러옴");
			}
		} else {
			tempArtsitDTO.setArtist_name("Various Artists");
			artistList.add(tempArtsitDTO);
		}
	}

	// ===============앨범의 정보를 가져오기==================
	private void getAlbum() {

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

			// 썸네일 경로
			Elements thumbnail_elements = doc.select("div.thumb a.image_typeAll#d_album_org img");
			album_thumbnail = thumbnail_elements.attr("src");

		} catch (Exception e) {
			System.out.println("앨범 못가져옴");
		}
	}

	// =================노래 가사 저장======================
	private String lyricSave(String song_name) {

		//노래 정보를 불러오지 못했는지?
		if (lyric != null) {
			
			if(song_name.length() > 10) {
				song_name = song_name.substring(0, 10);
			}

			String file_name = System.currentTimeMillis() + song_name + ".lyric";

			try {

				FileOutputStream fos = new FileOutputStream(path + "/lyric/" + file_name);
				DataOutputStream dos = new DataOutputStream(fos);

				dos.writeUTF(lyric);

				dos.close();
				fos.close();

			} catch (Exception e) {
				System.out.println("노래 가사 저장 이상발생");
			}
			
			return file_name;
			
		} else {
			return "-";
		}

	}

	// ===============썸네일 저장=====================
	// type = album, artist, song 중 택1
	// i = album, song 일경우 아무 숫자나 입력
	// i = artist 일경우 while문 내부에서 작동. count입력
	private String thumbnailSave(String name, String type, int i) {

		if(name.length() > 10) {
			name = name.substring(0, 10);
		}
		name = name.replace(':', ' ');
		name = name.replace(">", "");
		name = name.replace("<", "");
		
		name = name.trim();
		
		String file_name = System.currentTimeMillis() + name + ".jpg";
		URL url;

		try {

			FileOutputStream fos = new FileOutputStream(path + "images/thumbnail/" + type + "/" + file_name);
			BufferedOutputStream bos = new BufferedOutputStream(fos);

			// type에 따른 경로 설정
			switch (type) {
			case "song":
				url = new URL(song_thumbnail);
				break;
			case "album":
				url = new URL(album_thumbnail);
				break;
			case "artist":
				url = new URL(artist_thumbnail[i]);
				break;
			default:
				System.out.println("thumbnail의 type 확인 필요");
				int x = 10 / 0;
				System.out.println(x);
				url = new URL("ERROR");
			}

			// 파일을 받아옴
			InputStream is = url.openStream();
			BufferedInputStream bis = new BufferedInputStream(is);

			// 파일을 저장
			int data;
			while ((data = bis.read()) != -1) {
				bos.write(data);
			}

			bis.close();
			is.close();
			bos.close();
			fos.close();

		} catch (Exception e) {
			System.out.println(type + "사진 저장에 문제발생");
			System.out.println(file_name);
		}

		return file_name;
	}

}
