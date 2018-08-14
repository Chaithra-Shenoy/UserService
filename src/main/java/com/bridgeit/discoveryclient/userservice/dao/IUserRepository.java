
package com.bridgeit.discoveryclient.userservice.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.bridgeit.discoveryclient.userservice.model.User;


/**
 * 
 * 
 * @author Chaithra-Shenoy
 * @since Date 10-07-2018 <br>
 *        <p>
 *        <b>IUserRepository interface implements MongoRepository class having
 *        two abstract methods.</b>
 *        </p>
 */
@Repository
public interface IUserRepository extends MongoRepository<User, String> {

	/**
	 * @param emailId
	 * @return Optional
	 *         <p>
	 *         This method return User object corresponding to email. If user object
	 *         is present in DataBase it will return the same Object Otherwise
	 *         Optional Class Reference for null information.
	 *         </p>
	 */
	@Query("{'email': ?0}")
	public Optional<User> findByEmail(String email);

	/**
	 * @param user
	 *            <p>
	 *            Save user details to database.
	 *            </p>
	 */
	public void save(Optional<User> user);
	
	
	public List<?> findUserByEmail(String email);

}
