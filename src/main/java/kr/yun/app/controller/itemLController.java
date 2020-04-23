package kr.yun.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import kr.yun.app.service.HomeService;

@Controller
public class itemLController {
	
	@Autowired HomeService hs;
	
	
	@GetMapping("/itemList")
	public String itemList() {
		return "itemList";
	}

	//카테고리 페이지
	@GetMapping(value = "/itemList/{no}")
	public String home(@PathVariable int no, Model model) {
		model.addAttribute("getCategory", hs.getCategory());
		return "itemList";
	}
}
