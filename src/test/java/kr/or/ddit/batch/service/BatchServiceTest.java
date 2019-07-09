package kr.or.ddit.batch.service;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Test;

import kr.or.ddit.testenv.LogicTestEnv;

public class BatchServiceTest extends LogicTestEnv{

	@Resource(name = "batchService")
	private IBatchService batchService;
	
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
		int createCount= batchService.createDaily(ym);
		/***Then***/
		assertEquals(69, createCount);
	}
}
