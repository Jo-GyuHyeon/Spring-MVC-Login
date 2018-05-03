package org.regyu.sts;

import java.sql.Connection;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.regyu.sts.singin.service.SingInVO;
import org.regyu.sts.singin.service.impl.SingInMapper;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:config/spring/test.xml","classpath*:config/spring/context-mapper.xml"})

public class DataSourceTest {

	@Inject
	private DataSource ds;
	@Inject
	private SingInMapper singInMapper;
	SingInVO singInVO = new SingInVO();
	
//	@Test
	public void test() throws Exception {
		
	}
	@Test
	public void testConection() throws Exception {
		try (Connection con = ds.getConnection()) {
			System.out.println(con);
			singInVO.setMberId("ki@na.com");
			singInVO.setMberNm("regyu");
			singInVO.setPassword("123123");
			singInVO.setPasswordConfirm("123123");
			singInVO.setPhoneNumber("01012341234");
			singInMapper.insertMber(singInVO);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
