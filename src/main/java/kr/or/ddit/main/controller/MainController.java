package kr.or.ddit.main.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/*
  servlet
   - extends HttpServlet
   - servlet-mapping (web.xml, @WebSErvlet)
   - service -> doXXX
  spring :
   - pojo (Plain old Java Object), @Controller 
   - @RequestMapping(class, method)
   - @RequestMapping에 설정한 url method 호출
*/
@Controller
public class MainController {

	private static final Logger logger = LoggerFactory.getLogger(MainController.class);
	
	@RequestMapping("/main")
	public String mainView(Model model) {
		logger.debug("mainView");
		model.addAttribute("mainUserId", "brown");
		return "main";
	}
}