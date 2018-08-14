package com.bridgeit.discoveryclient.utilityservice.messageService;

import javax.mail.MessagingException;

import com.bridgeit.discoveryclient.userservice.model.MailDto;



/**
 * 
 * 
 * @author Chaithra-Shenoy
 * @since Date 10-07-2018 <br>
 *        <p>
 *        <b>IConsumer interface having one abstrct method.</b>
 *        </p>
 */
public interface IConsumer {

	/**
	 * @param mailDTO
	 * @throws MessagingException
	 */
	public void recievedMessage(MailDto mailDTO) throws MessagingException;
}
