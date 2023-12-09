package kr.co.Mua.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.Mua.Mapper.UserMapper;
import kr.co.Mua.bean.UserBean;

@Repository
public class UserDAO {
	
	//유저맵퍼 저장소
	
	@Autowired
	private UserMapper userMapper;
	
	public String checkUserIDExit(String user_id) {
		return userMapper.checkUserIDExit(user_id);
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
}
