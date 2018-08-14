/**
 * 
 */
package com.bridgeit.discoveryclient.utilityservice.messageService;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.bridgeit.discoveryclient.userservice.model.MailDto;



/**
 * 
 * 
 * @author Chaithra-Shenoy
 * @since Date 10-07-2018 <br>
 *        <p>
 *        <b>Producer implementation class having implementation of abstract
 *        method.</b>
 *        </p>
 */
@Component
public class ProducerImpl implements IProducer {

	@Autowired
	private AmqpTemplate amqpTemplate;

	@Value("${rabbit.rabbitmq.exchange}")
	private String exchange;

	@Value("${rabbit.rabbitmq.routingkey}")
	private String routingKey;

	/* (non-Javadoc)
	 * @see com.bridgeit.todoapplication.utilityservice.messageService.IProducer#produceMessage(com.bridgeit.todoapplication.userservice.model.MailDto)
	 */
	@Override
	public void produceMessage(MailDto mailDTO) {
		amqpTemplate.convertAndSend(exchange, routingKey, mailDTO);
		System.out.println("Send msg = " + mailDTO);
	}

}
