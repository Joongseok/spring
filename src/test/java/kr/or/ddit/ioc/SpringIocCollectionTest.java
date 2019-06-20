package kr.or.ddit.ioc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.or.ddit.ioc.collection.IocCollection;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:kr/or/ddit/ioc/application-ioc-collection.xml")
public class SpringIocCollectionTest {

	@Resource(name = "iocCollection")
	private IocCollection iocCollection;

	/**
	* Method : springIocCollectionTest
	* 작성자 : PC25
	* 변경이력 :
	* Method 설명 : IocCollection객체에 값이 주입되었는지 확인하는 테스트 
	*/
	@Test
	public void springIocCollectionTest() {
		/***Given***/
		/***When***/
		/***Then***/
		assertEquals("brown", iocCollection.getList().get(0));
		assertEquals("sally", iocCollection.getList().get(1));
		assertEquals("cony", iocCollection.getList().get(2));
		assertEquals(3, iocCollection.getList().size());
		
		assertEquals("brown", iocCollection.getMap().get("name"));
		assertEquals("2019-08-08", iocCollection.getMap().get("birth"));
		
		assertTrue("brown", iocCollection.getSet().contains("brown"));
		assertTrue("brown", iocCollection.getSet().contains("sally"));
		
		assertEquals("brown", iocCollection.getProperties().get("userId"));
		assertEquals("브라운", iocCollection.getProperties().get("name"));
	}
}
