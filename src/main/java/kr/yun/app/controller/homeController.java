package kr.yun.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import kr.yun.app.service.HomeService;

@Controller
public class homeController {
	
	@Autowired HomeService hs;
	
	@GetMapping("/")
	public String home() {
//		if(hs.test() == 1) System.out.println("DB 연결완료");
		return "home";
	}

	
}
