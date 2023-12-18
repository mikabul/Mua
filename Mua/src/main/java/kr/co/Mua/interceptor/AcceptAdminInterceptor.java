package kr.co.Mua.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;

import kr.co.Mua.bean.AdminDto;

public class AcceptAdminInterceptor implements HandlerInterceptor{
	
	private AdminDto loginAdminDto;
	
	public AcceptAdminInterceptor(AdminDto loginAdminDto) {
		this.loginAdminDto = loginAdminDto;
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		if(loginAdminDto.isLoginState() == false) {
			String contextPath = request.getContextPath();
			
			response.sendRedirect(contextPath + "/NotAccept");
			return false;
		} else {
			request.setAttribute("loginAdminDto", loginAdminDto);
		}
		
		return true;
	}
}
