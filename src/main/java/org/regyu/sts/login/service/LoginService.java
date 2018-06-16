package org.regyu.sts.login.service;

import java.util.HashMap;

public interface LoginService {

	LoginVO actionLogin(LoginVO loginVO) throws Exception;

	String resetPassword(HashMap<String, String> reqMap) throws Exception;

	void sendEmail(HashMap<String, String> reqMap) throws Exception;

	boolean getResetUser(HashMap<String, String> reqMap) throws Exception;

	String resetPasswordProc(HashMap<String, String> reqMap) throws Exception;

	String resetPasswordProcTx(HashMap<String, String> reqMap) throws Exception;

}
