package org.regyu.sts.cmmn.service.impl;

import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;

import org.regyu.sts.cmmn.service.MailService;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service("mailService")
public class MailServiceImpl implements MailService{

	@Resource(name = "javaMailSender")
	private JavaMailSender javaMailSender;
	
	@Override
	public void sendMail(String to, String subject, String text) throws Exception {
		//전자우편을 위한 웹 표준 포맷
		//구글 메일 디펜던시 필요함 : MimeMessage
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, false, "utf-8");
				
		mimeMessage.setContent(text, "text/html;charset=utf-8");
		
		helper.setTo(to);
		helper.setSubject(subject);
		
		javaMailSender.send(mimeMessage);
	}
	
}
