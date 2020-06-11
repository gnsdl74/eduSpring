package edu.spring.ex03.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ReplyController {
	
	@GetMapping(value = "/reply")
	// get방식으로 url 전달받음
	public void reply() {
		
	}
} // end ReplyController
