package kr.co.Mua.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.co.Mua.bean.ChartDTO;
import kr.co.Mua.service.ChartService;

@RestController
public class RestApiController {

	@Autowired
	private ChartService chartService;
	
	@GetMapping("/chart/getChart")
	public void getChart(HttpSession session) {
		System.out.println("와따");
		ArrayList<ChartDTO> chart = chartService.getChart();
		session.setAttribute("chart", chart);
	}
	
}
