package kr.or.ddit.exception.controller;

import java.io.IOException;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.exception.NoFileException;

@Controller
public class ExceptionController {

	
	@RequestMapping("/exception")
	public String exception() {
		throw new ArithmeticException();
	}
	
	@RequestMapping("/ioException")
	public String ioException() throws IOException {
		// myabtis-config-not_exists.xml 파일이 존재하지 않기 떄문에 예외 발생
		ClassPathResource cpr = new ClassPathResource("kr/or/ddit/config/mybatis/mybatis-config-not_exists.xml");
		cpr.getInputStream();
		
		return "main";
	}

	@RequestMapping("/nofileException")
	public String nofileException() throws NoFileException {
		// myabtis-config-not_exists.xml 파일이 존재하지 않기 떄문에 예외 발생
		try {
			ClassPathResource cpr = new ClassPathResource("kr/or/ddit/config/mybatis/mybatis-config-not_exists.xml");
			cpr.getInputStream();
		} catch (Exception e) {
			// 발생한 예외를 개발작 새로운 예외로 던진다
			throw new NoFileException();
		}
		return "main";
	}
	
}
