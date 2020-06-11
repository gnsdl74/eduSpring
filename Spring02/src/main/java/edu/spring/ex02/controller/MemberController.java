package edu.spring.ex02.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.spring.ex02.domain.MemberVO;
import edu.spring.ex02.service.MemberService;

@Controller
@RequestMapping(value = "/member")
public class MemberController {

	@Autowired
	private MemberService memberService;
	
	@RequestMapping(value = "/list",
			method = RequestMethod.GET)
	public void list(Model model) {
		List<MemberVO> list = memberService.read();
		model.addAttribute("memberList", list);
	}
	
	@RequestMapping(value = "/register",
			method = RequestMethod.GET)
	public void registerGET() {
		
	}
	
	@RequestMapping(value = "/register",
			method = RequestMethod.POST)
	public String registerPOST(MemberVO vo) {
		memberService.create(vo);
		
		return "redirect:/member/list";
	}
	
	@RequestMapping(value = "/detail",
			method = RequestMethod.GET)
	public void detail(String userid, Model model) {
		MemberVO vo = memberService.read(userid);
		model.addAttribute("member", vo);
	}
	
	@RequestMapping(value = "/update",
			method = RequestMethod.POST)
	public String update(MemberVO vo) {
		memberService.update(vo);
		
		return "redirect:list";
	}
	
	@RequestMapping(value = "/delete",
			method = RequestMethod.POST)
	public String delete(String userid) {
		memberService.delete(userid);
		
		return "redirect:list";
	}
	
	@RequestMapping(value = "/deregister")
	public String deregister(String userid) {
		memberService.deregister(userid);
		
		return "redirect:list";
	}
	
} // end class MemberController



















