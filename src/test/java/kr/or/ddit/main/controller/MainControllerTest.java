package kr.or.ddit.main.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.testenv.ControllerTestEnv;


public class MainControllerTest extends ControllerTestEnv{
	
	private static final Logger logger = LoggerFactory.getLogger(MainControllerTest.class);
	
	/**
	* Method : mainViewTest
	* 작성자 : PC25
	* 변경이력 :
	* Method 설명 : Main View 호출 테스트
	 * @throws Exception 
	*/
	@Test
	public void mainViewTest() throws Exception {
		/***Given***/
		/***When***/
		MvcResult mvcResult = mockMvc.perform(get("/main")).andReturn();
		ModelAndView mav = mvcResult.getModelAndView();
		String viewName = mav.getViewName();
		String mainUserId = (String) mav.getModel().get("mainUserId");
		logger.debug("[mainUserId : {}", mainUserId + "]");
		logger.debug("[viewName : {}", viewName + "]");
		/***Then***/
		assertEquals("main", viewName);
		assertEquals("brown", mainUserId);
		assertNotNull(mav.getModel().get("rangers"));
		
	}
	
	/**
	 * Method : mainViewTest
	 * 작성자 : PC25
	 * 변경이력 :
	 * Method 설명 : Main View 호출 테스트
	 * @throws Exception 
	 */
	@Test
	public void mainViewAndExpectTest() throws Exception {
		mockMvc.perform(get("/main"))
					.andExpect(status().isOk())
					.andExpect(view().name("main"))
					.andExpect(model().attribute("mainUserId", "brown"))
					.andExpect(model().attributeExists("rangers"))
					.andExpect(model().attributeExists("userVo"));
		
	}
	
	/**
	* Method : mainViewMavTest
	* 작성자 : PC25
	* 변경이력 :
	* Method 설명 : modelAndView객체를 이용한 main페이지 요청 테스트
	 * @throws Exception 
	*/
	@Test
	public void mainViewMavTest() throws Exception {
		/***Given***/

		/***When***/
		MvcResult mvcResult = mockMvc.perform(get("/mainMav")).andReturn();
		ModelAndView mav = mvcResult.getModelAndView();
		String viewName = mav.getViewName();

		String mainUserId = (String) mav.getModel().get("mainUserId");
		
		/***Then***/
		// viewName이 기대한 문자열 리턴
		assertEquals("main", viewName);
	
		// model객체에 controller에서 설정한 속성이 있는지
		assertEquals("brown", mainUserId);
		
		assertNotNull(mav.getModel().get("rangers"));
		assertNotNull(mav.getModel().get("userVo"));
	}

	/**
	* Method : pathvariableTest
	* 작성자 : PC25
	* 변경이력 :
	* Method 설명 : @Pathvariable 테스트
	 * @throws Exception 
	*/
	@Test
	public void pathvariableTest() throws Exception {
		/***Given***/
		/***When***/
		mockMvc.perform(get("/main/pathvariable/brown"))
						.andExpect(status().isOk())
						.andExpect(view().name("main"));
		/***Then***/
	}
	/**
	 * Method : pathvariableTest
	 * 작성자 : PC25
	 * 변경이력 :
	 * Method 설명 : @Pathvariable Test
	 * @throws Exception 
	 */
	@Test
	public void pathvariableTest2() throws Exception {
		/***Given***/
		/***When***/
		mockMvc.perform(get("/main/pathvariable/sally"))
		.andExpect(status().isOk())
		.andExpect(view().name("main"));
		/***Then***/
	}
	
	/**
	* Method : requestHeaderTest
	* 작성자 : PC25
	* 변경이력 :
	* @throws Exception
	* Method 설명 : @RequestHeader test
	*/
	@Test
	public void requestHeaderTest() throws Exception {
		mockMvc.perform(get("/main/header").accept("text/html"))
					.andExpect(status().isOk())
					.andExpect(view().name("main"));
	}
}
