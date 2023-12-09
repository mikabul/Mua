package kr.co.Mua.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

import kr.co.Mua.bean.ChartDto;

import java.util.ArrayList;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value="/chart")
public class ChartController {

    @GetMapping("/top100")
    public String top100() {
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
        // 세션에 저장된 데이터를 가져옴
        Map<String, ArrayList<ChartDto>> genreDataMap = (Map<String, ArrayList<ChartDto>>) request.getSession().getAttribute("genreDataMap");
        // 가져온 데이터를 모델에 담아서 뷰로 전달
        model.addAttribute("genreDataMap", genreDataMap);
        // 해당하는 뷰 페이지 반환
        return "/chart/genre";
    }
}
