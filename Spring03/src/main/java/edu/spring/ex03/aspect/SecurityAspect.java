package edu.spring.ex03.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class SecurityAspect {
	private static final Logger logger = LoggerFactory.getLogger(SecurityAspect.class);
	
	@Pointcut("execution(* edu.spring.ex03.service.Customer*.update*(..))")
	public void updateAdvice() {}
	
	@Around(value = "updateAdvice()")
	public Object aroundAdvice(ProceedingJoinPoint jp) {
		Object result = null;
		
		try {
			// @Before에서 할 기능들 작성
			logger.info("===== 메소드 호출 전");
			
			// 타겟의 포인트컷 메소드를 호출
			result = jp.proceed();

			// @AfterReturning에서 해야할 기능 작성
			logger.info("===== 메소드 리턴 후");
		} catch (Throwable e) {
			// @AfterThrowing에서 해야할 기능 작성
			logger.info("===== 메소드 실행 중 예외 발생");
		} finally {
			// @After에서 해야할 기능 작성
			logger.info("===== finally 실행");
		}
		
		return result;
	} // aroundAdvice()
	
} // end SecurityAspect
