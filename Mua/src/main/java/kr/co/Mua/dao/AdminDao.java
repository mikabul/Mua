package kr.co.Mua.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.Mua.Mapper.AdminMapper;
import kr.co.Mua.bean.AdminDto;

@Repository
public class AdminDao {
	
	@Autowired
	private AdminMapper adminMapper;

	public AdminDto getLogin(AdminDto tempAdminDto) {
		return adminMapper.getLogin(tempAdminDto);
	}
}
