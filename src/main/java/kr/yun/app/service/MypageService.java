package kr.yun.app.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.yun.app.mapper.MypageMapper;

@Service
public class MypageService {

	@Autowired MypageMapper mm;
	
	public List<Map<String, Object>> myItem(int no){
		return mm.myItem(no);
	}
	
	
}
