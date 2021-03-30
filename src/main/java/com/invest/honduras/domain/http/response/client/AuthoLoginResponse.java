
package com.invest.honduras.domain.http.response.client;

import lombok.*;


@Data
public class AuthoLoginResponse {

	
	private String status;
	private  AuthResponse data;
	private String message;
	
}
