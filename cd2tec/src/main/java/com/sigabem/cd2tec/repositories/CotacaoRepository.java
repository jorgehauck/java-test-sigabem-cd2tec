package com.sigabem.cd2tec.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sigabem.cd2tec.model.Cotacao;

@Repository
public interface CotacaoRepository extends JpaRepository<Cotacao, Long> {
	
	Cotacao findByCepOrigem(String cep);
	
}
