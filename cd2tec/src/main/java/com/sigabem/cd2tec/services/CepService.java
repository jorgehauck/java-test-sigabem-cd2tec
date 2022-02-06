package com.sigabem.cd2tec.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(url = "https://viacep.com.br/ws/", name = "viacep") // Biblioteca Spring Boot para consumo de serviços externos
public interface CepService {
	
	@GetMapping(value = "{cep}/json")
	EnderecoService findByCEP(@PathVariable("cep") String cep);
	
	
}
