package kr.yun.app.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import kr.yun.app.util.KakaoAPI;


@Service
public class kakaoService {
	
		@Autowired KakaoAPI sn;
		
		// access_Token 가져오기
		public String getAccessToken(String authorize_code) {
			
			String access_Token = "";
			String refresh_Token = "";
//			String reqURL = "https://kauth.kakao.com/oauth/token";
			
			try {
				URL url = new URL(sn.kakao_Token);
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				
				conn.setRequestMethod("POST");
				conn.setDoOutput(true);
				
				BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
				StringBuilder sb = new StringBuilder();
				
				sb.append("grant_type=authorization_code");
				sb.append("&client_id=" + sn.kakao_client_id);
				sb.append("&redirect_uri=" + sn.kakao_redirect_uri2);
				sb.append("&code=" + authorize_code);
				bw.write(sb.toString());
				bw.flush();  // 현재 버퍼에 저장되어 있는 내용을 클라이언트로 전송하고 버퍼를 비운다
				
				int responseCode = conn.getResponseCode();
				System.out.println("responseCode : " + responseCode);
				
				// 요청을 통해 얻은 JSON타입의 Response 메세지 읽어오기
				BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				String line = "";
				String result = "";
				
				while ((line = br.readLine()) != null) {
					result += line;
				}
				
				System.out.println("response body : " + result);
			
				JsonParser parser = new JsonParser();
				JsonElement element = parser.parse(result);
				
				access_Token = element.getAsJsonObject().get("access_token").getAsString();
				refresh_Token = element.getAsJsonObject().get("refresh_token").getAsString();
				
				System.out.println("access_token : " + access_Token);
				System.out.println("refresh_token : " + refresh_Token);
				
				br.close();
				bw.close();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			return access_Token;
		}
		
		//유저정보 가져오기
		public HashMap<String, Object> getUserInfo (String access_Token){
			
			HashMap<String, Object> userInfo = new HashMap<>();
//			String reqURL = "https://kapi.kakao.com/v2/user/me";
			try {
				URL url = new URL(sn.kakao_user);
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				conn.setRequestMethod("POST");
				
				//헤더에 포함할 내용
				conn.setRequestProperty("Authorization", "Bearer " + access_Token);
				
				int responseCode = conn.getResponseCode();
				System.out.println("responseCode : " + responseCode);
				
				BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				
				String line = "";
				String result = "";
				
				while((line = br.readLine()) != null) {
					result += line;
				}
				
				System.out.println("response body :" + result);
				
				JsonParser parser = new JsonParser();
				JsonElement element = parser.parse(result);
				
				JsonObject properties = element.getAsJsonObject().get("properties").getAsJsonObject();
				JsonObject kakao_account = element.getAsJsonObject().get("kakao_account").getAsJsonObject();
				
				String id = element.getAsJsonObject().get("id").getAsString();
				String nickname = properties.getAsJsonObject().get("nickname").getAsString();
				String thumbnail_image = properties.getAsJsonObject().get("thumbnail_image").getAsString();
				String profile_image = properties.getAsJsonObject().get("profile_image").getAsString();
				
				if(kakao_account.getAsJsonObject().get("email") != null) {
					String email = kakao_account.getAsJsonObject().get("email").getAsString();
					userInfo.put("email", email);
				}
				
				userInfo.put("id", id);
				userInfo.put("nickname", nickname);
				userInfo.put("thumbnail_image", thumbnail_image);
				userInfo.put("profile_image", profile_image);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			return userInfo;
		}
		
		//카카오 로그아웃
		public boolean setKakaoLogout (HttpSession session){
			
//			HashMap<String, Object> userInfo = new HashMap<>();
//			String reqURL = "https://kapi.kakao.com/v1/user/logout";
			
			boolean logout_status = false;
			try {
				URL url = new URL(sn.kakao_logout);
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				conn.setRequestMethod("POST");
				
				String access_Token = session.getAttribute("access_Token").toString();
				
				//헤더에 포함할 내용
				conn.setRequestProperty("Authorization", "Bearer " + access_Token);
				
				int responseCode = conn.getResponseCode();
				System.out.println("responseCode : " + responseCode);
				
				BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				
				String line = "";
				String result = "";
				
				while((line = br.readLine()) != null) {
					result += line;
				}
				
				System.out.println("response body :" + result);
				
				JsonParser parser = new JsonParser();
				JsonElement element = parser.parse(result);
				
				String result_id = element.getAsJsonObject().get("id").getAsString();
				String session_id = session.getAttribute("userId").toString();
				
				System.out.println("result id : " + result_id);
				System.out.println("session id : " + session_id);
				
//				 kakao id (session) 비교 맞으면 true > 다르면  false
				if(result_id.equals(session_id)) {
					System.out.println("id 확인완료 / 로그아웃 합니다.");
					logout_status = true;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
//					return userInfo;
			return logout_status;
		}
		
		//회원탈퇴
		public boolean setKakaoRemove (HttpSession session){
			
//			HashMap<String, Object> userInfo = new HashMap<>();
//			String reqURL = "https://kapi.kakao.com/v1/user/logout";
			boolean user_remove = false;
			
			try {
				URL url = new URL(sn.kakao_auth_remove);
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				conn.setRequestMethod("POST");
				
				String access_Token = session.getAttribute("access_Token").toString();
				
				//헤더에 포함할 내용
				conn.setRequestProperty("Authorization", "Bearer " + access_Token);
				
				int responseCode = conn.getResponseCode();
				System.out.println("responseCode : " + responseCode);
				
				BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				
				String line = "";
				String result = "";
				
				while((line = br.readLine()) != null) {
					result += line;
				}
				
				System.out.println("response body :" + result);

				JsonParser parser = new JsonParser();
				JsonElement element = parser.parse(result);
				
				String result_id = element.getAsJsonObject().get("id").getAsString();
				String session_id = session.getAttribute("userId").toString();
				
				System.out.println("result id : " + result_id);
				System.out.println("session id : " + session_id);
				
//				 kakao id (session) 비교 맞으면 true > 다르면  false
				if(result_id.equals(session_id)) {
					System.out.println("id 확인완료 / 회원탈퇴 합니다.");
					user_remove = true;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		//			return userInfo;
			return user_remove;
		}
		
		//로그인 확인여부
		public String loginConfirm(HttpSession session) throws Exception{
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
			return url2;
		}
		
		//토큰확인 후 로그인
		public void loginRun(String code,  HttpSession session) {
			System.out.println("code : " + code);
			String access_Token = getAccessToken(code);
			System.out.println("controller access_token : " + access_Token);
			HashMap<String, Object> userInfo = getUserInfo(access_Token);
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
		}
		
		
}
