package com.testeweb.course.services;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.testeweb.course.domain.Cliente;
import com.testeweb.course.repositories.ClienteRepository;
import com.testeweb.course.services.exception.ObjectNotFoundException;

@Service
public class AuthService {
	/*esquecir a senha
	 *   Criar a classe AuthService com a operação sendNewPassword(String email) 
		  Em EmailService, acrescentar a operação sendNewPasswordEmail(Cliente cliente, String newPass) 
		  Implementar a operação em AbstractEmailService 
		  Em AuthResource, criar o endpoint /auth/forgot (POST) 
		  Definir o endpoint /auth/forgot como público 
	 * */
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private BCryptPasswordEncoder pe;
	@Autowired
	private EmailService emailService;
	
	private Random rand = new Random();
	
	public void sendNewPassword(String email) {
		//verifcar se esse cliente existe
		//vou buscar o cliente pelo email
		Cliente cliente = clienteRepository.findByEmail(email);
		//teste de verifcação
		if(cliente == null) {
			throw new ObjectNotFoundException("Email não encontrado!");
		}
		
		//processo de gerar senha
		String newPass = newPassword();
		cliente.setSenha(pe.encode(newPass));
		//salva no banco a nova senha
		clienteRepository.save(cliente);
		//enviar o email para cliente
		emailService.sendNewPasswordEmail(cliente, newPass);
		
	}
	//metodo que vai gerar uma senha aleatoria
	private String newPassword() {
		char[] vet = new char[10];
		for(int i=0;i<10;i++) {
			vet[i] = randomChar();//essa funcao vai gerar caractere aleatorios
		}
		return new String(vet);
	}
	
	private char randomChar() {
		int opt = rand.nextInt(3);
		if(opt == 0) {//gerar um digito
			return (char) (rand.nextInt(10)+48);
		}else if(opt == 1) {//gerar letra maiuscula
			return (char) (rand.nextInt(26)+65);
		}else { //gerar letra minuscula
			return (char) (rand.nextInt(26)+97);
		}
	}
	
}
