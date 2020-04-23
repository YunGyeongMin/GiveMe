package kr.yun.app.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import kr.yun.app.Bean.UserBean;
import kr.yun.app.mapper.UserMapper;
import kr.yun.app.service.HomeService;
import kr.yun.app.service.MypageService;

@Controller
public class myPageController {
	
	@Autowired MypageService ms;
	@Autowired UserMapper um;
	@Autowired HomeService hs;
	
	@GetMapping("/myPage")
	public String myPage(Model model, HttpSession session) {
		UserBean ub = new UserBean();
		ub = um.getUser(session.getAttribute("id").toString());
		model.addAttribute("user_info", ub);
		model.addAttribute("myItem",ms.myItem(ub.getNo()));
		model.addAttribute("getCategory", hs.getCategory());
//		System.out.println(model.getAttribute("user_info"));
//		System.out.println(model.getAttribute("myItem"));
		return "myPage";
	}

}
