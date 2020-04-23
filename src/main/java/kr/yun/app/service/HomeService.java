package kr.yun.app.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.yun.app.mapper.HomeMapper;

@Service
public class HomeService {

	@Autowired HomeMapper hm;
	
	public int test() {
		return hm.test();
	}
	
	//새로 등록된 상품 
	public List<Map<String, Object>> newItem(){
		return hm.newItem();
	}
	//인기 상품4개
	public List<Map<String, Object>> hitItem(){
		return hm.hitItem();
	}
	
	
}
