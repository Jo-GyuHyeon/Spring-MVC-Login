package org.regyu.sts;

import java.sql.Connection;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.regyu.sts.login.service.LoginService;
import org.regyu.sts.login.service.LoginVO;
import org.regyu.sts.login.service.impl.LoginMapper;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:config/spring/test.xml",
		"classpath*:config/spring/context-mapper.xml" })

public class LoginTest {

	@Inject
	private DataSource ds;
	
	@Inject
	private LoginMapper loginMapper;
	
	@Resource(name = "loginService")
	private LoginService loginService;
	
	LoginVO loginVO = new LoginVO();
	
//	@Test
	public void test() throws Exception {
		
	}

	private void initVO() {
		loginVO.setId("ki@na.com");
		loginVO.setPassword("123123");
	}

	//@Test
	public void loginMapper() throws Exception {
		try (Connection con = ds.getConnection()) {
			System.out.println(con);
			initVO();

			LoginVO result =loginMapper.actionLogin(loginVO);
			
			System.out.println(result.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void loginService() throws Exception {
		try (Connection con = ds.getConnection()) {
			System.out.println(con);
			initVO();

			LoginVO result =loginService.actionLogin(loginVO);
			
			System.out.println(result.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
