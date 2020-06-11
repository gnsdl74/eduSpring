package edu.spring.ex04.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.spring.ex04.domain.MemberVO;
import edu.spring.ex04.service.MemberService;

@Controller
@RequestMapping(value = "/member")
public class MemberController {
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	@Autowired
	private MemberService memberService;
	
	@GetMapping(value = "/login")
	public void loginGet(String url, Model model) {
		logger.info("loginGet() 호출");
		logger.info("url : " + url); // 이전 경로(로그인을 위해 왔던)의 값 출력
		model.addAttribute("targetUrl", url);
	} // end loginGet()
	
	@PostMapping(value = "/login-post")
	public void loginPost(MemberVO vo, Model model) {
		logger.info("loginPost() 호출");
		MemberVO result = memberService.loginCheck(vo);
		// 아이디 비밀번호가 일치 : result != null
		// 아이디 비밀번호가 불일치 : result == null
		logger.info("result : " + result);
		model.addAttribute("login_result", result);
	} // end loginPost()
	
	@GetMapping(value = "/logout")
	public String logout(HttpServletRequest request) {
		logger.info("logout() 호출");
		
		HttpSession session = request.getSession();
		session.removeAttribute("login_id");
		session.invalidate();
		
		return "redirect:/member/login";
	} // end logout()
	
} // end MemberController
