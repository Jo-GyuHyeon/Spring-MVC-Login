package org.regyu.sts.singin.service.impl;

import org.regyu.sts.singin.service.SingInService;
import org.regyu.sts.singin.service.SingInVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("singInService")
public class SingInServiceImpl implements SingInService {
	
	private static final Logger logger = LoggerFactory.getLogger(SingInServiceImpl.class);
	
	@Autowired
	private SingInMapper SingInMapper;
	
	@Override
	public void insertMber(SingInVO singInVO) throws Exception {
//		String encPassword	= CryptoUtil.CryptoSHA3(singInVO.getPassword(), 256);
//		
//		logger.info(encPassword);
//		
//		singInVO.setPassword(encPassword);
		
		SingInMapper.insertMber(singInVO);
	}

}
