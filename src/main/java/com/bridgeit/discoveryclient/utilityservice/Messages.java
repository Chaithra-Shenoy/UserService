/**
 * 
 */
package com.bridgeit.discoveryclient.utilityservice;

import java.util.Locale;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Component;

/**
 * 
 * 
 * @author Chaithra-Shenoy
 * @since Date 10-07-2018 <br>
 *        <p>
 *        <b>POJO Class having User related information and method.</b>
 *        </p>
 */
@Component
public class Messages {


    @Autowired
    private MessageSource messageSource;

   
    private MessageSourceAccessor accessor;

    @PostConstruct
    private void init() {
        accessor = new MessageSourceAccessor(messageSource, Locale.ENGLISH);
    }
  
    public String get(String code) {
        return accessor.getMessage(code);
    }
}
