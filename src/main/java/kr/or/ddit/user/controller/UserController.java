package kr.or.ddit.user.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.encrypt.kisa.sha256.KISA_SHA256;
import kr.or.ddit.paging.model.PageVO;
import kr.or.ddit.user.model.UserVO;
import kr.or.ddit.user.model.UserVoValidator;
import kr.or.ddit.user.service.IUserService;
import kr.or.ddit.util.PartUtil;

@RequestMapping("/user")
@Controller
public class UserController {

	@Resource(name = "userService")
	private IUserService userService;
	
	/**
	* Method : userList
	* 작성자 : PC25
	* 변경이력 :
	* @param model
	* @return
	* Method 설명 : 사용자 전체 리스트 뷰
	*/
	@RequestMapping("/list")
	public String userList(Model model) {
		List<UserVO> userList = userService.userList();
		
		model.addAttribute("userList", userList);
		
		return "user/list";
	}
	
	@RequestMapping("/userListExcel")
	 public String userListExcel(Model model, String filename) {
		List<String> header = new ArrayList<String>();
		header.add("userId");
		header.add("name");
		header.add("alias");
		header.add("addr1");
		header.add("addr2");
		header.add("zipcd");
		header.add("birth");
		
		model.addAttribute("filename", filename);
		model.addAttribute("header", header);
		model.addAttribute("data", userService.userList());
		
		return "userExcelView";
	 }
	
	/**
	* Method : userPagingList
	* 작성자 : PC25
	* 변경이력 :
	* @return
	* Method 설명 : 사용자 페이징 리스트 뷰
	*/
	@RequestMapping("/pagingList")
	public String userPagingList(Model model, PageVO pageVo) {
		
		Map<String, Object> resultMap = userService.userPagingList(pageVo);
		
		List<UserVO> userList = (List<UserVO>) resultMap.get("userList");
		int paginationSize = (int) resultMap.get("paginationSize");
		
		model.addAttribute("userList", userList);
		model.addAttribute("paginationSize", paginationSize);
		model.addAttribute("pageVo", pageVo);
		
		return "user/pageList";
	}
	
	@RequestMapping("/profile") 
	public String profile(String userId, Model model) { 
		UserVO userVo = userService.getUser(userId);
		model.addAttribute("userVo", userVo);
		return "profileView";
		
	}
	
	/**
	* Method : user
	* 작성자 : PC25
	* 변경이력 :
	* @param userId
	* @param model
	* @return
	* Method 설명 : 사용자 상세 조회
	*/
	@RequestMapping("/user")
	public String user(String userId, Model model ) {
		model.addAttribute("userVo", userService.getUser(userId));
		model.addAttribute("userId", userId);
		logger.debug("user/user userVo : {}" , userService.getUser(userId));
		
		return "user/user";
	}
	/**
	* Method : userAjax
	* 작성자 : PC25
	* 변경이력 :
	* @param userId
	* @param model
	* @return
	* Method 설명 : 사용자 정보 json 응답
	*/
	@RequestMapping("/userJson")
	public String userJson(String userId, Model model ) {
		model.addAttribute("userVo", userService.getUser(userId));
//		model.addAttribute("userId", userId);
		logger.debug("user/user userVo : {}" , userService.getUser(userId));
		
		return "jsonView";
	}
	
	/**
	* Method : userForm
	* 작성자 : PC25
	* 변경이력 :
	* @return
	* Method 설명 : 사용자 등록 요청
	*/
	@RequestMapping(path = "/form", method = RequestMethod.GET)
	public String userForm() {
		return "user/form";
	}
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	/**
	* Method : userForm
	* 작성자 : PC25
	* 변경이력 :
	* @param profile
	* @param userVO
	* @param model
	* @return
	* Method 설명 : 사용자 등록 응답
	*/
	//@RequestMapping(path = "/form", method = RequestMethod.POST)
	public String userFormProcess(UserVO userVO, BindingResult result, MultipartFile profile, 
							Model model) {
		
		new UserVoValidator().validate(userVO, result);
		
		if (result.hasErrors()) 
			return "user/form";
		
		logger.debug("userForm profile");
		UserVO dbUser = userService.getUser(userVO.getUserId());
		if (dbUser == null) {
			// profile 파일 업로드 처리
			if (profile.getSize() > 0 && profile != null) {
				String fileName = profile.getOriginalFilename();
				String ext = PartUtil.getExt(fileName);
				String sp = File.separator;
				
				Map<String, Object> resultMap = PartUtil.setMkdir();
				String uploadPath =  (String) resultMap.get("uploadPath");
				File uploadFolder = (File) resultMap.get("uploadFolder");
				
				// 파일 디스크에 쓰기
				String filePath = uploadPath + sp + UUID.randomUUID().toString() + ext;
				userVO.setPath(filePath);
				userVO.setFilename(fileName);
				try {
					profile.transferTo(new File(filePath));
				} catch (IllegalStateException | IOException e) {
					e.printStackTrace();
				}
			}
			
			userVO.setPass(KISA_SHA256.encrypt(userVO.getPass()));
			
			if(userService.insertUser(userVO) == 1);
				return "redirect:/user/pagingList";
		}else{
			model.addAttribute("msg", "중복된 아이디입니다");
			return userForm();
		}
	}
	
