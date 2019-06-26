package kr.or.ddit.user.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.encrypt.kisa.sha256.KISA_SHA256;
import kr.or.ddit.paging.model.PageVO;
import kr.or.ddit.user.model.UserVO;
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
	public void profile(String userId,HttpServletResponse response, HttpServletRequest request) { 
		UserVO userVo = userService.getUser(userId);
		
		// path정보로 file을 읽어 들여서 
		ServletOutputStream sos = null;
		try {
			sos = response.getOutputStream();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		FileInputStream fis = null; 
		String filePath = null;
		
		// 사용자가 업로드한 파일이 존재할 경우 : path 
		if (userVo.getPath() != null) { 
			filePath = userVo.getPath(); 
		}else{
			// 사용자가 업로드한 파일이 존재하지 않을 경우 : no_image.gif 
			filePath = request.getServletContext().getRealPath("/img/no_image.gif"); 
		}
		
		File file = new File(filePath); 
		try {
			fis = new FileInputStream(file);
			byte[] buffer = new byte[512];
			
			//response객체에 스트림으로 써준다. 
			while( fis.read(buffer, 0, 512) != -1){
				sos.write(buffer); 
			} 
			fis.close(); 
			sos.close(); 
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
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
		
		return "user/user";
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
	* @param userForm
	* @param model
	* @return
	* Method 설명 : 사용자 등록 응답
	*/
	@RequestMapping(path = "/form", method = RequestMethod.POST)
	public String userFormProcess(MultipartFile profile, UserVO userForm
							,Model model) {
		logger.debug("userForm profile");
		UserVO dbUser = userService.getUser(userForm.getUserId());
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
				userForm.setPath(filePath);
				userForm.setFilename(fileName);
				try {
					profile.transferTo(new File(filePath));
				} catch (IllegalStateException | IOException e) {
					e.printStackTrace();
				}
			}
			
			userForm.setPass(KISA_SHA256.encrypt(userForm.getPass()));
			
			if(userService.insertUser(userForm) == 1);
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
	 String userModifyProcess(MultipartFile modifyFile, UserVO userVo, Model model) {
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
			 return "redirect:/user/user?userId=" + userVo.getUserId(); 
		 }else{ 
			 model.addAttribute("msg", "회원정보 수정 실패"); 
			 return "user/modify"; 
		 } 
	 }
	
}
