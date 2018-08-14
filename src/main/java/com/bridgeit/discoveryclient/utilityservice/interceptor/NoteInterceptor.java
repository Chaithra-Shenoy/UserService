/**
 * 
 */
package com.bridgeit.discoveryclient.utilityservice.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.bridgeit.discoveryclient.utilityservice.Utility;
import com.bridgeit.discoveryclient.utilityservice.redisservice.IRedisRepository;
import com.bridgeit.discoveryclient.utilityservice.redisservice.RedisRepositoryImpl;

import io.jsonwebtoken.Claims;



/**
 * 
 * 
 * @author Chaithra-Shenoy
 * @since Date 10-07-2018 <br>
 *        <p>
 *        <b>POJO Class having User related information and method.</b>
 *        </p>
 */
/*@Component
public class NoteInterceptor implements HandlerInterceptor {

	
	public static final Logger logger = LoggerFactory.getLogger(NoteInterceptor.class);
	@Autowired
	IRedisRepository iRedisRepository;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
		iRedisRepository=new RedisRepositoryImpl();
		Utility utility=new Utility();
		
		
		logger.info("IN INTERCEPTOR");
		String token = request.getHeader("token");
		System.out.println("----------"+token);
		
		Claims user_Id = utility.parseJwt(token);
		String userId = user_Id.getId();
		System.out.println(userId);
		
		String tokenFromRedis = iRedisRepository.getToken(userId);
		logger.info(tokenFromRedis);
		
		if(tokenFromRedis==null)
		{
			return false;
		}
		request.setAttribute("userId", userId);
		logger.info("Inceptor work done");
		
		return true;
	}

	}
*/

