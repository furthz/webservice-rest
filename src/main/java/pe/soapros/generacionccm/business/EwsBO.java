package pe.soapros.generacionccm.business;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import pe.soapros.generacionccm.beans.Solicitud;
import services.hpexstream.engine.DriverFile;
import services.hpexstream.engine.EngineService;
import services.hpexstream.engine.EngineServiceException_Exception;
import services.hpexstream.engine.EngineWebService;
import services.hpexstream.engine.EwsComposeRequest;
import services.hpexstream.engine.EwsComposeResponse;

@Component
public class EwsBO {

	private static final Logger logger = LogManager.getLogger(EwsBO.class);

	public byte[] callEWS(Solicitud solicitud) throws JsonProcessingException, EngineServiceException_Exception {
		
		ObjectMapper mapper = new ObjectMapper();
		
		String jsonInput = mapper.writeValueAsString(solicitud);
		logger.debug("jsonInput" + jsonInput);


		EngineService test = new EngineService();
		
		EngineWebService test1 = test.getEngineServicePort();
		logger.debug("Puerto: " + test.getEngineServicePort().toString());
		
		EwsComposeRequest ewsComposeRequest = new EwsComposeRequest();

		DriverFile value = new DriverFile();
		value.setDriver(jsonInput.getBytes());
		value.setFileName("dd:input");
		logger.debug("Driver: " + value.toString());
		

		logger.debug("PUB: " + solicitud.getCabecera().getDetallePDF().getCodigoPlantilla() + ".pub");
		
		ewsComposeRequest.setDriver(value);
		ewsComposeRequest.setPubFile(solicitud.getCabecera().getDetallePDF().getCodigoPlantilla() + ".pub");
		
		
		EwsComposeResponse response = test1.compose(ewsComposeRequest);
		logger.debug("Response:" + response.getStatusMessage());
		
		byte[] pdf = null;
		
		if((response != null) && (response.getFiles().get(0) != null)) {
			pdf = response.getFiles().get(0).getFileOutput();
		}
		
		return pdf;
	}
}
