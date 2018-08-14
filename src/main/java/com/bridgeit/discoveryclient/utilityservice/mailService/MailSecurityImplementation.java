/**
 * 
 */
package com.bridgeit.discoveryclient.utilityservice.mailService;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import com.bridgeit.discoveryclient.userservice.model.MailDto;



/**
 * 
 * 
 * @author Chaithra-Shenoy
 * @since Date 10-07-2018 <br>
 *        <p>
 *        <b>MailService Implementation class implements MailSecurity having one
 *        Overrided method to send mail to valid user.</b>
 *        </p>
 */
@Component
public class MailSecurityImplementation implements MailSecurity {

	@Autowired
	private JavaMailSender emailSender;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.bridgeit.todoapplication.userservice.security.MailSecurity#sendEmail(com.
	 * bridgeit.todoapplication.userservice.dto.MailDto)
	 */
	@Override
	public void sendEmail(MailDto mailDTO) throws MessagingException {
		MimeMessage mimeMessage = emailSender.createMimeMessage();
		MimeMessageHelper message = new MimeMessageHelper(mimeMessage);

		message.setTo(mailDTO.getTo());
		message.setSubject(mailDTO.getText() + "\n" + "\n" + mailDTO.getSubject());
		message.setText(mailDTO.getBody() + "\n" + "\n" + mailDTO.getSignature());
		emailSender.send(mimeMessage);

	}

}
