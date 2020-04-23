package kr.yun.app.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface HomeMapper {
	
	//DB연결 테스트
	@Select("select 1")
	public Integer test();
	
	//새로 등록된 상품 4개
	@Select("SELECT a.*, b.img_path\n" + 
			"FROM t_item AS a\n" + 
			"INNER JOIN t_img AS b\n" + 
			"ON(a.no = b.item_no)\n" + 
			"WHERE img_type = 'T'\n" + 
			"ORDER BY `no` DESC LIMIT 4;")
	public List<Map<String, Object>> newItem();
	
	//인기 상품4개
	@Select("SELECT a.*, b.img_path\n" + 
			"FROM t_item AS a\n" + 
			"INNER JOIN t_img AS b\n" + 
			"ON(a.no = b.item_no)\n" + 
			"WHERE img_type = 'T'\n" + 
			"ORDER BY a.hit DESC LIMIT 4;")
	public List<Map<String, Object>> hitItem();
	
	//카테고리no
	@Select("SELECT * FROM t_category WHERE `delYn` = 'N'")
	public List<Map<String, Object>> getCategory();
	
}
