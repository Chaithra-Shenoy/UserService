/**
 * 
 */
package com.bridgeit.discoveryclient.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Predicate;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import static springfox.documentation.builders.PathSelectors.regex;

import static com.google.common.base.Predicates.or;

/**
 * 
 * 
 * @author Chaithra-Shenoy
 * @since Date 10-07-2018 <br>
 *        <p>
 *        <b>Swagger Configuration Class .</b>
 *        </p>
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

	/**
	 * @return Docket
	 */
	@Bean
	public Docket postsApi() {
		return new Docket(DocumentationType.SWAGGER_2).groupName("Aurtorization").apiInfo(apiInfo()).select()
				.paths(postPaths()).build();
	}

	/**
	 * Using the RequestHandlerSelectors and PathSelectors we configure the
	 * predicates for selection of RequestHandlers.
	 * 
	 * @return Predicate
	 */
	private Predicate<String> postPaths() {
		return or(regex("/.*"), regex("/.*"));
	}

	/**
	 * @return ApiInfo
	 */
	@SuppressWarnings("deprecation")
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Discover Client-User")
				.description("USER SERVICE USING SPRING BOOT AND MONGODB").termsOfServiceUrl("http://javainuse.com")
				.contact("chaithrashenoy08@gmail.com").version("1.0").build();
	}
	
}
