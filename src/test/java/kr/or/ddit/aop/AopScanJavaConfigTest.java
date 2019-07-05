package kr.or.ddit.aop;

import static org.junit.Assert.assertEquals;

import javax.annotation.Resource;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;

import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.testenv.LogicTestEnv;


//@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:kr/or/ddit/aop/application-aop-scan.xml")
public class AopScanJavaConfigTest extends LogicTestEnv{

	@Resource(name = "boardService")
	private IBoardService boardService;
	
	@Test@Ignore
	public void aopBeforeTest() {
		/***Given***/
		/***When***/
		String msg = boardService.sayHello();
		/***Then***/
		assertEquals("boardDao sayHello", msg);
	}

}
