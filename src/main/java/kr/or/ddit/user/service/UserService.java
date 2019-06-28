package kr.or.ddit.user.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.or.ddit.encrypt.kisa.sha256.KISA_SHA256;
import kr.or.ddit.paging.model.PageVO;
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
		return userDao.userList();
	}

	/**
	* Method : getUser
	* 작성자 : PC25
	* 변경이력 :
	* @param userId
	* @return
	* Method 설명 : 사용자 조회 리스트
	*/
	@Override
	public UserVO getUser(String userId) {
		return userDao.getUser(userId);
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
	public Map<String, Object> userPagingList(PageVO pageVo) {
		List<UserVO> userList = userDao.userPagingList(pageVo); //10
		int usersCnt = userDao.usersCnt(); //105
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		resultMap.put("userList", userList); // 10
		resultMap.put("usersCnt", usersCnt); // 105
//		resultMap.put("pageVo", pageVo); // 105
		
		int paginationSize = (int) Math.ceil((double)usersCnt/pageVo.getPageSize()); // 105/10
		resultMap.put("paginationSize", paginationSize);
		
		return resultMap;
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
		return userDao.usersCnt();
	}
	
	/**
	* Method : insertUser
	* 작성자 : PC25
	* 변경이력 :
	* @return
	* Method 설명 : 사용자 등록
	*/
	@Override
	public int insertUser(UserVO userVo) {
		int result = userDao.insertUser(userVo);
		return result;
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
		int result = userDao.deleteUser(userId);
		return result;
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
		int result = userDao.updateDateUser(modifyUser);
		return result;
	}

	/**
	* Method : encryptPassAllUser
	* 작성자 : PC25
	* 변경이력 :
	* @param userVo
	* @return
	* Method 설명 : 사용자 비밀번호 암호화 일괄 적용 배치
	* 			  : *********************** 재적용 금지 ***************************************
	*/
	@Override
	public int encryptPassAllUser() {
		// 사용금지 이미 암호화 적용됌
		if( 1 == 1){
			return 0;
		}
		//0. sql 실행에 필요한 sqlSession 객체를 생성
		//1. 모든 사용자 정보를 조회 (단, 기존 암호화 적용 사용자 제외, brown, userTest)
		List<UserVO> userListForPassEncrypt = userDao.userListForPassEncrypt(); 
		
		//2. 조회된 사용자의 비밀번호를 암호화 적용 후 사용자 업데이트 
		int updateCntSum = 0;
		for(UserVO userVo : userListForPassEncrypt){
			String encryptPass = KISA_SHA256.encrypt(userVo.getPass());
			userVo.setPass(encryptPass);
			
			int updateCnt = userDao.updateUserEncryptPass(userVo);
		}
		
		return updateCntSum;
	}

	// ***********사용금지*****************
//	private static final Logger logger = LoggerFactory
//			.getLogger(UserService.class);
//	public static void main(String[] args) {
//		IuserService service = new UserService();
//		int updateCnt = service.encryptPassAllUser();
//		logger.debug("updateCnt : {}", updateCnt);
//	}
	
}
