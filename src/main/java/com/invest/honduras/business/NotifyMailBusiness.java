package com.invest.honduras.business;

import com.invest.honduras.domain.http.request.ItemUserNotifyRequest;
import com.invest.honduras.domain.http.request.UserNotifyRequest;

public interface NotifyMailBusiness {

		String sendMail(ItemUserNotifyRequest itemUserNotifyRequest);
	   String sendUserMailToRol(UserNotifyRequest userUpdateNotifyRequest) ;
	   
}
