package kr.co.Mua.service;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.Mua.bean.UserBean;
import kr.co.Mua.dao.UserDAO;

@Service
public class UserService {
	
	@Autowired
	private UserDAO userDAO;
	
	@Resource(name = "loginUserBean")
	private UserBean loginUserBean;
	
	//아이디값을 통해 가져온 유저 이름이 있을경우 true 없을경우 false
	public boolean checkUserIDExit(String user_id) {
		
		String user_name = userDAO.checkUserIDExit(user_id);
		if(user_name == null) {
			return true;
		}else {
			return false;
		}
		
	}
	
	//이메일 값을 통해 가져온 유저 이름이 있을경우 true 없을경우 false
	public boolean checkUserEmailExit(String user_email) {
		String user_name = userDAO.checkUserEmailExit(user_email);
		if(user_name == null) {
			System.out.println(loginUserBean.isUserEmailExit());
			return true;
		}else {
			System.out.println(loginUserBean.isUserEmailExit());
			return false;
		}
	}
	
	//유저 정보 추가
	public void addUserInfo(UserBean RegisterUserBean) {
		userDAO.addUserInfo(RegisterUserBean);
	}
	
	// 유저 로그인
	public void getLoginUserInfo(UserBean tempLoginUserBean) {
		UserBean tempLoginUserBean2 = userDAO.getLoginUserInfo(tempLoginUserBean);
		
		if(tempLoginUserBean2 != null) {
			loginUserBean.setUser_num(tempLoginUserBean2.getUser_num());
			loginUserBean.setUser_id(tempLoginUserBean2.getUser_id());
			loginUserBean.setUserLogin(true);
		}
	}
	
	// 유저 정보 수정
	public UserBean getModifyUserInfo(UserBean modifyUserBean) {
		
		UserBean tempModifyUserBean = userDAO.getModifyUserInfo(loginUserBean.getUser_num());
		modifyUserBean.setUser_id(tempModifyUserBean.getUser_id());
		modifyUserBean.setUser_name(tempModifyUserBean.getUser_name());
		modifyUserBean.setUser_email(tempModifyUserBean.getUser_email());
		modifyUserBean.setUser_address(tempModifyUserBean.getUser_address());
		modifyUserBean.setUser_tel(tempModifyUserBean.getUser_tel());
		modifyUserBean.setUser_birthday(tempModifyUserBean.getUser_birthday());
		modifyUserBean.setUser_registdate(tempModifyUserBean.getUser_registdate());
		
		return modifyUserBean;
	}
	
	// 유저 정보 업데이트
	public boolean modifyUserInfo(UserBean modifyUserBean) {
		UserBean tempLoginUserBean2 = userDAO.getLoginUserInfo(modifyUserBean);
		
		if(tempLoginUserBean2 == null) {
			
			return false;
		}
		
		modifyUserBean.setUser_num(loginUserBean.getUser_num());
		userDAO.modifyUserInfo(modifyUserBean);
		
		return true;
	}
	
	
	
	
}
