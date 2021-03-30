package com.invest.honduras.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.invest.honduras.business.NotifyMailBusiness;
import com.invest.honduras.domain.http.request.ItemUserNotifyRequest;
import com.invest.honduras.domain.http.request.UserNotifyRequest;
import com.invest.honduras.service.NotifyService;

 
@Service
public class NotifyServiceImpl implements NotifyService{

	@Autowired
	NotifyMailBusiness notifyMailBusiness;	

	@Override
	public String sendMail(ItemUserNotifyRequest userInvitationRequest) {
		return notifyMailBusiness.sendMail(userInvitationRequest);	
	}

	@Override
	public String sendUserMailToRol(UserNotifyRequest userNotifyRequest) {
		return notifyMailBusiness.sendUserMailToRol(userNotifyRequest);
	}

}
