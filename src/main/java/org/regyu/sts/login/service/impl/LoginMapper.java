package org.regyu.sts.login.service.impl;

import org.apache.ibatis.annotations.Mapper;
import org.regyu.sts.login.service.LoginVO;

@Mapper
public interface LoginMapper {

	LoginVO actionLogin(LoginVO loginVO) throws Exception;

}
