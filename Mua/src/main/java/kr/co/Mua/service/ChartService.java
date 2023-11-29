package kr.co.Mua.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.Mua.bean.ChartDTO;
import kr.co.Mua.dao.ChartDAO;

@Service
public class ChartService {

	@Autowired
	private ChartDAO chartDAO;

	@Autowired
	private InsertDBService insertDBService;

	public Integer chartSongMatch(String song_name, String artist_name, String album_name, int tempSong_id) {
		
		String temp_artist_name;
		Integer temp = chartDAO.chartSongMatch(song_name, artist_name, album_name);
		
		// null이라면 마지막 공백의 뒷부분 혹은 ()를 제거 후 한번더 시도
		if (temp == null) {
			try {// 아티스트 이름을 자를수 없을 경우
				try {
					temp_artist_name = artist_name.substring(0, artist_name.lastIndexOf(" "));
				} catch (Exception e) {// IndexOutofBounds
					temp_artist_name = artist_name.substring(0, artist_name.indexOf("("));
				}
				temp = chartDAO.chartSongMatch(song_name, temp_artist_name, album_name);
			} catch (Exception e) {
				System.out.println("43 : 가수이름 자르기 불가능 -> " + artist_name);
			}

		}
		// null이라면 등록 후 반환
		if (temp == null) {
			insertDBService.insertDB(tempSong_id);
			temp = chartDAO.chartSongMatch(song_name, artist_name, album_name);
		}
		// 등록 후 null이라면 마지막 공백의 뒷부분 혹은 ()를 제거 후 한번더 시도
		if (temp == null) {
			try {// 아티스트 이름을 자를수 없을 경우
				try {
					temp_artist_name = artist_name.substring(0, artist_name.lastIndexOf(" "));
				} catch (Exception e) {// IndexOutofBounds
					temp_artist_name = artist_name.substring(0, artist_name.indexOf("("));
				}
				temp = chartDAO.chartSongMatch(song_name, temp_artist_name, album_name);
			} catch (Exception e) {
				System.out.println("64 : 가수이름 자르기 불가능 -> " + artist_name);
			}
		}

		return temp;
	}

	public Integer chartArtistmatch(int song_id, String artist_name, int tempSong_id) {

		String temp_artist_name;
		Integer temp = chartDAO.chartArtistmatch(song_id, artist_name);

		// temp가 null일경우 artist_name에서 마지막 공백의 뒷부분 혹은 ()를 제거 후 한번더 시도
		if (temp == null) {
			try {// 아티스트 이름을 자를수 없을 경우
				try {
					temp_artist_name = artist_name.substring(0, artist_name.lastIndexOf(" "));
				} catch (Exception e) {// IndexOutofBounds
					temp_artist_name = artist_name.substring(0, artist_name.indexOf("("));
				}
				temp = chartDAO.chartArtistmatch(song_id, temp_artist_name);
			} catch (Exception e) {
				System.out.println("87 : 가수이름 자르기 불가능 -> " + artist_name);
			}
		}
		// temp가 null 일 경우 등록 후 재시도
		if (temp == null) {
			insertDBService.insertDB(tempSong_id);
			temp = chartDAO.chartArtistmatch(song_id, artist_name);
		}
		// 그래도 temp가 null일경우 artist_name에서 마지막 공백의 뒷부분 혹은 ()를 제거 후 한번더 시도
		if (temp == null) {
			try {// 아티스트 이름을 자를수 없을 경우
				try {
					temp_artist_name = artist_name.substring(0, artist_name.lastIndexOf(" "));
				} catch (Exception e) {// IndexOutofBounds
					temp_artist_name = artist_name.substring(0, artist_name.indexOf("("));
				}
				temp = chartDAO.chartArtistmatch(song_id, temp_artist_name);
			} catch (Exception e) {
				System.out.println("105 : 가수이름 자르기 불가능 -> " + artist_name);
			}
		}
		return temp;
	}

	public Integer chartAlbumMatch(int song_id) {
		return chartDAO.chartAlbumMatch(song_id);
	}

	public ArrayList<ChartDTO> getChart() {

		ArrayList<ChartDTO> chart = new ArrayList<ChartDTO>();

		// URL
		String urlSearch = "https://www.melon.com/chart/index.htm?dayTime=";
		// 날짜 형식 지정(2023년 11월 27일 13시=> 23112713)
		SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHH");
		Calendar cal = Calendar.getInstance();
		String now = sdf.format(cal.getTime());

		Document searchResult;

		try {
			// HTML전문 가져오기
			searchResult = Jsoup.connect(urlSearch + now).get();
			// 차트를 이루는 tbody내부의 tr을 전부 불러옴
			Elements trElements = searchResult.select("tbody tr");

			// tr을 하나씩 가져옴
			for (Element trElement : trElements) {

				// ==================이름======================
				// 저장을 위한 객체 생성
				ChartDTO temp = new ChartDTO();

				// 노래 이름
				Elements nameElements = trElement.select("td:nth-child(6) div:nth-child(1) div.rank01 a");
				temp.setSong_name(nameElements.text());

				// 임시로 사용할 song_id
				int tempSong_id = 0;
				try {
					String strSong_id = nameElements.attr("href");
					tempSong_id = Integer.parseInt(strSong_id.substring(strSong_id.lastIndexOf(",") + 1, strSong_id.lastIndexOf(")")));
				} catch (Exception e) {
					System.out.println("ChartService 임시 Song_id 오류" + tempSong_id);
					e.printStackTrace();
				}
				// 아티스트 이름
				Elements artistElements = trElement
						.select("td:nth-child(6) div.wrap_song_info div.ellipsis.rank02 span a");
				String tempArtist_name[] = new String[artistElements.size()];
				for (int i = 0; i < artistElements.size(); i++) {
					tempArtist_name[i] = artistElements.get(i).text();
				}
				temp.setArtist_name(tempArtist_name);
				// 앨범 이름
				Elements albumElements = trElement.select("td:nth-child(7)");
				temp.setAlbum_name(albumElements.text());

				// ================ID=================
				// song_id
				temp.setSong_id(chartSongMatch(temp.getSong_name(), temp.getArtist_name()[0], temp.getAlbum_name(),
						tempSong_id));

				// artist_id
				int tempArtist_id[] = new int[tempArtist_name.length];
				for (int i = 0; i < tempArtist_id.length; i++) {
					tempArtist_id[i] = chartArtistmatch(temp.getSong_id(), tempArtist_name[i], tempSong_id);
				}
				temp.setArtist_id(tempArtist_id);

				// album_id
				temp.setAlbum_id(chartAlbumMatch(temp.getSong_id()));

				chart.add(temp);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return chart;
	}

}
