package kr.or.ddit.ioc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.text.SimpleDateFormat;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.or.ddit.typeConvert.model.FormattingVo;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:kr/or/ddit/ioc/application-ioc-type-formatting.xml")
public class ApplicationIocTypeFormattingTest {

	@Resource(name = "FormattingVo")
	private FormattingVo formattingVo;
		
	@Test
	public void test() {
		/***Given***/
		/***When***/
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String reg_dt = sdf.format(formattingVo.getReg_dt());
		String mod_dt = sdf.format(formattingVo.getMod_dt());
		
		/***Then***/
		assertNotNull(formattingVo);
		assertEquals("2019-06-21", reg_dt);
		assertEquals("2019-06-21", mod_dt);
		assertEquals(6000, formattingVo.getNumber());
	}

}
