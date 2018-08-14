/**
 * 
 */
package com.bridgeit.discoveryclient.utilityservice;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

import com.bridgeit.discoveryclient.userservice.model.RegisterDto;
import com.bridgeit.discoveryclient.utilityservice.exception.ToDoException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * 
 * 
 * @author Chaithra-Shenoy
 * @since Date 10-07-2018 <br>
 *        <p>
 *        <b>Utility Class having Validation method to Validate User
 *        information.</b>
 *        </p>
 */
@Component
public class Utility {

	final static String KEY = "chaithra";

	public Utility() {

	}

	/**
	 * @param registerDTO
	 * @throws ToDoException
	 * 
	 *             <p>
	 *             To validate all the user details entered.
	 *             </p>
	 */
	public static void isValidateAllFields(RegisterDto registerDTO) throws ToDoException {
		if (!validateEmailAddress(registerDTO.getEmail())) {
			throw new ToDoException("Invalid Email ");
		} else if (!isValidUserName(registerDTO.getUserName())) {
			throw new ToDoException(" Invalid UserName ");
		} else if (!validatePassword(registerDTO.getPassword())) {
			throw new ToDoException("Invalid password ");
		} else if (!isValidMobileNumber(registerDTO.getMobileNumber())) {
			throw new ToDoException("Invalid mobilenumber ");
		} else if (!isPasswordMatch(registerDTO.getPassword(), registerDTO.getPasswordConfirmation())) {
			throw new ToDoException("password mismatch ");
		}
	}

	/**
	 * @param emailId
	 * @return boolean
	 *         <p>
	 *         Validates email example:abc12@gmail.com
	 *         </p>
	 */
	public static boolean validateEmailAddress(String emailId) {
		Pattern emailNamePtrn = Pattern
				.compile("^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
		Matcher mtch = emailNamePtrn.matcher(emailId);
		return mtch.matches();

	}

	/**
	 * @param password
	 * @return boolean
	 *         <p>
	 *         Validates password. password must contain atleast one digit,one
	 *         lowercase,one uppercase, one special character and must be atleast 8
	 *         character long
	 *         </p>
	 */
	public static boolean validatePassword(String password) {
		Pattern pattern = Pattern.compile("(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}");
		Matcher matcher = pattern.matcher(password);
		return matcher.matches();

	}

	/**
	 * @param userName
	 * @return boolean
	 *         <p>
	 *         Validates userName. userName must contains all lowercase and one
	 *         digit and must be atleast 6 character long.
	 *         </p>
	 */
	public static boolean isValidUserName(String userName) {
		Pattern userNamePattern = Pattern.compile("^[a-z0-9_-]{6,}$");
		Matcher matcher = userNamePattern.matcher(userName);
		return matcher.matches();

	}

	/**
	 * @param mobileNumber
	 * @return boolean
	 *         <p>
	 *         validates Mobile number. Mobile number must contain 10 digits.
	 *         </p>
	 */
	public static boolean isValidMobileNumber(String mobileNumber) {
		Pattern mobileNumberPattern = Pattern.compile("\\d{10}");
		Matcher matcher = mobileNumberPattern.matcher(mobileNumber);
		return matcher.matches();
	}

	/**
	 * @param password
	 * @param confirmPassword
	 * @return boolean
	 *         <p>
	 *         Checks for password matches. it returns true if the password matches
	 *         with confirm password, otherwise returns false.
	 *         </p>
	 */
	public static boolean isPasswordMatch(String password, String confirmPassword) {
		return password.equals(confirmPassword);
	}

	/**
	 * @param loginDTO
	 * @return String
	 *         <p>
	 *         Create a valid token based on user email.
	 *         </p>
	 */
	public String createToken(RegisterDto loginDTO) {
		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
		String subject = loginDTO.getEmail();
		Date date = new Date();

		JwtBuilder builder = Jwts.builder().setSubject(subject).setIssuedAt(date).signWith(signatureAlgorithm, KEY);
		return builder.compact();
	}

	/**
	 * @param id
	 * @return String
	 *         <p>
	 *         Create a valid token based on user id.
	 *         </p>
	 */
	public String createToken(String id) {
		SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
		Date startTime = new Date();
		Date expireTime = new Date(startTime.getTime() + (1000 * 60 * 60 * 24));

		JwtBuilder builder = Jwts.builder().setId(id).setIssuedAt(startTime).setExpiration(expireTime)
				.signWith(signatureAlgorithm, KEY);
		return builder.compact();
	}

	/**
	 * @param jwt
	 * @return Claims
	 *         <p>
	 *         Pasre the jwt by passing String jwt.Based on jwt passed user
	 *         information can be fetched.
	 *         </p>
	 */
	public Claims parseJwt(String jwt) {
		System.out.println(jwt);
		return Jwts.parser().setSigningKey(KEY).parseClaimsJws(jwt).getBody();
	}

}
