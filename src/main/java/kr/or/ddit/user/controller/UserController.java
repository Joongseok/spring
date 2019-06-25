package kr.or.ddit.user.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.paging.model.PageVO;
import kr.or.ddit.user.model.UserVO;
import kr.or.ddit.user.service.IUserService;
import kr.or.ddit.util.PartUtil;

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
	@RequestMapping("/userList")
	public String userList(Model model) {
		List<UserVO> userList = userService.userList();
		
		model.addAttribute("userList", userList);
		
		return "user/userList";
	}
	
	/**
	* Method : userPagingList
	* 작성자 : PC25
	* 변경이력 :
	* @return
	* Method 설명 : 사용자 페이징 리스트 뷰
	*/
	@RequestMapping("/userPagingList")
	public String userPagingList(Model model, PageVO pageVo) {
		
		Map<String, Object> resultMap = userService.userPagingList(pageVo);
		
		List<UserVO> userList = (List<UserVO>) resultMap.get("userList");
		int paginationSize = (int) resultMap.get("paginationSize");
		
		model.addAttribute("userList", userList);
		model.addAttribute("paginationSize", paginationSize);
		model.addAttribute("pageVo", pageVo);
		
		return "user/userPageList";
	}
	
	/*
	 * @RequestMapping("/profile") public void profile(String userId,
	 * HttpServletResponse response) { UserVO userVo = userService.getUser(userId);
	 * 
	 * // path정보로 file을 읽어 들여서 ServletOutputStream sos = response.getOutputStream();
	 * FileInputStream fis = null; String filePath = null;
	 * 
	 * // 사용자가 업로드한 파일이 존재할 경우 : path if (userVo.getPath() != null) { filePath =
	 * userVo.getPath(); }else{ // 사용자가 업로드한 파일이 존재하지 않을 경우 : no_image.gif filePath
	 * = getServletContext().getRealPath("/img/no_image.gif"); }
	 * 
	 * File file = new File(filePath); fis = new FileInputStream(file); byte[]
	 * buffer = new byte[512];
	 * 
	 * //response객체에 스트림으로 써준다. while( fis.read(buffer, 0, 512) != -1){
	 * sos.write(buffer); } fis.close(); sos.close(); }
	 */
	
	@RequestMapping("/user")
	public String user(String userId, HttpServletRequest request, HttpSession session) {
		UserVO userVo = userService.getUser(userId);
		
		request.setAttribute("userVo", userVo);
		
		session.setAttribute("userVo", userVo);
		if (userVo.getBirth() != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String birthStr = sdf.format(userVo.getBirth());
			request.setAttribute("birthStr", birthStr);
		}
		return "user/user";
	}
	
	/**
	* Method : userForm
	* 작성자 : PC25
	* 변경이력 :
	* @return
	* Method 설명 : 사용자 등록 요청
	*/
	@RequestMapping(path = "/userForm", method = RequestMethod.GET)
	public String userForm() {
		return "user/userForm";
	}
	
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
	@RequestMapping(path = "/userForm", method = RequestMethod.POST)
	public String userFormProcess(@RequestPart("profile") MultipartFile profile, UserVO userForm
							,Model model) {
		
		UserVO checkId = userService.getUser(userForm.getUserId());
		if (checkId == null) {
			// profile 파일 업로드 처리
			if (profile.getSize() > 0 && profile != null) {
				String fileName = profile.getOriginalFilename();
				String ext = PartUtil.getExt(fileName);
				String sp = File.separator;
				
				Map<String, Object> resultMap = PartUtil.setMkdir();
				String uploadPath =  (String) resultMap.get("uploadPath");
				File uploadFolder = (File) resultMap.get("uploadFolder");
				
				if(uploadFolder.exists()){
					// 파일 디스크에 쓰기
					String filePath = uploadPath + sp + UUID.randomUUID().toString() + ext;
					userForm.setPath(filePath);
					userForm.setFilename(fileName);
					try {
						profile.transferTo(uploadFolder);
					} catch (IllegalStateException | IOException e) {
						e.printStackTrace();
					}
				}
			}
			userService.insertUser(userForm);
			return "redirect:userPagingList";
		}else{
			model.addAttribute("msg", "중복된 아이디입니다");
			return "user/userForm";
		}
	}
	
	/**
	* Method : userModify
	* 작성자 : PC25
	* 변경이력 :
	* @return
	* Method 설명 : 사용자 수정 요청
	*/
	@RequestMapping(path = "/userModify", method = RequestMethod.GET)
	public String userModify() {
		return "user/userModify"; 
	}
	
	/**
	* Method : userModifyProcess
	* 작성자 : PC25
	* 변경이력 :
	* @return
	* Method 설명 : 사용자 수정 응답
	*/
	/*
	 @RequestMapping(path = "/userModify", method = RequestMethod.GET) public
	 String userModifyProcess(UserVO modifyUser) {
	 
		 userVo.setPass(KISA_SHA256.encrypt(userVo.getPass()));
		 
		 Part profile = request.getPart("modifyFile");
		 if (profile.getSize() > 0 && profile != null) {
		 String contentDisposition = profile.getHeader("content-disposition"); 
		 String fileName = PartUtil.getFileName(contentDisposition); 
		 String ext = PartUtil.getExt(fileName); 
		 String sp = File.separator;
		 
		 Map<String, Object> resultMap = PartUtil.setMkdir(); 
		 File uploadFolder =(File) resultMap.get("uploadFolder"); 
		 String uploadPath = (String)resultMap.get("uploadPath");
		 
		 if(uploadFolder.exists()){ 
			 // 파일 디스크에 쓰기 String filePath = uploadPath + sp +
			 UUID.randomUUID().toString() + ext; modifyUser.setPath(filePath);
			 modifyUser.setFilename(fileName); profile.write(filePath); profile.delete();
		 } 
		 
		 } 
		 int modifyResult = userService.updateDateUser(modifyUser); 
		 if	 (modifyResult == 1) { 
			 response.sendRedirect(request.getContextPath() +"/userPagingList"); 
		 }else{ 
			 request.setAttribute("msg", "회원정보 수정 실패"); 
			 return "/user/userModify"; 
		 } 
		 	return "user/userModify"; 
	 }
	 */
	
}
