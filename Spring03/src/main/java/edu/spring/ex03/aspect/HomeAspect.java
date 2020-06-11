package edu.spring.ex03.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

// @Component, @Aspect를 통해 새로운 Proxy 객체 생성(Weaving, 위빙)
@Component // Proxy 객체에 주입(injection)하기 위해 선언
// Proxy에 HomeAspect를 추가.
// home.jsp를 호출할 때, HomeController가 호출되는 것이 아니라
// HomeAspect가 호출되고 해당 메소드가 진행됨.
@Aspect // Aspect = Advice + Pointcut : 여기서는 homeAdvice메소드와 @Pointcut
public class HomeAspect {
	private static final Logger logger = LoggerFactory.getLogger(HomeAspect.class);
	
	// pointcut을 지정하는 방법
	// 1. @Before, @After, @Aspect, ... 어노테이션 안에서 지정 - LoggingAspect클래스 방식
	// 2. @Pointcut 어노테이션 안에서 지정 - 현재 클래스 방식
	// -> 동일한 pointcut이 반복되는 것을 피할 수 있음(반복 노동의 효율성 문제)
	//	  "execution(* edu.spring.ex03.HomeController.home(..))"를 
	//	     여러 번 타이핑하는 것보다 @Pointcut을 활용하여 메소드명만 적는 것이 효율적
	// -> 한 번 지정한 pointcut을 여러 advice 메소드에서 참조
	@Pointcut("execution(* edu.spring.ex03.HomeController.home(..))")
	// 포인트컷 위치 지정
	// => Aspect가 실행될 메소드들의 범위를 지정
	// => Pointcut은 메소드 몸체를 가지지않음, 메소드 범위만 지정
	public void pcHome() {} // end pcHome()
	
	@Around("pcHome()") // pointcut으로 지정된 메소드들만 아래 homeAdvice 메소드를 적용
	// @Around 어노테이션만 매개변수로 ProceedingJoinPoint를 인자로 갖는다. 해당 API는 JoinPoint를 상속받는다.
//	@Around("execution(* edu.spring.ex03.HomeController.home(..))") => 가능
//	@Pointcut("execution(* edu.spring.ex03.HomeController.home(..))")
	// => Pointcut은 메소드 몸체가 없기 때문에 실행은 되지만 homeAdvice가 실행되지 않음
	// pcHome()에 있는 모든 메소드들이 해당 @Around의 영향을 받음
	// pcHome()은 @Pointcut으로 "execution(* edu.spring.ex03.HomeController.home(..))"
	// 의 경로에 있는 메소드들을 @Around의 영향을 받도록 연동함.
	// 해당 경로에 있는 메소드가 실행될때마다 homeAdvice()가 실행됨.
	public Object homeAdvice(ProceedingJoinPoint jp) {
		Object result = null;
		
		logger.info("===== homeAdvice");
		try {
			logger.info("===== home() 호출 전"); // @Before
			result = jp.proceed(); // HomeController url 실행
			logger.info("===== home() 리턴 후"); // @AfterReturning
		} catch (Throwable e) { // @AfterThrowing
			logger.info("===== 예외 발생 : " + e.getMessage());
		} finally { // @After
			logger.info("===== finally");
		}
		
		return "reply";
	} // end homeAdvice()
} // end HomeAspect
