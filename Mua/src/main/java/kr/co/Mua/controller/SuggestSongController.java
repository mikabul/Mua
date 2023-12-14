package kr.co.Mua.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.Mua.dao.SuggestDao;
import kr.co.Mua.service.ChartService;
import kr.co.Mua.bean.ChartDto;
import kr.co.Mua.bean.SongDto;
import kr.co.Mua.bean.UserBean;

@Controller
@RequestMapping("/suggest")
public class SuggestSongController {

	@Autowired
	private SuggestDao suggestDAO;

	@Autowired
	private ChartService chartService; // ChartService 주입
	
	// 각각의 조건을 검사하는 메서드 추가
    private int findMatchingSongId(ArrayList<ChartDto> chartList, String nationName, String genreName, String artistName) {

        int matchedsongid = 0;
        int anotherArtistsongid = 0;
        int anotherGenresongid = 0;
        int anotherNationsongid = 0;
        
        boolean isNationMatch = false;
        boolean isGenreMatch = false;
        boolean isArtistMatch = false;

        for (ChartDto chartItem : chartList) {
            if (!isNationMatch && suggestDAO.getSongNation(chartItem.getSong_id()).equals(nationName)) {
            	matchedsongid = chartItem.getSong_id();
                isNationMatch = true;
            }
            if (!isGenreMatch && suggestDAO.getSongGenre(chartItem.getSong_id()).equals(genreName)) {
            	matchedsongid = chartItem.getSong_id();
                isGenreMatch = true;
            }
            for (String artist : chartItem.getArtist_name()) {
                if (!isArtistMatch && artist.equals(artistName)) {
                	matchedsongid = chartItem.getSong_id();
                    isArtistMatch = true;
                    break; // 만족하는 아티스트를 찾으면 반복문 종료
                }
            }
            // 모든 조건을 만족하면 반복문 종료
            if (isNationMatch && isGenreMatch && isArtistMatch) {
                break;
            }
        }
        return matchedsongid;
    }
    
    private int findMatchingSongIdFromGenreDataMap(Map<String, ArrayList<ChartDto>> genreDataMap, String nationName, String genreName, String artistName) {
        int matchedSongId = 0;

        for (Map.Entry<String, ArrayList<ChartDto>> entry : genreDataMap.entrySet()) {
            String key = entry.getKey();
            ArrayList<ChartDto> chartList = entry.getValue();

            // 키에 해당하는 차트 목록에서 곡을 찾기 위한 메서드 호출
            matchedSongId = findMatchingSongId(chartList, nationName, genreName, artistName);

            // 곡이 발견되면 반복문 종료
            if (matchedSongId != 0) {
                break;
            }
        }

        return matchedSongId;
    }

	@GetMapping("song_suggest")
    public String showSongSuggestPage(Model model, HttpSession session, HttpServletRequest request) {
        int userNum = 0; // 기본값 설정 또는 예외 처리

        UserBean loginUserBean = (UserBean) session.getAttribute("loginUserBean");

        if (loginUserBean != null) {
            userNum = loginUserBean.getUser_num();
            System.out.println(userNum);
        } else {
            userNum = 1;
        }

        String artistName = suggestDAO.getMostRecentArtistName(userNum);
        String genreName = suggestDAO.getMostGenreName(userNum);
        String nationName = suggestDAO.getMostNationName(userNum);
        
        // ChartService를 이용하여 차트 가져오기
        ArrayList<ChartDto> newchart = chartService.getNewChart();
        ArrayList<ChartDto> top100chart = chartService.getChart();
        Map<String, ArrayList<ChartDto>> genreDataMap = (Map<String, ArrayList<ChartDto>>) session.getAttribute("genreDataMap");
                
        Integer top100Count = (Integer) request.getSession().getAttribute("/Mua/chart/top100");
        Integer newchartCount = (Integer) request.getSession().getAttribute("/Mua/chart/newchart");
        Integer genreCount = (Integer) request.getSession().getAttribute("/Mua/chart/genre");
        
        Integer maxCount = Math.max(Math.max(top100Count, newchartCount), genreCount);
        
        int suggestSongId = 0;

        if (top100Count != null && newchartCount != null && genreCount != null) {
            if (maxCount.equals(top100Count)) {
                suggestSongId = findMatchingSongId(top100chart, nationName, genreName, artistName);
            } else if (maxCount.equals(newchartCount)) {
                suggestSongId = findMatchingSongId(newchart, nationName, genreName, artistName);
            } else if (maxCount.equals(genreCount)) {
                suggestSongId = findMatchingSongIdFromGenreDataMap(genreDataMap, nationName, genreName, artistName);
            }
        }

        model.addAttribute("recentArtistName", artistName);
        model.addAttribute("genreName", genreName);
        model.addAttribute("nationName", nationName);
        model.addAttribute("suggestSongId", suggestSongId);

        
        
        return "/suggest/song_suggest";
    }
	
	@GetMapping("suggestDifferent")
	public String suggestDifferentSong(@RequestParam("condition") String condition, Model model, HttpSession session, HttpServletRequest request) {
		
		int userNum = 0; // 기본값 설정 또는 예외 처리

        UserBean loginUserBean = (UserBean) session.getAttribute("loginUserBean");

        if (loginUserBean != null) {
            userNum = loginUserBean.getUser_num();
            System.out.println(userNum);
        } else {
            userNum = 1;
        }

        String artistName = suggestDAO.getMostRecentArtistName(userNum);
        String genreName = suggestDAO.getMostGenreName(userNum);
        String nationName = suggestDAO.getMostNationName(userNum);
        
        // ChartService를 이용하여 차트 가져오기
        ArrayList<ChartDto> newchart = chartService.getNewChart();
        ArrayList<ChartDto> top100chart = chartService.getChart();
        Map<String, ArrayList<ChartDto>> genreDataMap = (Map<String, ArrayList<ChartDto>>) session.getAttribute("genreDataMap");
                
        Integer top100Count = (Integer) request.getSession().getAttribute("/Mua/chart/top100");
        Integer newchartCount = (Integer) request.getSession().getAttribute("/Mua/chart/newchart");
        Integer genreCount = (Integer) request.getSession().getAttribute("/Mua/chart/genre");
        
        Integer maxCount = Math.max(Math.max(top100Count, newchartCount), genreCount);
	    
	    int suggestSongId = 0;

	    if (condition.equals("artist")) {
	        // 다른 가수를 추천받을 경우 가수만 다르게 선택하여 추천받습니다.
	        suggestSongId = findMatchingSongIdWithoutArtist(top100chart, nationName, genreName, artistName);
	    } else if (condition.equals("genre")) {
	        // 다른 장르를 추천받을 경우 장르만 다르게 선택하여 추천받습니다.
	        suggestSongId = findMatchingSongIdWithoutGenre(top100chart, nationName, genreName, artistName);
	    } else if (condition.equals("nation")) {
	        // 다른 국적을 추천받을 경우 국적만 다르게 선택하여 추천받습니다.
	        suggestSongId = findMatchingSongIdWithoutNation(top100chart, nationName, genreName, artistName);
	    }

	    model.addAttribute("suggestSongId", suggestSongId);

	    return "/suggest/song_suggest";
	}

}
