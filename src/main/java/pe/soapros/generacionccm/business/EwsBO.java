package pe.soapros.generacionccm.business;

import org.apache.commons.codec.binary.Base64;
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
		
		byte[] bytesEncoded = Base64.encodeBase64(jsonInput.getBytes());
		logger.debug("Bytes: " + bytesEncoded);
		

		EngineService test = new EngineService();
		EngineWebService test1 = test.getEngineServicePort();

		EwsComposeRequest ewsComposeRequest = new EwsComposeRequest();

		DriverFile value = new DriverFile();
		value.setDriver(bytesEncoded);
		value.setFileName(solicitud.getCabecera().getDetallePDF().getNombreDocumento());

		ewsComposeRequest.setDriver(value);
		ewsComposeRequest.setPubFile(solicitud.getCabecera().getDetallePDF().getCodigoPlantilla() + ".pub");

		EwsComposeResponse response = test1.compose(ewsComposeRequest);
		logger.debug("Response:" + response.getStatusMessage());
		
		return response.getEngineMessage();
	}
}
