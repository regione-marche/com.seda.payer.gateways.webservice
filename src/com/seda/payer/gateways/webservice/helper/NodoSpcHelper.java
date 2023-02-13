package com.seda.payer.gateways.webservice.helper;

import java.io.StringWriter;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.URL;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.seda.commons.logger.LoggerServer;
import com.seda.commons.properties.tree.PropertiesTree;
import com.seda.payer.commons.utility.DateUtility;
import com.seda.payer.gateways.webservice.config.PropKeys;
import com.seda.payer.gateways.webservice.config.RptNodoSpcDatiPagamenti;
import com.seda.payer.pgec.webservice.commons.dati.InserisciRptNodoSpcRequest;
import com.seda.payer.pgec.webservice.commons.dati.InserisciRptNodoSpcResponse;
import com.seda.payer.pgec.webservice.commons.dati.RptNodoSpc;
import com.seda.payer.pgec.webservice.commons.dati.UpdateRptNodoSpcRequest;
import com.seda.payer.pgec.webservice.commons.dati.UpdateRptNodoSpcResponse;
import com.seda.payer.pgec.webservice.commons.source.CommonsSOAPBindingStub;
import com.seda.payer.pgec.webservice.commons.source.CommonsServiceLocator;

public class NodoSpcHelper {

	public static final String DBSCHEMACODSOCIETA = "dbSchemaCodSocieta";

	public void NodoInviaRPT() {

	}

	public static CommonsSOAPBindingStub getCommonsManager(PropertiesTree env, String dbSchemaCodSocieta) throws Exception {
		// we initialize commons serviceLocator
		CommonsServiceLocator serviceLocator = new CommonsServiceLocator();
		// we initialize commons stub
		CommonsSOAPBindingStub binding = (CommonsSOAPBindingStub)serviceLocator.getCommonsPort(
				new URL(env.getProperty(PropKeys.COMMONS_ENDPOINTURL.format(PropKeys.DEFAULT_NODE.format()))));

		binding.clearHeaders();
		binding.setHeader("",DBSCHEMACODSOCIETA,dbSchemaCodSocieta);	

		return binding;
	}


	private String calculateIUV(int paymentId){
		String res = "";

		String id = String.valueOf(paymentId);
		String checkString = id + "2715" + "00"; //2715 fisso, corrisponde a 'RF' numerico
		long check = Long.valueOf(checkString) % 97;
		check = 98-check;
		res = "RF" + ((check<10)?"0":"") + check + ("0000000000000000000000000000000" + id).substring(id.length());

		return res;
	}


	/**
	 * Scrive riga su tabella PYRPTTB e genera codice IUV con apposito algoritmo basato sul contatore degli iuv 
	 * @param env
	 * @param log
	 * @param dbSchemaCodSocieta
	 * @return
	 * @throws Exception 
	 */
	public static String[] inserisciRPT( PropertiesTree env, LoggerServer log, String dbSchemaCodSocieta, RptNodoSpc rptNodoSpc) throws Exception {
		String[] outCode = new String[2];
		outCode[0] = "";
		outCode[1] = "";
		try {
			CommonsSOAPBindingStub commonsInterface = NodoSpcHelper.getCommonsManager(env, dbSchemaCodSocieta);

			String codSocieta = "";
			String chiaveTra = "";
			String codUtente = "";

			//IuvNodoSpc iuvNodoSpc = new IuvNodoSpc();
			//iuvNodoSpc.setCodSocieta(codSocieta);
			//iuvNodoSpc.setChiaveTra(chiaveTra);
			rptNodoSpc.setCodiceIuv(""); //Verrà autogenerato e restituito nella response;
			//iuvNodoSpc.setCodUtente(codUtente);
			//iuvNodoSpc.setImporto(new BigDecimal(100));
			//iuvNodoSpc.setChiaveTabella("DTD"); //dipende dal tipo di bollettino corrispondente al pagamento
			//iuvNodoSpc.setCodiceTabella("DTD1234"); //chiave del record sulla tabella sopra
			rptNodoSpc.setDataIuv(Calendar.getInstance());


			InserisciRptNodoSpcRequest in = new  InserisciRptNodoSpcRequest(codSocieta, rptNodoSpc );

			InserisciRptNodoSpcResponse out = commonsInterface.inserisciRptNodoSpc(in);
			
			outCode[0] = out.getRptNodoSpc().getId().toString();
			outCode[1] = out.getRptNodoSpc().getCodiceIuv();

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Errore in inserisciRPT con generazione iuv: " + e.getMessage(),e);		//31012017 GG
		}
		return outCode;
	}

	//inizio LP PG210130 Step04
	public static String inserisciRPTMB( PropertiesTree env, LoggerServer log, String dbSchemaCodSocieta, RptNodoSpc rptNodoSpc) throws Exception {
		String outCode = "";
		try {
			CommonsSOAPBindingStub commonsInterface = NodoSpcHelper.getCommonsManager(env, dbSchemaCodSocieta);

			String codSocieta = "";

			//IuvNodoSpc iuvNodoSpc = new IuvNodoSpc();
			//iuvNodoSpc.setCodSocieta(codSocieta);
			//iuvNodoSpc.setChiaveTra(chiaveTra);
			//rptNodoSpc.setCodiceIuv(""); //Vieneimpostato dall'esterno
			//iuvNodoSpc.setCodUtente(codUtente);
			//iuvNodoSpc.setImporto(new BigDecimal(100));
			//iuvNodoSpc.setChiaveTabella("DTD"); //dipende dal tipo di bollettino corrispondente al pagamento
			//iuvNodoSpc.setCodiceTabella("DTD1234"); //chiave del record sulla tabella sopra
			rptNodoSpc.setDataIuv(Calendar.getInstance());

			InserisciRptNodoSpcRequest in = new InserisciRptNodoSpcRequest(codSocieta, rptNodoSpc);

			InserisciRptNodoSpcResponse out = commonsInterface.inserisciRptNodoSpc(in);
			
			outCode = out.getRptNodoSpc().getId().toString();

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Errore in inserisciRPTMB senza generazione iuv: " + e.getMessage(),e);
		}
		return outCode;
	}

	public static void updateRPTMB(PropertiesTree env, LoggerServer log, String dbSchemaCodSocieta, RptNodoSpc rptNodoSpc) throws Exception {
		try {
			CommonsSOAPBindingStub commonsInterface = NodoSpcHelper.getCommonsManager(env, dbSchemaCodSocieta);
			String codSocieta = "";
			UpdateRptNodoSpcRequest in = new UpdateRptNodoSpcRequest(codSocieta, rptNodoSpc);
			commonsInterface.updateRptNodoSpc(in);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Errore in updateRPTMB: " + e.getMessage(),e);
		}
		return;
	}
	//fine LP PG210130 Step04
	
