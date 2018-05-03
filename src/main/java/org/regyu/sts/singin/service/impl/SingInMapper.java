package org.regyu.sts.singin.service.impl;

import org.apache.ibatis.annotations.Mapper;
import org.regyu.sts.singin.service.SingInVO;


@Mapper
public interface SingInMapper {

	void insertMber(SingInVO singInVO) throws Exception;

}
