package kr.yun.app.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.yun.app.service.HomeService;

@Controller
public class homeController {
	
	@Autowired HomeService hs;
	
	@GetMapping("/")
	public String home(Model model) {
		model.addAttribute("newItem",hs.newItem());
		model.addAttribute("hitItem",hs.hitItem());
//		if(hs.test() == 1) System.out.println("DB 연결완료");
		return "home";
	}
	
	//카테고리 페이지
	@GetMapping(value = "/{num}")
	public String home(@PathVariable int num) {
		
		return "home";
	}
	
	
	
	
}
