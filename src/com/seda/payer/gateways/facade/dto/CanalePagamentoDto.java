package com.seda.payer.gateways.facade.dto;

import java.io.Serializable;

import com.seda.payer.core.bean.CanalePagamento;

public class CanalePagamentoDto implements Serializable {

	private static final long serialVersionUID = 1L;
	private java.lang.String chiaveCanalePagamento;
	private java.lang.String descrizioneCanalePagamento;
	private java.lang.String codiceOperatore;

    public CanalePagamentoDto() { }

    public CanalePagamentoDto(CanalePagamento object) {
    	if (object == null)
    		return;

    	this.chiaveCanalePagamento = object.getChiaveCanalePagamento();
    	this.descrizioneCanalePagamento = object.getDescrizioneCanalePagamento();
    	this.codiceOperatore = object.getCodiceOperatore();
    }

	public java.lang.String getChiaveCanalePagamento() {
		return chiaveCanalePagamento;
	}

	public void setChiaveCanalePagamento(java.lang.String chiaveCanalePagamento) {
		this.chiaveCanalePagamento = chiaveCanalePagamento;
	}

	public java.lang.String getDescrizioneCanalePagamento() {
		return descrizioneCanalePagamento;
	}

	public void setDescrizioneCanalePagamento(
			java.lang.String descrizioneCanalePagamento) {
		this.descrizioneCanalePagamento = descrizioneCanalePagamento;
	}

	public java.lang.String getCodiceOperatore() {
		return codiceOperatore;
	}

	public void setCodiceOperatore(java.lang.String codiceOperatore) {
		this.codiceOperatore = codiceOperatore;
	}

    

}