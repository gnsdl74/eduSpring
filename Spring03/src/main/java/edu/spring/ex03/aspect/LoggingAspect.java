package edu.spring.ex03.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {
	private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);
	
	@Pointcut("execution(* edu.spring.ex03.service.CustomerServiceImple.*Customer(..))")
	public void before() {}
	
	@Before("before()")
	// 앞의 * 은 접근제한자, 반환형을 상관하지않는 다는 의미.
	// *Customer(..)) : CustomerServiceImple 클래스내에 Customer 이름이 뒤에 붙는 메소드에 적용
	public void beforeAdvice(JoinPoint jp) {
		logger.info("===== beforeAdvice");
		logger.info("target : " + jp.getTarget());
		logger.info("signature : " + jp.getSignature());
	} // end beforeAdvice()
	
	@After("execution(* edu.spring.ex03.service.CustomerServiceImple.create*(..))")
	// 앞의 * 은 접근제한자, 반환형을 상관하지않는 다는 의미.
	// create*(..)) : CoustomerServiceImple 클래스내에 create 이름으로 시작하는 메소드에 적용
	public void afterAdvice() {
		logger.info("===== afterAdvice");
	} // end afterAdvice()
	
	@AfterReturning(pointcut="execution(* edu.spring.ex03.service.CustomerServiceImple.create*(..))", returning = "ret")
	// 리턴값이 있을때 @AfterReturning을 사용
	public void afterReturningAdvice(Object ret) {
		logger.info("===== afterReturningAdvice");
		logger.info("ret : " + ret);
	}
	
	@AfterThrowing("execution(* edu.spring.ex03.service.CustomerServiceImple.create*(..))")
	// After에서 중간에 예외가 발생할 경우 처리하는 어노테이션
	public void afterThorwingAdvice() {
		logger.info("===== afterThrowingAdvice");
	}
} // end LoggingAspect
