package kr.or.ddit.ioc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.or.ddit.board.dao.IBoardDao;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.testenv.LogicTestEnv;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ApplicationIocBeanScanConfig.class})
public class ApplicationIocBeanScanConfigTest{

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
