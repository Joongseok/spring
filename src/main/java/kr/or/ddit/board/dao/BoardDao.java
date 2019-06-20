package kr.or.ddit.board.dao;

import org.springframework.stereotype.Repository;

@Repository
public class BoardDao implements IBoardDao {

	@Override
	public String sayHello() {
		return "boardDao sayHello";
	}
	
}
