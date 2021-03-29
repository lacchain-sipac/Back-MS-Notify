package com.invest.honduras.domain.http.request;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ItemUserNotifyRequest {

	@ApiModelProperty(notes = "email" , example = "email@email.com")
	private String email;
	
	@ApiModelProperty(notes = "codigo de usuario" , example = "123456789")
	private String userId;
	
	@ApiModelProperty(notes = "nombre de usuario" , example = "Navarrete Guerrero")
	private String name;
	
	@ApiModelProperty(notes = "observacion" , example = "observacion")
	private String obs;
	
	@ApiModelProperty(notes = "tipo de notificacion" , example = "INVITATION_USER")
	private String typeNotify;
	
	@ApiModelProperty(notes = "modificado por " , example = "Navarrete Guerrero")
	private String modifyBy;
	
	@ApiModelProperty(notes = "Roles del Usuario" , example = "[ROLE_COO_OPERATIVO]")
	
	private List<String> roles;
	
	@ApiModelProperty(notes = "plantilla a usar" , example = "invitationUser")
	private String template;
	
	@ApiModelProperty(notes = "Subject del mensaje" , example = "Invitacion a la aplicacion SIPAC")
	private String subject;
	
	@ApiModelProperty(notes = "detalle de url" , example = "https://sipac.kaytrust.id/security/complete-password" )
	private String detailsURL;
}