package kr.or.ddit.lprod.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.or.ddit.lprod.dao.ILprodDao;
import kr.or.ddit.lprod.model.BuyerVO;
import kr.or.ddit.lprod.model.LprodVO;
import kr.or.ddit.lprod.model.ProdVO;
import kr.or.ddit.paging.model.PageVO;

@Service
public class LprodService implements ILprodService {

	@Resource(name = "lprodDao")
	ILprodDao lprodDao;
	
	/**
	* Method : lprodList
	* 작성자 : PC25
	* 변경이력 :
	* @return
	* Method 설명 : lprod 전체 조회
	*/
	@Override
	public List<LprodVO> lprodList() {
		return lprodDao.lprodList();
	}

	/**
	* Method : lprodPagingList
	* 작성자 : PC25
	* 변경이력 :
	* @return
	* Method 설명 : lprod 페이징 조회
	*/
	@Override
	public Map<String, Object> lprodPagingList(Map<String, Object> map) {
		List<ProdVO> prodList = lprodDao.prodPagingList(map);
		int prodsCnt = lprodDao.prodCnt((String)map.get("prod_lgu"));
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		resultMap.put("prodList", prodList);
		PageVO pageVo = new PageVO((int)map.get("page"), (int)map.get("pageSize"));
		
		int paginationSize = (int) Math.ceil((double) prodsCnt/pageVo.getPageSize()); //최대갯수 / pageVo.
		resultMap.put("paginationSize", paginationSize);
		
		return resultMap;
	}

	/**
	* Method : lprodCnt
	* 작성자 : PC25
	* 변경이력 :
	* @return
	* Method 설명 : lprod 전체 수 조회
	*/
	@Override
	public int lprodCnt() {
		
		return lprodDao.lprodCnt();
	}
	/**
	* Method : getLprod
	* 작성자 : PC25
	* 변경이력 :
	* @param lprod_id
	* @return
	* Method 설명 : lprod 선택 조회
	*/
	@Override
	public LprodVO getLprod(String lprod_id) {
		return lprodDao.getLprod(lprod_id);
	}
	/**
	* Method : prodList
	* 작성자 : PC25
	* 변경이력 :
	* @param lprod_gu
	* @return
	* Method 설명 : lprod_gu에 해당하는 prodList를 조회
	*/
	@Override
	public List<ProdVO> prodList(String lprod_gu) {
		return lprodDao.prodList(lprod_gu);
	}

	/**
	* Method : getBuyer
	* 작성자 : PC25
	* 변경이력 :
	* @param prod_buyer
	* @return
	* Method 설명 : prod_buyer에 해당하는 buyer정보
	*/
	@Override
	public BuyerVO getBuyer(String prod_buyer) {
		return lprodDao.getBuyer(prod_buyer);
	}

}
