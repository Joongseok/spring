package kr.or.ddit.user.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import kr.or.ddit.testenv.LogicTestEnv;
import kr.or.ddit.user.model.UserVO;

public class UserServiceTest extends LogicTestEnv{

	
	@Resource(name = "userService")
	private IUserService userService;
	
	/**
	* Method : userListTest
	* 작성자 : PC25
	* 변경이력 :
	* Method 설명 : 사용자 전체 조회 테스트
	*/
	@Test
	public void userListTest() {
		/***Given***/
		/***When***/
		List<UserVO> userList = userService.userList();

		/***Then***/
		assertNotNull(userService);
		assertNotNull(userList);
		assertTrue(userList.size() >= 100);
		
	}

	
	/**
	* Method : insertUserTest
	* 작성자 : PC25
	* 변경이력 :
	* Method 설명 : 사용자 등록 테스트
	 * @throws ParseException 
	*/
	@Test
	public void insertUserTest() throws ParseException{
		/***Given***/
		String userId   = "1251251test214152525";
		String name     = "test";
		String alias    = "test";
		String pass     = "test";
		String addr1    = "test";
		String addr2    = "test";
		String zipcd    = "test";
		Date birth    = new Date();
		String path     = "test";
		String filename = "test";

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		UserVO userVo = new UserVO(userId, name, alias, pass, addr1, addr2, zipcd, sdf.parse("2019-05-31"), path, filename);
		
		/***When***/
		int result = userService.insertUser(userVo);
		/***Then***/
		assertEquals(1, result);
		
		int delResult = userService.deleteUser(userId);
		assertEquals(1, delResult);

	}
	
	/**
	* Method : getUserTest
	* 작성자 : PC25
	* 변경이력 :
	* Method 설명 : 사용자 정보 조회 테스트
	*/
	@Test
	public void getUserTest() {
		/***Given***/
		String userId = "brown";

		/***When***/
		UserVO userVo = userService.getUser(userId);
		/***Then***/
		assertEquals("브라운", userVo.getName());
		assertEquals("곰", userVo.getAlias());
	}
}
