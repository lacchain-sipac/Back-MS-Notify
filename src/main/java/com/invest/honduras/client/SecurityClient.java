package com.invest.honduras.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.invest.honduras.domain.http.request.client.AutoLoginRequest;
import com.invest.honduras.domain.http.response.client.AuthoLoginResponse;

@FeignClient(name="posts", url="${client.ms-api-gateway}")
public interface SecurityClient {

    @RequestMapping(method = RequestMethod.POST, value = "api/v1/auth/auto-login")
    AuthoLoginResponse getToken(AutoLoginRequest autoLogin);
 
   
}
