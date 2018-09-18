package com.example.med.api.security.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


//Classe pra Gerar Senha Encodadas // Run as Java Application
public class GeradorSenha {

	public static void main(String[] args) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		System.out.println(encoder.encode("bruno"));
	}
	
}