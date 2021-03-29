package com.invest.honduras.domain.http.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class NotifyResponse {

	@ApiModelProperty(notes = "mensaje devuelto" , example = "OK")
	
	private String status;
}
