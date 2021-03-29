package com.invest.honduras.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import com.invest.honduras.domain.http.request.ItemUserNotifyRequest;
import com.invest.honduras.domain.http.request.UserNotifyRequest;
import com.invest.honduras.domain.http.response.UserMailNotifyResponse;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

public interface ApiNotifyController {

    @ApiOperation(
            value = "Interfaz API para enviar un correo a un determinado usuario de la aplicacion SIPAC",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, response = UserMailNotifyResponse.class,
            httpMethod = "POST" 
            )
    
    @ApiResponses(value = {    		
   		 @ApiResponse(code = 200, message = "Exito al enviar el correo" , response = UserMailNotifyResponse.class ),
            @ApiResponse(code = 401, message = "No autorizado para obtener el recurso"),
            @ApiResponse(code = 403, message ="El acceso al recurso esta prohibido"),
            @ApiResponse(code = 404, message = " El recurso no pudo ser encontrado")
   }
   		)
    
    ResponseEntity<UserMailNotifyResponse> sendMail(@RequestBody ItemUserNotifyRequest itemUserNotifyRequest) ;


    @ApiOperation(
            value = "Interfaz API para enviar un correo a un grupo de determinados usuarios de la aplicacion SIPAC",
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, response = UserMailNotifyResponse.class,
            httpMethod = "POST" 
            )
    @ApiResponses(value = {    		
      		 @ApiResponse(code = 200, message = "Exito al enviar el correo" , response = UserMailNotifyResponse.class ),
               @ApiResponse(code = 401, message = "No autorizado para obtener el recurso"),
               @ApiResponse(code = 403, message ="El acceso al recurso esta prohibido"),
               @ApiResponse(code = 404, message = " El recurso no pudo ser encontrado")
      }
      		)
    
    ResponseEntity<UserMailNotifyResponse> sendMail(@RequestBody UserNotifyRequest userNotifyRequest) ;
    
}