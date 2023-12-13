package kr.co.Mua.bean;

import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

public class UserBean {
	
	private int user_num;
	
	@Pattern(regexp = "[a-zA-Z0-9]*")
	private String user_id;
	
	@Pattern(regexp = "[a-zA-Z0-9!@#$%^&*()_+=\\-`~]*")
	private String user_pw;
	
	@Email
	private String user_email;
	
	private String user_tel;
	
	private String user_address;
	
	private String user_name;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date user_birthday;
	
	private String user_registdate;
	
	private boolean userIdExit;
	private boolean userLogin;
	private boolean userEmailExit;
	
	//1 = code, 2 = input code
	private String authCode1;
	private String authCode2;
	
	private String user_pw2;
	
	public UserBean() {
		this.userIdExit = false;
		this.userLogin = false;
		this.userEmailExit = false;
	}
	
	public int getUser_num() {
		return user_num;
	}
	public void setUser_num(int user_num) {
		this.user_num = user_num;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getUser_pw() {
		return user_pw;
	}
	public void setUser_pw(String user_pw) {
		this.user_pw = user_pw;
	}
	public String getUser_email() {
		return user_email;
	}
	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}
	public String getUser_tel() {
		return user_tel;
	}
	public void setUser_tel(String user_tel) {
		this.user_tel = user_tel;
	}
	public String getUser_address() {
		return user_address;
	}
	public void setUser_address(String user_address) {
		this.user_address = user_address;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public boolean isUserIdExit() {
		return userIdExit;
	}

	public void setUserIdExit(boolean userIdExit) {
		this.userIdExit = userIdExit;
	}

	public boolean isUserLogin() {
		return userLogin;
	}

	public void setUserLogin(boolean userLogin) {
		this.userLogin = userLogin;
	}

	public String getUser_pw2() {
		return user_pw2;
	}

	public void setUser_pw2(String user_pw2) {
		this.user_pw2 = user_pw2;
	}
	public boolean isUserEmailExit() {
		return userEmailExit;
	}

	public void setUserEmailExit(boolean userEmailExit) {
		this.userEmailExit = userEmailExit;
	}

	public Date getUser_birthday() {
		return user_birthday;
	}

	public void setUser_birthday(Date user_birthday) {
		this.user_birthday = user_birthday;
	}

	public String getUser_registdate() {
		return user_registdate;
	}

	public void setUser_registdate(String user_registdate) {
		this.user_registdate = user_registdate;
	}

	public String getAuthCode1() {
		return authCode1;
	}

	public void setAuthCode1(String authCode1) {
		this.authCode1 = authCode1;
	}

	public String getAuthCode2() {
		return authCode2;
	}

	public void setAuthCode2(String authCode2) {
		this.authCode2 = authCode2;
	}
	
	
	
	
}
