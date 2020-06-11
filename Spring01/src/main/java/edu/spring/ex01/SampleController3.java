package edu.spring.ex01;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SampleController3 {
	private static final Logger LOGGER = LoggerFactory.getLogger(SampleController3.class);

	@RequestMapping(value = "/test4")
	public String test4(@ModelAttribute(name = "username") String username) {
		// @ModelAttribute : 요청받은 데이터(param)을 view에 다시 전송
		// name="username" : key 값을 의미
		return "param-test";
	} // end test4()

	@RequestMapping(value = "/test5")
	public String test5(@ModelAttribute(name = "username") String username, @ModelAttribute(name = "age") int age) {

		return "param-test";
	} // end test5()

} // end SampleController3
