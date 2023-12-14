package kr.co.Mua.controller;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.co.Mua.bean.AdminDto;
import kr.co.Mua.service.AdminService;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

	@Resource(name = "loginAdminDto")
	private AdminDto loginAdminDto;

	@Autowired
	private AdminService adminService;

	

	@RequestMapping(value = "/login")
	public String login(@ModelAttribute("tempAdminDto") AdminDto tempAdminDto) {

		return "/admin/login";
	}

	@RequestMapping(value = "/login_pro")
	public String login_pro(@ModelAttribute("tempAdminDto") AdminDto tempAdminDto) {
		System.out.println(tempAdminDto.getAdmin_id());
		System.out.println(tempAdminDto.getAdmin_pw());

		if (!adminService.getLogin(tempAdminDto)) {
			return "/admin/login_fail";
		} else {
			return "/admin/login_success";
		}

	}
	
	@RequestMapping(value="/main")
	public String admin_main() {
		return "/admin/main";
	}
	
	
}
