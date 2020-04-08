package kr.yun.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import kr.yun.app.service.HomeService;

@Controller
public class writingController {
	
	@Autowired HomeService hs;
	
	@GetMapping("/writing")
	public String writing() {
		return "writing";
	}

	
}
