package kr.yun.app.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.yun.app.Bean.UserBean;

@Mapper
public interface MypageMapper {
	
	//내상품 가져오기
	@Select("SELECT a.*, b.img_path, b.img_type, \n" + 
			"case when a.`status` = 'I' then '나눔중'\n" + 
			"	  ELSE '나눔완료'\n" + 
			"	  END AS `stat`,\n" + 
			"DATE_FORMAT(a.reg, '%Y-%m-%d') AS 'date'	   \n" + 
			"FROM t_item AS a\n" + 
			"INNER JOIN t_img AS b\n" + 
			"ON(a.`no` = b.item_no AND `delYn` = 'N') \n" + 
			"WHERE a.user_no = #{no} AND b.img_type = 'T'\n" + 
			"ORDER BY `no` DESC")
	public List<Map<String, Object>> myItem(int no);
	
}
