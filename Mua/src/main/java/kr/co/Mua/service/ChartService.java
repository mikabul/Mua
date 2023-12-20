package kr.co.Mua.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.Mua.bean.AlbumDto;
import kr.co.Mua.bean.ArtistDto;
import kr.co.Mua.bean.ChartDto;
import kr.co.Mua.bean.SearchResultDto;
import kr.co.Mua.bean.SongDto;
import kr.co.Mua.dao.ChartDao;

@Service
public class ChartService {

	@Autowired
	private ChartDao chartDAO;

	@Autowired
	private InsertDBService insertDBService;
	
	@Autowired
	private SearchService searchService;
	
	SongDto songDto;
	ArrayList<SongDto> songList;
	ArrayList<ArtistDto> artistList;
	ArrayList<ChartDto> chart;
	SearchResultDto searchResultDto;
	ArrayList<SearchResultDto> resultList;
	
	String song_name;
	String song_releaseDate;
	String song_genre;
	
	String tempSongName;
	
	int tempArtistId[];
	String tempArtistName[];
	String tempArtistThumbnail[];
	String tempAlbumName;

	// ---------차트 정보를 얻어옴------------
	public ArrayList<ChartDto> getChart() {

		chart = new ArrayList<ChartDto>();

		// URL
		String urlSearch = "https://www.melon.com/chart/index.htm?dayTime=";
		String urlSong = "https://www.melon.com/song/detail.htm?songId=";
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
				int tempSongId = 0;
				
				// 임시 노래 아이디
				Elements tempIdElements = trElement.select("td:nth-child(6) div:nth-child(1) div.rank01 a");
				tempSongName = tempIdElements.text();
				
				try {
					String strSong_id = tempIdElements.attr("href");
					tempSongId = Integer.parseInt(strSong_id.substring(strSong_id.lastIndexOf(",") + 1, strSong_id.lastIndexOf(")")));
				} catch (Exception e) {
					System.out.println("ChartService 임시 Song_id 오류" + tempSongId);
					e.printStackTrace();
				}
				
				// 아티스트 이름
				Elements artistElements = trElement
						.select("td:nth-child(6) div.wrap_song_info div.ellipsis.rank02 span a");
				tempArtistName = new String[artistElements.size()];
				for (int i = 0; i < artistElements.size(); i++) {
					tempArtistName[i] = artistElements.get(i).text();
				}
				// 앨범 이름
				Elements albumElements = trElement.select("td:nth-child(7)");
				tempAlbumName = albumElements.text();
				try {
					songDto = chartDAO.chartSongMatch_fast(tempSongName, tempArtistName[0], tempAlbumName);
				} catch (Exception e) {
					System.out.println(e);
					songDto = null;
				}
				
				// 노래가 존재하지 않는다면 등록후 다시 불러옴
				if(songDto == null) {
					songMatch(tempSongId);
				}
				// 아티스트와 앨범의 정보를 불러오는 메서드
				otherMatch();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return chart;
	}

