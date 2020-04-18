package kr.yun.app.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.yun.app.service.WritingService;

@Controller
public class writingController {
	
	@Autowired WritingService ws;
	
	@GetMapping("/writing")
	public String writing() {
		return "writing";
	}
	
	//상품등록
	@PostMapping("/itemRegister")
	public @ResponseBody int itemRegister(@RequestBody Map<String , Object> paramMap,HttpSession session) {
		System.out.println("컨트");
		return ws.setItem(paramMap,session);
	}
}
