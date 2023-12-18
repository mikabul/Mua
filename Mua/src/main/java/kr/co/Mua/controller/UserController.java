package kr.co.Mua.controller;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.jsoup.Connection.Method;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.Mua.bean.UserBean;
import kr.co.Mua.service.UserService;
import kr.co.Mua.validator.UserValidator;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Resource(name="loginUserBean")
	private UserBean loginUserBean;
	
	@GetMapping("/login")
	public String login(@ModelAttribute("tempLoginUserBean") UserBean tempLoginUserBean, @RequestParam(value = "fail", defaultValue = "false") boolean fail, Model model) 
	{
		model.addAttribute("fail",fail);
		return "user/login";		
	}
	
	@PostMapping("/login")
	public String postlogin(@ModelAttribute("tempLoginUserBean") UserBean tempLoginUserBean, @RequestParam(value = "fail", defaultValue = "false") boolean fail, Model model) 
	{
		model.addAttribute("fail",fail);
		return "user/login";		
	}
	
	@PostMapping("/login_pro")
	public String login_proc(@Valid @ModelAttribute("tempLoginUserBean") UserBean tempLoginUserBean, BindingResult result) {
		if(result.hasErrors()) {
			return "user/login";
		}
		
		userService.getLoginUserInfo(tempLoginUserBean);
		
		if(loginUserBean.isUserLogin()==true) {
			return "user/login_success";
		}else {
			return "user/login_fail";
		}
	}
	
	@GetMapping("/register")
	public String register(@ModelAttribute("registerUserBean") UserBean RegisterUserBean) {
		
		return "user/register";
	}
	
	@GetMapping("/modifyPassword")
	public String modifyPassword(@ModelAttribute("modifyPasswordBean") UserBean modifyPasswordBean) {
		
		return "user/modifyPassword";
	}
	
	@PostMapping("/modifyPassword_pro")
	public String modifyPassword_pro(@Valid @ModelAttribute("modifyPasswordBean") UserBean modifyPasswordBean, BindingResult result) {
		if(result.hasErrors()) {
			return "user/modify_fail2";
		}
		System.out.println(modifyPasswordBean.getUser_pw());
		userService.ModifyUserPassword(modifyPasswordBean.getUser_pw(),modifyPasswordBean.getUser_email());
		
		return "user/modify_success2";
	}
	
	@PostMapping("/register_pro")
	public String register_pro(@Valid @ModelAttribute("registerUserBean") UserBean RegisterUserBean, BindingResult result) {
		
		if(result.hasErrors()) {
			return "user/register";
		}
		
		userService.addUserInfo(RegisterUserBean);
		
		return "user/register_success";
		
	}
	
	@GetMapping("/info")
	public String info(@ModelAttribute("infoUserBean") UserBean infoUserBean) {
		
		infoUserBean = userService.getModifyUserInfo(infoUserBean);
		
		return "user/info";
	}
	
	@PostMapping("/modify")
	public String modify(@ModelAttribute("modifyUserBean") UserBean modifyUserBean, BindingResult result) {
		
		modifyUserBean = userService.getModifyUserInfo(modifyUserBean);
		
		return "user/modify";
	}
	
	@PostMapping("/modify_pro")
	public String modify_pro(
			@Valid @ModelAttribute("modifyUserBean") UserBean modifyUserBean , BindingResult result ) {
		if(result.hasErrors()) {
			System.out.println(result);
			return "user/modify_fail";
		}
		
		userService.modifyUserInfo2(modifyUserBean);
		
		return "user/modify_success";
	}
	
	@GetMapping("/logout")
	public String logout() {
		loginUserBean.setUserLogin(false);
		
		return "user/logout";
	}
	
	@GetMapping("/not_login")
	public String not_login() {
		return "user/not_login";
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		UserValidator validator1 = new UserValidator();
		binder.addValidators(validator1);
	}
}





































