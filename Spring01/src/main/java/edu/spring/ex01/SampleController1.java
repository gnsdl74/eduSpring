package edu.spring.ex01;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

// POJO : Plain Old Java Object
@Controller
// servlet-context.xml 파일에서
// component-scanning의 대상으로 만들어주는 어노테이션
// @Component 어노테이션을 사용해도 됨
public class SampleController1 {
	// 스프링 프레임워크에서 로그를 사용하기 위한 객체
	private static final Logger LOGGER = 
			LoggerFactory.getLogger(SampleController1.class);
	
	@RequestMapping(value = "/sample1", method = RequestMethod.GET)
	// @RequestMapping : 콘트롤러의 URL 매핑과 메소드(GET/POST)를 설정
	// value = URL 경로
	// method = 요청 방식 설정(GET, POST)
	// method 속성을 생략하면 GET/POST 모두 처리 가능)
	public String sample1() {
		LOGGER.info("sample() 호출");
		
		return "sample1";
		// 콘트롤러 메소드의 return 값의 의미
		// ViewResolver에게 실제 View를 결정하도록(갖도록) 요청
		
	} // end sample1()
	
	@RequestMapping(value = "/sample2")
	// return 타입이 void인 경우는 URL 매핑을 통해서 View를 찾음
	public void sample2() {
		LOGGER.info("sample2() 호출");
		
		
	} // end sample2()
} // end SampleController1