	/**
	 * Method : userForm
	 * 작성자 : PC25
	 * 변경이력 :
	 * @param profile
	 * @param userVO
	 * @param model
	 * @return
	 * Method 설명 : 사용자 등록 응답(hibernate)
	 */
	@RequestMapping(path = "/form", method = RequestMethod.POST)
	public String userFormProcessJsr(@Valid UserVO userVO, BindingResult result, MultipartFile profile, 
			Model model) {
		
		if (result.hasErrors()) 
			return "user/form";
		
		logger.debug("userForm profile");
		UserVO dbUser = userService.getUser(userVO.getUserId());
		if (dbUser == null) {
			// profile 파일 업로드 처리
			if (profile.getSize() > 0 && profile != null) {
				String fileName = profile.getOriginalFilename();
				String ext = PartUtil.getExt(fileName);
				String sp = File.separator;
				
				Map<String, Object> resultMap = PartUtil.setMkdir();
				String uploadPath =  (String) resultMap.get("uploadPath");
				File uploadFolder = (File) resultMap.get("uploadFolder");
				
				// 파일 디스크에 쓰기
				String filePath = uploadPath + sp + UUID.randomUUID().toString() + ext;
				userVO.setPath(filePath);
				userVO.setFilename(fileName);
				try {
					profile.transferTo(new File(filePath));
				} catch (IllegalStateException | IOException e) {
					e.printStackTrace();
				}
			}
			
			userVO.setPass(KISA_SHA256.encrypt(userVO.getPass()));
			
			if(userService.insertUser(userVO) == 1);
			return "redirect:/user/pagingList";
		}else{
			model.addAttribute("msg", "중복된 아이디입니다");
			return userForm();
		}
	}
	
	/**
	* Method : userModify
	* 작성자 : PC25
	* 변경이력 :
	* @return
	* Method 설명 : 사용자 수정 요청
	*/
	@RequestMapping(path = "/modify", method = RequestMethod.GET)
	public String userModify(String userId, Model model) {
		logger.debug("userModify get userId : {}" , userId);
		model.addAttribute("userVo", userService.getUser(userId));
		return "user/modify"; 
	}
	
	/**
	* Method : userModifyProcess
	* 작성자 : PC25
	* 변경이력 :
	* @return
	* Method 설명 : 사용자 수정 응답
	*/
	 @RequestMapping(path = "/modify", method = RequestMethod.POST) public
	 String userModifyProcess(MultipartFile modifyFile, UserVO userVo, Model model, RedirectAttributes redirectAttributes) {
	 logger.debug("userVo : {}", userVo);
		 userVo.setPass(KISA_SHA256.encrypt(userVo.getPass()));
		 
		 if(modifyFile.getSize() > 0) {
			 String fileName =  modifyFile.getOriginalFilename();
			 String ext = PartUtil.getExt(fileName); 
			 String sp = File.separator;
			 
			 Map<String, Object> resultMap = PartUtil.setMkdir(); 
			 String uploadPath = (String)resultMap.get("uploadPath");
			 String filePath = uploadPath + sp + UUID.randomUUID().toString() + ext; 
			 File file = new File(filePath);
			 userVo.setPath(filePath);
			 userVo.setFilename(fileName); 
			 try {
				modifyFile.transferTo(file);
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}
		 }

		 if	 (userService.updateDateUser(userVo) == 1) { 
			 redirectAttributes.addFlashAttribute("msg", "수정 성공");
			 logger.debug("userId : {}", userVo.getUserId());
			 redirectAttributes.addAttribute("userId", userVo.getUserId());
			 
			 return "redirect:/user/user"; 
		 }else{ 
			 model.addAttribute("msg", "회원정보 수정 실패"); 
			 return "user/modify"; 
		 } 
	 }
	 
	 
	
}