	//최신곡 페이지에 사용할 메서드
	public ArrayList<ChartDto> getNewChart() {

		chart = new ArrayList<ChartDto>();
	    String urlSearch  = "https://www.melon.com/new/index.htm#params%5BareaFlg%5D=I&params%5BorderBy%5D=&po=pageObj&startIndex=1";;
	    String urlSong = "https://www.melon.com/song/detail.htm?songId=";
	    
		Document searchResult;

		try {
			// HTML 전문 가져오기
	        // URL로 접속
	        searchResult = Jsoup.connect(urlSearch)
	                            .userAgent("Mozilla")
	                            .referrer("https://www.google.com")
	                            .get();
			// 차트를 이루는 tbody내부의 tr을 전부 불러옴
			Elements trElements = searchResult.select("tbody tr");
			// tr을 하나씩 가져옴
			for (Element trElement : trElements) {
				
				// ==================이름======================
				// 노래 이름
				Elements nameElements = trElement.select("td:nth-child(5) div:nth-child(1) div.rank01 a");
				tempSongName = nameElements.text();

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
				Elements artistElements = trElement.select("td:nth-child(5) div.wrap_song_info div.ellipsis.rank02 span a");
				tempArtistName = new String[artistElements.size()];
				for (int i = 0; i < artistElements.size(); i++) {
					tempArtistName[i] = artistElements.get(i).text();
				}
				// 앨범 이름
				Elements albumElements = trElement.select("td:nth-child(6)");
				tempAlbumName = albumElements.text();
				
				try {
					songDto = chartDAO.chartSongMatch_fast(tempSongName, tempArtistName[0], tempAlbumName);
				} catch (Exception e) {
					System.out.println(e);
					songDto = null;
				}
				
				if(songDto == null) {
					songMatch(tempSong_id);
				}
				
				otherMatch();
				
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return chart;
	}
	
	//============== 장르별 음악 =================
	public ArrayList<SearchResultDto> getGenreSong(String sTabValue, String fTabValue, int index, int endIndex) {
		String replaceSTabValue = "%" + sTabValue + "%";
		songList = chartDAO.getGenreSong(replaceSTabValue, fTabValue, index, endIndex);
		getSearchResult();
		return resultList;
		
	}

	public int getGenreSongMaxIndex(String sTabValue, String fTabValue) {
		String replaceSTabValue = "%" + sTabValue + "%";
		return chartDAO.getGenreSongMaxIndex(replaceSTabValue, fTabValue);
	}

	public ArrayList<SearchResultDto> getGenreSongOST(int index, int endIndex) {
		songList = chartDAO.getGenreSongOST(index, endIndex);
		getSearchResult();
		return resultList;
	}

	public int getGenreSongOSTMaxIndex() {
		return chartDAO.getGenreSongOSTMaxIndex();
	}

	public ArrayList<SearchResultDto> getOtherGenreSong(String sTabValue, int index, int endIndex) {
		String replaceSTabValue = "%" + sTabValue + "%";
		songList = chartDAO.getOtherGenreSong(replaceSTabValue, index, endIndex);
		getSearchResult();
		return resultList;
	}

	public int getOtherGenreSongMaxIndex(String sTabValue) {
		String replaceSTabValue = "%" + sTabValue + "%";
		return chartDAO.getOtherGenreSongMaxIndex(replaceSTabValue);
	}
	
	private void getSearchResult() {
		
		Iterator<SongDto> songIt = songList.iterator();
		resultList = new ArrayList<SearchResultDto>();
		int thumbup_song;
		
		while(songIt.hasNext()) {
			SongDto tempSongDto = songIt.next();
			searchResultDto = new SearchResultDto();
			
			artistList = searchService.getBriefArtist(tempSongDto.getSong_id());
			thumbup_song = searchService.getThumbup(tempSongDto.getSong_id(), "song");
			tempSongDto.setSong_thumbup(thumbup_song);
			
			searchResultDto.setSongDto(tempSongDto);
			searchResultDto.setArtistList(artistList);
			
			resultList.add(searchResultDto);
			
		}
		
	}
	
	//============ chart에서 사용 ===============
	// 속도의 문제로 인해 이전 방법을 먼저 시도한 후에 시도
	private void songMatch(int tempSongID) {
		
		String urlSong = "https://www.melon.com/song/detail.htm?songId=";
		//============= 비교할 노래 정보 =================
		try {
			Document docSong = Jsoup.connect(urlSong + tempSongID).get();
			Elements elements = docSong.select("div.entry");
			
			// 노래의 이름을 가져옴
			Elements name_elements = elements.select("div.song_name").not("strong");
			String span_name = name_elements.select("span span").text();
			song_name = name_elements.text().substring(3);
			if(span_name.length() > 0) {
				song_name = song_name.substring(song_name.lastIndexOf(span_name) + span_name.length() + 1);
			}
			
			// 노래의 정보를 구분하기위한 Elements
			Elements song_info_elements = elements.select("div.meta dl.list dt");
			// 노래의 정보를 저장하기위한 Elements
			Elements song_info = elements.select("div.meta dl.list dd");
			// 발매일과 장르의 위치를 찾음
			for (int i = 0; i < song_info_elements.size(); i++) {
				String tempString = song_info.get(i).text();
				if(!tempString.isEmpty()) {
					if (song_info_elements.get(i).text().equals("발매일")) {
						song_releaseDate = tempString;
					}
					
					if (song_info_elements.get(i).text().equals("장르")) {
						song_genre = tempString;
					}
				}
			}
		} catch (Exception e) {
			System.out.println("songMatch");
			System.out.println(e);
		}
		
		
		// 노래 아이디와 이름, 앨범 아이디 불러오기
		songDto = chartDAO.chartSongMatch(song_name, song_releaseDate, song_genre);
		if(songDto == null) {
			insertDBService.insertDB(tempSongID);
			songDto = chartDAO.chartSongMatch(song_name, song_releaseDate, song_genre);
		}
	}
	
	private void otherMatch() {
		ChartDto temp = new ChartDto();
		
		// 노래 아이디를 이용하여, 아티스트 앨범 가져오기
		artistList = chartDAO.chartArtistmatch(songDto.getSong_id());
		AlbumDto albumDto = chartDAO.chartAlbumMatch(songDto.getAlbum_id());
		
		// 노래 정보 dto에 담기
		temp.setSong_name(songDto.getSong_name());
		temp.setSong_id(songDto.getSong_id());
		temp.setSong_thumbnail(songDto.getSong_thumbnail());
		
		// 아티스트의 아이디, 이름, 썸네일을 저장하기위한 공간 확보
		tempArtistId = new int[artistList.size()];
		tempArtistName = new String[artistList.size()];
		tempArtistThumbnail = new String[artistList.size()];
		
		// 아티스트의 정보를 하나씩 꺼내서 담아줌
		for(int i = 0; i < artistList.size(); i++) {
			tempArtistId[i] = artistList.get(i).getArtist_id();
			tempArtistName[i] = artistList.get(i).getArtist_name();
			tempArtistThumbnail[i] = artistList.get(i).getArtist_thumbnail();
		}
		
		// 아티스트의 정보의 배열을 temp에 추가
		temp.setArtist_id(tempArtistId);
		temp.setArtist_name(tempArtistName);
		temp.setArtist_thumbnail(tempArtistThumbnail);
		
		// 앨범의 정보를 temp에 추가
		temp.setAlbum_id(albumDto.getAlbum_id());
		temp.setAlbum_name(albumDto.getAlbum_name());
		
		chart.add(temp);

	}

}
