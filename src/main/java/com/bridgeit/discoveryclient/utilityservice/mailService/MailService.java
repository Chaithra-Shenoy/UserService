/**
 * 
 */
package com.bridgeit.discoveryclient.utilityservice.mailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgeit.discoveryclient.userservice.model.MailDto;
import com.bridgeit.discoveryclient.utilityservice.messageService.ProducerImpl;



/**
 * 
 * 
 * @author Chaithra-Shenoy
 * @since Date 10-07-2018 <br>
 *        <p>
 *        <b>Mail Service class.</b>
 *        </p>
 */
@Service
public class MailService {

	@Autowired
	private ProducerImpl producer;
	
	/**
	 * @param to
	 * @param body
	 * @param message
	 */
	public void sendMail(String to, String body, String message) {
	
		MailDto mailDTO = new MailDto();
		mailDTO.setTo(to);
		mailDTO.setSubject(body);
		mailDTO.setText("Congratulation! ");
		mailDTO.setBody("HI, Welcome to ToDoApplication "+message);
		mailDTO.setSignature(" With regards, \n Chaithra Shenoy \n  Contact me-8970190641 \n Mumbai");
	
		producer.produceMessage(mailDTO);
	}
}
