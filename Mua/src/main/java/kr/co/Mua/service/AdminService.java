package kr.co.Mua.service;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.Mua.bean.AdminDto;
import kr.co.Mua.dao.AdminDao;

@Service
public class AdminService {

	@Autowired
	private AdminDao adminDao;
	
	@Resource(name="loginAdminDto")
	private AdminDto loginAdminDto;
	
	public boolean getLogin(AdminDto tempAdminDto) {
		AdminDto adminDto = adminDao.getLogin(tempAdminDto);
		System.out.println(adminDto);
		if(adminDto == null) {
			return false;
		} else {
			loginAdminDto.setAdmin_num(adminDto.getAdmin_num());
			loginAdminDto.setAdmin_id(adminDto.getAdmin_id());
			loginAdminDto.setLoginState(true);
			return true;
		}
	}
	
}
