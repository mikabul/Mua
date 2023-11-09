package kr.co.Mua.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String home(HttpSession session ) {
		if(session.getAttribute("user_num") == null) {
			session.setAttribute("login_state", "-1");
		}
		return "redirect:/main";
	}
	
}
