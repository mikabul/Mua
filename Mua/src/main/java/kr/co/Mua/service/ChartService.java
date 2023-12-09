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

import kr.co.Mua.bean.ChartDto;
import kr.co.Mua.dao.ChartDao;

@Service
public class ChartService {

	@Autowired
	private ChartDao chartDAO;

	@Autowired
	private InsertDBService insertDBService;

	// ------------해당하는 노래의 id를 가져옴--------------
	public Integer chartSongMatch(String song_name, String artist_name, String album_name, int tempSong_id) {

		String temp_artist_name;
		Integer temp = chartDAO.chartSongMatch(song_name, artist_name, album_name);

		// null이라면 마지막 공백의 뒷부분 혹은 ()를 제거 후 한번더 시도
		if (temp == null) {

			try {
				temp_artist_name = artist_name.substring(0, artist_name.indexOf("("));
				temp = chartDAO.chartSongMatch(song_name, temp_artist_name.trim(), album_name);
			} catch (Exception e) {// IndexOutofBounds
				System.out.println("ChartService 39: 가수 이름 자르기 불가능 =>" + artist_name);
			}

		}

		// null이라면 등록 후 반환
		if (temp == null) {
			insertDBService.insertDB(tempSong_id);
			temp = chartDAO.chartSongMatch(song_name, artist_name, album_name);
		}

		// 등록 후 null이라면 마지막 공백의 뒷부분 혹은 ()를 제거 후 한번더 시도
		if (temp == null) {

			try {
				temp_artist_name = artist_name.substring(0, artist_name.indexOf("("));
				temp = chartDAO.chartSongMatch(song_name, temp_artist_name.trim(), album_name);
			} catch (Exception e) {// IndexOutofBounds
				System.out.println("ChartService 57: 가수 이름 자르기 불가능 =>" + artist_name);
			}

		}
		return temp;
	}

	// -----------해당하는 아티스트의 id를 가져옴--------------
	public Integer chartArtistmatch(int song_id, String artist_name, int tempSong_id) {

		String temp_artist_name;
		Integer temp = chartDAO.chartArtistmatch(song_id, artist_name);

		// temp가 null일경우 artist_name에서 마지막 공백의 뒷부분 혹은 ()를 제거 후 한번더 시도
		if (temp == null) {
			
			try {
				temp_artist_name = artist_name.substring(0, artist_name.indexOf("("));
				temp = chartDAO.chartArtistmatch(song_id, temp_artist_name.trim());
			} catch (Exception e) {// IndexOutofBounds
				System.out.println("ChartService 77: 가수 이름 자르기 불가능 =>" + artist_name);
			}
			
		}
		
		// temp가 null 일 경우 등록 후 재시도
		if (temp == null) {
			insertDBService.insertDB(tempSong_id);
			temp = chartDAO.chartArtistmatch(song_id, artist_name);
		}
		
		// 그래도 temp가 null일경우 artist_name에서 마지막 공백의 뒷부분 혹은 ()를 제거 후 한번더 시도
		if (temp == null) {
			
			try {
				temp_artist_name = artist_name.substring(0, artist_name.indexOf("("));
				temp = chartDAO.chartArtistmatch(song_id, temp_artist_name);
			} catch (Exception e) {// IndexOutofBounds
				System.out.println("ChartService 57: 가수 이름 자르기 불가능 =>" + artist_name);
			}
			
		}
		return temp;
	}

	// ---------------해당하는 앨범의 id를 가져옴-------------
	public Integer chartAlbumMatch(int song_id) {
		return chartDAO.chartAlbumMatch(song_id);
	}

	// ---------차트 정보를 얻어옴------------
	public ArrayList<ChartDto> getChart() {

		ArrayList<ChartDto> chart = new ArrayList<ChartDto>();

		// URL
		String urlSearch = "https://www.melon.com/chart/index.htm?dayTime=";
		// 날짜 형식 지정(2023년 11월 27일 13시=> 23112713)
		SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHH");
		Calendar cal = Calendar.getInstance();
		String now = sdf.format(cal.getTime());

		Document searchResult;

		try {
			// 테스트용, 삭제필요 10개로 제한
			int limit = 0;
			// HTML전문 가져오기
			searchResult = Jsoup.connect(urlSearch + now).get();
			// 차트를 이루는 tbody내부의 tr을 전부 불러옴
			Elements trElements = searchResult.select("tbody tr");
			int nothing = 1;

			// tr을 하나씩 가져옴
			for (Element trElement : trElements) {
				if(nothing == 1) {
					break;
				}
				// ==================이름======================
				// 저장을 위한 객체 생성
				ChartDto temp = new ChartDto();

				// 노래 이름
				Elements nameElements = trElement.select("td:nth-child(6) div:nth-child(1) div.rank01 a");
				temp.setSong_name(nameElements.text());

				// 임시로 사용할 song_id
				int tempSong_id = 0;
				try {
					String strSong_id = nameElements.attr("href");
					tempSong_id = Integer.parseInt(
							strSong_id.substring(strSong_id.lastIndexOf(",") + 1, strSong_id.lastIndexOf(")")));
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

				// ================== 데이터 베이스 불러오기 ==================
				// song_id
				temp.setSong_id(chartSongMatch(temp.getSong_name(), temp.getArtist_name()[0], temp.getAlbum_name(),
						tempSong_id));

				// artist_id, artist_thumbnail
				int tempArtist_id[] = new int[tempArtist_name.length];
				String tempArtist_thumbnail[] = new String[tempArtist_name.length];
				for (int i = 0; i < tempArtist_id.length; i++) {
					tempArtist_id[i] = chartArtistmatch(temp.getSong_id(), tempArtist_name[i], tempSong_id);
					tempArtist_thumbnail[i] = chartDAO.getArtist_thumbnail(tempArtist_id[i]);
				}
				temp.setArtist_id(tempArtist_id);
				temp.setArtist_thumbnail(tempArtist_thumbnail);

				// album_id
				temp.setAlbum_id(chartAlbumMatch(temp.getSong_id()));

				// 노래의 썸네일을 가져옴
				temp.setSong_thumbnail(chartDAO.getSong_thumbnail(temp.getSong_id()));

				// 노래의 좋아요 갯수를 가져옴
				temp.setSong_thumbup(chartDAO.getCount_thumbup(temp.getSong_id()));
				chart.add(temp);
				
				// 테스트용, 삭제필요 10개로 제한
				if(++limit == 100) {
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return chart;
	}

}
