package pe.soapros.generacionccm.business;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;
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
	private static final Marker ADMIN_USER = MarkerManager.getMarker("ADMIN");

	public byte[] callEWS(Solicitud solicitud) throws JsonProcessingException, EngineServiceException_Exception {

		logger.debug(ADMIN_USER, "callEWS parametro {}", solicitud);

		ObjectMapper mapper = new ObjectMapper();

		String jsonInput = mapper.writeValueAsString(solicitud);
		logger.debug(ADMIN_USER, "JSON Input: {}", jsonInput);

		EngineService test = new EngineService();

		EngineWebService test1 = test.getEngineServicePort();
		logger.debug(ADMIN_USER, "EngineWebService: {}", test1);

		EwsComposeRequest ewsComposeRequest = new EwsComposeRequest();

		DriverFile value = new DriverFile();
		value.setDriver(jsonInput.getBytes());
		value.setFileName("dd:input");
		logger.debug(ADMIN_USER, "Driver: {} ", value);

		logger.debug(ADMIN_USER, "PUB: " + solicitud.getCabecera().getDetallePDF().getCodigoPlantilla() + ".pub");

		ewsComposeRequest.setDriver(value);
		ewsComposeRequest.setPubFile(solicitud.getCabecera().getDetallePDF().getCodigoPlantilla() + ".pub");

		EwsComposeResponse response = test1.compose(ewsComposeRequest);
		logger.debug(ADMIN_USER, "Response: {}", response.getStatusMessage());

		byte[] pdf = null;

		try {
			if ((response != null) && (response.getFiles().get(0) != null)) {
				pdf = response.getFiles().get(0).getFileOutput();
			}
		} catch (Exception e) {
			logger.error(ADMIN_USER, "Error: {}", e);
		}

		return pdf;
	}
}
