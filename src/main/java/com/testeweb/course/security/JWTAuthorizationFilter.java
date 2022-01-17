package com.testeweb.course.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter{
	/*    parte de autorização
	 *	  Criar um filtro de autorização .
		  Acrescentar uma função em JWTUtil para verificar se um dado token é válido 
		  Em SecurityConfig, registrar o filtro de autorização  
	 * */
	
private JWTUtil jwtUtil;
	
	private UserDetailsService userDetailsService;
	
	public JWTAuthorizationFilter(AuthenticationManager authenticationManager, JWTUtil jwtUtil, UserDetailsService userDetailsService) {
		super(authenticationManager);
		this.jwtUtil = jwtUtil;
		this.userDetailsService = userDetailsService;
	}
	
	//verificar se o token é valido,//para intercipa, e verificar se esse cara esta autorizado
	@Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws IOException, ServletException {
		
		//pegar o valor do cabeçalho que veio na requisição
		String header = request.getHeader("Authorization");
		if(header !=null && header.startsWith("Bearer ")) {
			//criar um metodo que vou passar o token pra ele, ele vai me retorna algum elemento desse tipo definido ai
			UsernamePasswordAuthenticationToken auth = getAuthetication( header.substring(7));
			//depois que eu construir esse meu metodo,aprtit do token eu tenho que testar ele
			if(auth != null) {
				//chamo a função para liebar meu acesso
				SecurityContextHolder.getContext().setAuthentication(auth);
			}
		}
		//depois que eu passar esse verificar , ele vai as requisiçoes normalmente
		chain.doFilter(request, response);
	}

	private UsernamePasswordAuthenticationToken getAuthetication( String token) {
		//verificação de token valido
		if(jwtUtil.tokenValido(token)) {
			String username = jwtUtil.getUsername(token);//pegar username dentro do token
			//buscar no banco de dados esse cara aqui
			UserDetails user = userDetailsService.loadUserByUsername(username);
			return new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
		}
		return null;
	}
	
	
	
	
}
