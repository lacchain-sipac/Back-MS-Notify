package com.invest.honduras.domain.http.request;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserNotifyRequest {

	@ApiModelProperty(notes = "email" , example = "email@email.com")
	private String email;
	
	@ApiModelProperty(notes = "nombre del usuario" , example ="Navarrete Guerrero")
	private String fullname;
	
	private List<ItemUserNotifyRequest> userRol;

}
