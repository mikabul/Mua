package kr.co.Mua.Mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.co.Mua.bean.UserBean;

public interface UserMapper {
	
	//유저 아이디를 통해 유저 네임 불러오기
	@Select("select user_name from user_info where id = #{user_id}")
	String checkUserIDExit(String user_id);
	
	//사용자 정보 생성
	@Insert("insert into user_info values(user_numSEQ.nextval, #{user_id},#{user_pw},#{user_email},#{user_tel},#{user_address},#{user_name})")
	void addUserInfo(UserBean RegisterUserBean);
	
	//유저 아이디, 비밀번호를 통해 유저 네임, 넘버 가져옴
	@Select("select user_name, user_num from user_info where user_id = #{user_id} and user_pw = #{user_pw}")
	UserBean getLoginUserInfo(UserBean tempLoginUserBean);
	
	//유저 넘버를 통해 아이디(readOnly), 이름, 이메일, 주소, 전화번호 가져오기
	@Select("select user_id, user_name, user_email, user_address, user_tel from user_info where user_num = #{user_num}")
	UserBean getModifyUserInfo(int user_num);
	
	// 유저 넘버를 통해 비밀번호, 이메일, 
	@Update("update user_info set user_pw = #{user_pw}, user_email = #{user_email}, user_name = #{user_name}, "
			+ "user_address=#{user_address} where user_num = #{user_num}")
	void modifyUserInfo(UserBean modifyUserBean);
	
}
