package com.bridgeit.discoveryclient.utilityservice.messageService;
import javax.mail.MessagingException;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bridgeit.discoveryclient.userservice.model.MailDto;
import com.bridgeit.discoveryclient.utilityservice.mailService.MailSecurity;



/**
 * 
 * 
 * @author Chaithra-Shenoy
 * @since Date 10-07-2018 <br>
 *        <p>
 *        <b>Consumer implementation class implements IConsumer interface having
 *        implementation of the abstract method.</b>
 *        </p>
 */
@Component
public class ConsumerImpl implements IConsumer {

	@Autowired
	private MailSecurity emailSecurity;

	/* (non-Javadoc)
	 * @see com.bridgeit.todoapplication.utilityservice.messageService.IConsumer#recievedMessage(com.bridgeit.todoapplication.userservice.model.MailDto)
	 */
	@Override
	@RabbitListener(queues = "${rabbit.rabbitmq.queue}")
	public void recievedMessage(MailDto mailDTO) throws MessagingException {
		System.out.println(mailDTO);
		emailSecurity.sendEmail(mailDTO);
	}
}
