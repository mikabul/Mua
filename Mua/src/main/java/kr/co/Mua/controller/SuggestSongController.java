package kr.co.Mua.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Date;

import javax.servlet.http.HttpSession;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.Mua.dao.SuggestDao;
import kr.co.Mua.service.ChartService;
import kr.co.Mua.bean.ArtistDto;
import kr.co.Mua.bean.ChartDto;
import kr.co.Mua.bean.SongDto;
import kr.co.Mua.bean.UserBean;

@Controller
@RequestMapping("/suggest")
public class SuggestSongController {

    private final int MAX_SONG_ID_NUM = 5;

	@Autowired
	private SuggestDao suggestDAO;

	@Autowired
	private ChartService chartService; // ChartService 주입
	
	@Resource(name = "loginUserBean")
	private UserBean loginUserBean;
	
	// 각각의 조건을 검사하는 메서드 추가
	private int findMatchingSongId(ArrayList<ChartDto> chartList, String nationName, String genreName, String artistName) {

	    Date artistThumbupDate = new Date();
	    int matchedSongId = 0;
	    
	    boolean isNationMatch = false;
	    boolean isGenreMatch = false;
	    boolean isArtistMatch = false;

	    for (ChartDto chartItem : chartList) {
	        if (chartItem.getSong_thumbup() == 0) {
	            // 넘어온 조건 중 하나라도 null 또는 비어있다면 다음 chartItem으로 넘어감
	            if (nationName != null && !isNationMatch && nationName.equals(suggestDAO.getSongNation(chartItem.getSong_id()))) {
	                matchedSongId = chartItem.getSong_id();
	                isNationMatch = true;
	            }
	            if (genreName != null && !isGenreMatch && genreName.equals(suggestDAO.getSongGenre(chartItem.getSong_id()))) {
	                matchedSongId = chartItem.getSong_id();
	                isGenreMatch = true;
	            }
	            if (artistName != null) {
	                for (String artist : chartItem.getArtist_name()) {
	                    if (!isArtistMatch && artistName.equals(artist)) {
	                        matchedSongId = chartItem.getSong_id();
	                        isArtistMatch = true;
	                        break; // 만족하는 아티스트를 찾으면 반복문 종료
	                    }
	                }
	            }

	            // 모든 조건을 만족하면 반복문 종료
	            if ((nationName == null || isNationMatch) && (genreName == null || isGenreMatch) && (artistName == null || isArtistMatch)) {
	                break;
	            }
	        }
	    }
        System.out.println(matchedSongId);
	    return matchedSongId;
	}

    
    private int findMatchingSongIdFromGenreDataMap(Map<String, ArrayList<ChartDto>> genreDataMap, String nationName, String genreName, String artistName, String type) {
        int matchedSongId = 0;
        int newMatchedSongId = 0;

        for (Map.Entry<String, ArrayList<ChartDto>> entry : genreDataMap.entrySet()) {
            String key = entry.getKey();
            ArrayList<ChartDto> chartList = entry.getValue();

            matchedSongId = findMatchingSongId(chartList, nationName, genreName, artistName);
            
            if(type.equals("artist")) {
            	newMatchedSongId = anotherArtist(chartList, nationName, genreName, matchedSongId);
            } else if(type.equals("genre")) {
            	newMatchedSongId = anotherGenre(chartList, nationName, artistName, matchedSongId);
            } else if(type.equals("nation")) {
            	newMatchedSongId = anotherNation(chartList, matchedSongId);
            } else {
            	newMatchedSongId = matchedSongId;
            }

            // 곡이 발견되면 반복문 종료
            if (newMatchedSongId != 0) {
                break;
            }
        }

        return newMatchedSongId;
    }
    
