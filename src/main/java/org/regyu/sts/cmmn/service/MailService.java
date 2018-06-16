package org.regyu.sts.cmmn.service;

public interface MailService {

	void sendMail(String to, String subject, String text) throws Exception;

}
