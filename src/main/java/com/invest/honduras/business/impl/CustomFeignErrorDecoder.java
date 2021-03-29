package com.invest.honduras.business.impl;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class CustomFeignErrorDecoder implements ErrorDecoder {
        
    @Override
    public Exception decode(String methodKey, Response response) {
 
       
        switch (response.status()){
            case 400:
                     log.error("Status code " + response.status() + ", methodKey = " + methodKey);
            case 404:
            {
                     log.error("Error  Status code " + response.status() + ", methodKey = " + methodKey);                     
                   return new ResponseStatusException(HttpStatus.valueOf(response.status()), "not found user for generate token "); 
            }
            default:
                return new Exception(response.reason());
        } 
    }

}
