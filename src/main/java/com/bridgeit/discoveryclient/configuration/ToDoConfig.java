/**
 * 
 */
package com.bridgeit.discoveryclient.configuration;


import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 
 * 
 * @author Chaithra-Shenoy
 * @since Date 18-07-2018 <br>
 *        <p>
 *        <b>ToDoConfiguration class.</b>
 *        </p>
 */
@Configuration
public class ToDoConfig {

	@Bean
	public ModelMapper model() {
		return new ModelMapper();
	}
	/**
	 * @return PasswordEncoder
	 *         <p>
	 *         Method to BCrypt the password
	 *         </p>
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
