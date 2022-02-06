package com.sigabem.cd2tec.request;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sigabem.cd2tec.model.Cotacao;
import com.sigabem.cd2tec.services.FreteService;

@RestController
@RequestMapping(value = "/sigabem")
public class CepRequests {
	
	@Autowired
	private FreteService freteService;
	
	@GetMapping
	public ResponseEntity<List<Cotacao>> findAll() {
		
		List<Cotacao> list = freteService.findAllQuotation();
		
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{cep}")
	public ResponseEntity<Cotacao> findByCEP(@PathVariable String cep) {
		
		Cotacao cotacao = freteService.findByCepOrigem(cep);
		
		if(cotacao != null) {
			return ResponseEntity.status(HttpStatus.OK).body(cotacao);
		}
		
		else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(cotacao);
		}
		
	}
	@GetMapping(value = "/buscar")
	public ResponseEntity<Cotacao> findByQuotation(
			@RequestParam String cepOrigem, 
			@RequestParam String cepDestino, 
			@RequestParam String nomeDestinatario, 
			@RequestParam Double peso)  {
		Cotacao cotacao = freteService.registerQuote(cepOrigem, cepDestino, nomeDestinatario, peso);
		return ResponseEntity.status(HttpStatus.OK).body(cotacao);
	}
}
