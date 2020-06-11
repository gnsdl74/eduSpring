package edu.spring.ex03.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImple implements CustomerService {
	
	private static final Logger logger = LoggerFactory.getLogger(CustomerServiceImple.class);
	
	@Override
	public String createCustomer() throws Exception {
		logger.info("고객 정보 생성");
		return "afterReturning 어노테이션 사용";
		// 예외를 발생시켜서 던짐 - 로그에 afterAdvice 이후에 마지막으로 출력됨
	} // end createCustomer()

	@Override
	public int deleteCustomer() throws Exception {
		logger.info("고객 정보 삭제");
		throw new Exception("고객 정보 삭제 실패");
	} // end deleteCustomer()

	@Override
	public int updateCustomer() throws Exception {
		logger.info("고객 정보 업데이트");
		return 0;
	} // end updateCustomer()

} // end CustomerServiceImple
