/**
 * 
 */
package com.bridgeit.discoveryclient.userservice.service;

import java.util.List;
import java.util.Optional;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.bridgeit.discoveryclient.userservice.model.PasswordDto;
import com.bridgeit.discoveryclient.userservice.model.RegisterDto;
import com.bridgeit.discoveryclient.userservice.model.ResponseDto;
import com.bridgeit.discoveryclient.userservice.model.User;
import com.bridgeit.discoveryclient.utilityservice.exception.ToDoException;

/**
 * 
 * 
 * @author Chaithra-Shenoy
 * @since Date 10-07-2018 <br>
 *        <p>
 *        <b>IUserService interface between IUserRepository interface and User
 *        controller class.</b>
 *        </p>
 */
@Service
public interface IUserService {

	/**
	 * @param registerDTO
	 * @param uri
	 * @throws MessagingException
	 * @throws ToDoException
	 * 
	 *             <p>
	 *             Register the user by passing user related information. Checks
	 *             user email is not repeating, if user passes same email then it
	 *             will throw an exception.otherwise stored the details in database.
	 *             </p>
	 */
	String registerUser(RegisterDto registerDTO, String uri) throws MessagingException, ToDoException;

	/**
	 * @param loginDTO
	 * @param uri
	 * @throws ToDoException
	 * @throws MessagingException
	 * 
	 *             <p>
	 *             Login the user by passing user email and valid password, if user
	 *             enters invalid details then it throws exception.
	 *             </p>
	 */
	String loginUser(RegisterDto loginDTO, String uri) throws ToDoException, MessagingException;

	/**
	 * @param token
	 * @throws ToDoException
	 *             <p>
	 *             Activation status is settrue in database, if user is active based
	 *             on the user token generated.otherwise the user is not an active
	 *             user.
	 *             </p>
	 */
	public void setActivationStatus(String token) throws ToDoException;

	/**
	 * @param resetPasswordDTO
	 * @param token
	 * @throws ToDoException
	 * 
	 *             <p>
	 *             Reset existing password by passing new password and confirm
	 *             password, here new password and confirm password matches then it
	 *             will reset the password of the particular user who passes the
	 *             valid token,otherwise exception occurs.
	 *             </p>
	 */
	public void resetPassword(PasswordDto resetPasswordDTO, String token) throws ToDoException;

	/**
	 * @param emailId
	 * @param uri
	 * @throws MessagingException
	 * 
	 *             <p>
	 *             Forgot password to get the token to reset the password. By
	 *             passing valid email it generates a token and that token is sent
	 *             to user mail then user need to pass that token to reset password
	 *             to change existing password.
	 *             </p>
	 * @throws ToDoException
	 */
	public void forgotPassword(String emailId, String uri) throws MessagingException, ToDoException;

	/**
	 * @return List
	 *
	 *             <p>
	 *             This method is used to display all the user present in database.
	 *             </p>
	 */
	List<?> getUser();

	/**
	 * @param email
	 * @return Optional
	 * @throws ToDoException
	 *             <p>
	 *             This method returns particular user details corresponding to the
	 *             provided email.
	 *             </p>
	 */
	List<?> findUserByEmail(String email) throws ToDoException;

}
