package kr.or.ddit.user.dao;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import kr.or.ddit.user.model.UserVO;

@Repository
public class UserDao implements IUserDao {

	@Resource(name = "sqlSession")
	private SqlSessionTemplate sqlSession;

	/**
	* Method : userList
	* 작성자 : PC25
	* 변경이력 :
	* @return
	* Method 설명 : 사용자 전체 리스트 조화
	*/
	@Override
	public List<UserVO> userList() {
		return sqlSession.selectList("user.userList");
	}

	@Override
	public int insertUser(UserVO userVo) {
		
		return sqlSession.insert("user.insertUser", userVo);
	}

	@Override
	public int deleteUser(String userId) {

		return sqlSession.delete("user.deleteUser", userId);
	}
	
	
	
}
