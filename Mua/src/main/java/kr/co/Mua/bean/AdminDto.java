package kr.co.Mua.bean;

public class AdminDto {

	private int admin_num;
	private String admin_id;
	private String admin_pw;
	
	private boolean loginState;
	
	public AdminDto() {
		loginState = false;
	}

	//----------get,set-----------
	public int getAdmin_num() {
		return admin_num;
	}

	public void setAdmin_num(int admin_num) {
		this.admin_num = admin_num;
	}

	public String getAdmin_id() {
		return admin_id;
	}

	public void setAdmin_id(String admin_id) {
		this.admin_id = admin_id;
	}

	public String getAdmin_pw() {
		return admin_pw;
	}

	public void setAdmin_pw(String admin_pw) {
		this.admin_pw = admin_pw;
	}

	public boolean isLoginState() {
		return loginState;
	}

	public void setLoginState(boolean loginState) {
		this.loginState = loginState;
	}
	
	
}
