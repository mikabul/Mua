package kr.co.Mua.Mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.co.Mua.bean.UserBean;

public interface UserMapper {

	//유저 아이디로 유저 이름 조회하는 쿼리(중복검사)
	@Select("select user_name from user_info where user_id = #{user_id}")
	String checkUserIDExit(String user_id);
	
	@Select("select user_name from user_info where user_id = #{arg0}")
	String checkGoogleUserIDExit(String user_id);
	
	//유저이메일을 통해 유저인포의 아이디를 조회(값이 있을경우 fail 없을경우 true)
	@Select("select user_name from user_info where user_email = #{user_email}")
	String checkUserEmailExit(String user_email);
	
	//유저 회원가입
	@Insert("insert into user_info values(user_seq.nextval, #{user_id},#{user_pw},#{user_email},#{user_tel},#{user_address},#{user_name},#{user_birthday},sysdate,'Mua 회원')")
	void addUserInfo(UserBean RegisterUserBean);
	
	//구글 소셜 로그인정보 저장
	//arg0:user_id
	//arg1:user_pw
	//arg2:user_email
	//arg3:user_tel
	//arg4:user_address
	//arg5:user_name
	//arg6:user_birthday	
	@Insert("insert into user_info values(user_seq.nextval, #{arg0},#{arg1},#{arg2},null,null,#{arg3},TO_DATE('2999-12-31', 'YYYY-MM-DD'),sysdate,'Google 회원')")
	void addGoogleUserInfo(String user_id, String user_pw, String user_email, String user_name);
	
	@Select("select user_name, user_num from user_info where user_id = #{user_id} and user_pw = #{user_pw}")
	UserBean getLoginUserInfo(UserBean tempLoginUserBean);
	
	@Select("select user_name, user_num from user_info where user_id = #{arg0}")
	UserBean getGoogleLoginUserInfo(String user_id);
	
	//유저아이디, 이름, 이메일, 집주소, 전화번호, 생일, 생성일
	@Select("select user_id, user_name, user_email, user_address, user_tel, user_birthday, user_registdate from user_info where user_num = #{user_num}")
	UserBean getModifyUserInfo(int user_num);
	
	//이메일 전화번호 주소 이름
	@Update("update user_info set user_email = #{user_email}, user_tel = #{user_tel}, user_name = #{user_name}, "
			+ "user_address=#{user_address} where user_num = #{user_num}")
	void modifyUserInfo(UserBean modifyUserBean);
	
	//유저 정보 수정
	@Update("update user_info set user_email = #{user_email}, user_tel = #{user_tel}, user_name = #{user_name}, user_address = #{user_address} "
			+ "where user_num = #{user_num}")
	void modifyUserInfo2(UserBean modifyUserBean);
	
	//유저 이름 수정
	@Update("update user_info set user_name = #{arg0} where user_num = #{arg1}")
	void modifyUserName(String user_name, int user_num);
	
	//유저 비밀번호 수정
	@Update("update user_info set user_pw = #{arg0} where user_email = #{arg1}")
	void modifyUserPassword(String user_password, String user_email);
}
