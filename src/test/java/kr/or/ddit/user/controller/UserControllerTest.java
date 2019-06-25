package kr.or.ddit.user.controller;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.List;

import org.junit.Test;
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
		 MvcResult mvcResult = mockMvc.perform(get("/userList")).andReturn();
		 ModelAndView mav = mvcResult.getModelAndView();
		/***Then***/
		 assertEquals("user/userList", mav.getViewName());
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
		 MvcResult mvcResult = mockMvc.perform(get("/userPagingList")
									 .param("page", "2")
									 .param("pageSize", "10"))
									 .andReturn();
		 ModelAndView mav = mvcResult.getModelAndView();
		 List<UserVO> userList = (List<UserVO>)mav.getModelMap().get("userList");
		 int paginationSize = (int)mav.getModelMap().get("paginationSize");
		 PageVO pageVo = (PageVO)mav.getModelMap().get("pageVo");
		 String viewName = mav.getViewName();
		 
		/***Then***/
		 assertEquals("user/userPageList", viewName);
		 assertEquals(10, userList.size());
		 assertEquals(13, paginationSize);
		 assertEquals(2, pageVo.getPage());
		 assertEquals(10, pageVo.getPageSize());
	}

}
