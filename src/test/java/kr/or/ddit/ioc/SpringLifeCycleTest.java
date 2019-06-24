package kr.or.ddit.ioc;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.or.ddit.ioc.placeholder.DbInfo;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:kr/or/ddit/ioc/application-ioc-placeholder.xml")
public class SpringLifeCycleTest {

	
	@Resource(name = "dbInfo")
	private DbInfo dbInfo;
	
	/**
	* Method : dbInfoTest
	* 작성자 : PC25
	* 변경이력 :
	* Method 설명 : dbInfo에 properties의 값이 제대로 주입되어쓴지 확인 하는 테스트
	*/
	@Test
	public void lifeCycleTest() {
		/***Given***/
		/***When***/
		/***Then***/
		assertEquals("oracle.jdbc.driver.OracleDriver", dbInfo.getDriver());
		assertEquals("jdbc:oracle:thin:@localhost:1521:xe", dbInfo.getUrl());
		assertEquals("PC25_TEST", dbInfo.getUsername());
		assertEquals("java", dbInfo.getPassword());
	}

}
