package edu.spring.ex04.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import edu.spring.ex04.domain.MemberVO;

// interceptor를 사용하기 위해 HandlerInterceptorAdapter를 상속받아야함.
public class LoginInterceptor extends HandlerInterceptorAdapter {
	private static final Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		logger.info("===== preHandle 호출");
		// targetUrl이 요청 매개변수(request parameter)가 있는 경우는
		// 세션에 그 정보를 저장
		String url = request.getParameter("targetUrl");
		if (url != null && url != "") {
			request.getSession().setAttribute("dest", url);
		}
		
		return true;
	}
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		logger.info("===== postHandle 호출");
		// Model 객체의 login_result 속성을 확인
		// null이 아니면(로그인 성공) session 객체에 login_id 속성 추가, 페이지 이동
		// null이면(로그인 실패) 메인 페이지 이동
		
		// MemberVO 객체 확인
		MemberVO vo = (MemberVO) modelAndView.getModel().get("login_result");
		logger.info("vo 객체값 : " + vo);
		if(vo != null) { // 로그인 성공
			logger.info("로그인 성공");
			HttpSession session = request.getSession();
			session.setAttribute("login_id", vo.getUserid()); // session에 login_id 속성 추가
			session.removeAttribute("login_fail");
//			response.sendRedirect("/ex04"); // home.jsp로 이동
			
			String dest = (String) session.getAttribute("dest"); 
			// 세션에서 목적 url 가져오기
			if (dest != null) {
				response.sendRedirect(dest);
			} else {
				response.sendRedirect("/ex04");
			}
			
		} else { // 로그인 실패
			logger.info("로그인 실패");
			HttpSession session = request.getSession();
			session.setAttribute("login_fail", "fail");
			response.sendRedirect("/ex04/member/login");
		}
	} // end postHandler()
} // end LoginInterceptor
