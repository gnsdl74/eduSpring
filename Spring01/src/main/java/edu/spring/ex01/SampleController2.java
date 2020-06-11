package edu.spring.ex01;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SampleController2 {
	private static final Logger LOGGER = 
			LoggerFactory.getLogger(SampleController2.class);
	
	@RequestMapping(value = "/test1")
	public String test1(Model model, String username) {
		// Model : view에 데이터를 전송하기 위한 객체
		LOGGER.info("test1() 호출 : username = " + username);
		// 매개변수 username : request.getParameter("username");
		
		model.addAttribute("username", username);
		// view에 username 데이터를 전송
		return "param-test";
	} // end test1()
	
	@RequestMapping(value = "/test2")
	public String test2(Model model, int age) {
		LOGGER.info("test1() 호출 : age = " + age);
		
		model.addAttribute("age", age);
		return "param-test";
	} // end test2()
	
	@RequestMapping(value = "/test3")
	public String test3(Model model, String username, int age) {
		LOGGER.info("test1() 호출 : username = " + username + ", age = " + age);
		
		model.addAttribute("username", username);
		model.addAttribute("age", age);
		return "param-test";
	} // end test3()
	
} // end SampleController2
