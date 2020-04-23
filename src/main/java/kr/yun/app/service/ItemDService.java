package kr.yun.app.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.yun.app.mapper.HomeMapper;
import kr.yun.app.mapper.ItemDMapper;

@Service
public class ItemDService {

	@Autowired ItemDMapper dm;
	
	public List<Map<String, Object>> get_img(int no){
		return dm.get_img(no);
	}

	public Map<String, Object> get_ItemD(int no){
		return dm.get_ItemD(no);
	}
	
	public Map<String, Object> get_user(int user_no){
		return dm.get_user(user_no);
	}
}
