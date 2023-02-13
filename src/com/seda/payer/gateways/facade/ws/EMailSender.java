package com.seda.payer.gateways.facade.ws;

import java.rmi.RemoteException;

import com.seda.emailsender.webservices.dati.EMailSenderRequestType;
import com.seda.emailsender.webservices.dati.EMailSenderResponse;
import com.seda.emailsender.webservices.source.EMailSenderInterface;
import com.seda.emailsender.webservices.source.EMailSenderServiceLocator;
import com.seda.emailsender.webservices.srv.EMailSenderFaultType;



public class EMailSender
{
	private EMailSenderInterface emsCaller = null;
	
	public EMailSender(String endPoint)
	{
		try 
		{
			EMailSenderServiceLocator emsService = new EMailSenderServiceLocator();
			emsService.setEMailSenderPortEndpointAddress(endPoint);
			emsCaller = (EMailSenderInterface)emsService.getEMailSenderPort();
		}
		catch (Exception e)
		{
			System.out.println("EMailSender exception: " + e.getMessage());			
			e.printStackTrace();
		}
	} 
	
	public boolean sendEMail(String EMailDataTOList, 
			String EMailDataCCList,
			String EMailDataCCNList,
			String EMailDataObject,
			String EMailDataText)
	{
		EMailSenderResponse emsRes = null;
		EMailSenderRequestType emsBean = new EMailSenderRequestType();
		
		emsBean.setEMailDataTOList(EMailDataTOList);
		emsBean.setEMailDataCCList(EMailDataCCList);
		emsBean.setEMailDataCCNList(EMailDataCCNList);
		emsBean.setEMailDataSubject(EMailDataObject);
		emsBean.setEMailDataText(EMailDataText);
		emsBean.setEMailDataAttacchedFileList("");

		try
		{
			emsRes = (EMailSenderResponse)emsCaller.getEMailSender(emsBean);
		}
		catch(EMailSenderFaultType e)
		{
			System.out.println("Remote exception: " + e.getMessage());
			System.out.println("EMilSenderFaultType: " + e.getMessage1());
		}
		catch (RemoteException ex)
		{
			ex.printStackTrace();
		}

		// andata a buon fine OK altrimenti KO 
		if (emsRes == null)
			return false;
		else
			return emsRes.getValue().equalsIgnoreCase("OK");
						
	}
}
