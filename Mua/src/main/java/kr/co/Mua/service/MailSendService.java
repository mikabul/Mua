package kr.co.Mua.service;

import java.util.Random;

import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class MailSendService {
	
	@Resource(name="mailSender")
	private JavaMailSenderImpl mailSender;
	
	private int authNumber;
	
	public void makeRandomNumber() {
		Random r = new Random();
		int checkNum = r.nextInt(888888)+111111;
		System.out.println("占쏙옙占쏙옙占쏙옙호 : "+checkNum);
		authNumber = checkNum;
	}
	
	public String joinEmail(String email) {
		makeRandomNumber();
		String setFrom = "dma0501011@gmail.com";
		String toMail = email;
		String title = "Mua �씤利앸쾲�샇 �슂泥�.";
		String content = 
				"�븘�옒�쓽 鍮꾨�踰덊샇瑜� �엯�젰�빐二쇱떆湲� 諛붾엻�땲�떎."+ 
			    "�씤利앸쾲�샇�뒗 " + authNumber + "�엯�땲�떎.";
		mailSend(setFrom,toMail,title,content);
		return Integer.toString(authNumber);
	}
	
	public void mailSend(String setFrom, String toMail, String title, String content) {
		MimeMessage message = mailSender.createMimeMessage();
		try {
			MimeMessageHelper helper = new MimeMessageHelper(message,true,"utf-8");
			helper.setFrom(setFrom);
			helper.setTo(toMail);
			helper.setSubject(content);
			
			helper.setText(content,true);
			mailSender.send(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
