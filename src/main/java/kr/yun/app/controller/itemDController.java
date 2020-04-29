package kr.yun.app.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.yun.app.service.HomeService;
import kr.yun.app.service.ItemDService;

@Controller
public class itemDController {
	
	@Autowired ItemDService ds;
	@Autowired HomeService hs;
	
	private int item_no;
	
	@GetMapping("/itemDeatails")
	public String itemDeatails() {
		
		return "itemDeatails";
	} 

	//카테고리 페이지
	@GetMapping(value = "/itemDeatails/{user_no}/{no}")
	public String get_ItemD(@PathVariable int no, @PathVariable int user_no, Model model) {
		item_no = no;
		model.addAttribute("get_img", ds.get_img(no));
		model.addAttribute("get_ItemD", ds.get_ItemD(no));
		model.addAttribute("writer_info", ds.get_user(user_no));
		model.addAttribute("getCategory", hs.getCategory());
		model.addAttribute("comment", ds.get_comment(no));
		System.out.println("댓글정보 : " + model.getAttribute("comment"));
//		System.out.println("유저정보 : " + model.getAttribute("writer_info"));
		return "itemDeatails";
	}
	
	//댓글
	@PostMapping(value = "/comment")
	public @ResponseBody int comment(@RequestBody Map<String, Object> params, HttpSession session) {
		params.put("writer", session.getAttribute("nick_name").toString());
		params.put("t_img", session.getAttribute("profile_img").toString());
		params.put("item_no", item_no);
		return ds.set_comment(params);
	}
	
	
}
