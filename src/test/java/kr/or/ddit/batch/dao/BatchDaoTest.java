package kr.or.ddit.batch.dao;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.batch.model.BatchVO;
import kr.or.ddit.testenv.LogicTestEnv;

public class BatchDaoTest extends LogicTestEnv{

	private static final Logger logger = LoggerFactory.getLogger(BatchDaoTest.class);
	
	@Resource(name = "batchDao")
	private IBacthDao batchDao;
	
	/**
	* Method : createDailyTest
	* 작성자 : PC25
	* 변경이력 :
	* @param ym
	* @return
	* Method 설명 : 해당 년월의 일실적 일괄 생성 테스트
	*/
	@Test
	public void createDailyTest() {
		/***Given***/
		String ym = "201907";

		/***When***/
		int createCount= batchDao.createDaily(ym);
		/***Then***/
		assertEquals(69, createCount);
	}
	
	/**
	* Method : deleteDailyTest
	* 작성자 : PC25
	* 변경이력 :
	* @param ym
	* @return
	* Method 설명 : 해당년월의 일실적 일괄 삭제 테스트
	*/
	@Test
	public void deleteDailyTest() {
		/***Given***/
		String ym = "201907";
		batchDao.createDaily(ym);
		/***When***/
		int deleteCount = batchDao.deleteDaily(ym);
		/***Then***/
		assertEquals(69, deleteCount);
		
	}

	
	/**
	* Method : insertBatchTest
	* 작성자 : PC25
	* 변경이력 :
	* Method 설명 : 배치 실행 데이터 생성 테스트
	*/
	@Test
	public void insertBatchTest() {
		/***Given***/
		BatchVO batchVo = new BatchVO();
		batchVo.setBcd("01"); // 일실적 배치 : 01
		batchVo.setSt("01");  // 배치 실행상태 : 01 - 진행중
		
		/***When***/
		logger.debug("before insertBatch batchVo.getBid() : {}", batchVo.getBid());
		int insertCount = batchDao.insertBatch(batchVo);
		logger.debug("after insertBatch batchVo.getBid() : {}", batchVo.getBid());
		
		/***Then***/
		assertEquals(1, insertCount);
	}
	
	/**
	 * Method : updateBatchTest
	 * 작성자 : PC25
	 * 변경이력 :
	 * Method 설명 : 배치 데이터 업데이트 테스트
	 */
	@Test
	public void updateBatchTest() {
		/***Given***/
		BatchVO batchVo = new BatchVO();
		batchVo.setBcd("01"); // 일실적 배치 : 01
		batchVo.setSt("01");  // 배치 실행상태 : 01 - 진행중
		
		batchDao.insertBatch(batchVo); // selectKey때문에 bid가 들어가 있는 상태 
		logger.debug("update Batch batchVo.getBid() : {}", batchVo.getBid());
		batchVo.setSt("02");  // 배치 실행상태 : 01 - 진행중
		
		/***When***/
		int updateCount = batchDao.updateBatch(batchVo);
		
		/***Then***/
		assertEquals(1, updateCount);
	}
	
}
