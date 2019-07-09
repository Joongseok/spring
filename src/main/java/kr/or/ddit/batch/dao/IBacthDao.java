package kr.or.ddit.batch.dao;

import kr.or.ddit.batch.model.BatchVO;

public interface IBacthDao {

	/**
	* Method : deleteDaily
	* 작성자 : PC25
	* 변경이력 :
	* @param ym
	* @return
	* Method 설명 : 해당년월의 일실적 일괄 삭제
	*/
	int deleteDaily(String ym);
	
	/**
	* Method : createDaily
	* 작성자 : PC25
	* 변경이력 :
	* @param ym
	* @return
	* Method 설명 : 해당 년월의 일실적 일괄 생성
	*/
	int createDaily(String ym);

	/**
	* Method : insertBatch
	* 작성자 : PC25
	* 변경이력 :
	* @param batchVo
	* @return
	* Method 설명 : 배치 데이터 생성
	*/
	int insertBatch(BatchVO batchVo);

	/**
	* Method : updateBatch
	* 작성자 : PC25
	* 변경이력 :
	* @param batchVo
	* @return
	* Method 설명 : 배치 데이터 업데이트
	*/
	int updateBatch(BatchVO batchVo);
	
}
