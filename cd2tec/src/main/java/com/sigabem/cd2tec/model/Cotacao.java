package com.sigabem.cd2tec.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sigabem.cd2tec.services.EnderecoService;

@Entity
@Table(name = "TB_COTACAO")
public class Cotacao implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore
	private Long id;
	private Double peso;
	private String cepOrigem;
	private String cepDestino;
	private String nomeDestinatario;
	private Double vlTotalFrete;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date dataPrevistaEntrega;
	public static final Double TAXA_FRETE = 1.0;

	// @DateTimeFormat(pattern = "dd/MM/yyyy")
	// private Instant dataPrevistaEntrega;
	String dataConsulta = new SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().getTime());

	public Cotacao() {
	}

	public Cotacao(Long id, Double peso, EnderecoService cepOrigem, EnderecoService cepDestino,
			String nomeDestinatario, Date dataPrevistaEntrega) {
		this.id = id;
		this.peso = peso;
		this.cepOrigem = cepOrigem.getCep();
		this.cepDestino = cepDestino.getCep();
		this.nomeDestinatario = nomeDestinatario;
		this.dataPrevistaEntrega = dataPrevistaEntrega;
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getPeso() {
		return peso;
	}

	public void setPeso(Double peso) {
		this.peso = peso;
	}

	public String getCepOrigem() {
		return cepOrigem;
	}

	public void setCepOrigem(String cepOrigem) {
		this.cepOrigem = cepOrigem;
	}

	public String getCepDestino() {
		return cepDestino;
	}

	public void setCepDestino(String cepDestino) {
		this.cepDestino = cepDestino;
	}

	public String getNomeDestinatario() {
		return nomeDestinatario;
	}

	public void setNomeDestinatario(String nomeDestinatario) {
		this.nomeDestinatario = nomeDestinatario;
	}

	public String getDataConsulta() {
		return dataConsulta;
	}

	public Double getVlTotalFrete() {
		return vlTotalFrete;
	}
	
	public Date getDataPrevistaEntrega() {
		return dataPrevistaEntrega;
	}

	public void setVlTotalFrete(EnderecoService enderecoOrigem, EnderecoService enderecoDestino) {
		compareDDD(enderecoOrigem, enderecoDestino);
		compareUf(enderecoOrigem, enderecoDestino);

	}

	private void compareDDD(EnderecoService enderecoOrigem, EnderecoService enderecoDestino) {
		Double frete;
		Double varAux = peso * TAXA_FRETE;
		String dddOrigem = enderecoOrigem.getDdd();
		String dddDestino = enderecoDestino.getDdd();
		
		
		Calendar cal = Calendar.getInstance();
		
		if (dddOrigem.equals(dddDestino)) {
			cal.add(Calendar.DATE, 1);
			dataPrevistaEntrega = cal.getTime();
			frete = varAux * 0.50;
			vlTotalFrete = frete;
		}

		else {
			vlTotalFrete = varAux;
			cal.add(Calendar.DATE, 10);
			dataPrevistaEntrega = cal.getTime();
		}
	}

	private void compareUf(EnderecoService enderecoOrigem, EnderecoService enderecoDestino) {
		Double frete;
		Double varAux = peso * TAXA_FRETE;
		String ufOrigem = enderecoOrigem.getUf();
		String ufDestino = enderecoDestino.getUf();

		
		Calendar cal = Calendar.getInstance();
		
		if (ufOrigem.equals(ufDestino) && !(enderecoOrigem.getDdd().equals(enderecoDestino.getDdd()))) {
			cal.add(Calendar.DATE, 3);
			dataPrevistaEntrega = cal.getTime();
			frete = varAux * 0.75;
			vlTotalFrete = frete;
		}
	}
}
