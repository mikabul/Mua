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
		System.out.println("인증번호 : "+checkNum);
		authNumber = checkNum;
	}
	
	public String joinEmail(String email) {
		makeRandomNumber();
		String setFrom = "dma0501011@gmail.com";
		String toMail = email;
		String title = "Mua 인증번호 요청.";
		String content = 
				"아래의 비밀번호를 입력해주시길 바랍니다."+ 
			    "인증번호는 " + authNumber + "입니다.";
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
