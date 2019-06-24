package kr.or.ddit.ioc;

import static org.junit.Assert.assertEquals;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.or.ddit.board.dao.IBoardDao;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.testenv.LogicTestEnv;

//@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:kr/or/ddit/ioc/application-ioc-st.xml")
public class SpringIocStTest extends LogicTestEnv{

	@Resource(name = "bDao")
	private IBoardDao boardDao;
	
	@Resource(name = "bService")
	private IBoardService boardService;
	
	/**
	* Method : SpringIocStTest
	* 작성자 : PC25
	* 변경이력 :
	* Method 설명 :boardService 객체의 boardDao 속성과 spring 컨테이너로부터 받은 boardDao가 같은 객체인지 확인 
	*/
	@Test
	public void springIocStTest() {
		/***Given***/

		/***When***/

		/***Then***/
		assertEquals(boardDao, boardService.getBoardDao());
	}

}
