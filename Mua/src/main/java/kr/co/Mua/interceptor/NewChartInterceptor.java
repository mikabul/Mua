package kr.co.Mua.interceptor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;
import kr.co.Mua.bean.ChartDto;
import kr.co.Mua.bean.UserBean;
import kr.co.Mua.service.ChartService;

public class NewChartInterceptor implements HandlerInterceptor {

	private ChartService chartService;
	
	public NewChartInterceptor(ChartService chartService) {
		this.chartService = chartService;
	}
	

	@Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

            
    	ArrayList<ChartDto> newchart = chartService.getNewChart();
		request.setAttribute("newchart", newchart);
		

		return true;
    }

}
