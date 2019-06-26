package kr.or.ddit.lprod.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.lprod.model.BuyerVO;
import kr.or.ddit.lprod.model.LprodVO;
import kr.or.ddit.lprod.model.ProdVO;
import kr.or.ddit.lprod.service.ILprodService;
import kr.or.ddit.paging.model.PageVO;

@RequestMapping("/lprod")
@Controller
public class LprodController{
  
	@Resource(name = "lprodService")
	private ILprodService lprodService;
	
	
	/**
	* Method : test
	* 작성자 : PC25
	* 변경이력 :
	* Method 설명 : lprod 상세 화면
	 * @throws Exception 
	*/
	@RequestMapping("/prod")
	public String lprod(String prod_buyer ,Model model) {
		BuyerVO buyerVo = lprodService.getBuyer(prod_buyer);
		
		model.addAttribute("buyerVo", buyerVo);
		return "/lprod/buyer";
	}
	
	/**
	* Method : test
	* 작성자 : PC25
	* 변경이력 :
	* Method 설명 : lprodPagingList 화면
	 * @throws Exception 
	*/
	@RequestMapping("/pagingList")
	public String prodList(PageVO pageVo ,LprodVO lprodVo, Model model) {
		
		// selectBox
		List<LprodVO> lprodList = lprodService.lprodList();
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("page", pageVo.getPage());
		map.put("pageSize", pageVo.getPageSize());
		map.put("prod_lgu", lprodVo.getLprod_gu());
		
		// selectBox에 해당하는 prodPagingList
		Map<String, Object> resultMap = lprodService.lprodPagingList(map);
		List<ProdVO> prodList = (List<ProdVO>) resultMap.get("prodList");
		int paginationSize = (int) resultMap.get("paginationSize");
		
		model.addAttribute("paginationSize", paginationSize);
		model.addAttribute("prodList", prodList);
		model.addAttribute("lprodList", lprodList);
		model.addAttribute("lprod_gu", lprodVo.getLprod_gu());
		return "/lprod/lprodPageList";
		
	}


}
