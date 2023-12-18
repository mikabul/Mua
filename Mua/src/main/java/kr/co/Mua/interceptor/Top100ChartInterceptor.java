package kr.co.Mua.interceptor;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

import kr.co.Mua.bean.ChartDto;
import kr.co.Mua.service.ChartService;

public class Top100ChartInterceptor implements HandlerInterceptor{

	private ChartService chartService;
		
	public Top100ChartInterceptor(ChartService chartService) {
		this.chartService = chartService;
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		
		ArrayList<ChartDto> chart = chartService.getChart();
		request.setAttribute("chart", chart);
		
		return true;
	}
	
	
}
