package org.regyu.sts.login.service.impl;

import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;
import org.regyu.sts.login.service.LoginVO;

@Mapper
public interface LoginMapper {

	LoginVO actionLogin(LoginVO loginVO) throws Exception;

	int resetPassword(HashMap<String, String> reqMap) throws Exception;

	int getUserCnt(HashMap<String, String> reqMap) throws Exception;

	Integer getResetUser(HashMap<String, String> reqMap) throws Exception;

	void updateResetUser(HashMap<String, String> reqMap) throws Exception;

	void changePassword(HashMap<String, String> reqMap) throws Exception;

}
