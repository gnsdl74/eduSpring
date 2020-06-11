package edu.spring.ex02;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import oracle.jdbc.OracleDriver;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(
		locations = {"file:src/main/webapp/WEB-INF/spring/**/*.xml"}
)
public class OracleJDBCTest {
	private static final Logger logger = LoggerFactory.getLogger(OracleJDBCTest.class);
	
	// Oracle JDBC 연결을 위해서 필요한 정보
	private static final String URL = "jdbc:oracle:thin:@172.16.1.22:1521:orcl";
	private static final String USER = "scott";
	private static final String PASSWORD = "tiger";
	
	@Test
	public void testOracleConnect() {
		Connection conn = null;
		try {
			// JDBC 라이브러리를 로드 - oracleDriver import : oracle.jdbc.OracleDriver;
			DriverManager.registerDriver(new OracleDriver());
			// Connection 설정
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			logger.info("Oracle 연결 성공");
			
		} catch (Exception e) {
			logger.error("Oracle 연결 실패 : " + e.getMessage());
		} finally {
			try {
				conn.close();
				logger.info("Oracle 연결 해제 성공");
			} catch (Exception e) {
				logger.info("Oracle 연결 해제 실패 : " + e.getMessage());
			}
		}
	} // end testOracleConnect()
	
} // end OracleJDBCTest
