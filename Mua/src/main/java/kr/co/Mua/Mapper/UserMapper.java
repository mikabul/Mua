package kr.co.Mua.Mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.co.Mua.bean.UserBean;

public interface UserMapper {
	
	//유저 아이디로 유저 이름 조회하는 쿼리(중복검사)
	@Select("select user_name from user_info where user_id = #{user_id}")
	String checkUserIDExit(String user_id);
	
	//유저이메일을 통해 유저인포의 아이디를 조회(값이 있을경우 fail 없을경우 true)
	@Select("select user_name from user_info where user_email = #{user_email}")
	String checkUserEmailExit(String user_email);
	
	//유저 회원가입
	@Insert("insert into user_info values(user_seq.nextval, #{user_id},#{user_pw},#{user_email},#{user_tel},#{user_address},#{user_name},#{user_birthday},sysdate)")
	void addUserInfo(UserBean RegisterUserBean);
	
	@Select("select user_name, user_num from user_info where user_id = #{user_id} and user_pw = #{user_pw}")
	UserBean getLoginUserInfo(UserBean tempLoginUserBean);
	
	@Select("select user_id, user_name, user_email, user_address, user_tel, user_birthday, user_registdate from user_info where user_num = #{user_num}")
	UserBean getModifyUserInfo(int user_num);
	
	//이메일 전화번호 주소 이름
	@Update("update user_info set user_email = #{user_email}, user_tel = #{user_tel}, user_name = #{user_name}, "
			+ "user_address=#{user_address} where user_num = #{user_num}")
	void modifyUserInfo(UserBean modifyUserBean);
	
}