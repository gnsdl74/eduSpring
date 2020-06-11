package edu.spring.ex03;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.spring.ex03.domain.ReplyVO;

@RestController
// - @RestController로 선언된 클래스의 모든 메소드들은
// 그 리턴 값들이 View(JSP) 파일의 이름을 의미하는 것이 아니라,
// ResponseBody에 포함돼서 클라이언트에게 직접 전달됨
// - @RestController의 메소드들에는 @ResponseBody 어노테이션을 사용하지 않음.
// - @RestController는 스프링 4버전부터 지원
// 리턴타입이 String, void인 메소드들은 JSP를 따라감.
// But, RESTful 메소드들은 리턴타입이 다양함.
// RESTful api를 사용하면 데이터가 자동으로 JSON 형식으로 변환됨.
public class RESTController {
	@RequestMapping(value = "/rest1", method = RequestMethod.GET)
	public String rest1() {
		return "Hello, REST Controller1";
	} // end rest1()
	
	@RequestMapping(value = "/rest2", method = RequestMethod.GET)
	public List<ReplyVO> rest2() {
		List<ReplyVO> list = new ArrayList<ReplyVO>();
		for(int i = 0; i < 3; i++) {
			list.add(new ReplyVO(i, 1, "test", "test", null));
		}
		return list;
	} // end rest2()
	
	@RequestMapping(value = "/rest3", method = RequestMethod.GET)
	// RESTful로 데이터를 전송했을때 해당 메소드가 성공했는지 실패했는지 확인하기 위해
	// REsponseEntity<T>를 사용
	public ResponseEntity<ReplyVO> rest3() {
		ReplyVO vo = new ReplyVO(1, 3, "test", "test", new Date());
		// * ResponseEntity<T>
		// - 클라이언트에 데이터를 전송하는 클래스
		// - 데이터에 HttpStatus를 전송할 수 있음
		// - 매개변수 T body는 입력된 T 클래스(예를 들어, ReplyVO)의 타입에 고정됨
		ResponseEntity<ReplyVO> entity = new ResponseEntity<ReplyVO>(vo, HttpStatus.OK);
		return entity;
	} // end rest3()
	
	@RequestMapping(value = "/rest4", method = RequestMethod.GET)
	public ResponseEntity<List<ReplyVO>> rest4() {
		List<ReplyVO> list = new ArrayList<ReplyVO>();
		for(int i = 0; i < 3; i++) {
			list.add(new ReplyVO(i, 1, "test", "test", null));
		}
		return new ResponseEntity<List<ReplyVO>>(list, HttpStatus.OK);
	} // end rest4()
	
} // end RESTController
