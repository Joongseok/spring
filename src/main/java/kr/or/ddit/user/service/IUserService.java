package kr.or.ddit.user.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.paging.model.PageVO;
import kr.or.ddit.user.model.UserVO;

public interface IUserService {

	
	/**
	* Method : userList
	* 작성자 : PC25
	* 변경이력 :
	* @return
	* Method 설명 : 사용자 전체 조회
	*/
	List<UserVO> userList();
	
	/**
	* Method : getUser
	* 작성자 : PC25
	* 변경이력 :
	* @param userId
	* @return
	* Method 설명 : 사용자 정보 조회
	*/
	UserVO getUser(String userId);
	
	/**
	* Method : userPagingList
	* 작성자 : PC25
	* 변경이력 :
	* @param pageVo
	* @return
	* Method 설명 : 사용자 페이징 리스트 조회
	*/
	Map<String, Object> userPagingList(PageVO pageVo);
	
	/**
	* Method : usersCnt
	* 작성자 : PC25
	* 변경이력 :
	* @return
	* Method 설명 : 사용자 전체수 조회
	*/
	int usersCnt();
	/**
	* Method : insertUser
	* 작성자 : PC25
	* 변경이력 :
	* @return
	* Method 설명 : 사용자 등록
	*/
	int insertUser(UserVO userVo);
	
	/**
	* Method : deleteUser
	* 작성자 : PC25
	* 변경이력 :
	* @param userId
	* @return
	* Method 설명 : 사용자 삭제
	*/
	int deleteUser(String userId);

	/**
	* Method : updateDateUser
	* 작성자 : PC25
	* 변경이력 :
	* @param modifyUser
	* @return
	* Method 설명 : 사용자 수정
	*/
	int updateDateUser(UserVO modifyUser);
	
	/**
	* Method : encryptPassAllUser
	* 작성자 : PC25
	* 변경이력 :
	* @return
	* Method 설명 : 사용자 비밀번호 암호화 일괄적용 배치
	*/
	int encryptPassAllUser();
}