	//PG160230_001 GG 23112016 Introdotto idBollettiniList
//PG170300 - 30/1/2018 - INIZIO - MODIFICATA LA COSTRUZIONE DELL'XML IN QUANTO IL TAG IMPORTO-SINGOLO-PAGAMENTO PUO' ESSERE MULTIPLO
//		public static String generaRPTXml(PropertiesTree env, LoggerServer log, String codSocieta, String identificativoDominio, String identificativoStazioneRichiedente, String cfPagatore,
//				String nomePagatore, String emailPagatore, String cfEnteCreditore, String nomeEnteCreditore, BigDecimal importo, String iuv,
//				String ibanAccredito, String datiSpecificiRiscossione, String tipoVersamento, String codiceContestoPagamento, String idBollettiniList) {
		public static String generaRPTXml(PropertiesTree env, LoggerServer log, String codSocieta, String identificativoDominio, String identificativoStazioneRichiedente, String cfPagatore,
				String nomePagatore, String emailPagatore, String cfEnteCreditore, String nomeEnteCreditore, BigDecimal importo, String iuv,
				String ibanAccredito, String datiSpecificiRiscossione, String tipoVersamento, String codiceContestoPagamento, String idBollettiniList,
				List<RptNodoSpcDatiPagamenti> listRptDatiPagamenti,
//PG180070 - 31/7/2018 - INIZIO - Gestione carrello multiente per PSP
				String anagraficaVersante,
				String identificativoUnivocoVersante,
				String causale, String causaleMB      //PG200060
				) {
//PG170300 - 30/1/2018 - FINE

		//inizio LP PG200360 - 20210212
		String 	datiSpecificiRiscossioneExt = datiSpecificiRiscossione;
		System.out.println("NodoSpcHelper - [riga 135] - datiSpecificiRiscossione: "+datiSpecificiRiscossione);
		//fine LP PG200360 - 20210212
		try {

			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

			// RPT element
			Document doc = docBuilder.newDocument();
			Element rootElement = doc.createElement("RPT");
			rootElement.setAttribute("xmlns", "http://www.digitpa.gov.it/schemas/2011/Pagamenti/");
			doc.appendChild(rootElement);

			Element el = null;
			//PG200060 TODO: su versione RM la versioneOggetto e' sempre "6.2.0"
			// versioneOggetto elements
			String versioneOggetto ="";
			if (anagraficaVersante==null || anagraficaVersante.equals(""))
				versioneOggetto = "1.0";
			else
				versioneOggetto = "6.2.0";
			
			
			Element el_versioneOggetto = doc.createElement("versioneOggetto");
			el_versioneOggetto.setTextContent(versioneOggetto);
			rootElement.appendChild(el_versioneOggetto);

			// dominio elements
			Element dominio = doc.createElement("dominio");
			rootElement.appendChild(dominio);
			// dominio/identificativoDominio elements
			el = doc.createElement("identificativoDominio");
			el.setTextContent(identificativoDominio.trim());  //Codice fiscale della struttura che invia la richiesta di pagamento
			dominio.appendChild(el);
			// dominio/identificativoStazioneRichiedente elements
			el = doc.createElement("identificativoStazioneRichiedente");
			el.setTextContent(identificativoStazioneRichiedente);
			dominio.appendChild(el);

			// identificativoMessaggioRichiesta elements
			el = doc.createElement("identificativoMessaggioRichiesta");
                        //PG200060 TODO: el.setTextContent(DateUtility.frmtTimeByPattern("yyyyMMddHHmmssSSS",Calendar.getInstance().getTime())); //identificativo univoco, utilizzo data ora
			el.setTextContent(DateUtility.frmtTimeByPattern("yyyyMMddHHmmss",Calendar.getInstance().getTime())); //identificativo univoco, utilizzo data ora
			rootElement.appendChild(el);

			// dataOraMessaggioRichiesta elements
			el = doc.createElement("dataOraMessaggioRichiesta");
			el.setTextContent(DateUtility.frmtTimeByPattern("yyyy-MM-dd'T'HH:mm:ss",Calendar.getInstance().getTime())); //ISO 8601
			rootElement.appendChild(el);

			// autenticazioneSoggetto elements
			el = doc.createElement("autenticazioneSoggetto");
			el.setTextContent("N/A"); //CNS; USR; OTH; N/A;
			rootElement.appendChild(el);

			//			// soggettoVersante elements //necessario solo se diverso da soggetto pagante
			//			Element soggettoVersante = doc.createElement("soggettoVersante");
			//			rootElement.appendChild(el);
			
//PG180070 - 31/7/2018 - INIZIO - Gestione carrello multiente per PSP			
			if (anagraficaVersante!=null){
				Element soggettoVersante = doc.createElement("soggettoVersante");
				rootElement.appendChild(soggettoVersante);
				
				Element identificativoUnivocoVersante_ = doc.createElement("identificativoUnivocoVersante");
				soggettoVersante.appendChild(identificativoUnivocoVersante_);
				
				el = doc.createElement("tipoIdentificativoUnivoco");
				if(identificativoUnivocoVersante.matches("\\d+")) //se numerico allora partita iva
					el.setTextContent("G"); //F=persona fisica; G=persona giuridica
				else
					el.setTextContent("F"); //F=persona fisica; G=persona giuridica
				identificativoUnivocoVersante_.appendChild(el);
				
				el = doc.createElement("codiceIdentificativoUnivoco");
				el.setTextContent(identificativoUnivocoVersante.trim()); //CF  o PIVA
				identificativoUnivocoVersante_.appendChild(el);
				
				el = doc.createElement("anagraficaVersante");
				el.setTextContent(anagraficaVersante);
				soggettoVersante.appendChild(el);
				
				// soggettoPagatore/e-mailVersante elements
				el = doc.createElement("e-mailVersante");
				el.setTextContent(emailPagatore);
				soggettoVersante.appendChild(el);
				
			}
//PG180070 - 31/7/2018 - FINE			
			
			// soggettoPagatore elements 
			Element soggettoPagatore = doc.createElement("soggettoPagatore");
			rootElement.appendChild(soggettoPagatore);

			// soggettoPagatore/identificativoUnivocoPagatore elements 
			Element identificativoUnivocoPagatore = doc.createElement("identificativoUnivocoPagatore");
			soggettoPagatore.appendChild(identificativoUnivocoPagatore);

			// soggettoPagatore/identificativoUnivocoPagatore/tipoIdentificativoUnivoco elements
			el = doc.createElement("tipoIdentificativoUnivoco");
			if(cfPagatore.matches("\\d+")) //se numerico allora partita iva
				el.setTextContent("G"); //F=persona fisica; G=persona giuridica
			else
				el.setTextContent("F"); //F=persona fisica; G=persona giuridica
			identificativoUnivocoPagatore.appendChild(el);

			// soggettoPagatore/identificativoUnivocoPagatore/codiceIdentificativoUnivoco elements
			el = doc.createElement("codiceIdentificativoUnivoco");
			//if(cfPagatore.trim().length()==0)
			//	cfPagatore = "PAGAMENTO ANONIMO";// "RSSMRA00A01H501C"; //Fisso per pagamenti fatti dal payer perchè il dato non è gestito dal payer
			el.setTextContent(cfPagatore.trim()); //CF  o PIVA
			identificativoUnivocoPagatore.appendChild(el);

			// soggettoPagatore/anagraficaPagatore elements
			el = doc.createElement("anagraficaPagatore");
			//if(nomePagatore.trim().length()==0)
			//	nomePagatore = "PAGAMENTO ANONIMO Web Payer"; //Fisso per pagamenti fatti dal payer perchè il dato non è gestito dal payer
			el.setTextContent(nomePagatore);
			soggettoPagatore.appendChild(el);
			//PG200060 TODO: su versione RM non viene mai eseguito l'appendChild con "e-mailPagatore"
			if (anagraficaVersante==null || anagraficaVersante.equals("")){
				// soggettoPagatore/e-mailPagatore elements
				el = doc.createElement("e-mailPagatore");
				el.setTextContent(emailPagatore);
				soggettoPagatore.appendChild(el);
				
			}
			
			// enteBeneficiario elements 
			Element enteBeneficiario = doc.createElement("enteBeneficiario");
			rootElement.appendChild(enteBeneficiario);

			// enteBeneficiario/identificativoUnivocoPagatore elements 
			Element identificativoUnivocoBeneficiario = doc.createElement("identificativoUnivocoBeneficiario");
			enteBeneficiario.appendChild(identificativoUnivocoBeneficiario);

			// enteBeneficiario/identificativoUnivocoBeneficiario/tipoIdentificativoUnivoco elements
			el = doc.createElement("tipoIdentificativoUnivoco");
			el.setTextContent("G"); //G=persona giuridica fisso
			identificativoUnivocoBeneficiario.appendChild(el);

			// enteBeneficiario/identificativoUnivocoBeneficiario/codiceIdentificativoUnivoco elements
			el = doc.createElement("codiceIdentificativoUnivoco");
			el.setTextContent(cfEnteCreditore.trim()); //CF ente creditore
			identificativoUnivocoBeneficiario.appendChild(el);


			// enteBeneficiario/denominazioneBeneficiario elements
			el = doc.createElement("denominazioneBeneficiario");
			//30052018 - inizio - Eliminate []
			//el.setTextContent(nomeEnteCreditore.length()>70?nomeEnteCreditore.substring(0, 70):nomeEnteCreditore); //CF ente creditore
			String denominazioneBeneficiario = getDenominazioneBeneficiario(nomeEnteCreditore);
			el.setTextContent(denominazioneBeneficiario.length()>70?denominazioneBeneficiario.substring(0, 70):denominazioneBeneficiario); //CF ente creditore
			//30052018 - fine - Eliminate []
			enteBeneficiario.appendChild(el);


			// datiVersamento elements 
			Element datiVersamento = doc.createElement("datiVersamento");
			rootElement.appendChild(datiVersamento);

			// datiVersamento/dataEsecuzionePagamento elements
			el = doc.createElement("dataEsecuzionePagamento");
			el.setTextContent(DateUtility.frmtTimeByPattern("yyyy-MM-dd",Calendar.getInstance().getTime())); //
			datiVersamento.appendChild(el);

			// datiVersamento/importoTotaleDaVersare elements
			el = doc.createElement("importoTotaleDaVersare");
			DecimalFormat df = new DecimalFormat("0.00");
			df.setDecimalFormatSymbols(new DecimalFormatSymbols(Locale.ENGLISH));
			el.setTextContent(df.format(importo)); //
			datiVersamento.appendChild(el);

			// datiVersamento/tipoVersamento elements
			el = doc.createElement("tipoVersamento");
			//el.setTextContent("BBT"); //BBT; BP; AD; CP; PO;
			el.setTextContent(tipoVersamento); //BBT; BP; AD; CP; PO;
			datiVersamento.appendChild(el);

			// datiVersamento/identificativoUnivocoVersamento elements
			el = doc.createElement("identificativoUnivocoVersamento");
			el.setTextContent(iuv);
			datiVersamento.appendChild(el);

			// datiVersamento/codiceContestoPagamento elements
			el = doc.createElement("codiceContestoPagamento");
			el.setTextContent(codiceContestoPagamento); //"n/a" fisso perchè valorizzato se pagamento attivato da psp 
			datiVersamento.appendChild(el);

			// datiVersamento/ibanAddebito elements
			//			el = doc.createElement("ibanAddebito");
			//			el.setTextContent(""); //Valorizzato solo se tipoVersamento = AD
			//			datiVersamento.appendChild(el);

			// datiVersamento/bicAddebito elements
			//			el = doc.createElement("bicAddebito");
			//			el.setTextContent(""); //Valorizzato solo se tipoVersamento = AD
			//			datiVersamento.appendChild(el);

			// datiVersamento/firmaRicevuta elements
			el = doc.createElement("firmaRicevuta");
			el.setTextContent("0"); //0=non richiesta; 1=CaDes; 3=XaDes; 4=Elettronica Avanzata
			datiVersamento.appendChild(el);

			//1 per ogni bollettino (massimo 5)
			//for (int i = 0; i < 5; i++) {
			// datiVersamento/datiSingoloVersamento elements
//PG170300 - 30/1/2018 - INIZIO - MODIFICATA LA COSTRUZIONE DELL'XML IN QUANTO IL TAG IMPORTO-SINGOLO-PAGAMENTO PUO' ESSERE MULTIPLO
//			Element datiSingoloVersamento = doc.createElement("datiSingoloVersamento");
//			datiVersamento.appendChild(datiSingoloVersamento);
//PG170300 - 30/1/2018 -FINE

			// datiVersamento/datiSingoloVersamento/importoSingoloVersamento elements
//PG170300 - 30/1/2018 - INIZIO - MODIFICATA LA COSTRUZIONE DELL'XML IN QUANTO IL TAG IMPORTO-SINGOLO-PAGAMENTO PUO' ESSERE MULTIPLO
//			el = doc.createElement("importoSingoloVersamento");
//			el.setTextContent(df.format(importo)); 
//			datiSingoloVersamento.appendChild(el);
//
//			// datiVersamento/datiSingoloVersamento/ibanAccredito elements
//			el = doc.createElement("ibanAccredito");
//			el.setTextContent(ibanAccredito); 
//			datiSingoloVersamento.appendChild(el);
//
//			// datiVersamento/datiSingoloVersamento/causaleVersamento elements
//			el = doc.createElement("causaleVersamento");
//			//Causale = /RFS/IUV/Importo
//			/*
//			 * Modificata creazione causaleVersamento come indicato da Santoprete il 25/02/2016
//			String[] sGroup = iuv.split("(?<=\\G.{4})");
//			String gIuv = ""; //iuv raggruppato a 4 cifre
//			for (String gr : sGroup) {
//				gIuv += gr + " ";
//			}
//			gIuv = gIuv.trim();
//			*/
//			String gIuv = iuv;
//			//PG160230_001 GG 23112016 - inizio
//			//el.setTextContent("/RFS/" + gIuv + "/" + df.format(importo)); 
//			String causaleVersamento = "/RFS/" + gIuv + "/" + df.format(importo);
//			if (idBollettiniList!=null) {
//				causaleVersamento = causaleVersamento.concat("/TXT/Pagamento: ".concat(idBollettiniList));
//				if (causaleVersamento.length()>140) 
//					causaleVersamento = causaleVersamento.substring(0,140);
//			}
//			el.setTextContent(causaleVersamento);
//			//PG160230_001 GG 23112016 - fine
//			datiSingoloVersamento.appendChild(el);
//
//			// datiVersamento/datiSingoloVersamento/datiSpecificiRiscossione elements
//			el = doc.createElement("datiSpecificiRiscossione");
//			el.setTextContent(datiSpecificiRiscossione); //<tipo contabilità: 0;1;2;9;>/<codice contabilità>
//			datiSingoloVersamento.appendChild(el);
//PG170300 - 30/1/2018 - FINE

//PG170300 - 30/1/2018 - INIZIO - MODIFICATA LA COSTRUZIONE DELL'XML IN QUANTO IL TAG IMPORTO-SINGOLO-PAGAMENTO PUO' ESSERE MULTIPLO
			
			for (int i=0;i<listRptDatiPagamenti.size();i++){
//				System.out.println("dentro il FOR listRptDatiPagamenti");
//				System.out.println("listRptDatiPagamenti.get(i).getItemValorizzato().equals(S)" +  listRptDatiPagamenti.get(i).getItemValorizzato().equals("S"));
//				System.out.println("listRptDatiPagamenti.get(i).getTipoBolloDaErogare()" + listRptDatiPagamenti.get(i).getTipoBolloDaErogare());
				
				if (listRptDatiPagamenti.get(i).getItemValorizzato()!=null && listRptDatiPagamenti.get(i).getItemValorizzato().equals("S") && 
						(listRptDatiPagamenti.get(i).getImportoSingoloPagamento().compareTo(BigDecimal.ZERO) != 0
						|| (listRptDatiPagamenti.get(i).getTipoBolloDaErogare()!= null && listRptDatiPagamenti.get(i).getImportoMarcaDaBolloDigitale().compareTo(BigDecimal.ZERO) != 0)))
				{
					System.out.println("dentro la IF BOLLO");
					// vangono scaricati solo gli elementi valorizzati che potrebbero NON essere tutti... 
					Element datiSingoloVersamento = doc.createElement("datiSingoloVersamento");
					datiVersamento.appendChild(datiSingoloVersamento);
					
					el = doc.createElement("importoSingoloVersamento");
					if (listRptDatiPagamenti.get(i).getTipoBolloDaErogare()!= null && listRptDatiPagamenti.get(i).getTipoBolloDaErogare().length()>0)
					{
						el.setTextContent(df.format(listRptDatiPagamenti.get(i).getImportoMarcaDaBolloDigitale()));
					}
					else
					{
						el.setTextContent(df.format(listRptDatiPagamenti.get(i).getImportoSingoloPagamento()));
					}
						 
					datiSingoloVersamento.appendChild(el);
					
					// SE TRATTASI DI MARCA DA BOLLO DIGITALE LA STRTTURA CAMBIA...
					if (listRptDatiPagamenti.get(i).getTipoBolloDaErogare()== null || listRptDatiPagamenti.get(i).getTipoBolloDaErogare().length()==0){
						
						
						el = doc.createElement("ibanAccredito");
						el.setTextContent(listRptDatiPagamenti.get(i).getIbanAccredito()); 
						datiSingoloVersamento.appendChild(el);
						
						if (listRptDatiPagamenti.get(i).getIbanAppoggio()!=null &&
							!listRptDatiPagamenti.get(i).getIbanAppoggio().trim().equals("")){
							el = doc.createElement("ibanAppoggio");
							el.setTextContent(listRptDatiPagamenti.get(i).getIbanAppoggio()); 
							datiSingoloVersamento.appendChild(el);
						}
					}
					
					el = doc.createElement("causaleVersamento");
					//String gIuv = iuv;
					String[] sGroup = iuv.split("(?<=\\G.{4})");
					String gIuv = ""; //iuv raggruppato a 4 cifre
					for (String gr : sGroup) {
						gIuv += gr + " ";
					}
					gIuv = gIuv.trim();
					//String causaleVersamento = "/RFS/" + gIuv + "/" + df.format(listRptDatiPagamenti.get(i).getImportoMarcaDaBolloDigitale());
					String causaleVersamento = "";
					//inizio LP EP22405X
					String causaleBRAV = listRptDatiPagamenti.get(i).getCausalePreavvisiBRAV();
					boolean bCausaleBRAV = (causaleBRAV != null && causaleBRAV.trim().length() > 0);
					//fine LP EP22405X
					if (listRptDatiPagamenti.get(i).getTipoBolloDaErogare()!= null && listRptDatiPagamenti.get(i).getTipoBolloDaErogare().length()>0)
					{
						causaleVersamento = "/RFS/" + gIuv + "/" + df.format(listRptDatiPagamenti.get(i).getImportoMarcaDaBolloDigitale());
						//inizio LP EP22405X
						if(!bCausaleBRAV) {
						//fine LP EP22405X
						//inizio LP PG200060
						if(causaleMB!=null && !causaleMB.equals("")){
							causaleVersamento += "/TXT/" + causaleMB;
						}
						//fine LP PG200060
						//inizio LP EP22405X
						}
						//fine LP EP22405X
					}
					else
					{
						causaleVersamento = "/RFS/" + gIuv + "/" + df.format(listRptDatiPagamenti.get(i).getImportoSingoloPagamento());
						//inizio LP EP22405X
						if(!bCausaleBRAV) {
						//fine LP EP22405X
						//inizio LP PG200060
						if(causale!=null && !causale.equals("")){
							causaleVersamento += "/TXT/" + causale;
						}
						//fine LP PG200060
						//inizio LP EP22405X
						}
						//fine LP EP22405X
					}
					//inizio LP EP22405X
					if(bCausaleBRAV) {
						causaleVersamento = causaleVersamento.concat("/TXT/".concat(causaleBRAV));
						if (causaleVersamento.length() > 140) 
							causaleVersamento = causaleVersamento.substring(0, 140);
					} else
					//fine LP EP22405X
					
					if (idBollettiniList!=null) {
						causaleVersamento = causaleVersamento.concat("/TXT/Pagamento: ".concat(idBollettiniList));
						if (causaleVersamento.length()>140) 
							causaleVersamento = causaleVersamento.substring(0,140);
					}
					//inizio LP PG200360
					String tassonomia = listRptDatiPagamenti.get(i).getTassonomia();
					if(tassonomia != null) {
						tassonomia = tassonomia.trim();
						if(tassonomia.length() > 0) {
							//inizio LP PG200360 - 20210212
							//datiSpecificiRiscossione = datiSpecificiRiscossione.replace("<tassonomia>", tassonomia);
							datiSpecificiRiscossione = datiSpecificiRiscossioneExt.replace("<tassonomia>", tassonomia);
							//fine LP PG200360 - 20210212
						} else {
							//inizio LP PG200360 - 20210212
							//NOTA: Non deve succedere
							datiSpecificiRiscossione = datiSpecificiRiscossioneExt;
							//fine LP PG200360 - 20210212
							tassonomia = null; 
						}
					}
					//fine LP PG200360
					//inizio LP PG180290
					String tipoDovuto = listRptDatiPagamenti.get(i).getTipoDovuto();
					if(tipoDovuto != null && tipoDovuto.length() > 0) {
						causaleVersamento += "/" + tipoDovuto;
						//inizio LP PG200360
						if(tassonomia != null) {
							causaleVersamento += "-" + tassonomia;
						}
						//fine LP PG200360
					}
					//fine LP PG180290
					el.setTextContent(causaleVersamento);
					datiSingoloVersamento.appendChild(el);
					
					// datiVersamento/datiSingoloVersamento/datiSpecificiRiscossione elements
					//			el = doc.createElement("pag:datiSpecificiRiscossione");
								el = doc.createElement("datiSpecificiRiscossione");
								el.setTextContent(datiSpecificiRiscossione); //<tipo contabilità: 0;1;2;9;>/<codice contabilità>
								datiSingoloVersamento.appendChild(el);
					
							
					if (listRptDatiPagamenti.get(i).getTipoBolloDaErogare()!= null && listRptDatiPagamenti.get(i).getTipoBolloDaErogare().length()>0){
						
						

						Element datiMarcaBolloDigitale = doc.createElement("datiMarcaBolloDigitale");
						datiSingoloVersamento.appendChild(datiMarcaBolloDigitale);

							el = doc.createElement("tipoBollo");
							el.setTextContent(listRptDatiPagamenti.get(i).getTipoBolloDaErogare()); 
							datiMarcaBolloDigitale.appendChild(el);
						
							el = doc.createElement("hashDocumento");
							el.setTextContent(listRptDatiPagamenti.get(i).getSegnaturaMarcaDaBolloDigitale()); 
							datiMarcaBolloDigitale.appendChild(el);
							
							el = doc.createElement("provinciaResidenza");
							el.setTextContent(listRptDatiPagamenti.get(i).getProvinciaResidenza()); 
							datiMarcaBolloDigitale.appendChild(el);

						
						
					}  
					
					
				}
//fine LP PG1800XX_011
			}

//PG170300 - 30/1/2018 - FINE
			
			//}

			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);

			//StreamResult result = new StreamResult(new File("C:\\file.xml"));
			StringWriter writer = new StringWriter();
			StreamResult result = new StreamResult(writer);

			// Output to console for testing
			// StreamResult result = new StreamResult(System.out);

			transformer.transform(source, result);

			log.debug("RPT XML: \n" + writer.toString());
			System.out.println("RPT XML: \n" + writer.toString());
			return writer.toString();


		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
		}		
		return "";
	}
	//PG160230_001 GG 23112016 Introdotto idBollettiniList
