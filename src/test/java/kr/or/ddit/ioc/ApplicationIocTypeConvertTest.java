package kr.or.ddit.ioc;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.or.ddit.user.model.UserVO;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:kr/or/ddit/ioc/application-ioc-type-convert.xml")
public class ApplicationIocTypeConvertTest {

	@Resource(name = "userVo")
	private UserVO userVo;
	
	@Test
	public void typeInjectionTest() {
		/***Given***/
		/***When***/

		Date birth = userVo.getBirth();
		
		SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
		String birth_str = sdf.format(birth);
		
		/***Then***/
		assertNotNull(userVo);
		assertEquals("brown", userVo.getUserId());
		assertEquals("08-08-2019", birth_str);
		
	}

}
