package com.invest.honduras.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.invest.honduras.domain.http.request.ItemUserNotifyRequest;
import com.invest.honduras.domain.http.request.UserNotifyRequest;
import com.invest.honduras.domain.http.response.NotifyResponse;
import com.invest.honduras.domain.http.response.UserMailNotifyResponse;
import com.invest.honduras.service.NotifyService;
import com.invest.honduras.util.Constant;

@RestController()
@RequestMapping("/api/v1/notify")

public class NotifyController implements ApiNotifyController {

	@Autowired
	NotifyService notifyService;

	
	@PostMapping(value = "/send-mail", produces = { MediaType.APPLICATION_STREAM_JSON_VALUE,
			MediaType.APPLICATION_JSON_VALUE })	
	@Override
	public ResponseEntity<UserMailNotifyResponse> sendMail(ItemUserNotifyRequest itemUserNotifyRequest) {
		
		String statusMail = "";		
		statusMail = notifyService.sendMail(itemUserNotifyRequest);		
		if("ok".equals(statusMail)) {
			NotifyResponse smsResponse = new NotifyResponse();
			
			smsResponse.setStatus(statusMail);
			NotifyResponse notifyResponse = new NotifyResponse();
			notifyResponse.setStatus("OK");
			
			UserMailNotifyResponse  userInvitationResponse =
					new UserMailNotifyResponse(
							Constant.HTTP_STATUS_OK,notifyResponse,smsResponse.getStatus());
			return ResponseEntity.ok(userInvitationResponse);
		}		
		return new ResponseEntity<UserMailNotifyResponse>(HttpStatus.BAD_REQUEST);

	}

	@PostMapping(value = "/send-notify", produces = { MediaType.APPLICATION_STREAM_JSON_VALUE,
			MediaType.APPLICATION_JSON_VALUE })	
	@Override
	public ResponseEntity<UserMailNotifyResponse> sendMail(UserNotifyRequest userNotifyRequest) {
		String statusMail = "";
		
		statusMail = notifyService.sendUserMailToRol(userNotifyRequest);		
		if("ok".equals(statusMail)) {
			NotifyResponse smsResponse = new NotifyResponse();
			NotifyResponse notifyResponse = new NotifyResponse();
			notifyResponse.setStatus("OK");			
			smsResponse.setStatus(statusMail);
			UserMailNotifyResponse  userInvitationResponse =
					new UserMailNotifyResponse(
							Constant.HTTP_STATUS_OK,notifyResponse,smsResponse.getStatus());
			return ResponseEntity.ok(userInvitationResponse);
		}		
		return new ResponseEntity<UserMailNotifyResponse>(HttpStatus.BAD_REQUEST);


	}
	
	
	
}
