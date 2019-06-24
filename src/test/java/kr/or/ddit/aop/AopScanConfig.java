package kr.or.ddit.aop;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;

import kr.or.ddit.board.dao.BoardDao;
import kr.or.ddit.board.dao.IBoardDao;
import kr.or.ddit.board.service.BoardService;
import kr.or.ddit.board.service.IBoardService;

@Configuration
//@ComponentScan(basePackages = {"kr.or.ddit.board"} 
//				, excludeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, value = Controller.class) }
//				, includeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, value = Aspect.class)})
@EnableAspectJAutoProxy
public class AopScanConfig {
	
	@Bean
	public IBoardService boardService() {
		IBoardService boardService = new BoardService();
		return boardService;
	}
	@Bean
	public IBoardDao boardDao() {
		IBoardDao boardDao = new BoardDao();
		return boardDao;
	}
	@Bean
	public ProfilingAspect profillngAspect() {
		return new ProfilingAspect();
	}
	
}
