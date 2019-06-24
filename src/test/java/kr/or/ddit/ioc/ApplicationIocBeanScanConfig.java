package kr.or.ddit.ioc;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;

import kr.or.ddit.board.dao.BoardDao;
import kr.or.ddit.board.dao.IBoardDao;
import kr.or.ddit.board.service.BoardService;
import kr.or.ddit.board.service.IBoardService;

// defaultFilter : @Controller, @Service, @Repository, @Component
// useDefaultFilter를 false로 선언하면 개발자가 원하는 어노테이션만 스캔 가능
// ex : @Controller
@Configuration
@ComponentScan(basePackages = {"kr.or.ddit.board", "kr.or.ddit.aop"} ,useDefaultFilters = true)
public class ApplicationIocBeanScanConfig {
	@Bean
	public IBoardDao boardDao() {
		return new BoardDao();
	}
	
	@Bean
	public IBoardService boardService() {
		IBoardService boardService = new BoardService();
		boardService.setName("brown");
		boardService.setBoardDao(boardDao());
		return boardService;
	}
}