    private int anotherArtist(ArrayList<ChartDto> chartList, String nationName, String genreName, int matchedSongId) {

        int anotherArtistSongId = 0;
        
        List<String> artistName = suggestDAO.getArtistNames(matchedSongId);
        
        boolean isNationMatch_aA = false;
        boolean isGenreMatch_aA = false;
        boolean isArtistMatch_aA = false;

        for (ChartDto chartItem : chartList) {
            if (chartItem.getSong_thumbup() == 0) {
                boolean isMatched = true;

                if (nationName != null && !nationName.equals(suggestDAO.getSongNation(chartItem.getSong_id()))) {
                    isMatched = false;
                }
                if (genreName != null && !genreName.equals(suggestDAO.getSongGenre(chartItem.getSong_id()))) {
                    isMatched = false;
                }
                if (artistName != null) {
                    boolean isArtistFound = false;
                    List<String> songArtists = suggestDAO.getArtistNames(chartItem.getSong_id());
                    if (songArtists != null) {
                    	for (String artist : songArtists) {
                            if (artistName.contains(artist)) {
                                isArtistFound = true;
                                break;
                            }
                        }

                    }
                    if (isArtistFound) {
                        isMatched = false;
                    }
                }

                if (isMatched) {
                    anotherArtistSongId = chartItem.getSong_id();
                    break; // 만족하는 아이템을 찾았으므로 반복 종료
                }
            }
        }
        
        return anotherArtistSongId;
    }

    
    private int anotherNation(ArrayList<ChartDto> chartList, int matchedSongId) {

        int anotherNationSongId = 0;
        
        String nationName = suggestDAO.getSongNation(matchedSongId);
        
        boolean isNationMatch = false;

        for (ChartDto chartItem : chartList) {
            // 좋아요를 누르지 않았으며 처음 추천된 가수의 노래를 제외한 곡들 중에서 두 번째로 추천
            if (chartItem.getSong_thumbup() == 0) {
                boolean isMatched = false;

                if (nationName != null && !"-".equals(nationName)) {
                    String songNation = suggestDAO.getSongNation(chartItem.getSong_id());
                    if ("국내".equals(nationName) && "해외".equals(songNation)) {
                        isMatched = true;
                        System.out.println(nationName + "의 노래 추천 대신" + songNation + "의 노래 추천으로 바뀌었습니다.");
                    } else if ("해외".equals(nationName) && "국내".equals(songNation)) {
                        isMatched = true;
                        System.out.println(nationName + "의 노래 추천 대신" + songNation + "의 노래 추천으로 바뀌었습니다.");
                    }
                } else {
                    // null 또는 "-" 인 경우 국내 노래를 추천
                    String songNation = suggestDAO.getSongNation(chartItem.getSong_id());
                    if ("국내".equals(songNation)) {
                        isMatched = true;
                    }
                }

                if (isMatched) {
                    if (!isNationMatch) {
                        anotherNationSongId = chartItem.getSong_id();
                        isNationMatch = true;
                        break;
                    }
                }
            }
        }
        return anotherNationSongId;
    }



	// 두 문자열 중 하나가 다른 문자열에 완전히 포함되는지 확인하는 메서드
	public static boolean checkForDuplicates(String str1, String str2) {
		return str1.contains(str2) || str2.contains(str1);
	}
    
    private int anotherGenre(ArrayList<ChartDto> chartList, String nationName, String artistName, int matchedSongId) {

	    int anotherGenreSongId = 0;
	    
	    String genreName = suggestDAO.getSongGenre(matchedSongId);
	    
	    boolean isNationMatch = false;
	    boolean isGenreMatch = false;
	    boolean isArtistMatch = false;

	    for (ChartDto chartItem : chartList) {
	        if (chartItem.getSong_thumbup() == 0) {

	        	// 두 문자열 중 하나가 다른 문자열에 완전히 포함되는지 확인
	            boolean containsDuplicate = checkForDuplicates(genreName, suggestDAO.getSongGenre(chartItem.getSong_id()));
	            
				if (nationName != null && !isNationMatch
						&& nationName.equals(suggestDAO.getSongNation(chartItem.getSong_id()))) {
					anotherGenreSongId = chartItem.getSong_id();
					isNationMatch = true;
				}
				// 넘어온 조건 중 하나라도 null 또는 비어있다면 다음 chartItem으로 넘어감
				if (genreName != null && !isGenreMatch
						&& !containsDuplicate) {
					anotherGenreSongId = chartItem.getSong_id();
					isGenreMatch = true;
				}

				if (artistName != null) {
					for (String artist : chartItem.getArtist_name()) {
						if (!isArtistMatch && artistName.equals(artist)) {
							anotherGenreSongId = chartItem.getSong_id();
							isArtistMatch = true;
							break; // 만족하는 아티스트를 찾으면 반복문 종료
						}
					}
				}

	            // 모든 조건을 만족하면 반복문 종료
	            if ((nationName == null || isNationMatch) && (genreName == null || isGenreMatch) && (artistName == null || isArtistMatch)) {
	                break;
	            }
	        }
	    }
	    return anotherGenreSongId;
	}
    

