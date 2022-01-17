package com.testeweb.course.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JWTUtil {
	/* --autenticação
	 *   Criar classe de usuário conforme contrato do Spring Security (implements UserDetails) 
		  Criar classe de serviço conforme contrato do Spring Security (implements UserDetailsService) 
		  Em SecurityConfig, sobrescrever o método: public void configure(AuthenticationManagerBuilder auth) 
		  Criar a classe CredenciaisDTO (usuário e senha) 
		  Incluir as propriedades de JWT (segredo e tempo de expiração) em application.properties  
		  Criar uma classe JWTUtil (@Component) com a operação String generateToken(String username),para gerar meu token
		  Criar um filtro de autenticação 
		  Em SecurityConfig, registrar o filtro de autenticação  
	 * */
	
	@Value("${jwt.secret}")
	private String secret;
	@Value("${jwt.expiration}")
	private Long Expiration;
	
	//metodo para gerar o token,para enviar ao usuario
	public String generateToken(String username) {
		return Jwts.builder()//gerar o token
			   .setSubject(username) //inserir o usuario	
			   .setExpiration(new Date(System.currentTimeMillis() + Expiration ))//tempo de expiração
			   .signWith(SignatureAlgorithm.HS512,secret.getBytes()) //para dizer como vou assinar meu token, passando o meu algoritmo,palavra secreta, para embaralhar junto ao token
			   .compact();
	}

	public boolean tokenValido(String token) {
		//claims armazena as reenvidcaçoes do token,esta alegando que ele é usuario tal e tempo de expiração e tal
		Claims claims = getClaims(token);
		if(claims != null) {
			//se esse claims, for diferente nulo,entao pego
			String username = claims.getSubject();
			//isso vai servir para testar se esse token esta expirado
			//pego adata de expiracao aprtir do claim
			Date expirationDate = claims.getExpiration();
			//criar uma data atual
			Date now = new Date(System.currentTimeMillis());
			//teste de verificação para saber se meu token não esta expirado
			if(username != null && expirationDate !=null && now.before(expirationDate)) {
				return true;
			}
			
		}
		return false;
	}
	//funçaõ que recupera os claims apartir de um token
	private Claims getClaims(String token) {
		try {
		return Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(token).getBody();
		}catch(Exception e) {
			return null;
		}
	}
	//pegar o susuario aprtir do token
	public String getUsername(String token) {
		Claims claims = getClaims(token);
		if(claims != null) {
		return claims.getSubject();
		}
		return null;
	}
}
