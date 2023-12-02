package kr.co.Mua.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
		return "redirect:/main";
	}

}
