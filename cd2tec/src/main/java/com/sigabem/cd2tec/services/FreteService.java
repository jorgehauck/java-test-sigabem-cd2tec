package com.sigabem.cd2tec.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sigabem.cd2tec.model.Cotacao;
import com.sigabem.cd2tec.repositories.CotacaoRepository;

@Service
public class FreteService {

	@Autowired
	private CepService cepService;

	@Autowired
	private CotacaoRepository cotacaoRepository;

	public List<Cotacao> findAllQuotation() {
		List<Cotacao> cotacao = cotacaoRepository.findAll();
		return cotacao;
	}
	
	public Cotacao findByCepOrigem(String cep) {
		Cotacao cotacao = cotacaoRepository.findByCepOrigem(cep);
		return cotacao;
	}
	
	public Cotacao registerQuote(String cepOrigem, String cepDestino, String nomeDestinatario, Double peso)  {

		EnderecoService origem = cepService.findByCEP(cepOrigem); //Objeto responsável em trazer os dados da API
		EnderecoService destino = cepService.findByCEP(cepDestino);
		
		Cotacao cotacao = new Cotacao(null, peso, origem, destino, nomeDestinatario, new Date());
		cotacao.setVlTotalFrete(origem, destino); // Chamada do método para valor do cálculo do frete.
		cotacao.getDataPrevistaEntrega();
		cotacaoRepository.save(cotacao);
		return cotacao;
	}
}
