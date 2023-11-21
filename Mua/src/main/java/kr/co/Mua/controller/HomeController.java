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
		// ���� ��¥�� �޾ƿ�(23112013 23�� 11�� 20�� 13��)
		SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHH");
		Calendar cal = Calendar.getInstance();
		String now = sdf.format(cal.getTime());
		
		Document searchResult;
		
		try {
			// ����Ʈ�� �����Ͽ� HTML������ �ܾ��
			searchResult = Jsoup.connect(urlSearch + now).get();
			// tbody ���� ��� tr ��Ҹ� �����մϴ�.
			Elements trElements = searchResult.select("tbody tr");

			// �� tr ��ҿ��� �ټ� ��° td�� �����ɴϴ�.
			for (Element trElement : trElements) {
				
				// ������ �����ϱ����� ��ü ����
				ChartDTO temp = new ChartDTO();
				
				// �뷡����
			    Elements nameElements = trElement.select("td:nth-child(6) div:nth-child(1) div.rank01");
			    temp.setName(nameElements.text());
			    // ��Ƽ��Ʈ�̸�
			    Elements artistElements = trElement.select("td:nth-child(6) div:nth-child(1)");
			    temp.setArtist(artistElements.text());
			    // �ٹ��̸�
			    Elements albumElements = trElement.select("td:nth-child(7)");
			    temp.setAlbum(albumElements.text());
			    
			    chart.add(temp);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
