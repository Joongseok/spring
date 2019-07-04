package kr.or.ddit.ajax.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.paging.model.PageVO;
import kr.or.ddit.user.model.UserVO;
import kr.or.ddit.user.service.IUserService;

@RequestMapping("/ajax")
@Controller
public class AjaxController {

	private static final Logger logger = LoggerFactory.getLogger(AjaxController.class);
	@Resource(name = "userService")
	private IUserService userService;
	/**
	* Method : view
	* 작성자 : PC25
	* 변경이력 :
	* @return
	* Method 설명 : ajax 호출용 view
	*/
	@RequestMapping("/view")
	public String view() {
		
		return "tiles.ajaxView";
	}
	
	@RequestMapping("/requestData")
	public String requestData(Model model) {
		
		model.addAttribute("pageVo", new PageVO(5, 10));
//		model.addAttribute("pageVo2", new PageVO(2, 10));
//		List<String> rangers = new ArrayList<String>();
//		rangers.add("brown");
//		rangers.add("sally");
//		rangers.add("cony");
//		model.addAttribute("rangers", rangers);
		
		return "jsonView";
	}
	
	@RequestMapping("/user")
	public String userdata(Model model, String userId) {
		
		UserVO userVo = userService.getUser(userId);
		model.addAttribute("userVo", userVo);
		return "jsonView";
	}
	@RequestMapping("/userHtml")
	public String userHtml(Model model, String userId) {
		UserVO userVo = userService.getUser(userId);
		model.addAttribute("userVo", userVo);
		return "user/userHtml";
	}
	
	@RequestMapping("/requestDataResponseBody")
	@ResponseBody // 응답 내용을 responseBody에 작성
	public PageVO requestDataResponseBody() {
		return new PageVO(5, 10);
	}
	
	@RequestMapping(path = "/requestBody", consumes = "application/json" // consumes : content-type 재현
			, produces = {"application/json", "application/xml"}) // produces : 메소드가 생성 가능한 타입
	@ResponseBody
	public UserVO requestBody(@RequestBody UserVO userVo) {
		logger.debug("userVo : {}", userVo);
		userVo.setUserId(userVo.getUserId() + "_MODIFY");
		userVo.setPass(userVo.getPass() + "_MODIFY");
		
		return userVo;
	}
	
	
}
