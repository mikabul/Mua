package kr.co.Mua.controller;

<<<<<<< HEAD
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
=======
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.http.HttpSession;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
>>>>>>> refs/heads/ê¹€ì§„ìš±
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.Mua.service.ChartService;

import kr.co.Mua.bean.ChartDTO;

@Controller
public class HomeController {
<<<<<<< HEAD

	@Autowired
	private ChartService chartService;

	@RequestMapping(value = "/")
	public String home(HttpSession session) {
		if (session.getAttribute("user_num") == null) {
			session.setAttribute("login_state", "false");
		}
=======
	
	ArrayList<ChartDTO> chart;
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String home(HttpSession session ) {
		if(session.getAttribute("user_num") == null) {
			session.setAttribute("login_state","false");
		}
		getChart();
		session.setAttribute("chart", chart);
>>>>>>> refs/heads/ê¹€ì§„ìš±
		return "redirect:/main";
	}
<<<<<<< HEAD

=======
	
	public void getChart(){
		
		 chart = new ArrayList<ChartDTO>();
		
		// URL
		String urlSearch = "https://www.melon.com/chart/index.htm?dayTime=";
		// ÇöÀç ³¯Â¥¸¦ ¹Þ¾Æ¿È(23112013 23³â 11¿ù 20ÀÏ 13½Ã)
		SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHH");
		Calendar cal = Calendar.getInstance();
		String now = sdf.format(cal.getTime());
		
		Document searchResult;
		
		try {
			// »çÀÌÆ®¿¡ ¿¬°áÇÏ¿© HTMLÀü¹®À» ±Ü¾î¿È
			searchResult = Jsoup.connect(urlSearch + now).get();
			// tbody ³»ÀÇ ¸ðµç tr ¿ä¼Ò¸¦ ¼±ÅÃÇÕ´Ï´Ù.
			Elements trElements = searchResult.select("tbody tr");

			// °¢ tr ¿ä¼Ò¿¡¼­ ´Ù¼¸ ¹øÂ° td¸¦ °¡Á®¿É´Ï´Ù.
			for (Element trElement : trElements) {
				
				// Á¤º¸¸¦ ÀúÀåÇÏ±âÀ§ÇÑ °´Ã¼ »ý¼º
				ChartDTO temp = new ChartDTO();
				
				// ³ë·¡Á¦¸ñ
			    Elements nameElements = trElement.select("td:nth-child(6) div:nth-child(1) div.rank01");
			    temp.setName(nameElements.text());
			    // ¾ÆÆ¼½ºÆ®ÀÌ¸§
			    Elements artistElements = trElement.select("td:nth-child(6) div:nth-child(1)");
			    temp.setArtist(artistElements.text());
			    // ¾Ù¹üÀÌ¸§
			    Elements albumElements = trElement.select("td:nth-child(7)");
			    temp.setAlbum(albumElements.text());
			    
			    chart.add(temp);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
>>>>>>> refs/heads/ê¹€ì§„ìš±
}
