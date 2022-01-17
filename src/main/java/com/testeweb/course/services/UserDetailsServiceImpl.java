package com.testeweb.course.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.testeweb.course.domain.Cliente;
import com.testeweb.course.repositories.ClienteRepository;
import com.testeweb.course.security.UserSS;
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	/*---processo de autenticação---
	 *    Criar classe de usuário conforme contrato do Spring Security (implements UserDetails) = UserSS ->contem os metodos de autenticação que vão trabalhar encima dece usuario
		  Criar classe de serviço conforme contrato do Spring Security (implements UserDetailsService) = UserDetailsService ->permite a busca pelo nome do usuario
		  Em SecurityConfig, sobrescrever o método: public void configure(AuthenticationManagerBuilder auth) 
		  Criar a classe CredenciaisDTO (usuário e senha) 
		  Incluir as propriedades de JWT (segredo e tempo de expiração) em application.properties  
		  Criar uma classe JWTUtil (@Component) com a operação String generateToken(String username) 
		  Criar um filtro de autenticação 
		  Em SecurityConfig, registrar o filtro de autenticação
	 * 
	 * */
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Cliente cli = clienteRepository.findByEmail(email);
		if(cli == null ) {
			throw new UsernameNotFoundException(email);
		}
		return new UserSS(cli.getId(), cli.getEmail(), cli.getSenha(), cli.getPerfis()); //depois eu faço uma instancia na classe, retonando a ela
	}
	
	
}
