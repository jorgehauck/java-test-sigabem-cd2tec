package com.sigabem.cd2tec;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class Cd2tecApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(Cd2tecApplication.class, args);
	}

	//@Autowired
	//private CotacaoRepository cotacaoRepository;
	
	//@Autowired
	//private EnderecoService enderecoService;
	
	@Override
	public void run(String... args) throws Exception {
	
		
	}

}
