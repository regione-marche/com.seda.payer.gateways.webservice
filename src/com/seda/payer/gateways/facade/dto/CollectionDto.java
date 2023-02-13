package com.seda.payer.gateways.facade.dto;

import java.io.Serializable;
import java.util.List;
import com.seda.data.spi.PageInfo;

public class CollectionDto implements Serializable {

	private static final long serialVersionUID = 1L;
	private List list;
    private String listXml;
    private PageInfo pageInfo;

    public CollectionDto() { 
    	pageInfo = new PageInfo();
    }
    
    public CollectionDto(String listXml, PageInfo pageInfo) {
    	this.listXml = listXml;
    	this.pageInfo = pageInfo;
    }

	public CollectionDto(List list, PageInfo pageInfo) {
    	this.list = list;
    	this.pageInfo = pageInfo;
    }

	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

	public String getListXml() {
		return listXml;
	}

	public void setListXml(String listXml) {
		this.listXml = listXml;
	}

	public PageInfo getPageInfo() {
		return pageInfo;
	}

	public void setPageInfo(PageInfo pageInfo) {
		this.pageInfo = pageInfo;
	}
}