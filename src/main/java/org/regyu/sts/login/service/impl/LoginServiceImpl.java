package org.regyu.sts.login.service.impl;

import java.util.HashMap;

import javax.annotation.Resource;

import org.apache.commons.lang.RandomStringUtils;
import org.regyu.sts.cmmn.Constants;
import org.regyu.sts.cmmn.security.CryptoUtil;
import org.regyu.sts.cmmn.service.MailService;
import org.regyu.sts.login.service.LoginService;
import org.regyu.sts.login.service.LoginVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

@Service("loginService")
public class LoginServiceImpl implements LoginService {

	@Resource(name = "messageSource")
	private MessageSource messageSource;

	@Resource(name = "mailService")
	private MailService mailService;

	@Autowired
	private LoginMapper loginMapper;

	@Override
	public LoginVO actionLogin(LoginVO loginVO) throws Exception {
		String encPassword = CryptoUtil.CryptoSHA3(loginVO.getPassword(), 256);

		loginVO.setPassword(encPassword);

		LoginVO resultVO = loginMapper.actionLogin(loginVO);

		boolean loginVOChk = (resultVO != null && !"".equals(resultVO.getId()) && !"".equals(resultVO.getName()));

		if (loginVOChk) {
			return resultVO;
		} else {
			return null;
		}
	}

	@Override
	public String resetPassword(HashMap<String, String> reqMap) throws Exception {
		int userCnt = loginMapper.getUserCnt(reqMap);

		if (userCnt != 1) {
			return Constants.FAIL;
		}

		String hash = RandomStringUtils.randomAlphabetic(64);

		System.out.println("hash:" + hash);

		reqMap.put("hash", hash);

		loginMapper.resetPassword(reqMap);

		return Constants.SUCCESS;
	}

	@Override
	public void sendEmail(HashMap<String, String> reqMap) throws Exception {

		String host = "http://http://lwww.regyu.com",
				port = "8080",
				contextPath = "/sts",
				reqUrl = "/resetPasswordForm.do", hash = (String) reqMap.get("hash"), id = (String) reqMap.get("id");

		String linkUrl = host + ":" + port + "/" + contextPath + reqUrl + "?hash=" + hash + "&id=" + id;

		System.out.println("linkUrl : " + linkUrl);

		String to = id, subject = "비밀번호 초기화 안내입니다.",
				text = "아래 링크를 클릭하시면 비밀번호를 초기화 하실 수 있습니다." + "<br/><a href=\"" + linkUrl + "\">비밀번호 초기화 하기</a>";

		mailService.sendMail(to, subject, text);

	}

	@Override
	public boolean getResetUser(HashMap<String, String> reqMap) throws Exception {
		Integer resetUserId = loginMapper.getResetUser(reqMap);

		if (resetUserId == null) {
			return false;
		}

		return true;
	}

	@Override
	public String resetPasswordProc(HashMap<String, String> reqMap) throws Exception {
		Integer resetUserId = loginMapper.getResetUser(reqMap);

		if (resetUserId == null) {
			return Constants.FAIL;
		}

		reqMap.put("resetUserId", resetUserId + "");

		loginMapper.updateResetUser(reqMap);

		reqMap.put("password", CryptoUtil.CryptoSHA3((String) reqMap.get("password"), 256));

		loginMapper.changePassword(reqMap);

		return Constants.SUCCESS;
	}

	@Override
	public String resetPasswordProcTx(HashMap<String, String> reqMap) throws Exception {
		Integer resetUserId = loginMapper.getResetUser(reqMap);

		if (resetUserId == null) {
			return Constants.FAIL;
		}

		reqMap.put("resetUserId", resetUserId + "");

		loginMapper.updateResetUser(reqMap);

		if ("fail".equals(Constants.FAIL)) {
			throw new Exception();
		}

		reqMap.put("password", CryptoUtil.CryptoSHA3((String) reqMap.get("password"), 256));

		loginMapper.changePassword(reqMap);

		return Constants.SUCCESS;
	}

}
