package edu.spring.ex04.interceptor;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class SampleInterceptor2 extends HandlerInterceptorAdapter {
	private static final Logger logger = LoggerFactory.getLogger(SampleInterceptor2.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		logger.info("===== preHandle 호출");
		HandlerMethod handlerMethod = (HandlerMethod) handler;
		Method method = handlerMethod.getMethod();
		logger.info("Bean : " + handlerMethod.getBean()); // url 경로에 있는 Bean 객체
		logger.info("method : " + method.getName()); // url 경로에 있는 메소드 이름
		return true;
		// preHandle()의 리턴값 의미
		// - true : 원래 실행하려고 했던 콘트롤러 메소드를 실행
		// - false : 콘트롤러 메소드가 실행하지 않음
	} // end preHandle()
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		logger.info("===== postHandle 호출");
		// * ModelAndView 객체
		// - model.addAttribute("data", "test2")의 데이터를 가지고 있음
		String data = (String) modelAndView.getModel().get("data");
		logger.info("data : " + data);
		if(data == null) { // test1의 경우 data가 null
			HttpSession session = request.getSession();
			session.setAttribute("data", "DUMMY DATA");
		}
		
		super.postHandle(request, response, handler, modelAndView);
	} // end postHandle()
} // end SampleInterceptor2
