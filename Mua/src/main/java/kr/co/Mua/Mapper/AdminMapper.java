package kr.co.Mua.Mapper;

import org.apache.ibatis.annotations.Select;

import kr.co.Mua.bean.AdminDto;

public interface AdminMapper {

	@Select("select admin_num, admin_id from admin where admin_id=#{admin_id} and admin_pw=#{admin_pw}")
	public AdminDto getLogin(AdminDto tempAdminDto);
}
