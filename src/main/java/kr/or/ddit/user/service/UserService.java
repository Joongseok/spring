package kr.or.ddit.user.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.or.ddit.user.dao.IUserDao;
import kr.or.ddit.user.model.UserVO;

@Service
public class UserService implements IUserService {

	@Resource(name = "userDao")
	private IUserDao userDao;
	/**
	* Method : userList
	* 작성자 : PC25
	* 변경이력 :
	* @return
	* Method 설명 : 사용자 전체 리스트 조회
	*/
	@Override
	public List<UserVO> userList() {
		List<UserVO> userList = userDao.userList();
		return userList;
	}
	@Override
	public int insertUser(UserVO userVo) {
		
		return userDao.insertUser(userVo);
	}
	@Override
	public int deleteUser(String userId) {
		return userDao.deleteUser(userId);
	}
}
