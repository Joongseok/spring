package kr.or.ddit.ioc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import kr.or.ddit.board.service.IBoardService;

public class SpringIocTest {
	
	/**
	* Method : springIocTest
	* 작성자 : PC25
	* 변경이력 :
	* Method 설명 : 스프링 컨테이너 생성 테스트
	*/
	@Test
	public void springIocTest() {
		/***Given***/
		// 스프링 컨테이너 생성
		String configLocation = "classpath:kr/or/ddit/ioc/application-ioc-test.xml";
		ApplicationContext context = new ClassPathXmlApplicationContext(configLocation);

		/***When***/
		IBoardService boardService = (IBoardService)context.getBean("boardService");
		String sayHello = boardService.sayHello();

		/***Then***/
		assertNotNull(boardService);
		assertEquals("boardDao sayHello", sayHello);
	}
}