//PG170300 - 30/1/2018 - INIZIO - MODIFICATA LA COSTRUZIONE DELL'XML IN QUANTO IL TAG IMPORTO-SINGOLO-PAGAMENTO PUO' ESSERE MULTIPLO
//	public static String generaRPTXmlPoste(PropertiesTree env, LoggerServer log, String codSocieta, String identificativoDominio, String identificativoStazioneRichiedente, String cfPagatore,
//			String nomePagatore, String emailPagatore, String cfEnteCreditore, String nomeEnteCreditore, BigDecimal importo, String iuv,
//			String ibanAccredito, String datiSpecificiRiscossione, String tipoVersamento, String codiceContestoPagamento, String idBollettiniList) {
	public static String generaRPTXmlPoste(PropertiesTree env, LoggerServer log, String codSocieta, String identificativoDominio, String identificativoStazioneRichiedente, String cfPagatore,
			String nomePagatore, String emailPagatore, String cfEnteCreditore, String nomeEnteCreditore, BigDecimal importo, String iuv,
			String ibanAccredito, String datiSpecificiRiscossione, String tipoVersamento, String codiceContestoPagamento, String idBollettiniList,
			List<RptNodoSpcDatiPagamenti> listRptDatiPagamenti,
			//PG180070 - 31/7/2018 - INIZIO - Gestione carrello multiente per PSP
			String anagraficaVersante,
			String identificativoUnivocoVersante,
			String causale, String causaleMB   //PG200060
			) {
//PG170300 - 30/1/2018 - FINE
		//inizio LP PG200360 - 20210212
		String 	datiSpecificiRiscossioneExt = datiSpecificiRiscossione;
		System.out.println("NodoSpcHelper - [riga 578] - datiSpecificiRiscossione: "+datiSpecificiRiscossione);
		//fine LP PG200360 - 20210212
		try {

			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

			// RPT element
			Document doc = docBuilder.newDocument();
//			Element rootElement = doc.createElement("pag:RPT");
			Element rootElement = doc.createElement("RPT");
//			rootElement.setAttribute("xmlns:pag", "http://www.digitpa.gov.it/schemas/2011/Pagamenti/");
			rootElement.setAttribute("xmlns", "http://www.digitpa.gov.it/schemas/2011/Pagamenti/");
			doc.appendChild(rootElement);

			Element el = null;

			//PG200060 TODO: su versione RM la versioneOggetto e' sempre "6.2.0"
			//versioneOggetto elements
			String versioneOggetto = "";
			if (anagraficaVersante==null || anagraficaVersante.equals(""))
				versioneOggetto = "1.0";
			else
				versioneOggetto = "6.2.0";
			
			
//			Element el_versioneOggetto = doc.createElement("pag:versioneOggetto");
			Element el_versioneOggetto = doc.createElement("versioneOggetto");
			el_versioneOggetto.setTextContent(versioneOggetto);
			rootElement.appendChild(el_versioneOggetto);

			// dominio elements
//			Element dominio = doc.createElement("pag:dominio");
			Element dominio = doc.createElement("dominio");
			rootElement.appendChild(dominio);
			// dominio/identificativoDominio elements
//			el = doc.createElement("pag:identificativoDominio");
			el = doc.createElement("identificativoDominio");
			el.setTextContent(identificativoDominio.trim());  //Codice fiscale della struttura che invia la richiesta di pagamento
			dominio.appendChild(el);
			// dominio/identificativoStazioneRichiedente elements
//			el = doc.createElement("pag:identificativoStazioneRichiedente");
			el = doc.createElement("identificativoStazioneRichiedente");
			el.setTextContent(identificativoStazioneRichiedente);
			dominio.appendChild(el);

			// identificativoMessaggioRichiesta elements
//			el = doc.createElement("pag:identificativoMessaggioRichiesta");
			el = doc.createElement("identificativoMessaggioRichiesta");
			//PG200060 TODO: el.setTextContent(DateUtility.frmtTimeByPattern("yyyyMMddHHmmssSSS",Calendar.getInstance().getTime())); //identificativo univoco, utilizzo data ora
			el.setTextContent(DateUtility.frmtTimeByPattern("yyyyMMddHHmmss",Calendar.getInstance().getTime())); //identificativo univoco, utilizzo data ora
			rootElement.appendChild(el);

			// dataOraMessaggioRichiesta elements
//			el = doc.createElement("pag:dataOraMessaggioRichiesta");
			el = doc.createElement("dataOraMessaggioRichiesta");
			el.setTextContent(DateUtility.frmtTimeByPattern("yyyy-MM-dd'T'HH:mm:ss",Calendar.getInstance().getTime())); //ISO 8601
			rootElement.appendChild(el);

			// autenticazioneSoggetto elements
//			el = doc.createElement("pag:autenticazioneSoggetto");
			el = doc.createElement("autenticazioneSoggetto");
			el.setTextContent("N/A"); //CNS; USR; OTH; N/A;
			rootElement.appendChild(el);

//PG180070 - 31/7/2018 - INIZIO - Gestione carrello multiente per PSP			
			if (anagraficaVersante!=null){
				Element soggettoVersante = doc.createElement("soggettoVersante");
				rootElement.appendChild(soggettoVersante);
				
				Element identificativoUnivocoVersante_ = doc.createElement("identificativoUnivocoVersante");
				soggettoVersante.appendChild(identificativoUnivocoVersante_);
				
				el = doc.createElement("tipoIdentificativoUnivoco");
				if(identificativoUnivocoVersante.matches("\\d+")) //se numerico allora partita iva
					el.setTextContent("G"); //F=persona fisica; G=persona giuridica
				else
					el.setTextContent("F"); //F=persona fisica; G=persona giuridica
				identificativoUnivocoVersante_.appendChild(el);
				
				el = doc.createElement("codiceIdentificativoUnivoco");
				el.setTextContent(identificativoUnivocoVersante.trim()); //CF  o PIVA
				identificativoUnivocoVersante_.appendChild(el);
				
				el = doc.createElement("anagraficaVersante");
				el.setTextContent(anagraficaVersante);
				soggettoVersante.appendChild(el);
				
				// soggettoPagatore/e-mailVersante elements
				el = doc.createElement("e-mailVersante");
				el.setTextContent(emailPagatore);
				soggettoVersante.appendChild(el);
				
				
			}
//PG180070 - 31/7/2018 - FINE			

/*     Dati asteriscati in data 19/11/2015 Poste sembra aver utilizzato, per il modello 1
 *              la versione della RPT utilizzata da tutti gli altri
 *              
			// soggettoVersante elements //necessario solo se diverso da soggetto pagante
			Element soggettoVersante = doc.createElement("pag:soggettoVersante");
			rootElement.appendChild(soggettoVersante);
			// soggettoVersante elements/identificativoUnivocoVersante
			Element identificativoUnivocoVersante = doc.createElement("pag:identificativoUnivocoVersante");
			soggettoVersante.appendChild(identificativoUnivocoVersante);
			// soggettoVersante elements/identificativoUnivocoVersante/tipoIdentificativoUnivoco
			el = doc.createElement("pag:tipoIdentificativoUnivoco");
			el.setTextContent("F"); // fissa
			identificativoUnivocoVersante.appendChild(el);
			
			// soggettoVersante elements/identificativoUnivocoVersante/codiceIdentificativoUnivoco
			el = doc.createElement("pag:codiceIdentificativoUnivoco");
			el.setTextContent(cfEnteCreditore); 
			identificativoUnivocoVersante.appendChild(el);
			
			// anagraficaVersante elements
			el = doc.createElement("pag:anagraficaVersante");
//			el.setTextContent(anagraficaVersante);
			soggettoVersante.appendChild(el);
			
			// indirizzoVersante elements
			el = doc.createElement("pag:indirizzoVersante");
//			el.setTextContent(indirizzoVersante);
			soggettoVersante.appendChild(el);
			
			// civicoVersante elements
			el = doc.createElement("pag:civicoVersante");
//			el.setTextContent(civicoVersante);
			soggettoVersante.appendChild(el);
			
			// capVersante elements
			el = doc.createElement("pag:capVersante");
//			el.setTextContent(capVersante);
			soggettoVersante.appendChild(el);
			// localitaVersante elements
			el = doc.createElement("pag:localitaVersante");
//			el.setTextContent(localitaVersante);
			soggettoVersante.appendChild(el);

			// provinciaVersante elements
			el = doc.createElement("pag:provinciaVersante");
//			el.setTextContent(provinciaVersante);
			soggettoVersante.appendChild(el);

			// nazioneVersante elements
			el = doc.createElement("pag:nazioneVersante");
			el.setTextContent("IT");
			soggettoVersante.appendChild(el);

			// e-mailVersante elements
			el = doc.createElement("pag:e-mailVersante");
//			el.setTextContent(e-mailVersante);
			soggettoVersante.appendChild(el);
*/
			// soggettoPagatore elements 
//			Element soggettoPagatore = doc.createElement("pag:soggettoPagatore");
			Element soggettoPagatore = doc.createElement("soggettoPagatore");
			rootElement.appendChild(soggettoPagatore);

			// soggettoPagatore/identificativoUnivocoPagatore elements 
//			Element identificativoUnivocoPagatore = doc.createElement("pag:identificativoUnivocoPagatore");
			Element identificativoUnivocoPagatore = doc.createElement("identificativoUnivocoPagatore");
			soggettoPagatore.appendChild(identificativoUnivocoPagatore);

			// soggettoPagatore/identificativoUnivocoPagatore/tipoIdentificativoUnivoco elements
//			el = doc.createElement("pag:tipoIdentificativoUnivoco");
			el = doc.createElement("tipoIdentificativoUnivoco");
			if(cfPagatore.matches("\\d+")) //se numerico allora partita iva
				el.setTextContent("G"); //F=persona fisica; G=persona giuridica
			else
				el.setTextContent("F"); //F=persona fisica; G=persona giuridica
			identificativoUnivocoPagatore.appendChild(el);

			// soggettoPagatore/identificativoUnivocoPagatore/codiceIdentificativoUnivoco elements
//			el = doc.createElement("pag:codiceIdentificativoUnivoco");
			el = doc.createElement("codiceIdentificativoUnivoco");
			//if(cfPagatore.trim().length()==0)
			//	cfPagatore = "PAGAMENTO ANONIMO";// "RSSMRA00A01H501C"; //Fisso per pagamenti fatti dal payer perchè il dato non è gestito dal payer
			el.setTextContent(cfPagatore.trim()); //CF  o PIVA
			identificativoUnivocoPagatore.appendChild(el);

			// soggettoPagatore/anagraficaPagatore elements
//			el = doc.createElement("pag:anagraficaPagatore");
			el = doc.createElement("anagraficaPagatore");
			//if(nomePagatore.trim().length()==0)
			//	nomePagatore = "PAGAMENTO ANONIMO Web Payer"; //Fisso per pagamenti fatti dal payer perchè il dato non è gestito dal payer
			el.setTextContent(nomePagatore);
			soggettoPagatore.appendChild(el);

/*     Dati asteriscati in data 19/11/2015 Poste sembra aver utilizzato, per il modello 1
 *              la versione della RPT utilizzata da tutti gli altri
 * 
			// soggettoPagatore/indirizzoPagatore elements
			el = doc.createElement("pag:indirizzoPagatore");
//			el.setTextContent(indirizzoPagatore); MATTEO
			el.setTextContent(" ");
			soggettoPagatore.appendChild(el);

			// soggettoPagatore/civicoPagatore elements
			el = doc.createElement("pag:civicoPagatore");
//			el.setTextContent(civicoPagatore); MATTEO
			el.setTextContent("");
			soggettoPagatore.appendChild(el);

			// soggettoPagatore/capPagatore elements
			el = doc.createElement("pag:capPagatore");
//			el.setTextContent(capPagatore); MATTEO
			el.setTextContent("");
			soggettoPagatore.appendChild(el);

			// soggettoPagatore/localitaPagatore elements
			el = doc.createElement("pag:localitaPagatore");
//			el.setTextContent(localitaPagatore); MATTEO
			el.setTextContent("");
			soggettoPagatore.appendChild(el);

			// soggettoPagatore/provinciaPagatore elements
			el = doc.createElement("pag:provinciaPagatore");
//			el.setTextContent(provinciaPagatore); MATTEO
			el.setTextContent("");
			soggettoPagatore.appendChild(el);

			// soggettoPagatore/nazionePagatore elements
			el = doc.createElement("pag:nazionePagatore");
//			el.setTextContent(nazionePagatore); MATTEO
			el.setTextContent("");
			soggettoPagatore.appendChild(el);
*/
			// soggettoPagatore/e-mailPagatore elements
//			el = doc.createElement("pag:e-mailPagatore");
//			el = doc.createElement("e-mailPagatore");
//			el.setTextContent(emailPagatore);
//			soggettoPagatore.appendChild(el);
			//PG200060 TODO: su versione RM non viene mai eseguito l'appendChild con "e-mailPagatore"
			if (anagraficaVersante==null || anagraficaVersante.equals("")){
				// soggettoPagatore/e-mailPagatore elements
				el = doc.createElement("e-mailPagatore");
				el.setTextContent(emailPagatore);
				soggettoPagatore.appendChild(el);
				
			}
			
			

			// enteBeneficiario elements 
//			Element enteBeneficiario = doc.createElement("pag:enteBeneficiario");
			Element enteBeneficiario = doc.createElement("enteBeneficiario");
			rootElement.appendChild(enteBeneficiario);

			// enteBeneficiario/identificativoUnivocoPagatore elements 
//			Element identificativoUnivocoBeneficiario = doc.createElement("pag:identificativoUnivocoBeneficiario");
			Element identificativoUnivocoBeneficiario = doc.createElement("identificativoUnivocoBeneficiario");
			enteBeneficiario.appendChild(identificativoUnivocoBeneficiario);

			// enteBeneficiario/identificativoUnivocoBeneficiario/tipoIdentificativoUnivoco elements
//			el = doc.createElement("pag:tipoIdentificativoUnivoco");
			el = doc.createElement("tipoIdentificativoUnivoco");
			el.setTextContent("G"); //G=persona giuridica fisso
			identificativoUnivocoBeneficiario.appendChild(el);

			// enteBeneficiario/identificativoUnivocoBeneficiario/codiceIdentificativoUnivoco elements
//			el = doc.createElement("pag:codiceIdentificativoUnivoco");
			el = doc.createElement("codiceIdentificativoUnivoco");
			el.setTextContent(cfEnteCreditore.trim()); //CF ente creditore
			identificativoUnivocoBeneficiario.appendChild(el);


			// enteBeneficiario/denominazioneBeneficiario elements
//			el = doc.createElement("pag:denominazioneBeneficiario");
			el = doc.createElement("denominazioneBeneficiario");
			//30052018 - inizio - Eliminate []
			//el.setTextContent(nomeEnteCreditore.length()>70?nomeEnteCreditore.substring(0, 70):nomeEnteCreditore); //CF ente creditore
			String denominazioneBeneficiario = getDenominazioneBeneficiario(nomeEnteCreditore);
			el.setTextContent(denominazioneBeneficiario.length()>70?denominazioneBeneficiario.substring(0, 70):denominazioneBeneficiario); //CF ente creditore
			//30052018 - fine - Eliminate []
			enteBeneficiario.appendChild(el);

/*     Dati asteriscati in data 19/11/2015 Poste sembra aver utilizzato, per il modello 1
 *              la versione della RPT utilizzata da tutti gli altri
 * 
			// enteBeneficiario/codiceUnitOperBeneficiarioBeneficiario elements
			el = doc.createElement("pag:codiceUnitOperBeneficiario");
//			el.setTextContent(codiceUnitOperBeneficiario); MATTEO
			el.setTextContent("");
			enteBeneficiario.appendChild(el);

			// enteBeneficiario/codiceUnitOperBeneficiarioBeneficiario elements
			el = doc.createElement("pag:denomUnitOperBeneficiario");
//			el.setTextContent(denomUnitOperBeneficiario); MATTEO
			el.setTextContent("");
			enteBeneficiario.appendChild(el);

			// enteBeneficiario/indirizzoBeneficiarioBeneficiario elements
			el = doc.createElement("pag:indirizzoBeneficiario");
//			el.setTextContent(indirizzoBeneficiario); MATTEO
			el.setTextContent("");
			enteBeneficiario.appendChild(el);

			// enteBeneficiario/civicoBeneficiarioBeneficiario elements
			el = doc.createElement("pag:civicoBeneficiario");
//			el.setTextContent(civicoBeneficiario); MATTEO
			el.setTextContent("");
			enteBeneficiario.appendChild(el);

			// enteBeneficiario/capBeneficiarioBeneficiario elements
			el = doc.createElement("pag:capBeneficiario");
//			el.setTextContent(capBeneficiario); MATTEO
			el.setTextContent("");
			enteBeneficiario.appendChild(el);

			// enteBeneficiario/localitaBeneficiarioBeneficiario elements
			el = doc.createElement("pag:localitaBeneficiario");
//			el.setTextContent(localitaBeneficiario); MATTEO
			el.setTextContent("");
			enteBeneficiario.appendChild(el);

			// enteBeneficiario/provinciaBeneficiarioBeneficiario elements
			el = doc.createElement("pag:provinciaBeneficiario");
//			el.setTextContent(provinciaBeneficiario); MATTEO
			el.setTextContent("");
			enteBeneficiario.appendChild(el);

			// enteBeneficiario/nazioneBeneficiarioBeneficiario elements
			el = doc.createElement("pag:nazioneBeneficiario");
//			el.setTextContent(nazioneBeneficiario);
			el.setTextContent("");
			enteBeneficiario.appendChild(el);
*/
			// datiVersamento elements 
//			Element datiVersamento = doc.createElement("pag:datiVersamento");
			Element datiVersamento = doc.createElement("datiVersamento");
			rootElement.appendChild(datiVersamento);


			// datiVersamento/dataEsecuzionePagamento elements
//			el = doc.createElement("pag:dataEsecuzionePagamento");
			el = doc.createElement("dataEsecuzionePagamento");
			el.setTextContent(DateUtility.frmtTimeByPattern("yyyy-MM-dd",Calendar.getInstance().getTime())); //
			datiVersamento.appendChild(el);

			// datiVersamento/importoTotaleDaVersare elements
//			el = doc.createElement("pag:importoTotaleDaVersare");
			el = doc.createElement("importoTotaleDaVersare");
			DecimalFormat df = new DecimalFormat("0.00");
			df.setDecimalFormatSymbols(new DecimalFormatSymbols(Locale.ENGLISH));
			el.setTextContent(df.format(importo)); //
			datiVersamento.appendChild(el);

			// datiVersamento/tipoVersamento elements
//			el = doc.createElement("pag:tipoVersamento");
			el = doc.createElement("tipoVersamento");
			//el.setTextContent("BBT"); //BBT; BP; AD; CP; PO;
			el.setTextContent(tipoVersamento); //BBT; BP; AD; CP; PO;
			datiVersamento.appendChild(el);

			// datiVersamento/identificativoUnivocoVersamento elements
//			el = doc.createElement("pag:identificativoUnivocoVersamento");
			el = doc.createElement("identificativoUnivocoVersamento");
			el.setTextContent(iuv);
			datiVersamento.appendChild(el);

			// datiVersamento/codiceContestoPagamento elements
//			el = doc.createElement("pag:codiceContestoPagamento");
			el = doc.createElement("codiceContestoPagamento");
			el.setTextContent(codiceContestoPagamento); //"n/a" fisso perchè valorizzato se pagamento attivato da psp 
			datiVersamento.appendChild(el);

/*     Dati asteriscati in data 19/11/2015 Poste sembra aver utilizzato, per il modello 1
 *              la versione della RPT utilizzata da tutti gli altri
 * 
			// datiVersamento/ibanAddebito elements
			el = doc.createElement("pag:ibanAddebito");
			el.setTextContent(""); //Valorizzato solo se tipoVersamento = AD
			datiVersamento.appendChild(el);

			// datiVersamento/bicAddebito elements
			el = doc.createElement("pag:bicAddebito");
			el.setTextContent(""); //Valorizzato solo se tipoVersamento = AD
			datiVersamento.appendChild(el);
*/
			// datiVersamento/firmaRicevuta elements
//			el = doc.createElement("pag:firmaRicevuta");
//          Per Poste, come da documento del 26/06/2015, la firma deve sempre essere impostata ad "1"
//			el.setTextContent("0"); //0=non richiesta; 1=CaDes; 3=XaDes; 4=Elettronica Avanzata
//			if (tipoVersamento.equals("BP"))
//				el.setTextContent("1");
			el = doc.createElement("firmaRicevuta");
			//Per Poste non serviva la firma ad 1. Può anche non essere richiesta 
			//el.setTextContent("1"); //0=non richiesta; 1=CaDes; 3=XaDes; 4=Elettronica Avanzata
			el.setTextContent("0"); //0=non richiesta; 1=CaDes; 3=XaDes; 4=Elettronica Avanzata
			datiVersamento.appendChild(el);

			//1 per ogni bollettino (massimo 5)
			//for (int i = 0; i < 5; i++) {
			// datiVersamento/datiSingoloVersamento elements
//			Element datiSingoloVersamento = doc.createElement("pag:datiSingoloVersamento");
//PG170300 - 30/1/2018 - INIZIO - MODIFICATA LA COSTRUZIONE DELL'XML IN QUANTO IL TAG IMPORTO-SINGOLO-PAGAMENTO PUO' ESSERE MULTIPLO
//			Element datiSingoloVersamento = doc.createElement("datiSingoloVersamento");
//			datiVersamento.appendChild(datiSingoloVersamento);
//PG170300 - 30/1/2018 - FINE

			// datiVersamento/datiSingoloVersamento/importoSingoloVersamento elements
//			el = doc.createElement("pag:importoSingoloVersamento");
//PG170300 - 30/1/2018 - INIZIO - MODIFICATA LA COSTRUZIONE DELL'XML IN QUANTO IL TAG IMPORTO-SINGOLO-PAGAMENTO PUO' ESSERE MULTIPLO
//			el = doc.createElement("importoSingoloVersamento");
//			el.setTextContent(df.format(importo)); 
//			datiSingoloVersamento.appendChild(el);
//PG170300 - 30/1/2018 - FINE

/*     Dati asteriscati in data 19/11/2015 Poste sembra aver utilizzato, per il modello 1
 *              la versione della RPT utilizzata da tutti gli altri
 * 
			// datiVersamento/datiSingoloVersamento/commissioneCaricoPA elements
			el = doc.createElement("pag:commissioneCaricoPA"); 
//			el.setTextContent(df.format(commissioneCaricoPA)); MATTEO
			el.setTextContent("");
			datiSingoloVersamento.appendChild(el);
*/			
			// datiVersamento/datiSingoloVersamento/ibanAccredito elements
//			el = doc.createElement("pag:ibanAccredito");
//PG170300 - 30/1/2018 - INIZIO - MODIFICATA LA COSTRUZIONE DELL'XML IN QUANTO IL TAG IMPORTO-SINGOLO-PAGAMENTO PUO' ESSERE MULTIPLO
//			el = doc.createElement("ibanAccredito");
//			el.setTextContent(ibanAccredito); 
//			datiSingoloVersamento.appendChild(el);
//PG170300 - 30/1/2018 - FINE

/*     Dati asteriscati in data 19/11/2015 Poste sembra aver utilizzato, per il modello 1
 *              la versione della RPT utilizzata da tutti gli altri
 * 			
			// datiVersamento/datiSingoloVersamento/bicAccredito elements
			el = doc.createElement("pag:bicAccredito");
//			el.setTextContent(bicAccredito); MATTEO 
			datiSingoloVersamento.appendChild(el);
			
			// datiVersamento/datiSingoloVersamento/ibanAppoggio elements
			el = doc.createElement("pag:ibanAppoggio");
//			el.setTextContent(ibanAppoggio); MATTEO 
			datiSingoloVersamento.appendChild(el);
			
			// datiVersamento/datiSingoloVersamento/bicAppoggio elements
			el = doc.createElement("pag:bicAppoggio");
//			el.setTextContent(bicAppoggio); MATTEO
			datiSingoloVersamento.appendChild(el);
			
			// datiVersamento/datiSingoloVersamento/credenzialiPagatore elements
			el = doc.createElement("pag:credenzialiPagatore");
//			el.setTextContent(credenzialiPagatore); MATTEO 
			datiSingoloVersamento.appendChild(el);
*/
			// datiVersamento/datiSingoloVersamento/causaleVersamento elements
//			el = doc.createElement("pag:causaleVersamento");
//PG170300 - 30/1/2018 - INIZIO - MODIFICATA LA COSTRUZIONE DELL'XML IN QUANTO IL TAG IMPORTO-SINGOLO-PAGAMENTO PUO' ESSERE MULTIPLO
//			el = doc.createElement("causaleVersamento");
//PG170300 - 30/1/2018 - FINE
			//Causale = /RFS/IUV/Importo
			/*
			 * Modificata creazione causaleVersamento come indicato da Santoprete il 25/02/2016
			String[] sGroup = iuv.split("(?<=\\G.{4})");
			String gIuv = ""; //iuv raggruppato a 4 cifre
			for (String gr : sGroup) {
				gIuv += gr + " ";
			}
			gIuv = gIuv.trim();
			*/
//PG170300 - 30/1/2018 - INIZIO - MODIFICATA LA COSTRUZIONE DELL'XML IN QUANTO IL TAG IMPORTO-SINGOLO-PAGAMENTO PUO' ESSERE MULTIPLO
//			String gIuv = iuv;
//			//PG160230_001 GG 23112016 - inizio
//			//el.setTextContent("/RFS/" + gIuv + "/" + df.format(importo));
//			String causaleVersamento = "/RFS/" + gIuv + "/" + df.format(importo);
//			if (idBollettiniList!=null) {
//				causaleVersamento = causaleVersamento.concat("/TXT/Pagamento: ".concat(idBollettiniList));
//				if (causaleVersamento.length()>140) 
//					causaleVersamento = causaleVersamento.substring(0,140);
//			}
//			el.setTextContent(causaleVersamento);
//			//PG160230_001 GG 23112016 - fine
//			datiSingoloVersamento.appendChild(el);
//
//			// datiVersamento/datiSingoloVersamento/datiSpecificiRiscossione elements
////			el = doc.createElement("pag:datiSpecificiRiscossione");
//			el = doc.createElement("datiSpecificiRiscossione");
//			el.setTextContent(datiSpecificiRiscossione); //<tipo contabilità: 0;1;2;9;>/<codice contabilità>
//			datiSingoloVersamento.appendChild(el);
//PG170300 - 30/1/2018 - FINE


//PG170300 - 30/1/2018 - INIZIO - MODIFICATA LA COSTRUZIONE DELL'XML IN QUANTO IL TAG IMPORTO-SINGOLO-PAGAMENTO PUO' ESSERE MULTIPLO
			for (int i=0;i<listRptDatiPagamenti.size();i++){

				if (listRptDatiPagamenti.get(i).getItemValorizzato()!=null && listRptDatiPagamenti.get(i).getItemValorizzato().equals("S") && 
						(listRptDatiPagamenti.get(i).getImportoSingoloPagamento().compareTo(BigDecimal.ZERO) != 0
						|| (listRptDatiPagamenti.get(i).getTipoBolloDaErogare()!= null && listRptDatiPagamenti.get(i).getImportoMarcaDaBolloDigitale().compareTo(BigDecimal.ZERO) != 0)))
				{
					// vangono scaricati solo gli elementi valorizzati che potrebbero NON essere tutti... 
					Element datiSingoloVersamento = doc.createElement("datiSingoloVersamento");
					datiVersamento.appendChild(datiSingoloVersamento);
					
					el = doc.createElement("importoSingoloVersamento");
					//el.setTextContent(df.format(listRptDatiPagamenti.get(i).getImportoSingoloPagamento())); 
					if (listRptDatiPagamenti.get(i).getTipoBolloDaErogare()!= null && listRptDatiPagamenti.get(i).getTipoBolloDaErogare().length()>0)
					{
						el.setTextContent(df.format(listRptDatiPagamenti.get(i).getImportoMarcaDaBolloDigitale()));
					}
					else
					{
						el.setTextContent(df.format(listRptDatiPagamenti.get(i).getImportoSingoloPagamento()));
					}
					datiSingoloVersamento.appendChild(el);
					
					// SE TRATTASI DI MARCA DA BOLLO DIGITALE LA STRTTURA CAMBIA...
					if (listRptDatiPagamenti.get(i).getTipoBolloDaErogare()== null || listRptDatiPagamenti.get(i).getTipoBolloDaErogare().length()==0){
						
						
						el = doc.createElement("ibanAccredito");
						el.setTextContent(listRptDatiPagamenti.get(i).getIbanAccredito()); 
						datiSingoloVersamento.appendChild(el);
						
						if (listRptDatiPagamenti.get(i).getIbanAppoggio()!=null &&
							!listRptDatiPagamenti.get(i).getIbanAppoggio().trim().equals("")){
							el = doc.createElement("ibanAppoggio");
							el.setTextContent(listRptDatiPagamenti.get(i).getIbanAppoggio()); 
							datiSingoloVersamento.appendChild(el);
						}
						
						
					}
					
					el = doc.createElement("causaleVersamento");
					//String gIuv = iuv;
					String[] sGroup = iuv.split("(?<=\\G.{4})");
					String gIuv = ""; //iuv raggruppato a 4 cifre
					for (String gr : sGroup) {
						gIuv += gr + " ";
					}
					gIuv = gIuv.trim();
					//String causaleVersamento = "/RFS/" + gIuv + "/" + df.format(listRptDatiPagamenti.get(i).getImportoMarcaDaBolloDigitale());
					String causaleVersamento = "";
					//inizio LP EP22405X
					String causaleBRAV = listRptDatiPagamenti.get(i).getCausalePreavvisiBRAV();
					boolean bCausaleBRAV = (causaleBRAV != null && causaleBRAV.trim().length() > 0);
					//fine LP EP22405X
					if (listRptDatiPagamenti.get(i).getTipoBolloDaErogare()!= null && listRptDatiPagamenti.get(i).getTipoBolloDaErogare().length()>0)
					{
						causaleVersamento = "/RFS/" + gIuv + "/" + df.format(listRptDatiPagamenti.get(i).getImportoMarcaDaBolloDigitale());
						//inizio LP EP22405X
						if(!bCausaleBRAV) {
						//fine LP EP22405X
						//inizio LP PG200060
						if(causaleMB!=null && !causaleMB.equals("")){
							causaleVersamento += "/TXT/" + causaleMB;
						}
						//fine LP PG200060
						//inizio LP EP22405X
						}
						//fine LP EP22405X
					}
					else
					{
						causaleVersamento = "/RFS/" + gIuv + "/" + df.format(listRptDatiPagamenti.get(i).getImportoSingoloPagamento());
						//inizio LP EP22405X
						if(!bCausaleBRAV) {
						//fine LP EP22405X
						//inizio LP PG200060
						if(causale!=null && !causale.equals("")){
							causaleVersamento += "/TXT/" + causale;
						}
						//fine LP PG200060
						//inizio LP EP22405X
						}
						//fine LP EP22405X
					}
					//inizio LP EP22405X
					if(bCausaleBRAV) {
						causaleVersamento = causaleVersamento.concat("/TXT/".concat(causaleBRAV));
						if (causaleVersamento.length() > 140) 
							causaleVersamento = causaleVersamento.substring(0, 140);
					} else
					//fine LP EP22405X
					if (idBollettiniList!=null) {
						causaleVersamento = causaleVersamento.concat("/TXT/Pagamento: ".concat(idBollettiniList));
						if (causaleVersamento.length()>140) 
							causaleVersamento = causaleVersamento.substring(0,140);
					}
					//inizio LP PG200360
					String tassonomia = listRptDatiPagamenti.get(i).getTassonomia();
					if(tassonomia != null) {
						tassonomia = tassonomia.trim();
						if(tassonomia.length() > 0) {
							//inizio LP PG200360 - 20210212
							//datiSpecificiRiscossione = datiSpecificiRiscossione.replace("<tassonomia>", tassonomia);
							datiSpecificiRiscossione = datiSpecificiRiscossioneExt.replace("<tassonomia>", tassonomia);
							//fine LP PG200360 - 20210212
						} else {
							//inizio LP PG200360 - 20210212
							//NOTA: Non deve succedere
							datiSpecificiRiscossione = datiSpecificiRiscossioneExt;
							//fine LP PG200360 - 20210212
							tassonomia = null; 
						}
					}
					//fine LP PG200360
					//inizio LP PG180290
					String tipoDovuto = listRptDatiPagamenti.get(i).getTipoDovuto();
					if(tipoDovuto != null && tipoDovuto.length() > 0) {
						causaleVersamento += "/" + tipoDovuto;
						//inizio LP PG200360
						if(tassonomia != null) {
							causaleVersamento += "-" + tassonomia;
						}
						//fine LP PG200360
					}
					//fine LP PG180290
					el.setTextContent(causaleVersamento);
					datiSingoloVersamento.appendChild(el);
					
					// datiVersamento/datiSingoloVersamento/datiSpecificiRiscossione elements
					//			el = doc.createElement("pag:datiSpecificiRiscossione");
								el = doc.createElement("datiSpecificiRiscossione");
								el.setTextContent(datiSpecificiRiscossione); //<tipo contabilità: 0;1;2;9;>/<codice contabilità>
								datiSingoloVersamento.appendChild(el);
					
							
					if (listRptDatiPagamenti.get(i).getTipoBolloDaErogare()!= null && listRptDatiPagamenti.get(i).getTipoBolloDaErogare().length()>0){
						
						

						Element datiMarcaBolloDigitale = doc.createElement("datiMarcaBolloDigitale");
						datiSingoloVersamento.appendChild(datiMarcaBolloDigitale);

							el = doc.createElement("tipoBollo");
							el.setTextContent(listRptDatiPagamenti.get(i).getTipoBolloDaErogare()); 
							datiMarcaBolloDigitale.appendChild(el);
						
							el = doc.createElement("hashDocumento");
							el.setTextContent(listRptDatiPagamenti.get(i).getSegnaturaMarcaDaBolloDigitale()); 
							datiMarcaBolloDigitale.appendChild(el);
							
							el = doc.createElement("provinciaResidenza");
							el.setTextContent(listRptDatiPagamenti.get(i).getProvinciaResidenza()); 
							datiMarcaBolloDigitale.appendChild(el);

						
						
					}  
					
					
				}
//fine LP PG1800XX_011
			}
			
			//}

			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);

			//StreamResult result = new StreamResult(new File("C:\\file.xml"));
			StringWriter writer = new StringWriter();
			StreamResult result = new StreamResult(writer);

			// Output to console for testing
			// StreamResult result = new StreamResult(System.out);

			transformer.transform(source, result);

			log.debug("RPT XML: \n" + writer.toString());
			System.out.println("RPT XML: \n" + writer.toString());
			return writer.toString();


		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
		}		
		return "";
	}


	public static String generaERXml(PropertiesTree env, LoggerServer log, String codSocieta, String versioneOggetto, String dominio,
			String identificativoDominio, String identificativoMessaggioEsito, String riferimentoMessaggioRevoca,
			String riferimentoDataRevoca, String istitutoMittente, String soggettoPagatore, BigDecimal importo,
			String iuv, String codiceContestoPagamento, String identificativoUnivocoRiscossione,
			String causaleEsito, String datiAggiuntiviEsito) {

		try {

			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

			// ER element
			Document doc = docBuilder.newDocument();
			Element rootElement = doc.createElement("ER");
			//rootElement.setAttribute("xmlns", "http://www.digitpa.gov.it/schemas/2011/Pagamenti/");
			doc.appendChild(rootElement);

			Element el = null;

			// versioneOggetto elements
			Element el_versioneOggetto = doc.createElement("versioneOggetto");
			el_versioneOggetto.setTextContent(versioneOggetto);
			rootElement.appendChild(el_versioneOggetto);
			// dominio elements
			Element el_dominio = doc.createElement("dominio");
			el_dominio.setTextContent(dominio);			//deve venire dal messaggio RR
			rootElement.appendChild(el_dominio);
			// dominio/identificativoDominio elements
			el = doc.createElement("identificativoDominio");
			el.setTextContent(identificativoDominio.trim());  //deve venire dal messaggio RR
			el_dominio.appendChild(el);
			// dominio/identificativoMessaggioEsito elements
			el = doc.createElement("identificativoMessaggioEsito");	//id univoco rispetto a data ora
			el.setTextContent(identificativoMessaggioEsito);
			el_dominio.appendChild(el);
			// dominio/dataOraMessaggioRichiesta elements
			el = doc.createElement("dataOraMessaggioEsito");
			el.setTextContent(DateUtility.frmtTimeByPattern("yyyy-MM-dd'T'HH:mm:ss",Calendar.getInstance().getTime())); //ISO 8601
			el_dominio.appendChild(el);

			// riferimentoMessaggioRevoca elements
			el = doc.createElement("riferimentoMessaggioRevoca");
			el.setTextContent(riferimentoMessaggioRevoca); //identificativo del messaggio di revoca RR
			rootElement.appendChild(el);

			// riferimentoDataRevoca elements
			el = doc.createElement("riferimentoDataRevoca");
			el.setTextContent(riferimentoDataRevoca); //data del messaggio di revoca RR
			rootElement.appendChild(el);

			// istitutoMittente elements
			el = doc.createElement("istitutoMittente");
			el.setTextContent(istitutoMittente); //deve venire dal messaggio RR
			rootElement.appendChild(el);

			//			// soggettoVersante elements //necessario solo se diverso da soggetto pagante
			//			Element soggettoVersante = doc.createElement("soggettoVersante");
			//			rootElement.appendChild(el);

			// soggettoPagatore elements 
			el = doc.createElement("soggettoPagatore");
			el.setTextContent(soggettoPagatore); //deve venire dal messaggio RR
			rootElement.appendChild(el);

			// datiRevoca elements 
			Element el_datiRevoca = doc.createElement("datiRevoca");
			rootElement.appendChild(el_datiRevoca);

			// datiRevoca/importoTotaleRevocato elements
			el = doc.createElement("importoTotaleRevocato");
			DecimalFormat df = new DecimalFormat("0.00");
			df.setDecimalFormatSymbols(new DecimalFormatSymbols(Locale.ENGLISH));
			el.setTextContent(df.format(importo)); //
			el_datiRevoca.appendChild(el);

			// datiVersamento/identificativoUnivocoVersamento elements
			el = doc.createElement("identificativoUnivocoVersamento");
			el.setTextContent(iuv);
			el_datiRevoca.appendChild(el);

			// datiVersamento/codiceContestoPagamento elements
			el = doc.createElement("codiceContestoPagamento");
			el.setTextContent(codiceContestoPagamento); //deve venire dal messaggio RR 
			el_datiRevoca.appendChild(el);

			Element datiSingolaRevoca = doc.createElement("datiSingolaRevoca");
			el_datiRevoca.appendChild(datiSingolaRevoca);

			el = doc.createElement("singoloImportoRevocato");
			el.setTextContent(df.format(importo)); //
			datiSingolaRevoca.appendChild(el);

			el = doc.createElement("identificativoUnivocoRiscossione");
			el.setTextContent(identificativoUnivocoRiscossione); //
			datiSingolaRevoca.appendChild(el);

			el = doc.createElement("causaleEsito");
			el.setTextContent(causaleEsito); //
			datiSingolaRevoca.appendChild(el);

			el = doc.createElement("datiAggiuntiviEsito");
			el.setTextContent(datiAggiuntiviEsito); //
			datiSingolaRevoca.appendChild(el);

			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);

			//StreamResult result = new StreamResult(new File("C:\\file.xml"));
			StringWriter writer = new StringWriter();
			StreamResult result = new StreamResult(writer);

			// Output to console for testing
			// StreamResult result = new StreamResult(System.out);

			transformer.transform(source, result);

			log.debug("RPT XML: \n" + writer.toString());
			System.out.println("RPT XML: \n" + writer.toString());
			return writer.toString();


		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
		}		
		return "";
	}
	
	//30052018 - inizio
	public static String getDenominazioneBeneficiario (String nomeEnteCreditore) {
		String denominazioneBeneficiario = "";
		String denominazioneBeneficiarioFull = nomeEnteCreditore;
		
		int init = denominazioneBeneficiarioFull.indexOf("[");
		int end = denominazioneBeneficiarioFull.indexOf("]");
		if (init > 0)
			denominazioneBeneficiario = denominazioneBeneficiarioFull.substring(0,init);
		if (end > 0 && end < denominazioneBeneficiarioFull.length())
			denominazioneBeneficiario = denominazioneBeneficiario.concat(denominazioneBeneficiarioFull.substring(end + 1));
		
		denominazioneBeneficiario = denominazioneBeneficiario.trim();
		return denominazioneBeneficiario;
	}
	//30052018 - fine
}
