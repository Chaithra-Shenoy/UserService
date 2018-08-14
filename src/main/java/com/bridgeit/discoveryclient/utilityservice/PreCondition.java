/**
 * 
 */
package com.bridgeit.discoveryclient.utilityservice;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import com.bridgeit.discoveryclient.utilityservice.exception.ToDoException;


/**
 * 
 * 
 * @author Chaithra-Shenoy
 * @since Date 10-07-2018 <br>
 *        <p>
 *        <b>PreCondition class having methods to check Exceptions</b>
 *        </p>
 */
@Component
public class PreCondition {
	private final static Logger logger = LoggerFactory.getLogger(PreCondition.class);

	PreCondition() {
	}

	/**
	 * @param reference
	 * @param errorMessage
	 * @return reference
	 * @throws ToDoException
	 * 
	 *             <p>
	 *             To check whether the reference is null or not. if null it throws
	 *             exception else it returns the same reference.
	 */
	public  <T> T checkNotNull(T reference, @Nullable Object errorMessage) throws ToDoException {
		if (reference==""||reference==null) {
			logger.error(String.valueOf(errorMessage));
			throw new ToDoException(String.valueOf(errorMessage));
		}
		return reference;
	}

	/**
	 * @param existsById
	 * @param string
	 * @throws ToDoException
	 *             <p>
	 *             To check the given argument is present or not, if present it
	 *             returns same argument otherwise it throws Exception.
	 *             </p>
	 */
	public <T> boolean checkArgument(boolean argument, @Nullable Object errorMessage) throws ToDoException {
		if (!argument) {
			logger.error(String.valueOf(errorMessage));
			throw new ToDoException(String.valueOf(errorMessage));
		}
		return argument;
	}

	/**
	 * @param <T>
	 * @param findByEmailId
	 * @param errorMessage
	 * @return 
	 * @throws ToDoException 
	 */
	public  <T> Optional<T> checkArgument(Optional<T> argument, String errorMessage) throws ToDoException {
		if (!argument.isPresent()) {
			logger.error(String.valueOf(errorMessage));
			throw new ToDoException(String.valueOf(errorMessage));
		}
		return argument;
	}

}
