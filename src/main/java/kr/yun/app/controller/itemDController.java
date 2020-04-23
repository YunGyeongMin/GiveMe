package kr.yun.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import kr.yun.app.service.HomeService;
import kr.yun.app.service.ItemDService;

@Controller
public class itemDController {
	
	@Autowired ItemDService ds;
	@Autowired HomeService hs;
	
	@GetMapping("/itemDeatails")
	public String itemDeatails() {
		
		return "itemDeatails";
	} 

	//카테고리 페이지
	@GetMapping(value = "/itemDeatails/{user_no}/{no}")
	public String get_ItemD(@PathVariable int no, @PathVariable int user_no, Model model) {
		model.addAttribute("get_img", ds.get_img(no));
		model.addAttribute("get_ItemD", ds.get_ItemD(no));
		model.addAttribute("writer_info", ds.get_user(user_no));
		model.addAttribute("getCategory", hs.getCategory());
//		System.out.println("유저정보 : " + model.getAttribute("writer_info"));
		return "itemDeatails";
	}
}
