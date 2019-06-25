package kr.or.ddit.util;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class PartUtil {
	
	private static final String UPLOAD_PATH = "d:"+ File.separator +"springupload"+ File.separator;

	/**
	* Method : getExt
	* 작성자 : PC25
	* 변경이력 :
	* @param fileName
	* @return
	* Method 설명 : 파일명으로부터 파일 확장자를 반환한다.
	*/
	public static String getExt(String fileName) {
		if (fileName.contains(".")) {
			int start = fileName.lastIndexOf('.');
			return fileName.substring(start);
		}
		return "";
	}
	
	/**
	* Method : setMkdir
	* 작성자 : PC25
	* 변경이력 :
	* @return
	* Method 설명 : 년,월 업로드 폴더가 존재하는지 체크 없을경우 폴더 생성
	*/
	public static Map<String, Object> setMkdir(){
		//년도에 해당하는 폴더가 있는지, 년도안에 월에 해당하는 폴더가 있는지
		Date dt = new Date();
		SimpleDateFormat yyyyMMSdf = new SimpleDateFormat("yyyyMM");
		
		String yyyyMM = yyyyMMSdf.format(dt);
		
		String yyyy = yyyyMM.substring(0, 4);
		String mm = yyyyMM.substring(4, 6);
		
		String sp = File.separator;
		
		File yyyyFolder = new File(UPLOAD_PATH + yyyy);
		// 신규년도로 넘어갔을때 해당 년도의 폴더를 생성한다.
		if(!yyyyFolder.exists()){
			yyyyFolder.mkdir();
		}
		
		//월에 해당하는 폴더가 있는지 확인
		File mmFolder = new File(UPLOAD_PATH + yyyy + sp + mm);
		if(!mmFolder.exists()){
			mmFolder.mkdir();
		}
		
		String uploadPath = UPLOAD_PATH + yyyy + sp + mm;
		File uploadFolder = new File(uploadPath);
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("uploadPath", uploadPath);
		resultMap.put("uploadFolder", uploadFolder);
		return resultMap;
	}

}
