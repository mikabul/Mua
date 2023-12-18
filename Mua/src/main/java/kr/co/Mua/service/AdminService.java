package kr.co.Mua.service;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import javax.annotation.Resource;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import kr.co.Mua.bean.AdminDto;
import kr.co.Mua.bean.AlbumDto;
import kr.co.Mua.bean.ArtistDto;
import kr.co.Mua.bean.ChartDto;
import kr.co.Mua.bean.ReviewDto;
import kr.co.Mua.bean.SearchResultDto;
import kr.co.Mua.bean.SongDto;
import kr.co.Mua.bean.UserBean;
import kr.co.Mua.dao.AdminDao;
import kr.co.Mua.dao.ChartDao;

@Service
@PropertySource("/WEB-INF/properties/option.properties")
public class AdminService {
	
	@Value("${resources.path}")
	private String path;

	@Autowired
	private AdminDao adminDao;
	
	@Autowired
	private InsertDBService insertDBService;
	
	@Autowired
	private ChartDao chartDao;
	
	@Resource(name="loginAdminDto")
	private AdminDto loginAdminDto;
	
	ArrayList<SongDto> songList;
	ArrayList<ArtistDto> artistList;
	ArrayList<ChartDto> chart;
	SearchResultDto searchResultDto;
	ArrayList<SearchResultDto> resultList;
	
	String song_name;
	String song_releaseDate;
	String song_genre;
	
	String tempSongName;
	int tempSongId;
	String tempArtistName[];
	int tempArtistId[];
	String tempArtistThumbnail[];
	String tempAlbumName;
	
	SongDto songDto;
	
	public boolean getLogin(AdminDto tempAdminDto) {
		AdminDto adminDto = adminDao.getLogin(tempAdminDto);
		if(adminDto == null) {
			return false;
		} else {
			loginAdminDto.setAdmin_num(adminDto.getAdmin_num());
			loginAdminDto.setAdmin_id(adminDto.getAdmin_id());
			loginAdminDto.setLoginState(true);
			return true;
		}
	}
	
	//=========== 노래 ============
	public ArrayList<SongDto> getSearchSongName(String str, int index, int endView){
		String replaceStr = "%" + str + "%";
		return adminDao.getSearchSongName(str, replaceStr, index, endView);
	}
	
	public SongDto getSearchSongId(int song_id) {
		return adminDao.getSearchSongId(song_id);
	}
	
	public ArrayList<SongDto> getEmptySongNation(int index, int maxIndex){
		return adminDao.getEmptySongNation(index, maxIndex);
	}
	
	public int getEmptySongNationMaxIndex() {
		return adminDao.getEmptySongNationMaxIndex();
	}
	
