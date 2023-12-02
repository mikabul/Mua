package kr.co.Mua.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

	@GetMapping("/main")
	public String main() {
<<<<<<< HEAD
		return "/main";
=======
		return "main";
>>>>>>> refs/heads/김진욱
	}
	
}
