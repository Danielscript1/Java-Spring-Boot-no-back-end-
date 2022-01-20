package com.testeweb.course.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.testeweb.course.security.JWTAuthenticationFilter;
import com.testeweb.course.security.JWTAuthorizationFilter;
import com.testeweb.course.security.JWTUtil;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) 
public class SecurityConfig extends  WebSecurityConfigurerAdapter  {
	@Autowired
	private Environment env;
	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private JWTUtil jwtUtil;
	//Configuração de liberar acesso, aos endponts
	private static final String[] PUBLIC_MATCHERS = {
			"/h2-console/**",
			
	};
	
	//criar outro vetor , com os caminhos somente de leitura, para usuario sem permissão de autenticação
	private static final String[] PUBLIC_MATCHERS_GET = {
			"/produtos/**",
			"/categorias/**",
			"/estados/**"
		
	};
	
	//criar outro vetor , com os caminhos para usuario se cadastrar
		private static final String[] PUBLIC_MATCHERS_POST = {
				"/clientes/",
				"/auth/forgot/**"
			
		};
		
	
	//metodo de configuração de liberação
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		//se eu tiver algum profile ativo libero acesso ao meu h2,pq ele tem um particularidade
		if(Arrays.asList(env.getActiveProfiles()).contains("prod")) {
			http.headers().frameOptions().disable();
		}
		//liberando acessos
		http.cors().and().csrf().disable();
		http.authorizeRequests()
			.antMatchers(HttpMethod.POST, PUBLIC_MATCHERS_POST).permitAll()//somente para leitura
			.antMatchers(HttpMethod.GET,PUBLIC_MATCHERS_GET).permitAll()//somente para leitura
			.antMatchers(PUBLIC_MATCHERS).permitAll() //acessando h2
			.anyRequest().authenticated();//demais somente permissão
			http.addFilter(new JWTAuthenticationFilter(authenticationManager(), jwtUtil));
			http.addFilter(new JWTAuthorizationFilter(authenticationManager(), jwtUtil, userDetailsService));
			http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);//segurar que nossa aplicação, não ira criar seção de usuario
	}
	
	
	//Em SecurityConfig, sobrescrever o método: public void configure(AuthenticationManagerBuilder auth) 
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
	}
	
	
	//liberar o acesso a meu endpoits , com configuraçoes basicas
		@Bean
		CorsConfigurationSource corsConfiguration() {
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
		return source;
		
	}
		
		//criando o algoritmo de criptografia de senha,instanciando o meu algoritmo
		@Bean
		public BCryptPasswordEncoder bCryptPasswordEncoder() {
			return new BCryptPasswordEncoder();
		}
}
