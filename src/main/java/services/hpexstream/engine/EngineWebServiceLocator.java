package services.hpexstream.engine;

import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pe.soapros.generacionccm.beans.Propiedades;

@Component
public class EngineWebServiceLocator extends Service implements EngineWebService{

	private String EngineServicePort_address;
	
	@Autowired
	private Propiedades propiedades;
	
	
	protected EngineWebServiceLocator(URL wsdlDocumentLocation, QName serviceName) {
		super(wsdlDocumentLocation, serviceName);
		// TODO Auto-generated constructor stub
	}

	@Override
	public EwsComposeResponse compose(EwsComposeRequest ewsComposeRequest) throws EngineServiceException_Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	

}
