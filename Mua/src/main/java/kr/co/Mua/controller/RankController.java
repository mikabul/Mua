package kr.co.Mua.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/rank")
public class RankController {

	@GetMapping("/top100")
	public String top100() {
		return "/rank/top100";
	}
	
}
