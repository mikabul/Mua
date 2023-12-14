package kr.co.Mua.interceptor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;
import kr.co.Mua.bean.ChartDto;
import kr.co.Mua.service.ChartService;

public class GenreChartInterceptor implements HandlerInterceptor {

	private ChartService chartService;
	private boolean hasDataLoaded = false;

	public GenreChartInterceptor(ChartService chartService) {
		this.chartService = chartService;
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		HttpSession session = request.getSession();
        String requestURI = request.getRequestURI();

        // 세션에서 해당 페이지의 방문 횟수 가져오기
        Integer visitCount = (Integer) session.getAttribute(requestURI);

        // 방문 횟수 증가시키기
        if (visitCount == null) {
            visitCount = 1;
        } else {
            visitCount++;
        }

        // 증가된 방문 횟수를 세션에 다시 저장 (해당 페이지의 URI를 키로 사용)
        session.setAttribute(requestURI, visitCount);

		if (!hasDataLoaded) {
	        loadGenreData(request);
	        hasDataLoaded = true;
	    }
		return true;
	}

	private void loadGenreData(HttpServletRequest request) {
		Map<String, String> genreMap = new HashMap<>();

		// 한국 대중 음악
		genreMap.put("kor_ballad", "GN0100"); //
		genreMap.put("kor_dance", "GN0200");
		genreMap.put("kor_rap", "GN0300");
		genreMap.put("kor_rnb", "GN0400");
		genreMap.put("kor_indi", "GN0500");
		genreMap.put("kor_rock", "GN0600");
		genreMap.put("kor_trot", "GN0700");
		genreMap.put("kor_folk", "GN0800");

		// 해외POP음악
		genreMap.put("pop", "GN0900");
		genreMap.put("pop_rock", "GN1000");
		genreMap.put("pop_elec", "GN1100");
		genreMap.put("pop_rap", "GN1200");
		genreMap.put("pop_rnb", "GN1300");
		genreMap.put("pop_folk", "GN1400");

		// 그외 인기 장르
		genreMap.put("ost", "GN1500");
		genreMap.put("classic", "GN1600");
		genreMap.put("jazz", "GN1700");
		genreMap.put("jpop", "GN1900");
		genreMap.put("ccm", "GN2100");
		genreMap.put("traditional", "GN2400");

		Map<String, ArrayList<ChartDto>> genreDataMap = new HashMap<>();
		for (Map.Entry<String, String> entry : genreMap.entrySet()) {
			String attributeName = entry.getKey();
			String genreCode = entry.getValue();

			ArrayList<ChartDto> genreData = chartService.getGenreChart(genreCode);

			if (genreData != null) {
				genreDataMap.put(attributeName, genreData);
			} else {
				// genreData가 NULL인 경우 처리할 내용을 여기에 추가
				// 예를 들어, 에러 메시지 설정 또는 기본값으로 설정 등
				genreDataMap.put(attributeName, genreData);
			}
		}

		request.getSession().setAttribute("genreDataMap", genreDataMap);
	}
}
