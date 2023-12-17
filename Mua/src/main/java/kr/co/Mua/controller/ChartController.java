package kr.co.Mua.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

import kr.co.Mua.bean.ChartDto;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value="/chart")
public class ChartController {

    @GetMapping("/top100")
    public String top100(Model model) {
    	
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy년MM월dd일HH시");
		Calendar cal = Calendar.getInstance();
		String now = sdf.format(cal.getTime());
		
		model.addAttribute("now", now);
    	
        return "/chart/top100";
    }

    //최신곡 페이지
    @GetMapping("/newchart")
    public String newchart(HttpServletRequest request, Model model) { 
        return "/chart/newchart";
    }
    
    //장르 페이지
    @GetMapping("/genre")
    public String genre(HttpServletRequest request, Model model) {
    	
        // 해당하는 뷰 페이지 반환
        return "/chart/genre";
    }
}
