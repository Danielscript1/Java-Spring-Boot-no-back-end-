package com.testeweb.course.resources;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.testeweb.course.security.JWTUtil;
import com.testeweb.course.security.UserSS;
import com.testeweb.course.services.UserService;

@RestController
@RequestMapping(value="/auth")
public class AuthResource {
	/*refresh token , renovação automatica quando o token de acesso esta perto de expirar
	 * 
	 * */
	@Autowired
	private JWTUtil jwtUtil;
	
	@RequestMapping(value="/refresh_token", method=RequestMethod.POST) 
	public ResponseEntity<Void> refreshToken(HttpServletResponse response) { 
	UserSS user = UserService.authenticated(); 
	String token = jwtUtil.generateToken(user.getUsername()); 
	response.addHeader("Authorization", "Bearer " + token); 
	return ResponseEntity.noContent().build(); 
	} 
}
