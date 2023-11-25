package kr.co.Mua.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.http.HttpSession;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.co.Mua.bean.ChartDTO;

@Controller
public class HomeController {
	
	ArrayList<ChartDTO> chart;
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String home(HttpSession session ) {
		if(session.getAttribute("user_num") == null) {
			session.setAttribute("login_state","false");
		}
		getChart();
		session.setAttribute("chart", chart);
		return "redirect:/main";
	}
	
	public void getChart(){
		
		 chart = new ArrayList<ChartDTO>();
		
		// URL
		String urlSearch = "https://www.melon.com/chart/index.htm?dayTime=";
		// 현재 날짜를 받아옴(23112013 23년 11월 20일 13시)
		SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHH");
		Calendar cal = Calendar.getInstance();
		String now = sdf.format(cal.getTime());
		
		Document searchResult;
		
		try {
			// 사이트에 연결하여 HTML전문을 긁어옴
			searchResult = Jsoup.connect(urlSearch + now).get();
			// tbody 내의 모든 tr 요소를 선택합니다.
			Elements trElements = searchResult.select("tbody tr");

			// 각 tr 요소에서 다섯 번째 td를 가져옵니다.
			for (Element trElement : trElements) {
				
				// 정보를 저장하기위한 객체 생성
				ChartDTO temp = new ChartDTO();
				
				// 노래제목
			    Elements nameElements = trElement.select("td:nth-child(6) div:nth-child(1) div.rank01");
			    temp.setName(nameElements.text());
			    // 아티스트이름
			    Elements artistElements = trElement.select("td:nth-child(6) div:nth-child(1)");
			    temp.setArtist(artistElements.text());
			    // 앨범이름
			    Elements albumElements = trElement.select("td:nth-child(7)");
			    temp.setAlbum(albumElements.text());
			    
			    chart.add(temp);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
