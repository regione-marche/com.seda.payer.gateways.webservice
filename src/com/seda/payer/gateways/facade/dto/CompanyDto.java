package com.seda.payer.gateways.facade.dto;

import java.io.Serializable;

import com.seda.payer.core.bean.Company;

public class CompanyDto implements Serializable {

	private static final long serialVersionUID = 1L;
	private java.lang.String companyCode;
	private java.lang.String companyDescription;
	private java.lang.String operatorCode;

    public CompanyDto() { }

    public CompanyDto(Company object) {
    	if (object == null)
    		return;

    	this.companyCode = object.getCompanyCode();
    	this.companyDescription = object.getCompanyDescription();
    	this.operatorCode = object.getOperatorCode();
    }

	public java.lang.String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(java.lang.String companyCode) {
		this.companyCode = companyCode;
	}

	public java.lang.String getCompanyDescription() {
		return companyDescription;
	}

	public void setCompanyDescription(java.lang.String companyDescription) {
		this.companyDescription = companyDescription;
	}

	public java.lang.String getOperatorCode() {
		return operatorCode;
	}

	public void setOperatorCode(java.lang.String operatorCode) {
		this.operatorCode = operatorCode;
	}
}