package kr.yun.app.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WritingMapper {
	
	//상품등록
	@Insert("INSERT INTO t_item (`user_no`,`category_no`,`title`,`content`,`price`,`writer`,`offerYn`)\n"  
			+"VALUES"
			+ "(#{no},(SELECT `NO` FROM t_category WHERE `delYn` = 'N' AND category_name = #{category}),#{title},#{content},#{price},#{nick_name},#{offerYn});")
	public int setItem(Map<String, Object> paramMap);
}
 