	@GetMapping("song_suggest")
    public String showSongSuggestPage(Model model, HttpSession session, HttpServletRequest request) {
        int userNum = 0; // 기본값 설정 또는 예외 처리

        if (loginUserBean != null) {
            userNum = loginUserBean.getUser_num();
            System.out.println(userNum);
        } else {
            userNum = 0;
            System.out.println(userNum);
        }
        
        List<ArtistDto> artistInfo = suggestDAO.getMostRecentArtistInfo(userNum);

        System.out.println(artistInfo.get(0).getArtist_thumbup_date());
        String artistName = artistInfo.get(0).getArtist_name();
        String ArtistThumbupDate = artistInfo.get(0).getArtist_thumbup_date();
        
        String genreName = suggestDAO.getMostGenreName(userNum);
        int mostGenreCount = suggestDAO.getMostGenreCount(userNum);
        
        String nationName = suggestDAO.getMostNationName(userNum);
        int mostNationCount = suggestDAO.getMostNationCount(userNum);
        
        // ChartService를 이용하여 차트 가져오기
        ArrayList<ChartDto> newchart = chartService.getNewChart();
        ArrayList<ChartDto> top100chart = chartService.getChart();
        Map<String, ArrayList<ChartDto>> genreDataMap = (Map<String, ArrayList<ChartDto>>) session.getAttribute("genreDataMap");
                
        int top100Count = loginUserBean.getTop100Count();
        int newchartCount = loginUserBean.getNewchartCount();
        int genreCount = loginUserBean.getGenreCount();
        
        Integer maxCount = Math.max(Math.max(top100Count, newchartCount), genreCount);
        
        int suggestSongId = 0;
        int anotherArtistSongId = 0;
        int anotherGenreSongId = 0;
        int anotherNationSongId = 0;

        if (top100Count != 0 && newchartCount != 0 && genreCount != 0) {
            if (maxCount.equals(top100Count)) {
            	
                suggestSongId = findMatchingSongId(top100chart, nationName, genreName, artistName);
                anotherArtistSongId = anotherArtist(top100chart, nationName, genreName, suggestSongId);
                anotherGenreSongId = anotherGenre(top100chart, nationName, artistName, suggestSongId);
                anotherNationSongId = anotherNation(top100chart, suggestSongId);
                
            } else if (maxCount.equals(newchartCount)) {
            	
                suggestSongId = findMatchingSongId(newchart, nationName, genreName, artistName);
                anotherArtistSongId = anotherArtist(newchart, nationName, genreName, suggestSongId);
                anotherGenreSongId = anotherGenre(newchart, nationName, artistName, suggestSongId);
                anotherNationSongId = anotherNation(newchart, suggestSongId);
                
            } else if (maxCount.equals(genreCount)) {
            	
                suggestSongId = findMatchingSongIdFromGenreDataMap(genreDataMap, nationName, genreName, artistName, "default");
                anotherArtistSongId = findMatchingSongIdFromGenreDataMap(genreDataMap, nationName, genreName, artistName, "artist");
                anotherGenreSongId = findMatchingSongIdFromGenreDataMap(genreDataMap, nationName, genreName, artistName, "genre");
                anotherNationSongId = findMatchingSongIdFromGenreDataMap(genreDataMap, nationName, genreName, artistName, "nation");
            }
            
		} else if (top100Count != 0 && newchartCount != 0) {
			if (maxCount.equals(top100Count)) {

				suggestSongId = findMatchingSongId(top100chart, nationName, genreName, artistName);
				anotherArtistSongId = anotherArtist(top100chart, nationName, genreName, suggestSongId);
				anotherGenreSongId = anotherGenre(top100chart, nationName, artistName, suggestSongId);
				anotherNationSongId = anotherNation(top100chart, suggestSongId);

			} else if (maxCount.equals(newchartCount)) {

				suggestSongId = findMatchingSongId(newchart, nationName, genreName, artistName);
				anotherArtistSongId = anotherArtist(newchart, nationName, genreName, suggestSongId);
				anotherGenreSongId = anotherGenre(newchart, nationName, artistName, suggestSongId);
				anotherNationSongId = anotherNation(newchart, suggestSongId);

			}
		} else if (newchartCount != 0 && genreCount != 0) {
			if (maxCount.equals(newchartCount)) {

				suggestSongId = findMatchingSongId(newchart, nationName, genreName, artistName);
				anotherArtistSongId = anotherArtist(newchart, nationName, genreName, suggestSongId);
				anotherGenreSongId = anotherGenre(newchart, nationName, artistName, suggestSongId);
				anotherNationSongId = anotherNation(newchart, suggestSongId);

			} else if (maxCount.equals(genreCount)) {

				suggestSongId = findMatchingSongIdFromGenreDataMap(genreDataMap, nationName, genreName, artistName,
						"default");
				anotherArtistSongId = findMatchingSongIdFromGenreDataMap(genreDataMap, nationName, genreName,
						artistName, "artist");
				anotherGenreSongId = findMatchingSongIdFromGenreDataMap(genreDataMap, nationName, genreName, artistName,
						"genre");
				anotherNationSongId = findMatchingSongIdFromGenreDataMap(genreDataMap, nationName, genreName,
						artistName, "nation");
			}
        } else if (top100Count != 0 && genreCount != 0) {
        	if (maxCount.equals(top100Count)) {

				suggestSongId = findMatchingSongId(top100chart, nationName, genreName, artistName);
				anotherArtistSongId = anotherArtist(top100chart, nationName, genreName, suggestSongId);
				anotherGenreSongId = anotherGenre(top100chart, nationName, artistName, suggestSongId);
				anotherNationSongId = anotherNation(top100chart, suggestSongId);

			} else if (maxCount.equals(genreCount)) {

				suggestSongId = findMatchingSongIdFromGenreDataMap(genreDataMap, nationName, genreName, artistName,
						"default");
				anotherArtistSongId = findMatchingSongIdFromGenreDataMap(genreDataMap, nationName, genreName,
						artistName, "artist");
				anotherGenreSongId = findMatchingSongIdFromGenreDataMap(genreDataMap, nationName, genreName, artistName,
						"genre");
				anotherNationSongId = findMatchingSongIdFromGenreDataMap(genreDataMap, nationName, genreName,
						artistName, "nation");
			}
        } else if (top100Count != 0) {
        	suggestSongId = findMatchingSongId(top100chart, nationName, genreName, artistName);
			anotherArtistSongId = anotherArtist(top100chart, nationName, genreName, suggestSongId);
			anotherGenreSongId = anotherGenre(top100chart, nationName, artistName, suggestSongId);
			anotherNationSongId = anotherNation(top100chart, suggestSongId);
        } else if (newchartCount != 0) {
        	suggestSongId = findMatchingSongId(newchart, nationName, genreName, artistName);
			anotherArtistSongId = anotherArtist(newchart, nationName, genreName, suggestSongId);
			anotherGenreSongId = anotherGenre(newchart, nationName, artistName, suggestSongId);
			anotherNationSongId = anotherNation(newchart, suggestSongId);
        } else if (genreCount != 0) {
        	suggestSongId = findMatchingSongIdFromGenreDataMap(genreDataMap, nationName, genreName, artistName,
					"default");
			anotherArtistSongId = findMatchingSongIdFromGenreDataMap(genreDataMap, nationName, genreName,
					artistName, "artist");
			anotherGenreSongId = findMatchingSongIdFromGenreDataMap(genreDataMap, nationName, genreName, artistName,
					"genre");
			anotherNationSongId = findMatchingSongIdFromGenreDataMap(genreDataMap, nationName, genreName,
					artistName, "nation");
        } else {
        	suggestSongId = findMatchingSongId(top100chart, nationName, genreName, artistName);
			anotherArtistSongId = anotherArtist(top100chart, nationName, genreName, suggestSongId);
			anotherGenreSongId = anotherGenre(top100chart, nationName, artistName, suggestSongId);
			anotherNationSongId = anotherNation(top100chart, suggestSongId);
        }

        model.addAttribute("mostGenreCount", mostGenreCount);
        model.addAttribute("mostNationCount", mostNationCount);
        model.addAttribute("ArtistThumbupDate", ArtistThumbupDate);
        model.addAttribute("recentArtistName", artistName);
        model.addAttribute("genreName", genreName);
        model.addAttribute("nationName", nationName);
        model.addAttribute("suggestSongId", suggestSongId);
        model.addAttribute("anotherArtistSongId", anotherArtistSongId);
        model.addAttribute("anotherGenreSongId", anotherGenreSongId);
        model.addAttribute("anotherNationSongId", anotherNationSongId);


        
        
        return "/suggest/song_suggest";
    }

}
