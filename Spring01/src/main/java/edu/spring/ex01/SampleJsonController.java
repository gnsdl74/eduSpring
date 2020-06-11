package edu.spring.ex01;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.spring.ex01.domain.ProductVO;

@Controller
public class SampleJsonController {
	private static final Logger logger = LoggerFactory.getLogger(SampleJsonController.class);
	
	@RequestMapping(value = "/json1")
	public String testJson1() {
		logger.info("testJson1() 호출");
		return "sample1";
	} // end testJson1()
	
	@RequestMapping(value = "/json2")
	@ResponseBody
	public String testJson2() {
		logger.info("testJson2() 호출");
		return "Hello, Spring!";
	} // end testJson2()
	
	@RequestMapping(value = "/json3")
	@ResponseBody
	public ProductVO testJson3() {
		logger.info("testJson3() 호출");
		return new ProductVO("둘리", 10);
	} // end testJson3()
	
} // end SampleJsonController
