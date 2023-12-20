package kr.co.Mua.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.Mua.Mapper.UserMapper;
import kr.co.Mua.bean.UserBean;
import kr.co.Mua.bean.notAcceptUserBean;

@Repository
public class UserDAO {
	
	//유저맵퍼 저장소
	@Autowired
	private UserMapper userMapper;
	
	public String checkUserIDExit(String user_id) {
		return userMapper.checkUserIDExit(user_id);
	}
	
	public String checkUserEmailExit(String user_email) {
		return userMapper.checkUserEmailExit(user_email);
	}
	public void modifyUserName(String user_name,int user_num) {
		userMapper.modifyUserName(user_name, user_num);
	}
	public void addUserInfo(UserBean RegisterUserBean) {
		userMapper.addUserInfo(RegisterUserBean);
	}
	
	public UserBean getLoginUserInfo(UserBean tempLoginUserBean) {
		return userMapper.getLoginUserInfo(tempLoginUserBean);
	}
	
	public UserBean getModifyUserInfo(int user_num) {
		return userMapper.getModifyUserInfo(user_num);
	}
	
	public void modifyUserInfo(UserBean modifyUserBean) {
		userMapper.modifyUserInfo(modifyUserBean);
	}
	
	public void addGoogleUserInfo(String user_id, String user_pw, String user_email, String user_name) {
		userMapper.addGoogleUserInfo(user_id, user_pw, user_email, user_name);
	}
	
	public UserBean getGoogleLoginUserInfo(String user_id) {
		return userMapper.getGoogleLoginUserInfo(user_id);
	}
	
	public String checkGoogleUserIDExit(String user_id) {
		return userMapper.checkGoogleUserIDExit(user_id);
	}
	
	public void modifyUserInfo2(UserBean modifyUserBean) {
		userMapper.modifyUserInfo2(modifyUserBean);
	}
	
	public void modifyUserPassword(String user_pw, String user_email) {
		userMapper.modifyUserPassword(user_pw, user_email);
	}
}
