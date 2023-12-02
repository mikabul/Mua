package kr.co.Mua.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.SessionScope;

import kr.co.Mua.bean.UserBean;

@Configuration
public class RootAppContext {
<<<<<<< HEAD

	@Bean("loginUserBean")
	@SessionScope
	public UserBean loginUserBean() {
		return new UserBean();
	}
=======
	
>>>>>>> refs/remotes/origin/이영민
}
