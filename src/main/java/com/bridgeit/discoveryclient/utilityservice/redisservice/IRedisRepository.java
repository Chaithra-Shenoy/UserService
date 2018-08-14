/**
 * 
 */
package com.bridgeit.discoveryclient.utilityservice.redisservice;

import org.springframework.stereotype.Repository;

/**
 * 
 * 
 * @author Chaithra-Shenoy
 * @since Date 10-07-2018 <br>
 *        <p>
 *        <b>POJO Class having User related information and method.</b>
 *        </p>
 */

public interface IRedisRepository {

	/**
	 * @param token
	 */
	void setToken(String token);
	/**
	 * @param userId
	 * @return
	 */
	public String getToken(String userId);
	/**
	 * @param userId
	 */
	public void deleteToken(String userId) ;
}
