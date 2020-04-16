package kr.yun.app.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface HomeMapper {
	
	//DB연결 테스트
	@Select("select 1")
	public Integer test();
	
	
}
