package kr.or.ddit.lprod.dao;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import kr.or.ddit.lprod.model.BuyerVO;
import kr.or.ddit.lprod.model.LprodVO;
import kr.or.ddit.lprod.model.ProdVO;


@Repository
public class LprodDao implements ILprodDao {

	@Resource(name = "sqlSession")
	SqlSessionTemplate sqlSession;
	
	
	/**
	* Method : lprodList
	* 작성자 : PC25
	* 변경이력 :
	* @return
	* Method 설명 : lprod 전체 조회
	*/
	@Override
	public List<LprodVO> lprodList() {
		return sqlSession.selectList("lprod.lprodList");
	}

	
	/**
	* Method : lprodPagingList
	* 작성자 : PC25
	* 변경이력 :
	* @return
	* Method 설명 : lprod 페이징 조회
	*/
	@Override
	public List<ProdVO> prodPagingList(Map<String, Object> map) {
		return sqlSession.selectList("lprod.prodPagingList", map);
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
		return sqlSession.selectOne("lprod.lprodCnt");
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
		return sqlSession.selectOne("lprod.getLprod", lprod_id);
	}

	/**
	* Method : getProdNameTest
	* 작성자 : PC25
	* 변경이력 :
	* Method 설명 : prod_lgu에 해당하는 prodName과 prodId를 조회하는 메서드
	*/
	@Override
	public List<ProdVO> getProdName(String prod_lgu) {
		return sqlSession.selectList("lprod.getProdName", prod_lgu);
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
		return sqlSession.selectList("lprod.prodList", lprod_gu);
	}

	/**
	* Method : prodCnt
	* 작성자 : PC25
	* 변경이력 :
	* @param string
	* @return
	* Method 설명 : prod_lgu에 해당하는 제품의 갯수 
	*/
	@Override
	public int prodCnt(String prod_lgu) {
		return sqlSession.selectOne("lprod.prodCnt", prod_lgu);
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
		return sqlSession.selectOne("lprod.getBuyer", prod_buyer);
	}



}
