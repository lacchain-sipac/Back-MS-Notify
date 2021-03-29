package com.invest.honduras.domain.http.response;

import com.invest.honduras.http.HttpResponse;

public class UserMailNotifyResponse extends HttpResponse<NotifyResponse>{

	public UserMailNotifyResponse(String status, NotifyResponse data, String message) {
		super(status, data, message);
		// TODO Auto-generated constructor stub
	}		
}
