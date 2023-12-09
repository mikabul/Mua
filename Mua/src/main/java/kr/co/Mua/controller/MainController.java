package kr.co.Mua.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

	@GetMapping("/main")
	public String main() {
		return "/main";
	}
	
	@RequestMapping(value="/NotAccept")
	public String notAccept() {
		return "/NotAccept";
	}
	
}