	public void updateSong(SongDto songDto) {
		if(songDto.getLyric() == null || songDto.getLyric().isEmpty()) {
			adminDao.updateSong(songDto);
		} else {
			
			String fileName = System.currentTimeMillis() + "_" + songDto.getSong_id() + ".txt";
			songDto.setLyrics(fileName);
			
			String str = "<div>" + songDto.getLyric() + "</div>";
			str = str.replaceAll("\n", "<br>");
			try {

				FileOutputStream fos = new FileOutputStream(path + "lyric/" + fileName);
				OutputStreamWriter osw = new OutputStreamWriter(fos, StandardCharsets.UTF_8);
				BufferedWriter bw = new BufferedWriter(osw);
				
				bw.write(str);
				bw.close();
				osw.close();
				fos.close();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			adminDao.updateSong(songDto);
		}
		
	}
	
	//========== 아티스트 ===========
	public ArrayList<ArtistDto> getSearchArtistName(String str, int index, int endIndex){
		String replaceStr = "%" + str + "%";
		return adminDao.getSearchArtistName(str, replaceStr, index, endIndex);
	}
	
	public ArtistDto getSearchArtistId(int artist_id) {
		return adminDao.getSearchArtistId(artist_id);
	}
	
	public void updateArtist(ArtistDto artistDto) {
		adminDao.updateArtist(artistDto);
	}
	
	//============ 앨범 ===========
	public ArrayList<AlbumDto> getSearchalbumName(String str, int index, int endView){
		String replaceStr = "%" + str + "%";
		return adminDao.getSearchalbumName(str, replaceStr, index, endView);
	}
	
	public AlbumDto getSearchAlbumId(int album_id) {
		return adminDao.getSearchAlbumId(album_id);
	}
	
	public void updateAlbum(AlbumDto albumDto) {
		adminDao.updateAlbum(albumDto);
	}
	
	public int getMaxIndex(String str, String type) {
		str = "%" + str + "%";
		return adminDao.getMaxIndex(str, type);
	}
	
	//============= 유저 =============
	public ArrayList<UserBean> searchUserName(String str, int index, int endView){
		String replaceStr = "%" + str + "%";
		return adminDao.searchUserName(str, replaceStr, index, endView);
	}
	
	public void insertNotAccepteUser(int user_num, int admin_num, String end_date) {
		adminDao.insertNotAccepteUser(user_num, admin_num, end_date);
	}
	
	public void deleteNotAccepteUser(int user_num) {
		adminDao.deleteNotAccepteUser(user_num);
	}
	
	//============== 리뷰 ===============
	public ArrayList<ReviewDto> getReviewReport(){
		return adminDao.getReviewReport();
	}
	
	public void deleteUserReview(int review_num) {
		adminDao.deleteUserReview(review_num);
	}
	
	public void deleteReport(int report_num) {
		adminDao.deleteReport(report_num);
	}
	
	//===================== 크롤링 ==========================
	// 장르 페이지에 사용할 메서드
	public void getGenreChart(String genreCode) {
	    ArrayList<ChartDto> newchart = new ArrayList<>();

	    try {
	        String urlSearch = "https://www.melon.com/genre/song_list.htm?gnrCode=" + genreCode;
	        Document searchResult = Jsoup.connect(urlSearch)
	                .userAgent("Mozilla")
	                .referrer("https://www.google.com")
	                .get();

	        Elements trElements = searchResult.select("tbody tr");
	        for (Element trElement : trElements) {
	            createChartDtoFromElement(trElement);
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }

	}
	
	// 장르 페이지에 사용할 메서드
	private void createChartDtoFromElement(Element trElement) {

		// 노래 정보 파싱 및 설정
		Elements nameElements = trElement.select("td:nth-child(5) div:nth-child(1) div.rank01 a");
		tempSongName = nameElements.text();
		
		try {
			String strSong_id = nameElements.attr("href");
			tempSongId = Integer.parseInt(strSong_id.substring(strSong_id.lastIndexOf(",") + 1, strSong_id.lastIndexOf(")")));
		} catch (Exception e) {
			System.out.println("ChartService 임시 Song_id 오류" + tempSongId);
			e.printStackTrace();
		}
		
		Elements artistElements = trElement.select("td:nth-child(5) div.wrap_song_info div.ellipsis.rank02 span a");
	    tempArtistName = new String[artistElements.size()];
	    for (int i = 0; i < artistElements.size(); i++) {
	    	tempArtistName[i] = artistElements.get(i).text();
	    }

	    Elements albumElements = trElement.select("td:nth-child(6)");
	    tempAlbumName = albumElements.text();
	    
	    try {
			songDto = chartDao.chartSongMatch_fast(tempSongName, tempArtistName[0], tempAlbumName);
		} catch (Exception e) {
			songDto = null;
		}
	    
	    songMatch(tempSongId);

	}
	
	// ============ chart에서 사용 ===============
	// 속도의 문제로 인해 이전 방법을 먼저 시도한 후에 시도
	private void songMatch(int tempSongID) {

		String urlSong = "https://www.melon.com/song/detail.htm?songId=";
		// ============= 비교할 노래 정보 =================
		try {
			Document docSong = Jsoup.connect(urlSong + tempSongID).get();
			Elements elements = docSong.select("div.entry");

			// 노래의 이름을 가져옴
			Elements name_elements = elements.select("div.song_name").not("strong");
			String span_name = name_elements.select("span span").text();
			song_name = name_elements.text().substring(3);
			if (span_name.length() > 0) {
				song_name = song_name.substring(song_name.lastIndexOf(span_name) + span_name.length() + 1);
			}

			// 노래의 정보를 구분하기위한 Elements
			Elements song_info_elements = elements.select("div.meta dl.list dt");
			// 노래의 정보를 저장하기위한 Elements
			Elements song_info = elements.select("div.meta dl.list dd");
			// 발매일과 장르의 위치를 찾음
			for (int i = 0; i < song_info_elements.size(); i++) {
				String tempString = song_info.get(i).text();
				if (!tempString.isEmpty()) {
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
		songDto = chartDao.chartSongMatch(song_name, song_releaseDate, song_genre);
		if (songDto == null) {
			insertDBService.insertDB(tempSongID);
		}
	}

}
