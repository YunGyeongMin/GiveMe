package kr.yun.app.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.yun.app.Bean.UserBean;

@Mapper
public interface UserMapper {
	
	//회원정보 등록
	@Insert("INSERT INTO t_user (`id`,`nick_name`,`gender`,`profile_img`,`email`)\n" + 
			"VALUES (#{id},#{nick_name},#{gender},#{profile_img},#{email})")
	public int setUser(Map<String , Object> userInfo);
	
	//회원정보 등록 중복여부
	@Select("SELECT id\n" + 
			"FROM t_user\n" + 
			"WHERE delYn = 'N' AND id = ${id};")
	public String setUserConfirm(String id);
	
	//회원탈퇴
	@Update("UPDATE t_user\n" + 
			"SET `delYn` = 'Y'\n" + 
			"WHERE `id` = #{id}")
	public int delUser(String id);
	
	//회원정보 빈 등록
	@Select("SELECT *\n" + 
			"FROM t_user\n" + 
			"WHERE delYn = 'N' AND id = #{id};")
	public UserBean getUser(String id);
	
}
