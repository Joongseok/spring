package kr.or.ddit.user.dao;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import kr.or.ddit.paging.model.PageVO;
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
	/**
	* Method : insertUser
	* 작성자 : PC25
	* 변경이력 :
	* @param userVo
	* @return
	* Method 설명 : 사용자 등록
	*/
	@Override
	public int insertUser(UserVO userVo) {
		
		return sqlSession.insert("user.insertUser", userVo);
	}
	/**
	* Method : deleteUser
	* 작성자 : PC25
	* 변경이력 :
	* @param userId
	* @return
	* Method 설명 : 사용자 삭제
	*/
	@Override
	public int deleteUser(String userId) {

		return sqlSession.delete("user.deleteUser", userId);
	}
	/**
	* Method : getUser
	* 작성자 : PC25
	* 변경이력 :
	* @param userId
	* @return
	* Method 설명 : 사용자 정보조회
	*/
	@Override
	public UserVO getUser(String userId) {

		return sqlSession.selectOne("user.getUser", userId);
	}
	
	/**
	* Method : userPagingList
	* 작성자 : PC25
	* 변경이력 :
	* @param pageVo
	* @return
	* Method 설명 : 사용자 페이징 리스트 조회
	*/
	@Override
	public List<UserVO> userPagingList(PageVO pageVo) {
		return sqlSession.selectList("user.userPagingList", pageVo);
	}
	
	/**
	* Method : usersCnt
	* 작성자 : PC25
	* 변경이력 :
	* @return
	* Method 설명 : 사용자 전체수 조회
	*/
	@Override
	public int usersCnt() {
		return sqlSession.selectOne("user.usersCnt");
	}
	
	/**
	* Method : updateDateUser
	* 작성자 : PC25
	* 변경이력 :
	* @param modifyUser
	* @return
	* Method 설명 : 사용자 수정
	*/
	@Override
	public int updateDateUser(UserVO modifyUser) {
		return sqlSession.update("user.updateDateUser", modifyUser);
	}
	
	/**
	* Method : userListForPassEncrypt
	* 작성자 : PC25
	* 변경이력 :
	* @param sqlSession
	* @return
	* Method 설명 : 비밀번호 암호화 적용대상 사용자 전체 조회
	*/
	@Override
	public List<UserVO> userListForPassEncrypt() {
		return sqlSession.selectList("user.userListForPassEncrypt");
	}

	/**
	* Method : updateUserEncryptPass
	* 작성자 : PC25
	* 변경이력 :
	* @param sqlSession
	* @param userVo
	* @return
	* Method 설명 : 사용자 비밀번호 암호확 적용
	*/
	@Override
	public int updateUserEncryptPass(UserVO userVo) {
		
		return sqlSession.update("user.updateUserEncryptPass", userVo);
	}
	
}
