package org.regyu.sts.login.service.impl;

import javax.inject.Inject;

import org.regyu.sts.cmmn.security.CryptoUtil;
import org.regyu.sts.login.service.LoginService;
import org.regyu.sts.login.service.LoginVO;
import org.springframework.stereotype.Service;

@Service("loginService")
public class LoginServiceImpl implements LoginService {
	
	@Inject
	private LoginMapper loginMapper;
	
	@Override
	public LoginVO actionLogin(LoginVO loginVO) throws Exception {
		String encPassword	= CryptoUtil.CryptoSHA3(loginVO.getPassword(), 256);
		
		loginVO.setPassword(encPassword);
		
		LoginVO resultVO	= loginMapper.actionLogin(loginVO);
		
		boolean loginVOChk	= (resultVO != null && !"".equals(resultVO.getId()) && !"".equals(resultVO.getName()));
		
		if (loginVOChk) {
			return resultVO;
		} else {
			return null;
		}
	}

}
