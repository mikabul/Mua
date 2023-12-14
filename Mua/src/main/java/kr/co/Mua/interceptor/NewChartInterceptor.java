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

public class NewChartInterceptor implements HandlerInterceptor {

	private ChartService chartService;

	public NewChartInterceptor(ChartService chartService) {
		this.chartService = chartService;
	}

	@Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
		
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

        System.out.println("Visited page: " + requestURI + ", Visit count: " + visitCount);
        
        // 증가된 방문 횟수를 세션에 다시 저장 (해당 페이지의 URI를 키로 사용)
        session.setAttribute(requestURI, visitCount);

    	ArrayList<ChartDto> newchart = chartService.getNewChart();
		request.setAttribute("newchart", newchart);
	
		return true;
    }

}
