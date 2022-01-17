package com.testeweb.course.security;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.testeweb.course.domain.enums.Perfil;

public class UserSS implements UserDetails {

	/*---processo de autenticação---
	 *    Criar classe de usuário conforme contrato do Spring Security (implements UserDetails) = UserSS ->contem os metodos de autenticação que vão trabalhar encima dece usuario
		  Criar classe de serviço conforme contrato do Spring Security (implements UserDetailsService) ->permite a busca pelo nome do usuario
		  Em SecurityConfig, sobrescrever o método: public void configure(AuthenticationManagerBuilder auth) 
		  Criar a classe CredenciaisDTO (usuário e senha) 
		  Incluir as propriedades de JWT (segredo e tempo de expiração) em application.properties  
		  Criar uma classe JWTUtil (@Component) com a operação String generateToken(String username) 
		  Criar um filtro de autenticação 
		  Em SecurityConfig, registrar o filtro de autenticação
	 * 
	 * */
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String email;
	private String senha;
	private Collection<? extends GrantedAuthority> authorities;
	
	
	
	public UserSS() {
		
	}

	public UserSS(Long id, String email, String senha, Set<Perfil> perfis) {
	
		this.id = id;
		this.email = email;
		this.senha = senha;
		//macete converter meu tipo perfil, para perfis de usuario do springSecurity
		this.authorities = perfis.stream().map(x -> new SimpleGrantedAuthority(x.getDescricao())).collect(Collectors.toList());
	}

	public Long getId() {
		return id;
	}
	
	@Override //perfis de usuario
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		return authorities;
	}

	@Override
	public String getPassword() {
		
		return senha;
	}

	@Override
	public String getUsername() {
		
		return email;
	}
	//minha conta não esta expirada
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	//conta não esta liberada
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}
	//as credencias não esta exprirada
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	//usuario esta ativado
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	
	
	
}
