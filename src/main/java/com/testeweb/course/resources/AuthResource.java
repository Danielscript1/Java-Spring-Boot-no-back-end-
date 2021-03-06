package com.testeweb.course.resources;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.testeweb.course.dto.EmailDTO;
import com.testeweb.course.security.JWTUtil;
import com.testeweb.course.security.UserSS;
import com.testeweb.course.services.AuthService;
import com.testeweb.course.services.UserService;

@RestController
@RequestMapping(value="/auth")
public class AuthResource {
	/*refresh token , renovação automatica quando o token de acesso esta perto de expirar
	 * 
	 * */
	@Autowired
	private JWTUtil jwtUtil;
	
	@Autowired
	private AuthService service;
	
	@RequestMapping(value="/refresh_token", method=RequestMethod.POST) 
	public ResponseEntity<Void> refreshToken(HttpServletResponse response) { 
	UserSS user = UserService.authenticated(); 
	String token = jwtUtil.generateToken(user.getUsername()); 
	response.addHeader("Authorization", "Bearer " + token);
	response.addHeader("access-control-expose-headers", "Authorization");
	return ResponseEntity.noContent().build(); 
	} 
	
	//RECEBENDO A RECUEPRACAO DE SENHA
	@RequestMapping(value = "/forgot", method = RequestMethod.POST)
	public ResponseEntity<Void> forgot(@Valid @RequestBody EmailDTO objDto) {
		service.sendNewPassword(objDto.getEmail());
		return ResponseEntity.noContent().build();
	}
}
