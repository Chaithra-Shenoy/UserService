/**
 * 
 */
package com.bridgeit.discoveryclient.utilityservice;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.bridgeit.discoveryclient.userservice.model.ResponseDto;
import com.bridgeit.discoveryclient.utilityservice.exception.ToDoException;


/**
 * 
 * 
 * @author Chaithra-Shenoy
 * @since Date 18-07-2018 <br>
 *        <p>
 *        <b>Global exception handler class.</b>
 *        </p>
 */
@ControllerAdvice
public class GlobalExceptionHandler {

	private final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	/**
	 * @param exception
	 * @return ResponseEntity
	 * 
	 *         <p>
	 *         Handles ToDoExceptions occurred, If Exception occurs it displays
	 *         respective messege and status
	 *         </p>
	 */
	@ExceptionHandler(ToDoException.class)
	public ResponseEntity<ResponseDto> handleTodoException(ToDoException exception) {
		logger.error("Error occured for: " + exception.getMessage(), exception);
		ResponseDto response = new ResponseDto();
		response.setMessage(exception.getMessage());
		response.setStatus(404);
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

	/**
	 * @param exception
	 * @param request
	 * @return ResponseEntity
	 * 
	 *         <p>
	 *         Handles Exception occurred, If Exception occurs it displays
	 *         respective messege and status
	 *         </p>
	 * 
	 */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ResponseDto> handleException(Exception exception, HttpServletRequest request) {
		logger.error("Error occured for: " + exception.getMessage(), exception);
		ResponseDto response = new ResponseDto();
		response.setMessage(exception.getMessage());
		response.setStatus(404);
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}
}
