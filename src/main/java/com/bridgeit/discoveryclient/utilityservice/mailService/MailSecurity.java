/**
 * 
 */
package com.bridgeit.discoveryclient.utilityservice.mailService;

import javax.mail.MessagingException;

import com.bridgeit.discoveryclient.userservice.model.MailDto;


/**
 * 
 * 
 * @author Chaithra-Shenoy
 * @since Date 10-07-2018 <br>
 *        <p>
 *        <b>MailSecurity interface having one abstract method.</b>
 *        </p>
 */
public interface MailSecurity {

	/**
	 * @param mailDTO
	 * @throws MessagingException
	 *             <p>
	 *             To send a mail to a user.
	 *             </p>
	 */
	public void sendEmail(MailDto mailDTO) throws MessagingException;

}
