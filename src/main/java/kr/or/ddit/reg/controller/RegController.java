package kr.or.ddit.reg.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RegController {

	@RequestMapping(path = "/reg", method = RequestMethod.GET)
	public String regGet() {
		
		return "reg";
	}
	
	@RequestMapping(path = "/reg", method = RequestMethod.POST)
	public String regPost() {
		
		return "reg";
	}
}
