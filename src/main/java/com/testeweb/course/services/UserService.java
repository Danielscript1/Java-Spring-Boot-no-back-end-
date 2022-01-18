package com.testeweb.course.services;

import org.springframework.security.core.context.SecurityContextHolder;

import com.testeweb.course.security.UserSS;

public class UserService {
	/*funcao que retorna o usuario que estiver autenticado no sistema*/
	public static UserSS authenticated() {
		try {
		return (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		}catch(Exception e) {
			return null;
		}
	}
}
