package kr.or.ddit.ioc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.or.ddit.board.dao.IBoardDao;
import kr.or.ddit.board.service.IBoardService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:kr/or/ddit/ioc/application-ioc-test.xml")
public class SpringIocJunitTest {
	
	@Resource(name = "boardService")
	private IBoardService boardService;
	
	@Resource(name = "boardService")
	private IBoardService boardService2;
	
	@Resource(name = "boardServiceConstructor")
	private IBoardService boardServiceConstructor;
	
	@Resource(name = "boardDao")
	private IBoardDao boardDao;
	
	@Resource(name = "boardDaoPrototype")
	private IBoardDao boardDaoPrototype;
	
	@Resource(name = "boardDaoPrototype")
	private IBoardDao boardDaoPrototype2;
	
	
	/**
	* Method : springIocTest
	* 작성자 : PC25
	* 변경이력 :
	* Method 설명 : 스프링 컨테이너 생성 테스트
	*/
	@Test
	public void springIocTest() {
		/***Given***/
		/***When***/
		String msg = boardService.sayHello();

		/***Then***/
		assertNotNull(boardService);
		assertEquals("boardDao sayHello", msg);
	}
	
	/**
	* Method : singletonScopeTest
	* 작성자 : PC25
	* 변경이력 :
	* Method 설명 : 스프링 컨테이너의 singleton scope테스트
	*/
	@Test
	public void singletonScopeTest() {
		/***Given***/
		/***When***/
		/***Then***/
		assertNotNull(boardService);
		assertNotNull(boardService2);
		assertNotNull(boardServiceConstructor);
		
		assertEquals(boardService, boardService2);
		
		assertNotEquals(boardService, boardServiceConstructor);
		assertNotEquals(boardService2, boardServiceConstructor);
	}

	/**
	 * Method : prototypeScopeTest
	 * 작성자 : PC25
	 * 변경이력 :
	 * Method 설명 : 스프링 컨테이너의 prototype scope테스트
	 */
	@Test
	public void prototypeScopeTest() {
		/***Given***/
		/***When***/
		/***Then***/
		assertNotNull(boardDao);
		assertNotNull(boardDaoPrototype);
		assertNotNull(boardDaoPrototype2);
		
		assertNotEquals(boardDao, boardDaoPrototype);
		assertNotEquals(boardDaoPrototype, boardDaoPrototype2);
	}
}
