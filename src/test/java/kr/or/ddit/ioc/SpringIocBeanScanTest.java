package kr.or.ddit.ioc;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.or.ddit.board.dao.IBoardDao;
import kr.or.ddit.board.service.IBoardService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:kr/or/ddit/ioc/application-ioc-bean-scan.xml")
public class SpringIocBeanScanTest {

	@Resource(name = "boardDao")
	private IBoardDao boardDao;
	@Resource(name = "boardService")
	private IBoardService boardService;
	
	@Test
	public void springIocBeanScanTest() {
		/***Given***/
		/***When***/
		/***Then***/
		assertNotNull(boardDao);
		assertNotNull(boardService);
		assertEquals("brown", boardService.getName());
		assertEquals(boardDao, boardService.getBoardDao());
		assertEquals("boardDao sayHello", boardDao.sayHello());
	}

}
