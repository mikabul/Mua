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
	
	//id瑜� �넻�빐 �쑀�� �꽕�엫 議댁옱�뿬遺� �솗�씤
	public boolean checkUserIDExit(String user_id) {
		
		String user_name = userDAO.checkUserIDExit(user_id);
		if(user_name == null) {
			return true;
		}else {
			return false;
		}
		
	}
	
	//�궗�슜�옄 �젙蹂� 異붽� (以묐났�솗�씤�� �럹�씠吏��뿉�꽌 �븘�옉�뒪濡� 而⑦듃濡�)
	public void addUserInfo(UserBean RegisterUserBean) {
		userDAO.addUserInfo(RegisterUserBean);
	}
	
	// 鍮꾨�踰덊샇�� �븘�씠�뵒濡� 濡쒓렇�씤 �쑀�� �젙蹂� 媛��졇�삤湲� (�걣怨좊떎�땲�뒗 �젙蹂대뒗 num怨� id �몢媛�)
	public void getLoginUserInfo(UserBean tempLoginUserBean) {
		UserBean tempLoginUserBean2 = userDAO.getLoginUserInfo(tempLoginUserBean);
		
		if(tempLoginUserBean2 != null) {
			loginUserBean.setUser_num(tempLoginUserBean2.getUser_num());
			loginUserBean.setUser_id(tempLoginUserBean2.getUser_id());
			loginUserBean.setUserLogin(true);
		}
	}
	
	// 濡쒓렇�씤�맂 �쑀�� �꽆踰꾨�� �넻�빐 �궗�슜�옄 �븘�씠�뵒, �씠由�, �씠硫붿씪, 二쇱냼, �쟾�솕踰덊샇瑜� 媛��졇���꽌 紐⑤뵒�뙆�씠�쑀��鍮덉뿉 �꽕�젙
	public UserBean getModifyUserInfo(UserBean modifyUserBean) {
		
		UserBean tempModifyUserBean = userDAO.getModifyUserInfo(loginUserBean.getUser_num());
		modifyUserBean.setUser_id(tempModifyUserBean.getUser_id());
		modifyUserBean.setUser_name(tempModifyUserBean.getUser_name());
		modifyUserBean.setUser_email(tempModifyUserBean.getUser_email());
		modifyUserBean.setUser_address(tempModifyUserBean.getUser_address());
		modifyUserBean.setUser_tel(tempModifyUserBean.getUser_tel());
		
		
		
		return modifyUserBean;
	}
	
	// �븘�씠�뵒 鍮꾨�踰덊샇瑜� �븳踰� �뜑 �솗�씤 諛쏆��썑 紐⑤뵒�뙆�씠�쑀��鍮덉쓣 �쁽�옱 濡쒓렇�씤�맂 �궗�슜�옄 �꽆踰꾨�� 李얠븘�꽌 議곗씤�썑 �뾽�뜲�씠�듃
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
