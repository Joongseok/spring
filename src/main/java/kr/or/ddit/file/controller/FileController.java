package kr.or.ddit.file.controller;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.util.PartUtil;

@RequestMapping("/file")
@Controller
public class FileController {

	private static final Logger logger = LoggerFactory.getLogger(FileController.class);
	
	/**
	* Method : uploadView
	* 작성자 : PC25
	* 변경이력 :
	* @return
	* Method 설명 : file upload를 할 수있는 테스트 view리턴
	*/
	@RequestMapping("/uploadView")
	public String uploadView() {
		return "upload/upload";
	}
	
	@RequestMapping("/upload")
	public String upload(@RequestPart("img") MultipartFile file, Model model) {
		logger.debug("file.getOriginalFilename() : {}",file.getOriginalFilename());
		logger.debug("file.getName() : {}",file.getName());
		logger.debug("file.getSize() : {}",file.getSize());
		
		Map<String, Object> map = PartUtil.setMkdir();
		String path = (String) map.get("uploadPath");
		String ext = PartUtil.getExt(file.getOriginalFilename());
		String fileName = UUID.randomUUID().toString();
		
		File uploadFile = new File(path + File.separator + fileName + ext);
		
		try {
			file.transferTo(uploadFile);
			model.addAttribute("msg", "SUCCESS");
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
			model.addAttribute("msg", "FAIL");
		}
		
		return "upload/upload";
	}
}
