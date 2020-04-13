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
	
	@GetMapping("/login")
	public RedirectView login(HttpSession session) throws Exception{
		String url2 = "/";
		if(session.getAttribute("userId") == null) {
			String url = sn.kakao_auth;
			url += "?client_id=" + sn.kakao_client_id;
			url += "&redirect_uri=" + sn.kakao_redirect_uri2;
			url += "&response_type=code";
			System.out.println(url);
			url2 = sn.kakao_login + "?continue=" + URLEncoder.encode(url, "utf-8");
			System.out.println(url2);
		}
		return new RedirectView(url2);
	}
	
	@RequestMapping(value = "/kakaoLogin" , method = RequestMethod.GET) 
	public RedirectView kakaoLogin(@RequestParam("code") String code, HttpSession session) throws IOException {
		System.out.println("code : " + code);
		String access_Token = ks.getAccessToken(code);
		System.out.println("controller access_token : " + access_Token);
		HashMap<String, Object> userInfo = ks.getUserInfo(access_Token);
		System.out.println("login Controller : " + userInfo);
		
		//이메일 존재시 세션에 이메일과 토큰등록
		if(userInfo.get("id") != null) {
			session.setAttribute("userId", userInfo.get("id"));
			if(userInfo.get("email") != null) {
				session.setAttribute("userEmail", userInfo.get("email"));
			}
			session.setAttribute("access_Token", access_Token);
			System.out.println("로그인 성공!");
		}
		return new RedirectView("/");
	}
	
	// 회원 로그아웃
	@GetMapping("/logout")
	public RedirectView logout(HttpSession session) {
		if(ks.setKakaoLogout(session)) {
			session.invalidate();
			System.out.println("로그아웃성공");
		}		
		return new RedirectView("/");
	}

	// 회원 탈퇴
	@GetMapping("/remove")
	public RedirectView remove(HttpSession session) {
		if(ks.setKakaoRemove(session)) {
			session.invalidate();
		}
		return new RedirectView("/");
	}

}
