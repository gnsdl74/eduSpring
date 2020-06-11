package edu.spring.ex04.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AuthInterceptor extends HandlerInterceptorAdapter {
	private static final Logger logger = LoggerFactory.getLogger(AuthInterceptor.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		logger.info("===== preHandle 호출");
		// 로그인 상태 : register 콘트롤러 메소드 실행
		// 로그인 안된 상태 : 로그인 양식(login)으로 redirect, 콘트롤러 메소드는 실행하지 않음
		HttpSession session = request.getSession();
		String userid = (String) session.getAttribute("login_id");
		if(userid != null) { // 로그인 상태
			logger.info("로그인 상태 -> register() 실행");
			return true; // 콘트롤러 메소드 실행
			
		} else { // 로그인 안 된 상태
			logger.info("로그인 안 된 상태 -> loginGet() 실행");
			
			// 로그인 성공 후에 redirect될 페이지를 세션에 저장
			saveDestination(request);
			// URL을 저장하고 login 페이지로 전송
			response.sendRedirect("../member/login");
			return false;
		}
	} // end preHandle()
	
	// 로그인 후 돌아갈 URL을 저장하는 메소드
	private void saveDestination(HttpServletRequest request) {
		logger.info("saveDestination() 호출");
		
		// 전체 요청 주소에서 쿼리 스트링을 제외한 부분
		String uri = request.getRequestURI();
		logger.info("요청 URI : " + uri);
		
		// 전체 요청 주소에서 쿼리 스트링만 추출
		String queryString = request.getQueryString();
		logger.info("쿼리 스트링 : " + queryString);
		String url = "";
		if(queryString == null) {
			url = uri;
		} else {
			url = uri + "?" + queryString;
		}
		
		request.getSession().setAttribute("dest", url);
	} // end saveDestination()
	
	
} // end AuthInterceptor
