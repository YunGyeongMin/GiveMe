package kr.yun.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.yun.app.mapper.HomeMapper;

@Service
public class HomeService {

	@Autowired HomeMapper hm;
	
	public int test() {
		return hm.test();
	}
	
}
