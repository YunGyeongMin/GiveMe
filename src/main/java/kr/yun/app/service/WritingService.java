package kr.yun.app.service;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.yun.app.Bean.UserBean;
import kr.yun.app.mapper.UserMapper;
import kr.yun.app.mapper.WritingMapper;

@Service
public class WritingService {
	
	@Autowired WritingMapper wm;
	@Autowired UserMapper um;
	
	public int setItem(Map<String , Object> paramMap,HttpSession session) {
		UserBean ub = new UserBean();
		ub = um.getUser(session.getAttribute("userId").toString());
		paramMap.put("no", ub.getNo());		
		paramMap.put("nick_name", ub.getNick_name());
		System.out.println("service 상품등록 : " + paramMap);
		return wm.setItem(paramMap);
	}
	
}
