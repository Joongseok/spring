package kr.or.ddit.lprod.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.lprod.model.BuyerVO;
import kr.or.ddit.lprod.model.LprodVO;
import kr.or.ddit.lprod.model.ProdVO;
import kr.or.ddit.paging.model.PageVO;

public interface ILprodService {

	/**
	* Method : lprodList
	* 작성자 : PC25
	* 변경이력 :
	* @return
	* Method 설명 : lprod 전체 조회
	*/
	List<LprodVO> lprodList();
	
	/**
	* Method : lprodPagingList
	* 작성자 : PC25
	* 변경이력 :
	* @return
	* Method 설명 : lprod 페이징 조회
	*/
	Map<String, Object> lprodPagingList(Map<String, Object> map);
	
	/**
	* Method : getLprod
	* 작성자 : PC25
	* 변경이력 :
	* @param lprod_id
	* @return
	* Method 설명 : lprod 선택 조회
	*/
	LprodVO getLprod(String lprod_id);
	
	/**
	* Method : lprodCnt
	* 작성자 : PC25
	* 변경이력 :
	* @return
	* Method 설명 : lprod 전체 수 조회
	*/
	int lprodCnt();

	/**
	* Method : prodList
	* 작성자 : PC25
	* 변경이력 :
	* @param lprod_gu
	* @return
	* Method 설명 : lprod_gu에 해당하는 prod리스트를 조회
	*/
	List<ProdVO> prodList(String lprod_gu);

	/**
	* Method : getBuyer
	* 작성자 : PC25
	* 변경이력 :
	* @param prod_buyer
	* @return
	* Method 설명 : prod_buyer에 해당하는 buyer정보
	*/
	BuyerVO getBuyer(String prod_buyer);
	
}
