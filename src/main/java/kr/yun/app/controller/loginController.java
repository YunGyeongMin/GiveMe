package kr.yun.app.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import kr.yun.app.service.kakaoService;
import kr.yun.app.util.KakaoAPI;


@Controller
public class loginController {
	
	@Autowired kakaoService ks;
	@Autowired KakaoAPI sn;
	
	//로그인여부 확인
	@GetMapping("/login")
	public RedirectView login(HttpSession session) throws Exception{
		return new RedirectView(ks.loginConfirm(session));
	}
	
	//로그인
	@RequestMapping(value = "/kakaoLogin" , method = RequestMethod.GET) 
	public RedirectView kakaoLogin(@RequestParam("code") String code, HttpSession session) throws IOException {
		ks.loginRun(code, session);
		return new RedirectView("/");
	}
	
	// 회원 로그아웃
	@GetMapping("/logout")
	public RedirectView logout(HttpSession session) {
		if(ks.setKakaoLogout(session)) {
			session.invalidate();
			System.out.println("로그아웃 성공");
		}		
		return new RedirectView("/");
	}

	// 회원 탈퇴
	@GetMapping("/remove")
	public RedirectView remove(HttpSession session) {
		if(ks.setKakaoRemove(session)) {
			session.invalidate();
			System.out.println("회원탈퇴 성공");
		}
		return new RedirectView("/");
	}

}
