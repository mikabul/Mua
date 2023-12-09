package kr.co.Mua.Mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.co.Mua.bean.UserBean;

public interface UserMapper {
	
	@Select("select user_name from user_info where user_id = #{user_id}")
	String checkUserIDExit(String user_id);
	
	@Insert("insert into user_info values(user_seq.nextval, #{user_id},#{user_pw},#{user_email},#{user_tel},#{user_address},#{user_name})")
	void addUserInfo(UserBean RegisterUserBean);
	
	@Select("select user_name, user_num from user_info where user_id = #{user_id} and user_pw = #{user_pw}")
	UserBean getLoginUserInfo(UserBean tempLoginUserBean);
	
	@Select("select user_id, user_name, user_email, user_address, user_tel from user_info where user_num = #{user_num}")
	UserBean getModifyUserInfo(int user_num);
	
	@Update("update user_info set user_pw = #{user_pw}, user_email = #{user_email}, user_name = #{user_name}, "
			+ "user_address=#{user_address} where user_num = #{user_num}")
	void modifyUserInfo(UserBean modifyUserBean);
	
}
