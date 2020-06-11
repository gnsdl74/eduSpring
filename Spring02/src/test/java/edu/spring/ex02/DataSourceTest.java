package edu.spring.ex02;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(
		locations = {"file:src/main/webapp/WEB-INF/spring/**/*.xml"}
)
public class DataSourceTest {
	private static final Logger logger = LoggerFactory.getLogger(OracleJDBCTest.class);
	
	// Spring Framework가 관리하는 DataSource 객체를 주입받음
	// - root-context.xml에서 bean으로 선언된 DataSource 객체를 주입받음
	@Autowired 
	// Autowired는 서버에서 생성한 객체를 주입하여 사용가능하게 하는 어노테이션
	// 생성된 객체의 타입과 변수의 타입이 같으면 객체 주입 (ex: 여기서는 DataSource)
	private DataSource ds;
	
	@Test
	public void testDataSource() {
		Connection conn = null;
		try {
			conn = ds.getConnection();
			logger.info("DataSource 연결 성공");
		} catch (Exception e) {
			logger.error("DataSource 연결 실패 : " + e.getMessage());
		} finally {
			try {
				conn.close();
				logger.info("DataSource 연결 반환 성공");
			} catch (Exception e) {
				logger.info("DataSource 연결 반환 실패 : " + e.getMessage());
			}
		}
	}
} // end DataSourceTest
