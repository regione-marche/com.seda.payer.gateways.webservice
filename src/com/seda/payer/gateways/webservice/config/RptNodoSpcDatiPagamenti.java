package com.seda.payer.gateways.webservice.config;


	public class RptNodoSpcDatiPagamenti implements java.io.Serializable {
	    
		private java.lang.String itemValorizzato;
		private java.math.BigDecimal importoSingoloPagamento;
		private java.lang.String ibanAccredito;
		private java.lang.String ibanAppoggio;
		private java.math.BigDecimal importoMarcaDaBolloDigitale;
		private java.lang.String segnaturaMarcaDaBolloDigitale;
		private java.lang.String tipoBolloDaErogare;
		private java.lang.String provinciaResidenza;
		//inizio LP PG200060
		private java.lang.String causale;
		private java.lang.String causaleMB;
		//fine LP PG200060
		//inizio LP PG180290
		private java.lang.String tipoDovuto;
		//fine LP PG180290
		//inizio LP PG200360
		private java.lang.String tassonomia;
		//fine LP PG200360
		//inizio LP EP22405X
		private java.lang.String causalePreavvisiBRAV;
		//fine LP EP22405X

	    public java.math.BigDecimal getImportoSingoloPagamento() {
			return importoSingoloPagamento;
		}
		public void setImportoSingoloPagamento(
				java.math.BigDecimal importoSingoloPagamento) {
			this.importoSingoloPagamento = importoSingoloPagamento;
		}
		public java.lang.String getIbanAccredito() {
			return ibanAccredito;
		}
		public void setIbanAccredito(java.lang.String ibanAccredito) {
			this.ibanAccredito = ibanAccredito;
		}
		public java.math.BigDecimal getImportoMarcaDaBolloDigitale() {
			return importoMarcaDaBolloDigitale;
		}
		public void setImportoMarcaDaBolloDigitale(
				java.math.BigDecimal importoMarcaDaBolloDigitale) {
			this.importoMarcaDaBolloDigitale = importoMarcaDaBolloDigitale;
		}
		public java.lang.String getSegnaturaMarcaDaBolloDigitale() {
			return segnaturaMarcaDaBolloDigitale;
		}
		public void setSegnaturaMarcaDaBolloDigitale(
				java.lang.String segnaturaMarcaDaBolloDigitale) {
			this.segnaturaMarcaDaBolloDigitale = segnaturaMarcaDaBolloDigitale;
		}
		public java.lang.String getTipoBolloDaErogare() {
			return tipoBolloDaErogare;
		}
		public void setTipoBolloDaErogare(java.lang.String tipoBolloDaErogare) {
			this.tipoBolloDaErogare = tipoBolloDaErogare;
		}
		public java.lang.String getProvinciaResidenza() {
			return provinciaResidenza;
		}
		public void setProvinciaResidenza(java.lang.String provinciaResidenza) {
			this.provinciaResidenza = provinciaResidenza;
		}
		public java.lang.String getItemValorizzato() {
			return itemValorizzato;
		}
		public void setItemValorizzato(java.lang.String itemValorizzato) {
			this.itemValorizzato = itemValorizzato;
		}
		public java.lang.String getIbanAppoggio() {
			return ibanAppoggio;
		}
		public void setIbanAppoggio(java.lang.String ibanAppoggio) {
			this.ibanAppoggio = ibanAppoggio;
		}

		//inizio LP PG200060
		public java.lang.String getCausale() {
			return causale;
		}
		public void setCausale(java.lang.String causale) {
			this.causale = causale;
		}
		public java.lang.String getCausaleMB() {
			return causaleMB;
		}
		public void setCausaleMB(java.lang.String causaleMB) {
			this.causaleMB = causaleMB;
		}
		//fine LP PG200060
		//inizio LP PG180290
		public java.lang.String getTipoDovuto() {
			return tipoDovuto;
		}
		public void setTipoDovuto(java.lang.String tipoDovuto) {
			this.tipoDovuto = tipoDovuto;
		}
		//fine LP PG180290
		//inizio LP PG200360
		public java.lang.String getTassonomia() {
			return tassonomia;
		}
		public void setTassonomia(java.lang.String tassonomia) {
			this.tassonomia = tassonomia;
		}
		//fine LP PG200360
		//inizio LP EP22405X
		public java.lang.String getCausalePreavvisiBRAV() {
			return causalePreavvisiBRAV;
		}
		public void setCausalePreavvisiBRAV(java.lang.String causalePreavvisiBRAV) {
			this.causalePreavvisiBRAV = causalePreavvisiBRAV;
		}
		//fine LP EP22405X
}
