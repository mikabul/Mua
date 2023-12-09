package kr.co.Mua.service;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import kr.co.Mua.bean.AlbumDto;
import kr.co.Mua.bean.ArtistDto;
import kr.co.Mua.bean.SongDto;
import kr.co.Mua.dao.InsertDBDao;

@Service
@PropertySource("/WEB-INF/properties/option.properties")
public class InsertDBService {

	@Value("${resources.path}")
	private String path;

	@Autowired
	private InsertDBDao insertDBDao;
	
	// 크롤링시 과도하게 많은 시도를 막는 변수
	static int sleepCount = 0;

	private SongDto tempSongDto;
	private ArtistDto tempArtsitDTO;
	private ArrayList<ArtistDto> artistList;
	private AlbumDto tempAlbumDTO;

	private int artist_id[];
	private int album_id;
	private String lyric;
	private String song_thumbnail;
	private String artist_thumbnail[];
	private String album_thumbnail;
	private String nation[];
	private ArrayList<String[]> memberList;

	public void insertDB(int song_id) {
		
		Integer temp_album_id;
		Integer temp_song_id;
		Integer temp_artist_id;

		// 노래 아티스트 앨범 정보를 가져옴
		getSong(song_id);
		getNation();
		getArtist();
		getAlbum();

		// album insert
		if (insertDBDao.album_match(tempAlbumDTO) == null) {
			// 데이터베이스에 저장후 앨범 번호를 가져옴
			insertDBDao.insert_album(tempAlbumDTO);
			temp_album_id = insertDBDao.album_match(tempAlbumDTO).getAlbum_id();
			//썸네일 저장
			insertDBDao.insert_album_thumbnail(temp_album_id, thumbnailSave(temp_album_id, "album", 0));
		} else {
			temp_album_id = insertDBDao.album_match(tempAlbumDTO).getAlbum_id();
		}

		// song insert
		if (insertDBDao.song_match(tempSongDto) == null) {
			// 국내노래인지?
			
			// 데이터베이스에 저장 전 앨범 번호를 입력
			tempSongDto.setAlbum_id(temp_album_id);
			// 국내, 해외 인지 구분
			for(int i = 0; i < nation.length; i++) {
				if(nation[i].equals("대한민국") || nation[i].equals("한국")) {
					tempSongDto.setSong_nation("국내");
				} else if(nation[i].equals("-") || nation[i].equals("")) {
					tempSongDto.setSong_nation("-");
				} else {
					tempSongDto.setSong_nation("해외");
				}
			}
			// 데이터 베이스에 저장후 노래 번호를 가져옴
			insertDBDao.insert_song(tempSongDto);
			temp_song_id = insertDBDao.song_match(tempSongDto).getSong_id();
			// 썸네일, 가사 저장
			insertDBDao.insert_song_thumbnail(temp_song_id, thumbnailSave(temp_song_id, "song", 0));
			insertDBDao.insert_lyrics(temp_song_id, lyricSave(temp_song_id));
		} else {
			temp_song_id = insertDBDao.song_match(tempSongDto).getSong_id();
		}

		// artist insert
		int count = 0;
		Iterator<ArtistDto> it = artistList.iterator();
		while (it.hasNext()) {

			ArtistDto artistDTO = it.next();
			System.out.println(artistDTO.getArtist_name());
			if (insertDBDao.artist_match(artistDTO) == null) {
				// 데이터 베이스에 저장후 아티스트 번호를 가져옴
				insertDBDao.insert_artist(artistDTO);
				System.out.println(insertDBDao.artist_match(artistDTO) == null);
				temp_artist_id = insertDBDao.artist_match(artistDTO).getArtist_id();
				// 썸네일 저장
				insertDBDao.insert_artist_thumbnail(temp_artist_id, thumbnailSave(temp_artist_id, "artist", count));
			} else {
				temp_artist_id = insertDBDao.artist_match(artistDTO).getArtist_id();
			}
			// 중복 방지 song_artist
			if (insertDBDao.song_artist_match(temp_song_id, temp_artist_id) == null) {
				insertDBDao.insert_song_artist(temp_song_id, temp_artist_id);
			}
			// 중복 방지 album_artist
			if (insertDBDao.album_artist_match(temp_album_id, temp_artist_id) == null) {
				insertDBDao.insert_album_artist(temp_album_id, temp_artist_id);
			}
			//
			if(artistDTO.getArtist_type().equals("그룹")) {
				for(String[] member : memberList) {
					for(int i = 0; i < member.length; i++) {
						if(insertDBDao.member_match(temp_artist_id, member[i]) == null) {
							insertDBDao.insert_member(temp_artist_id, member[i]);
						}
					}
				}
			}
			count++;
			System.out.println(artistList.size() + "개 중 " + count + "개 완료");

		}
		if(++sleepCount % 10 == 0) {
			System.out.println("쉬는 시간");
			try {
				Thread.sleep(5000);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	// ===================노래 정보를 가져옴=======================
	private void getSong(int song_id) {

		tempSongDto = new SongDto();

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
			tempSongDto.setSong_name(song_name);

			// 노래의 정보를 가져옴
			// 노래의 정보를 구분하기위한 Elements
			Elements song_info_elements = elements.select("div.meta dl.list dt");
			// 노래의 정보를 저장하기위한 Elements
			Elements song_info = elements.select("div.meta dl.list dd");
			// 발매일과 장르의 위치를 찾음
			for (int i = 0; i < song_info_elements.size(); i++) {
				String tempString = song_info.get(i).text();
				if(!tempString.isEmpty()) {
					if (song_info_elements.get(i).text().equals("발매일")) {
						tempSongDto.setRelease_date(tempString);
					}
					
					if (song_info_elements.get(i).text().equals("장르")) {
						tempSongDto.setSong_genre(tempString);
					}
				}
			}

			// 노래의 가사를 가져옴
			Elements lyric_elements = doc.select("div.lyric");
			String str_lyric = lyric_elements.toString();
			if (str_lyric.length() > 0) {
				lyric = str_lyric.substring(str_lyric.lastIndexOf("-->") + 4);
			} else {
				lyric = null;
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

					// 아티스트 인원 수 만큼 배열 생성
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
					
					// 아티스트의 인원 수 만큼 배열 생성
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

		artistList = new ArrayList<ArtistDto>();
		memberList = new ArrayList<String[]>();
		String member[];

		if (artist_id[0] != -1) {

			try {

				for (int i = 0; i < artist_id.length; i++) {

					tempArtsitDTO = new ArtistDto();

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
						// 비어있는지 확인
						String tempString = artist_info_elements.get(j).text();
						if(!tempString.isEmpty()) {
							if (info_elements.get(j).text().equals("데뷔")) {
								String str = artist_info_elements.get(j).select("span").text();
								if(!str.isEmpty()) {
									try {
										tempArtsitDTO.setArtist_date(str.substring(0, str.indexOf(" ", 2)));
									} catch (Exception e) {
										tempArtsitDTO.setArtist_date(str);
									}
								}
							} else if (info_elements.get(j).text().equals("활동유형")) {
								tempArtsitDTO.setArtist_type(tempString);
							} else if (info_elements.get(j).text().equals("소속사")) {
								tempArtsitDTO.setArtist_agency(tempString);
							}
						}
					}

					// 썸네일 경로
					Elements thumbnail_elements = doc.select("div.dtl_atist.clfix span#artistImgArea img");
					artist_thumbnail[i] = thumbnail_elements.attr("src");
					
					// 국적 추가
					tempArtsitDTO.setArtist_nation(nation[i]);
					
					// 멤버
					System.out.println("그룹 ? " + tempArtsitDTO.getArtist_type().trim().equals("그룹"));
					if(tempArtsitDTO.getArtist_type().trim().equals("그룹")) {
						Elements memberElements = elements.select("div.wrap_atistname a");
						member = new String[memberElements.size()];
						
						for(int j =0; j < memberElements.size(); j++) {
							Element ele = memberElements.get(j);
							member[j] = ele.text();
						}
						memberList.add(member);
					}
					
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

		tempAlbumDTO = new AlbumDto();
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
				// 비어있는지 확인
				String tempString = album_info_elements.get(i).text();
				if(!tempString.isEmpty()) {
					if (info_elements.get(i).text().equals("발매일")) {
						tempAlbumDTO.setRelease_date(tempString);
					} else if (info_elements.get(i).text().equals("장르")) {
						tempAlbumDTO.setAlbum_genre(tempString);
					} else if (info_elements.get(i).text().equals("발매사")) {
						tempAlbumDTO.setAlbum_publisher(tempString);
					} else if (info_elements.get(i).text().equals("기획사")) {
						tempAlbumDTO.setAlbum_agency(tempString);
					}
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
	private String lyricSave(int song_id) {

		//노래 정보를 불러오지 못했는지?
		if (lyric != null) {
		    String file_name = System.currentTimeMillis() + "_" + song_id + ".txt";

		    try {
		        FileOutputStream fos = new FileOutputStream(path + "lyric/" + file_name);
		        OutputStreamWriter osw = new OutputStreamWriter(fos, StandardCharsets.UTF_8);
		        BufferedWriter bw = new BufferedWriter(osw);

		        bw.write("<div>" + lyric);

		        bw.close();
		        osw.close();
		        fos.close();

		    } catch (Exception e) {
		        System.out.println("노래 가사 저장 이상발생");
		        System.out.println(e);
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
	private String thumbnailSave(int id, String type, int i) {

		String file_name = System.currentTimeMillis()+ "_" + id + ".jpg";
		URL url;

		try {

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
			
			// 저장할 파일을 생성
			FileOutputStream fos = new FileOutputStream(path + "images/thumbnail/" + type + "/" + file_name);
			BufferedOutputStream bos = new BufferedOutputStream(fos);

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
			System.out.println(e);
			return "default.jpg";
		}

		return file_name;
	}
	
	// 아티스트의 국적을 가져옴
	private void getNation() {
		
		nation = new String[artist_id.length];
		
		for(int i = 0; i < nation.length; i++) {
			
			String url = "https://www.melon.com/artist/detail.htm?artistId=" + artist_id[i];
			Document doc;
			
			try {
				doc = Jsoup.connect(url).get();
				
				Elements elements = doc.select("#conts div.section_atistinfo04 dl");
				
				// 정보를 분류하는 dt 와 정보를 담고있는 dd를 가져옴
				Elements dtElements = elements.select("dt");
				Elements ddElements = elements.select("dd");
				
				// 원하는 정보인 '국적'을 찾을 때까지 반복
				for(int j = 0; j < dtElements.size(); j++) {
					if(dtElements.get(j).text().equals("국적")) {
						nation[i] = ddElements.get(j).text();
						break;
					}
				}
				if(nation[i] == null) {
					nation[i] = "-";
				}
			} catch (Exception e) {
				System.out.println(e);
				System.out.println("InsertDBSrvice 461 : 국적 가져오는중 이상 발생");
			}
		}
	}
}
