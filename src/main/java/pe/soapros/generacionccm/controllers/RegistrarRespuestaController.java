package pe.soapros.generacionccm.controllers;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import pe.soapros.generacionccm.beans.Respuesta;
import pe.soapros.generacionccm.beans.Solicitud;
import pe.soapros.generacionccm.business.CommunicationServerBO;
import pe.soapros.generacionccm.business.EwsBO;
import pe.soapros.generacionccm.business.PeticionBO;

@RestController
public class RegistrarRespuestaController {

	private static final Logger logger = LogManager.getLogger(RegistrarRespuestaController.class);
	private static final Marker ADMIN_USER = MarkerManager.getMarker("ADMIN");

	@Autowired
	private PeticionBO peticionBO;

	@Autowired
	private CommunicationServerBO cs;

	@Autowired
	private EwsBO ews;

	@RequestMapping(method = RequestMethod.POST, value = "/registrar/pedido")
	@ResponseBody
	public Respuesta respuestaSolicitud(@Valid @RequestBody Solicitud solicitud) throws Exception {
		logger.debug(ADMIN_USER, "respuestaSolicitud parametro: {}", solicitud);

		Respuesta respuesta = new Respuesta();

		respuesta = cs.callOrquestador(solicitud);
		logger.debug(ADMIN_USER, "Orquestador: {}", respuesta);

		respuesta.setOrigen(solicitud.getOrigen());
		logger.debug(ADMIN_USER, "Respuesta con Origen: {}", respuesta);

		this.peticionBO.procesarPeticion(respuesta, solicitud);

		try {

			logger.debug(ADMIN_USER, "Indicador Vizualización",
					solicitud.getCabecera().getDetallePDF().getIndVisualizacion());
			if ("S".equals(solicitud.getCabecera().getDetallePDF().getIndVisualizacion())) {

				byte[] valor = ews.callEWS(solicitud);
				logger.debug(ADMIN_USER, "Retorno EWS: {}", valor);

				if (valor != null && valor.length > 0) {
					respuesta.setDocBase64(valor);
				}

			}

		} catch (Exception e) {
			logger.error(ADMIN_USER, "Error EWS {}", e);
			throw new Exception(e);
		}

		logger.info(ADMIN_USER, "Respuesta", respuesta);

		return respuesta;

	}

}
