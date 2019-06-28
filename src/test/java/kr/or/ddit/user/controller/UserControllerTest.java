package kr.or.ddit.user.controller;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.paging.model.PageVO;
import kr.or.ddit.testenv.ControllerTestEnv;
import kr.or.ddit.user.model.UserVO;

public class UserControllerTest extends ControllerTestEnv{

	
	/**
	* Method : userListTest
	* 작성자 : PC25
	* 변경이력 :
	* Method 설명 : 사용자 리스트 테스트
	 * @throws Exception 
	*/
	@Test
	public void userListTest() throws Exception {
		/***Given***/
		/***When***/
		 MvcResult mvcResult = mockMvc.perform(get("/user/list")).andReturn();
		 ModelAndView mav = mvcResult.getModelAndView();
		/***Then***/
		 assertEquals("user/list", mav.getViewName());
		 assertEquals(125, ((List<UserVO>)mav.getModelMap().get("userList")).size());
		 
	}
	
	/**
	 * Method : userListTest
	 * 작성자 : PC25
	 * 변경이력 :
	 * Method 설명 : 사용자 페이징 리스트 테스트
	 * @throws Exception 
	 */
	@Test
	public void userListPaingTest() throws Exception {
		/***Given***/
		/***When***/
		 MvcResult mvcResult = mockMvc.perform(get("/user/pagingList")
									 .param("page", "2")
									 .param("pageSize", "10"))
									 .andReturn();
		 ModelAndView mav = mvcResult.getModelAndView();
		 List<UserVO> userList = (List<UserVO>)mav.getModelMap().get("userList");
		 int paginationSize = (int)mav.getModelMap().get("paginationSize");
		 PageVO pageVo = (PageVO)mav.getModelMap().get("pageVo");
		 String viewName = mav.getViewName();
		 
		/***Then***/
		 assertEquals("tiles.pageList", viewName);
		 assertEquals(10, userList.size());
		 assertEquals(13, paginationSize);
		 
		 //pageVo equals, hashcode를 구현해 value객체 값 비교
		 assertEquals(new PageVO(2,10), pageVo);
		 
//		 assertEquals(2, pageVo.getPage());
//		 assertEquals(10, pageVo.getPageSize());
	}

	
	/**
	* Method : userTest
	* 작성자 : PC25
	* 변경이력 :
	* Method 설명 : 사용자 상세 조회 테스트
	 * @throws Exception 
	*/
	@Test
	public void userTest() throws Exception {
		/***Given***/
		/***When***/
		MvcResult mvcResult =  mockMvc.perform(get("/user/user").param("userId", "brown")).andReturn();
		ModelAndView mav = mvcResult.getModelAndView();
		String viewName = mav.getViewName();
		UserVO userVo = (UserVO) mav.getModelMap().get("userVo");
		
		/***Then***/
		assertEquals("user/user", viewName);
		assertEquals("브라운", userVo.getName());
	}
	
	/**
	* Method : userFormTest
	* 작성자 : PC25
	* 변경이력 :
	* Method 설명 : 사용자 등록 화면 요청 테스트
	 * @throws Exception 
	*/
	@Test
	public void userFormTest() throws Exception {
		/***Given***/
		/***When***/
		mockMvc.perform(get("/user/form")).
						andExpect(view().name("user/form"));
		/***Then***/
	
	}
	/**
	 * Method : userFormTest
	 * 작성자 : PC25
	 * 변경이력 :
	 * Method 설명 : 사용자 등록 응답 테스트(success시나리오)
	 * @throws Exception 
	 */
	@Test
	public void userFormPostSuccessTest() throws Exception {
		/***Given***/
		File f = new File("src/test/resources/kr/or/ddit/testenv/sally.png");
		MockMultipartFile file = new MockMultipartFile("profile", f.getName(), "", new FileInputStream(f));
		/***When***/
		mockMvc.perform(fileUpload("/user/form").file(file)
						.param("userId", "userTest45674")
						.param("name", "대덕인")
						.param("alias", "중앙로")
						.param("addr1", "대전광역시 중구 중앙로 76")
						.param("addr2", "영민빌딩 2층 204호")
						.param("zipcd", "34940")
						.param("birth", "2019-05-31")
						.param("pass", "userTest1234"))
						.andExpect(view().name("redirect:/user/pagingList"));
						
	}
	/**
	 * Method : userFormTest
	 * 작성자 : PC25
	 * 변경이력 :
	 * Method 설명 : 사용자 등록 응답 테스트(fail 시나리오)
	 * @throws Exception 
	 */
	@Test
	public void userFormPostFailTest() throws Exception {
		/***Given***/
		File f = new File("src/test/resources/kr/or/ddit/testenv/sally.png");
		MockMultipartFile file = new MockMultipartFile("profile", f.getName(), "", new FileInputStream(f));
		/***When***/
		mockMvc.perform(fileUpload("/user/form").file(file)
				.param("userId", "brown")
				.param("name", "대덕인")
				.param("alias", "중앙로")
				.param("addr1", "대전광역시 중구 중앙로 76")
				.param("addr2", "영민빌딩 2층 204호")
				.param("zipcd", "34940")
				.param("birth", "2019-05-31")
				.param("pass", "userTest1234"))
		.andExpect(view().name("user/form"));
		
	}
	
	/**
	* Method : profileTest
	* 작성자 : PC25
	* 변경이력 :
	* Method 설명 : 사용자 사진 응답 테스트
	 * @throws Exception 
	*/
	@Test
	public void profileTest() throws Exception {
		mockMvc.perform(get("/profile").param("userId", "brown"))
										.andExpect(status().isOk());
	}
	
	/**
	* Method : userModifyGetTest
	* 작성자 : PC25
	* 변경이력 :
	* Method 설명 : 사용자 수정화면 요청 테스트
	*/
	@Test
	public void userModifyGetTest() throws Exception{
		/***Given***/
		/***When***/
		MvcResult mvcResult = mockMvc.perform(get("/user/modify").param("userId", "brown")).andReturn();
		ModelAndView mav =  mvcResult.getModelAndView();
		String viewName = mav.getViewName();
		UserVO userVo = (UserVO) mav.getModelMap().get("userVo");
		/***Then***/
		assertEquals("user/modify", viewName);
		assertEquals("브라운", userVo.getName());
	}

	private static final Logger logger = LoggerFactory.getLogger(UserControllerTest.class);
	/**
	 * Method : userModifyPostTest
	 * 작성자 : PC25
	 * 변경이력 :
	 * Method 설명 : 사용자 수정화면 응답 테스트
	 */
	@Test
	public void userModifyPostTest() throws Exception{
		logger.debug("===================================================================================");
		/***Given***/
		File f = new File("src/test/resources/kr/or/ddit/testenv/sally.png");
		MockMultipartFile file = new MockMultipartFile("modifyFile", f.getName(), "", new FileInputStream(f));
		/***When***/
		mockMvc.perform(fileUpload("/user/modify").file(file)
				.param("userId", "brown")
				.param("name", "대덕인")
				.param("alias", "중앙로")
				.param("addr1", "대전광역시 중구 중앙로 76")
				.param("addr2", "영민빌딩 2층 204호")
				.param("zipcd", "34940")
				.param("birth", "2019-05-31")
				.param("pass", "userTest1234"))
		.andExpect(view().name("redirect:/user/user" ));
	}
	
}
