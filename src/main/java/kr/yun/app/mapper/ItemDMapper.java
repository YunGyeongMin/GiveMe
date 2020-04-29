package kr.yun.app.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ItemDMapper {
	
	//선택 아이템 정보
	@Select("SELECT a.*\n" + 
			"FROM t_item AS a\n" + 
			"WHERE a.`no` = #{no}")
	public Map<String, Object> get_ItemD(int no);
	
	//선택 이미지 정보
	@Select("SELECT *\n" + 
			"FROM t_img\n" + 
			"WHERE `item_no`= #{no}")
	public List<Map<String, Object>> get_img(int no);
	
	//선택 아이템 등록자 정보
	@Select("SELECT * FROM t_user WHERE `no` = #{user_no}")
	public Map<String, Object> get_user(int user_no);
	
	//댓글
	@Insert("INSERT INTO t_comment (`item_no`, `writer`, `comment`,`t_img`) VALUES (#{item_no},#{writer},#{comment},#{t_img})")
	public int set_comment(Map<String, Object> params);
	
	//선택 아이템 댓글 가져오기
	@Select("SELECT * FROM t_comment WHERE `item_no` = #{item_no} ORDER BY `no` DESC ")
	public List<Map<String, Object>> get_comment(int item_no);
	
}
