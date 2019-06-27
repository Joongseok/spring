package kr.or.ddit.view;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.View;

import kr.or.ddit.user.model.UserVO;

public class ProfileView implements View{

	private static final Logger logger = LoggerFactory.getLogger(ProfileView.class);
	
	@Override
	public String getContentType() {
 
		return "img";
	}

	@Override
	public void render(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		logger.debug("profileView.render()");
		UserVO userVo = (UserVO) model.get("userVo");
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

}
