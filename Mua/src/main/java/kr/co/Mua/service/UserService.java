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
	
	//id를 통해 유저 네임 존재여부 확인
	public boolean checkUserIDExit(String user_id) {
		
		String user_name = userDAO.checkUserIDExit(user_id);
		if(user_name == null) {
			return true;
		}else {
			return false;
		}
		
	}
	
	//사용자 정보 추가 (중복확인은 페이지에서 아작스로 컨트롤)
	public void addUserInfo(UserBean RegisterUserBean) {
		userDAO.addUserInfo(RegisterUserBean);
	}
	
	// 비밀번호와 아이디로 로그인 유저 정보 가져오기 (끌고다니는 정보는 num과 id 두개)
	public void getLoginUserInfo(UserBean tempLoginUserBean) {
		UserBean tempLoginUserBean2 = userDAO.getLoginUserInfo(tempLoginUserBean);
		
		if(tempLoginUserBean2 != null) {
			loginUserBean.setUser_num(tempLoginUserBean2.getUser_num());
			loginUserBean.setUser_id(tempLoginUserBean2.getUser_id());
			loginUserBean.setUserLogin(true);
		}
	}
	
	// 로그인된 유저 넘버를 통해 사용자 아이디, 이름, 이메일, 주소, 전화번호를 가져와서 모디파이유저빈에 설정
	public UserBean getModifyUserInfo(UserBean modifyUserBean) {
		
		UserBean tempModifyUserBean = userDAO.getModifyUserInfo(loginUserBean.getUser_num());
		modifyUserBean.setUser_id(tempModifyUserBean.getUser_id());
		modifyUserBean.setUser_name(tempModifyUserBean.getUser_name());
		modifyUserBean.setUser_email(tempModifyUserBean.getUser_email());
		modifyUserBean.setUser_address(tempModifyUserBean.getUser_address());
		modifyUserBean.setUser_tel(tempModifyUserBean.getUser_tel());
		
		
		
		return modifyUserBean;
	}
	
	// 아이디 비밀번호를 한번 더 확인 받은후 모디파이유저빈을 현재 로그인된 사용자 넘버를 찾아서 조인후 업데이트
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
