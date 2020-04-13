package kr.yun.app.util;

import org.springframework.stereotype.Component;

@Component
public class KakaoAPI {

	// kakao URL
	public String kakao_auth = "https://kauth.kakao.com/oauth/authorize";   // 권한 요청
	public String kakao_Token = "https://kauth.kakao.com/oauth/token";		// 토큰 요청
	public String kakao_user = "https://kapi.kakao.com/v2/user/me";			// 사용자 정보 요청
	public String kakao_login = "https://accounts.kakao.com/login";			// 카카오 로그인 요청
	public String kakao_logout = "https://kapi.kakao.com/v1/user/logout";	// 카카오 로그아웃 요청
	public String kakao_auth_remove = "https://kapi.kakao.com/v1/user/unlink";	// 카카오 연결 끊기
	
	// kakao Key
	public String kakao_client_id = "9e0cd187e904749a3392e4285389ef7c";		// 카카오 개발자 API KEY
	
	// kakao Back URL
	public String kakao_redirect_uri1 = "http://gm.gudi.kr:8080/kakaoLogin";	// 카카오 리다이렉트 URI (리눅스용) 
	public String kakao_redirect_uri2 = "http://gm.gudi.kr:8090/kakaoLogin";	// 카카오 리다이렉트 URI (원도우용)
	
}
