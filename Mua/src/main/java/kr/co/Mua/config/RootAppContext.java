package kr.co.Mua.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.SessionScope;
import kr.co.Mua.bean.AdminDto;
import kr.co.Mua.bean.UserBean;

@Configuration
public class RootAppContext {

	@Bean("loginUserBean")
	@SessionScope
	public UserBean loginUserBean() {
		return new UserBean();
	}
	@Bean("loginAdminDto")
	@SessionScope
	public AdminDto loginAdminDto() {
		return new AdminDto();
	}
}
