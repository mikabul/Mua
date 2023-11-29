package kr.co.Mua.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.Mua.bean.ChartDTO;
import kr.co.Mua.service.ChartService;

@Controller
public class HomeController {

	@Autowired
	private ChartService chartService;

	@RequestMapping(value = "/")
	public String home(HttpSession session) {
		if (session.getAttribute("user_num") == null) {
			session.setAttribute("login_state", "false");
		}
		ArrayList<ChartDTO> chart = chartService.getChart();
		System.out.println("!!!!!!!!!!!!불러오기 완료!!!!!!!!!!!!");
		session.setAttribute("chart", chart);
		return "redirect:/main";
	}

}
