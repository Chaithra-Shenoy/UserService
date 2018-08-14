/**
 * 
 */
package com.bridgeit.discoveryclient.userservice.controller;

import java.util.List;
import java.util.Optional;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bridgeit.discoveryclient.userservice.model.PasswordDto;
import com.bridgeit.discoveryclient.userservice.model.RegisterDto;
import com.bridgeit.discoveryclient.userservice.model.ResponseDto;
import com.bridgeit.discoveryclient.userservice.model.User;
import com.bridgeit.discoveryclient.userservice.service.IUserService;
import com.bridgeit.discoveryclient.utilityservice.exception.ToDoException;

/**
 * 
 * 
 * @author Chaithra-Shenoy
 * @since Date 10-07-2018 <br>
 *        <p>
 *        <b>Controller Class Controls the flow of execution of program.</b>
 *        </p>
 */
@RestController
@RequestMapping("/user")
@RefreshScope
public class UserController {

	public static final Logger logger = LoggerFactory.getLogger(UserController.class);
	@Value(value = "${spring.mail.username}")
	private String mailid;

	@Autowired
	private IUserService userService;

	final String REQ_ID = "IN_USER";
	final String RES_ID = "OUT_USER";

	ResponseDto response = new ResponseDto();

	/**
	 * @param registerDTO
	 * @param request
	 * @return ResponseEntity
	 * @throws ToDoException
	 * @throws MessagingException
	 * 
	 *             <p>
	 *             Regesters a new user and stored user details in database
	 *             </p>
	 */
	@PostMapping("/register")
	public ResponseEntity<ResponseDto> registerUser(@RequestBody RegisterDto registerDTO, HttpServletRequest request) {
		logger.info("Creating User ");
		logger.info(REQ_ID + " " + registerDTO.getFirstName());
		String token;

		try {
			token = userService.registerUser(registerDTO, request.getRequestURI());
		} catch (MessagingException | ToDoException e) {
			logger.error(e.getMessage());
			response.setMessage(e.getMessage());
			response.setStatus(404);
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}
		response.setMessage("Registered successfully and Your token is ::" + token);
		response.setStatus(200);
		logger.info("Reponse message ", response);
		logger.info(RES_ID + " " + registerDTO.getFirstName());
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	/**
	 * @param loginDTO
	 * @param request
	 * @return ResponseEntity
	 * @throws ToDoException
	 * @throws MessagingException
	 *             <p>
	 *             User Login Method to login a regestered user.
	 *             </p>
	 */
	@PostMapping("/login")
	public ResponseEntity<?> loginUser(@RequestBody RegisterDto loginDTO, HttpServletRequest request,
			HttpServletResponse resp) {
		logger.info("login  User");
		logger.info(REQ_ID + " " + loginDTO.getEmail());
		String token = null;
		try {
			token = userService.loginUser(loginDTO, request.getRequestURI());
			resp.setHeader("token", token);
		} catch (ToDoException | MessagingException e) {
			logger.error("Login Unsuccessfull");
			response.setMessage(e.getMessage());
			response.setStatus(404);
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}

		response.setMessage("Login successfully done, And Token is :: " + token);
		response.setStatus(200);

		logger.info("Response message:", response);
		logger.info(RES_ID + " " + loginDTO.getEmail());
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	/**
	 * @param token
	 * @return ResponseEntity
	 * @throws ToDoException
	 * 
	 *             <p>
	 *             To Activate the Regestered user, once the user is activated his
	 *             status is changed to active user
	 *             </p>
	 */
	@GetMapping("/activation")
	public ResponseEntity<ResponseDto> activationUser(@RequestParam("token") String token) {
		logger.info("check the user activation");
		try {
			userService.setActivationStatus(token);
		} catch (ToDoException e) {
			logger.error("User activated Unsuccessfully");
			response.setMessage(e.getMessage());
			response.setStatus(404);
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}
		response.setMessage("User activated successfully");
		response.setStatus(200);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	/**
	 * @param emailId
	 * @param request
	 * @return ResponseEntityREQ_ID
	 * @throws ToDoException
	 * @throws TodoException
	 * @throws MessagingException
	 *             <p>
	 *             Forgot password to get the token to reset the password. By
	 *             passing valid email it generates a token and that token is sent
	 *             to user mail then user need to pass that token to reset password
	 *             to change existing password.
	 *             </p>
	 * 
	 */
	@PostMapping("/forgotPassword")
	public ResponseEntity<ResponseDto> forgotPassword(@RequestBody String emailId, HttpServletRequest request)
			throws ToDoException {
		logger.info(REQ_ID + " " + emailId);
		logger.info("Reset the password");
		logger.info(mailid);

		try {
			userService.forgotPassword(emailId, request.getRequestURI());
		} catch (MessagingException e) {
			logger.error("Invalid Password Exception");
			response.setMessage(e.getMessage());
			response.setStatus(404);
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}
		response.setMessage("send the mail to userid to reset password");
		response.setStatus(200);
		logger.info(RES_ID + " " + emailId);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	/**
	 * @param resetPasswordDTO
	 * @param token
	 * @return ResponseEntity
	 * @throws TodoException
	 * 
	 *             <p>
	 *             Reset existing password by passing new password and confirm
	 *             password, here new password and confirm password matches then it
	 *             will reset the password of the particular user who passes the
	 *             valid token,otherwise exception occurs.
	 *             </p>
	 */
	@PostMapping("/resetPassword")
	public ResponseEntity<ResponseDto> resetPassword(@RequestBody PasswordDto resetPasswordDTO,
			@RequestParam("token") String token) {

		try {
			userService.resetPassword(resetPasswordDTO, token);
		} catch (ToDoException e) {
			logger.error("Password Reset Unsuccessfull");
			response.setMessage(e.getMessage());
			response.setStatus(404);
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}
		response.setMessage("reset the password successfully");
		response.setStatus(200);
		logger.info("Reset password done successfully");
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	/**
	 * @return List
	 *         <p>
	 *         This method is used to display all the user present in database.
	 *         </p>
	 */
	@GetMapping("/getalluser")
	public List<?> getUser() {
		List<?> user = null;
		user = userService.getUser();
		response.setMessage("user displayed successfully");
		response.setStatus(200);
		logger.info("user displayed successfully");
		return user;
	}

	/**
	 * @param email
	 * @return Optional
	 * @throws ToDoException
	 *             <p>
	 *             This method returns particular user details corresponding to the
	 *             provided email.
	 *             </p>
	 * 
	 */
	@GetMapping("/getuser/{email}")
	public List<?> getUserByEmail(@PathVariable String email) throws ToDoException {
		List<?> user = null;
		user = userService.findUserByEmail(email);
		response.setMessage("user displayed successfully");
		response.setStatus(200);
		logger.info("user displayed successfully");
		return user;
	}
}
