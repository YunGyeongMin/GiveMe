package kr.yun.app.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import kr.yun.app.service.HomeService;
import kr.yun.app.service.WritingService;

@Controller
public class writingController {
	
	@Autowired WritingService ws;
	@Autowired HomeService hs;
	
	@GetMapping("/writing")
	public String writing(Model model) {
		model.addAttribute("getCategory", hs.getCategory());
		return "writing";
	}
	
	//상품등록
	@PostMapping("/itemRegister")
	public @ResponseBody int itemRegister(@RequestBody Map<String , Object> paramMap,HttpSession session) {
		System.out.println("상품 등록");
		return ws.setItem(paramMap,session);
	}
	
	//썸네일 이미지 등록
	@PostMapping("/T_imgUpload")
	public @ResponseBody boolean T_imgUpload(@RequestParam("file") MultipartFile file, HttpSession session) {
		System.out.println("썸네일 이미지 등록 : " + file.getName());
		return ws.T_imgUpload(file, session);
	}
	
	//서브 이미지 등록
	@PostMapping("/S_imgUpload")
	public @ResponseBody boolean S_imgUpload(@RequestParam("file") MultipartFile[] files, HttpSession session) {
		System.out.println("서브이미지 등록 갯수 : " + files.length);
		return ws.S_imgUpload(files, session);
	}

	
	
}
