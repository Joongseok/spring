package kr.or.ddit.lprod.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.List;

import org.junit.Test;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.servlet.ModelAndView;

import kr.or.ddit.lprod.model.BuyerVO;
import kr.or.ddit.lprod.model.ProdVO;
import kr.or.ddit.testenv.ControllerTestEnv;


public class LprodControllerTest extends ControllerTestEnv{

	

	
	/**
	* Method : test
	* 작성자 : PC25
	* 변경이력 :
	* Method 설명 : lprod 상세 화면
	 * @throws Exception 
	*/
	@Test 
	public void lprodTest() throws Exception {
		/***Given***/
		/***When***/
		MvcResult mvcResult = mockMvc.perform(get("/lprod/prod").param("prod_buyer", "P10101")).andReturn();
		ModelAndView mav = mvcResult.getModelAndView();
		BuyerVO buyerVo = (BuyerVO) mav.getModelMap().get("buyerVo");
		String viewName = mav.getViewName();

		/***Then***/
		assertEquals("/lprod/buyer", viewName);
		assertNotNull(buyerVo);
	}
	
	/**
	* Method : test
	* 작성자 : PC25
	* 변경이력 :
	* Method 설명 : prodPagingList 화면 테스트
	 * @throws Exception 
	*/
	@Test
	public void prodListTest() throws Exception {
		/***Given***/
		/***When***/
		MvcResult mvcResult = mockMvc.perform(get("/lprod/pagingList").param("lprod_gu", "P101")).andReturn();
		
		ModelAndView mav = mvcResult.getModelAndView();
		String viewName = mav.getViewName();
		List<ProdVO> prodList = (List<ProdVO>) mav.getModelMap().get("prodList");

		/***Then***/
		assertEquals("/lprod/lprodPageList", viewName);
		assertEquals(6, prodList.size());
		
	}
}
