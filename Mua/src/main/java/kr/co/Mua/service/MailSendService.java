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
		System.out.println("������ȣ : "+checkNum);
		authNumber = checkNum;
	}
	
	public String joinEmail(String email) {
		makeRandomNumber();
		String setFrom = "dma0501011@gmail.com";
		String toMail = email;
		String title = "Mua ȸ�� ���� ���� �̸����Դϴ�.";
		String content = 
				"Ȩ�������� �湮���ּż� �����մϴ�." +
                "<br><br>" + 
			    "���� ��ȣ�� " + authNumber + "�Դϴ�." + 
			    "<br>" + 
			    "�ش� ������ȣ�� ������ȣ Ȯ�ζ��� �����Ͽ� �ּ���.";
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
