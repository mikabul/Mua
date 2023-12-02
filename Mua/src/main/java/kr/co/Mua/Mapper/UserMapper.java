package kr.co.Mua.Mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.co.Mua.bean.UserBean;

public interface UserMapper {
	
	//�쑀�� �븘�씠�뵒瑜� �넻�빐 �쑀�� �꽕�엫 遺덈윭�삤湲�
	@Select("select user_name from user_info where user_id = #{user_id}")
	String checkUserIDExit(String user_id);
	
	//�궗�슜�옄 �젙蹂� �깮�꽦
	@Insert("insert into user_info values(user_numSEQ.nextval, #{user_id},#{user_pw},#{user_email},#{user_tel},#{user_address},#{user_name})")
	void addUserInfo(UserBean RegisterUserBean);
	
	//�쑀�� �븘�씠�뵒, 鍮꾨�踰덊샇瑜� �넻�빐 �쑀�� �꽕�엫, �꽆踰� 媛��졇�샂
	@Select("select user_name, user_num from user_info where user_id = #{user_id} and user_pw = #{user_pw}")
	UserBean getLoginUserInfo(UserBean tempLoginUserBean);
	
	//�쑀�� �꽆踰꾨�� �넻�빐 �븘�씠�뵒(readOnly), �씠由�, �씠硫붿씪, 二쇱냼, �쟾�솕踰덊샇 媛��졇�삤湲�
	@Select("select user_id, user_name, user_email, user_address, user_tel from user_info where user_num = #{user_num}")
	UserBean getModifyUserInfo(int user_num);
	
	// �쑀�� �꽆踰꾨�� �넻�빐 鍮꾨�踰덊샇, �씠硫붿씪, 
	@Update("update user_info set user_pw = #{user_pw}, user_email = #{user_email}, user_name = #{user_name}, "
			+ "user_address=#{user_address} where user_num = #{user_num}")
	void modifyUserInfo(UserBean modifyUserBean);
	
}
