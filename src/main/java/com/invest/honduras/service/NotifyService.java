package com.invest.honduras.service;

import com.invest.honduras.domain.http.request.*;

public interface NotifyService {

	public String sendMail(ItemUserNotifyRequest userInvitationRequest);
	
	public String sendUserMailToRol( UserNotifyRequest UserNotifyRequest);
	


}
