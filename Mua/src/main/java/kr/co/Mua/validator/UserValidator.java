package kr.co.Mua.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import kr.co.Mua.bean.UserBean;

public class UserValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return UserBean.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		UserBean userBean = (UserBean) target;
		String beanName = errors.getObjectName();

		if (beanName.equals("modifyUserBean")) {
			if (userBean.getAuthCode1() != null) {
				if (userBean.getAuthCode1().equals(userBean.getAuthCode2()) == false) {
					errors.rejectValue("authCode2", "CodeNotEquals");
				}
			}
		}

		if (beanName.equals("modifyPasswordBean")) {
			if(userBean.getAuthCode1() != null) {
				if (userBean.getAuthCode1().equals(userBean.getAuthCode2()) == false) {
					errors.rejectValue("authCode2", "CodeNotEquals");
				}
			}
			if (userBean.getUser_pw().equals(" ") || userBean.getUser_pw().trim().isEmpty()) {
				errors.rejectValue("user_pw", "pwIsEmpty");
			} else if (userBean.getUser_pw().length() < 8 || userBean.getUser_pw().length() > 21) {
				errors.rejectValue("user_pw", "pwSizeWrong");
			} else if (userBean.getUser_pw().equals(userBean.getUser_pw2()) == false) {
				errors.rejectValue("user_pw", "NotEquals");
				errors.rejectValue("user_pw2", "NotEquals");
			}
		}

		// �븘�씠�뵒媛� �깮�꽦�맆�븣 �궗�슜�릺�뒗 鍮� registerUserBean�쓽 蹂��닔�뱾�쓣 �넻�빐 �쑀�슚�꽦 寃��궗
		if (beanName.equals("registerUserBean")) {
			if (userBean.getUser_pw().equals(" ") || userBean.getUser_pw().trim().isEmpty()) {
				errors.rejectValue("user_pw", "pwIsEmpty");
			} else if (userBean.getUser_pw().length() < 8 || userBean.getUser_pw().length() > 21) {
				errors.rejectValue("user_pw", "pwSizeWrong");
			} else if (userBean.getUser_pw().equals(userBean.getUser_pw2()) == false) {
				errors.rejectValue("user_pw", "NotEquals");
				errors.rejectValue("user_pw2", "NotEquals");
			}

			if (userBean.getAuthCode1() != null) {
				if (userBean.getAuthCode1().equals(userBean.getAuthCode2()) == false) {
					errors.rejectValue("authCode2", "CodeNotEquals");
				}
			}

			// �븘�씠�뵒 以묐났泥댄겕
			if (userBean.getUser_id().equals(" ") || userBean.getUser_id().trim().isEmpty()) {
				errors.rejectValue("user_id", "IdIsEmpty");
			} else if (userBean.getUser_id().length() < 6 || userBean.getUser_id().length() > 12) {
				errors.rejectValue("user_id", "IdSizeWrong");
			} else if (userBean.isUserIdExit() == false) {
				errors.rejectValue("user_id", "DontcheckedID");
			}

			// �씠硫붿씪 以묐났泥댄겕

			if (userBean.getUser_address().equals(" ") || userBean.getUser_address().trim().isEmpty()) {
				errors.rejectValue("user_address", "VNotEmpty");
			}

			if (userBean.getUser_tel().contains(" ") || userBean.getUser_tel().trim().isEmpty()) {
				errors.rejectValue("user_tel", "VNotEmpty");
			}
			if (userBean.getUser_name().contains(" ") || userBean.getUser_name().trim().isEmpty()) {
				errors.rejectValue("user_name", "VNotEmpty");
			}

			if (userBean.getUser_email().contains(" ") || userBean.getUser_email().trim().isEmpty()) {
				errors.rejectValue("user_email", "VNotEmpty");
			} else if (userBean.isUserEmailExit() == false) {
				errors.rejectValue("user_email", "DontcheckedEmail");
			}
		}

	}

}
