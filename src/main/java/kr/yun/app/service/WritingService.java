package kr.yun.app.service;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kr.yun.app.Bean.UserBean;
import kr.yun.app.mapper.UserMapper;
import kr.yun.app.mapper.WritingMapper;

@Service
public class WritingService {
	
	@Autowired WritingMapper wm;
	@Autowired UserMapper um;
	
	public int setItem(Map<String , Object> paramMap,HttpSession session) {
		UserBean ub = new UserBean();
		ub = um.getUser(session.getAttribute("userId").toString());
		paramMap.put("no", ub.getNo());		
		paramMap.put("nick_name", ub.getNick_name());
		System.out.println("service 상품등록 : " + paramMap);
		return wm.setItem(paramMap);
	}

	public boolean T_imgUpload(MultipartFile file, HttpSession session) {
		Object obj = session.getAttribute("id");
		if(obj != null) {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			String originFilename = file.getOriginalFilename();
			String TfilePath = "C:/Users/GD7/git/img_upload/" + session.getAttribute("id") + "/T/" + originFilename;
			System.out.println(TfilePath);
			paramMap.put("img_type", "T");
			paramMap.put("img_path", TfilePath);
//			System.out.println("DB 이미지 데이터" + paramMap);
			try {
				FileUtils.copyInputStreamToFile(file.getInputStream(), new File(TfilePath));
				wm.T_imgUpload(paramMap);
				return true;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	
	public boolean S_imgUpload(MultipartFile[] files, HttpSession session) {
		Object obj = session.getAttribute("id");
		if(obj != null) {
			for(int i = 0; i < files.length; i++) {
				Map<String, Object> paramMap = new HashMap<String, Object>();
				String originFilename = files[i].getOriginalFilename();
				String TfilePath = "C:/Users/GD7/git/img_upload/" + session.getAttribute("id") + "/S/" + originFilename;
				System.out.println(TfilePath);
				paramMap.put("img_type", "S");
				paramMap.put("img_path", TfilePath);
//				System.out.println("DB 이미지 데이터" + paramMap);
				try {
					FileUtils.copyInputStreamToFile(files[i].getInputStream(), new File(TfilePath));
					wm.T_imgUpload(paramMap);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return true;
		}
		return false;
	}
	
	
}
