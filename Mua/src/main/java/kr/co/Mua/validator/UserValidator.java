package kr.co.Mua.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import kr.co.Mua.bean.UserBean;

public class UserValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return UserBean.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		UserBean userBean = (UserBean)target;
		String beanName = errors.getObjectName();
		
		//회원가입시에만 낫블랭크 판단할것 그리고 유저빈 낫블랭크 유효성 없앨것
		if(beanName.equals("registerUserBean")) {
			//아이디 생성 때 비밀번호 확인
			if (userBean.getUser_pw().equals(userBean.getUser_pw2()) == false) {
				errors.rejectValue("user_pw", "NotEquals");
				errors.rejectValue("user_pw2", "NotEquals");
			}
			
			//아이디중복검사
			if (userBean.isUserIdExit() == false) {
				errors.rejectValue("user_id", "DontcheckedID");
			}
			
			if(userBean.getUser_address().equals(" ") || userBean.getUser_address().trim().isEmpty()) {
				errors.rejectValue("user_address", "VNotEmpty");
			}
			if(userBean.getUser_tel().contains(" ") || userBean.getUser_tel().trim().isEmpty()) {
				errors.rejectValue("user_tel", "VNotEmpty");
			}
			if(userBean.getUser_name().contains(" ") || userBean.getUser_name().trim().isEmpty()) {
				errors.rejectValue("user_name", "VNotEmpty");
			}
			if(userBean.getUser_email().contains(" ") || userBean.getUser_email().trim().isEmpty()) {
				errors.rejectValue("user_email", "VNotEmpty");
			}
		}
		
		// 비밀번호 수정
		if (beanName.equals("modifyUserBean")) {
			if (userBean.getUser_pw().equals(userBean.getUser_pw2()) == false) {
				errors.rejectValue("user_pw", "NotEquals");
				errors.rejectValue("user_pw2", "NotEquals");
			}
		}
		
		
		
		
	}
	
	
	
}
