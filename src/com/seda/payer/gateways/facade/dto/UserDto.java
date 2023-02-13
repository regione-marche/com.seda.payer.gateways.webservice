package com.seda.payer.gateways.facade.dto;

import java.io.Serializable;
import java.sql.SQLException;

import com.seda.payer.commons.transform.TransformersIf;
import com.seda.payer.core.bean.Company;
import com.seda.payer.core.bean.User;

public class UserDto implements Serializable, TransformersIf {

	private static final long serialVersionUID = 1L;
    private java.lang.String userCode;
    private java.lang.String scopeCncCode;
    private java.lang.String userDescription;
    private java.lang.String accountNumber;
    private java.lang.String accountHolder;
    private java.lang.String flagCheckRangeAbi;
    private java.lang.String emailFrom;
    private java.lang.String emailCcn;
    private java.lang.String emailTo;
    private java.lang.String descrMittente;


	private java.lang.String attachFlag;
    private java.lang.String operatorCode;
    private CompanyDto company;

    public UserDto() { 
    	company = new CompanyDto();
    }

    public UserDto(User object) throws SQLException {
    	if (object == null)
    		return;

        userCode = object.getUserCode();
        scopeCncCode = object.getScopeCncCode();
        userDescription = object.getUserDescription();
//        accountNumber = object.getAccountNumber();
//        accountHolder = object.getAccountHolder();
//        flagCheckRangeAbi = object.getFlagCheckRangeAbi();
//        emailFrom = object.getEmailFrom();
//        emailCcn = object.getEmailCcn();
//        emailTo = object.getEmailTo();
//        descrMittente = object.getDescrMittente();
//        attachFlag = object.getAttachFlag();
        operatorCode = object.getOperatorCode();
        company = new CompanyDto(object.getCompany());
    }
    
    public java.lang.String getDescrMittente() {
		return descrMittente;
	}

	public void setDescrMittente(java.lang.String descrMittente) {
		this.descrMittente = descrMittente;
	}
	public java.lang.String getUserCode() {
		return userCode;
	}

	public void setUserCode(java.lang.String userCode) {
		this.userCode = userCode;
	}

	public java.lang.String getScopeCncCode() {
		return scopeCncCode;
	}

	public void setScopeCncCode(java.lang.String scopeCncCode) {
		this.scopeCncCode = scopeCncCode;
	}

	public java.lang.String getUserDescription() {
		return userDescription;
	}

	public void setUserDescription(java.lang.String userDescription) {
		this.userDescription = userDescription;
	}

	public java.lang.String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(java.lang.String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public java.lang.String getAccountHolder() {
		return accountHolder;
	}

	public void setAccountHolder(java.lang.String accountHolder) {
		this.accountHolder = accountHolder;
	}

	public java.lang.String getFlagCheckRangeAbi() {
		return flagCheckRangeAbi;
	}

	public void setFlagCheckRangeAbi(java.lang.String flagCheckRangeAbi) {
		this.flagCheckRangeAbi = flagCheckRangeAbi;
	}

	public java.lang.String getEmailFrom() {
		return emailFrom;
	}

	public void setEmailFrom(java.lang.String emailFrom) {
		this.emailFrom = emailFrom;
	}

	public java.lang.String getEmailCcn() {
		return emailCcn;
	}

	public void setEmailCcn(java.lang.String emailCcn) {
		this.emailCcn = emailCcn;
	}

	public java.lang.String getEmailTo() {
		return emailTo;
	}

	public void setEmailTo(java.lang.String emailTo) {
		this.emailTo = emailTo;
	}

	public java.lang.String getAttachFlag() {
		return attachFlag;
	}

	public void setAttachFlag(java.lang.String attachFlag) {
		this.attachFlag = attachFlag;
	}

	public java.lang.String getOperatorCode() {
		return operatorCode;
	}

	public void setOperatorCode(java.lang.String operatorCode) {
		this.operatorCode = operatorCode;
	}

	public CompanyDto getCompany() {
		return company;
	}

	public void setCompany(CompanyDto company) {
		this.company = company;
	}

	/**
	 * @param arg0
	 * @return
	 * @throws Exception
	 */
	public User toBean(Object arg0) throws Exception {
		UserDto object = (UserDto) arg0;
		User out = new User();
		out.setUserCode(object.getUserCode());
		out.setScopeCncCode(object.getScopeCncCode());
		out.setUserDescription(object.getUserDescription());
//		out.setAccountNumber(object.getAccountNumber());
//		out.setAccountHolder(object.getAccountHolder());
//		out.setFlagCheckRangeAbi(object.getFlagCheckRangeAbi());
//		out.setEmailFrom(object.getEmailFrom());
//		out.setEmailCcn(object.getEmailCcn());
//		out.setEmailTo(object.getEmailTo());
//		out.setDescrMittente(object.getDescrMittente());
//		out.setAttachFlag(object.getAttachFlag());
		out.setOperatorCode(object.getOperatorCode());
        Company company = new Company();
        company.setCompanyCode(object.getCompany().getCompanyCode());
		return out;
	}
	
	/* (non-Javadoc)
	 * @see com.seda.payer.commons.transform.TransformersIf#beanToBean(java.lang.Object)
	 */
	public Serializable beanToBean(Object arg0) throws Exception {
		User object = (User) arg0;
        userCode = object.getUserCode();
        scopeCncCode = object.getScopeCncCode();
        userDescription = object.getUserDescription();
//        accountNumber = object.getAccountNumber();
//        accountHolder = object.getAccountHolder();
//        flagCheckRangeAbi = object.getFlagCheckRangeAbi();
//        emailFrom = object.getEmailFrom();
//        emailCcn = object.getEmailCcn();
//        emailTo = object.getEmailTo();
//        attachFlag = object.getAttachFlag();
        operatorCode = object.getOperatorCode();
//        descrMittente = object.getDescrMittente();
        company = new CompanyDto(object.getCompany());
		return this;
	}
}