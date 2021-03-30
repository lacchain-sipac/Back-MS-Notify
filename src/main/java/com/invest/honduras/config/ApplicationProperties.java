package com.invest.honduras.config;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import lombok.Data;

@Component
@Data
@ConfigurationProperties(prefix = "application", ignoreUnknownFields = false)
public class ApplicationProperties  implements InitializingBean  {

	@Value("${spring.mail.host}")
    private String mailHost;
    
	@Value("${spring.mail.username}")
    private String mailUsername;
    
	@Value("${spring.mail.password}")
    private String mailPassword;
    
	@Value("${spring.mail.port}")
    private int mailPort;
	
	@Value("${spring.mail.from}")
    private String mailFrom;

	@Value("${spring.redis.host}")
    private String redisHost;

	@Value("${spring.redis.port}")
    private int redisPort;
	
	@Value("${notify.url.completepassword}")
    private String urlCompletePassword;

	@Value("${notify.url.updateuser}")
    private String urlUpdateUser;

	@Value("${notify.url.updatestateuser}")
    private String urlUpdateStateUser;

	@Value("${notify.url.forgetpassword}")
    private String urlForgetPassword;

	@Value("${notify.url.invitationuser}")
    private String urlInvitationUser;
	@Value("${notify.url.changepassword}")
    private String urlChangePassword;
	
	@Value("${notify.url.attemptPassword}")
    private String urlAttemptPassword;

	@Value("${notify.config.auth}")
    private boolean auth;

	@Value("${notify.config.tls}")
    private boolean tls;
	
	@Value("${image.honduras}") 
    private String logo; 

	@Value("${image.bid}") 
    private String logoBid; 

	
	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub
		/////Se puede listar errores
	}

}
