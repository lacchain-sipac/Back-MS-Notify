package com.invest.honduras.business.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.invest.honduras.business.NotifyMailBusiness;
import com.invest.honduras.client.RedisClient;
import com.invest.honduras.client.SecurityClient;
import com.invest.honduras.config.ApplicationProperties;
import com.invest.honduras.domain.http.request.ItemUserNotifyRequest;
import com.invest.honduras.domain.http.request.UserNotifyRequest;
import com.invest.honduras.domain.http.request.client.AutoLoginRequest;
import com.invest.honduras.domain.http.response.client.AuthoLoginResponse;
import com.invest.honduras.util.Constant;
import com.invest.honduras.util.Util;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class NotifyMailBusinessImpl implements NotifyMailBusiness {

	@Autowired
	RedisClient redisClient;

	@Autowired
	SendMailComponent sendMailComponent;

	@Autowired	
	SecurityClient securityClient;
	
	@Autowired
	ApplicationProperties applicationProperties;

	
	@Override
	public String sendMail(ItemUserNotifyRequest userToNotifyRequest) {
		String message = "";
		Map<String, Object> mapNotify = new HashMap<String, Object>();
		mapNotify = preparedMail(userToNotifyRequest);

		log.info("userToNotifyRequest.getEmail() ==> " + userToNotifyRequest.getEmail());
		log.info("template ==> " + mapNotify.get("template").toString());

		boolean status = sendMailComponent.sendMail(mapNotify, userToNotifyRequest.getEmail(),
				mapNotify.get("template").toString());

		log.info("status ==> " + status);

		if (status) {
			message = "ok";
			if (Constant.TYPE_FORGET_PASSWORD.equals(userToNotifyRequest.getTypeNotify())
					|| Constant.TYPE_INVITATION_USER.equals(userToNotifyRequest.getTypeNotify())
					|| Constant.TYPE_UPDATE_USER.equals(userToNotifyRequest.getTypeNotify())
					|| Constant.TYPE_STATE_UPDATE_USER.equals(userToNotifyRequest.getTypeNotify())) {

				message = "";
				String uuid = Util.generateRandomUuid();
				String keyValue = Constant.TOKEN_MAIL + userToNotifyRequest.getUserId();
				if (!redisClient.existSession(keyValue)) {
					redisClient.setValueSession(keyValue, uuid);

				}
				message = "ok";
			}
		}
		return message;
	}

	public Map<String, Object> preparedMail(ItemUserNotifyRequest userToNotifyRequest) {

		
		AuthoLoginResponse authoLoginResponse = new AuthoLoginResponse();
		
		AutoLoginRequest autoLogin = new AutoLoginRequest();
		String token = null;

		
		Map<String, Object> mapNotify = new HashMap<String, Object>();
		List<String> values;

		log.info("getTypeNotify ==> " + userToNotifyRequest.getTypeNotify());

		switch (userToNotifyRequest.getTypeNotify()) {
		case Constant.TYPE_INVITATION_USER:

			autoLogin.setAccess_token(userToNotifyRequest.getUserId());
			authoLoginResponse = securityClient.getToken(autoLogin);
			 token = authoLoginResponse.getData().getToken();

			values = userToNotifyRequest.getRoles();
			mapNotify.put("variableRole", values);
			mapNotify.put("name", userToNotifyRequest.getName());			
			mapNotify.put("state", token);
			mapNotify.put("template", "invitationUser");
			mapNotify.put("subject", Constant.SUBJECT_TYPE_INVITATION_USER);
			mapNotify.put("detailsURL", applicationProperties.getUrlInvitationUser());
			break;

		case Constant.TYPE_UPDATE_USER:
			values = userToNotifyRequest.getRoles();
			
			//mapNotify.put("state", userToNotifyRequest.getUserId().trim());
			
			mapNotify.put("name", userToNotifyRequest.getName());
			mapNotify.put("modifyBy", userToNotifyRequest.getModifyBy());
			mapNotify.put("template", "updateUser");
			mapNotify.put("subject", Constant.SUBJECT_TYPE_UPDATE_USER);
			mapNotify.put("detailsURL", applicationProperties.getUrlUpdateUser());
			break;
		case Constant.TYPE_STATE_UPDATE_USER:
			values = userToNotifyRequest.getRoles();
			
			//mapNotify.put("state", userToNotifyRequest.getUserId().trim());
			mapNotify.put("name", userToNotifyRequest.getName());
			mapNotify.put("modifyBy", userToNotifyRequest.getModifyBy());
			mapNotify.put("variableRole", values);
			mapNotify.put("template", "updateUserState");
			mapNotify.put("obs", userToNotifyRequest.getObs());
			mapNotify.put("subject", Constant.SUBJECT_TYPE_UPDATE_USER);
			mapNotify.put("detailsURL", applicationProperties.getUrlUpdateUser());
			break;

		case Constant.TYPE_FORGET_PASSWORD:

			autoLogin.setAccess_token(userToNotifyRequest.getUserId());
			authoLoginResponse = securityClient.getToken(autoLogin);
			token = authoLoginResponse.getData().getToken();

			values = userToNotifyRequest.getRoles();
			mapNotify.put("state", token);
			mapNotify.put("variableRole", values);
			mapNotify.put("template", "forgetPassword");
			mapNotify.put("subject", Constant.SUBJECT_TYPE_FORGET_PASSWORD);
			mapNotify.put("detailsURL", applicationProperties.getUrlForgetPassword());
			break;

		case Constant.TYPE_CHANGE_PASSWORD:
			
			//mapNotify.put("state", userToNotifyRequest.getUserId().trim());
			mapNotify.put("name", userToNotifyRequest.getName());
			mapNotify.put("template", "changePassword");
			mapNotify.put("subject", Constant.SUBJECT_TYPE_CHANGE_PASSWORD);
			mapNotify.put("detailsURL", applicationProperties.getUrlChangePassword());
			break;
		default:


			autoLogin.setAccess_token(userToNotifyRequest.getUserId());
			authoLoginResponse = securityClient.getToken(autoLogin);
			token = authoLoginResponse.getData().getToken();

			log.info("template ==>" + userToNotifyRequest.getTemplate());
			mapNotify.put("state", token);
			mapNotify.put("name", userToNotifyRequest.getName());
			mapNotify.put("obs", userToNotifyRequest.getObs());
			mapNotify.put("template", userToNotifyRequest.getTemplate());
			mapNotify.put("subject", userToNotifyRequest.getSubject());
			mapNotify.put("detailsURL", userToNotifyRequest.getDetailsURL());
			break;
		}
		return mapNotify;
	}

	@Override
	public String sendUserMailToRol(UserNotifyRequest userMailNotifyRequest) {
		String message = "";

		Map<String, Object> mapNotify = new HashMap<String, Object>();

		mapNotify.put("usermodifyfullname", userMailNotifyRequest.getFullname());
		mapNotify.put("usermodifymail", userMailNotifyRequest.getEmail());
		List<ItemUserNotifyRequest> listuseRole = new ArrayList<ItemUserNotifyRequest>();

		listuseRole = userMailNotifyRequest.getUserRol();

		listuseRole.forEach(user -> {
			try {
				mapNotify.put("rolemail", user.getEmail());
				mapNotify.put("roleusername", user.getName());
				//mapNotify.put("state", user.getUserId().trim());

				if (user.getTypeNotify().equals(Constant.TYPE_COMPLETE_PASSWORD)) {
					mapNotify.put("subject", Constant.SUBJECT_TYPE_COMPLETE_PASSWORD);

					mapNotify.put("detailsURL", applicationProperties.getUrlCompletePassword());
					mapNotify.put("template", "completePassword");

				} else {
					mapNotify.put("detailsURL", applicationProperties.getUrlAttemptPassword());
					mapNotify.put("subject", Constant.SUBJECT_TYPE_ATTEMPT_PASSWORD);
					mapNotify.put("template", "attemptPassword");

				}

				boolean status = false;
				status = sendMailComponent.sendMail(mapNotify, user.getEmail(), mapNotify.get("template").toString());
				if (status) {
					String uuid = Util.generateRandomUuid();
					String keyValue = Constant.TOKEN_MAIL + user.getUserId();
					if (!redisClient.existSession(keyValue)) {
						redisClient.setValueSession(keyValue, uuid);

					}

				}
			} catch (Exception e) {
				log.error("Error.sendCompletePasswordUserMail", e);
			}
		});

		message = "ok";

		return message;
	}

}
