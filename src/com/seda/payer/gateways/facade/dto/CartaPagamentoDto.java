package com.seda.payer.gateways.facade.dto;

import java.io.Serializable;

import com.seda.payer.core.bean.CartaPagamento;

public class CartaPagamentoDto implements Serializable {

	private static final long serialVersionUID = 1L;
	private java.lang.String codiceCartaPagamento;
	private java.lang.String descrizioneCartaPagamento;

    public CartaPagamentoDto() { }

    public CartaPagamentoDto(CartaPagamento object) {
    	if (object == null)
    		return;

    	this.codiceCartaPagamento = object.getCodiceCartaPagamento();
    	this.descrizioneCartaPagamento = object.getDescrizioneCartaPagamento();
    }

	public java.lang.String getCodiceCartaPagamento() {
		return codiceCartaPagamento;
	}

	public void setCodiceCartaPagamento(java.lang.String codiceCartaPagamento) {
		this.codiceCartaPagamento = codiceCartaPagamento;
	}

	public java.lang.String getDescrizioneCartaPagamento() {
		return descrizioneCartaPagamento;
	}

	public void setDescrizioneCartaPagamento(
			java.lang.String descrizioneCartaPagamento) {
		this.descrizioneCartaPagamento = descrizioneCartaPagamento;
	}

}