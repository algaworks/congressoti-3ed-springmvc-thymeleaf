package com.algaworks.cti.imobiliaria.repository.filter;

import java.math.BigDecimal;

import com.algaworks.cti.imobiliaria.model.TipoImovel;

public class ImovelFilter {

	private String bairro;
	private BigDecimal valorInicial;
	private BigDecimal valorFinal;
	private TipoImovel tipo;

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public BigDecimal getValorInicial() {
		return valorInicial;
	}

	public void setValorInicial(BigDecimal valorInicial) {
		this.valorInicial = valorInicial;
	}

	public BigDecimal getValorFinal() {
		return valorFinal;
	}

	public void setValorFinal(BigDecimal valorFinal) {
		this.valorFinal = valorFinal;
	}

	public TipoImovel getTipo() {
		return tipo;
	}

	public void setTipo(TipoImovel tipo) {
		this.tipo = tipo;
	}

}
