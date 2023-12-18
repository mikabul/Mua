package kr.co.Mua.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

import kr.co.Mua.bean.ChartDto;
import kr.co.Mua.bean.UserBean;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = {"/chart", "/suggest"})

public class ChartController {

	@Resource(name = "loginUserBean")
	private UserBean loginUserBean;
	
    @GetMapping("/top100")
    public String top100() {
    	
    	 int visitCount = 0;
         
         // 방문 횟수 증가시키기
         if(loginUserBean != null) {
             // 세션에서 해당 페이지의 방문 횟수 가져오기
             visitCount = loginUserBean.getTop100Count();

         	if (visitCount == 0) {
                 visitCount = 1;
                 loginUserBean.setTop100Count(visitCount);
             } else {
                 visitCount++;
                 loginUserBean.setTop100Count(visitCount);
             }
             System.out.println("top100 Visit count: " + visitCount);
         } else {
         	System.out.println("유저 정보가 없습니다.");
         }
         
         SimpleDateFormat sdf = new SimpleDateFormat("yyyy년MM월dd일HH시");
         Calendar cal = Calendar.getInstance();
         String now = sdf.format(cal.getTime());
 		
         model.addAttribute("now", now);
         
        return "/chart/top100";
    }

    //최신곡 페이지
    @GetMapping("/newchart")
    public String newchart() {
    	
    	// 세션에서 해당 페이지의 방문 횟수 가져오기
         int visitCount = 0;

        // 방문 횟수 증가시키기
        if(loginUserBean != null) {
            // 세션에서 해당 페이지의 방문 횟수 가져오기
            visitCount = loginUserBean.getNewchartCount();

        	if (visitCount == 0) {
                visitCount = 1;
                loginUserBean.setNewchartCount(visitCount);
            } else {
                visitCount++;
                loginUserBean.setNewchartCount(visitCount);
            }
            System.out.println("newchart Visit count: " + visitCount);
            
        } else {
        	System.out.println("유저 정보가 없습니다.");
        }
    	
        return "/chart/newchart";
    }
    
    //장르 페이지
    @GetMapping("/genre")
    public String genre(HttpServletRequest request, Model model) {
    	
    	// 세션에서 해당 페이지의 방문 횟수 가져오기
        int visitCount = 0;

       // 방문 횟수 증가시키기
       if(loginUserBean != null) {
           // 세션에서 해당 페이지의 방문 횟수 가져오기
           visitCount = loginUserBean.getGenreCount();

       	if (visitCount == 0) {
               visitCount = 1;
               loginUserBean.setGenreCount(visitCount);
           } else {
               visitCount++;
               loginUserBean.setGenreCount(visitCount);
           }
           System.out.println("genrechart Visit count: " + visitCount);
           
       } else {
       	System.out.println("유저 정보가 없습니다.");
       }
    	
        // 세션에 저장된 데이터를 가져옴
        Map<String, ArrayList<ChartDto>> genreDataMap = (Map<String, ArrayList<ChartDto>>) request.getSession().getAttribute("genreDataMap");
        // 가져온 데이터를 모델에 담아서 뷰로 전달
        model.addAttribute("genreDataMap", genreDataMap);
        // 해당하는 뷰 페이지 반환
        return "/chart/genre";
    }
}
