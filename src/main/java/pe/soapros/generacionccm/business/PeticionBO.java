package pe.soapros.generacionccm.business;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import pe.soapros.generacionccm.beans.AlmacenamientoS3;
import pe.soapros.generacionccm.beans.Cabecera;
import pe.soapros.generacionccm.beans.DetallePDF;
import pe.soapros.generacionccm.beans.DetalleSMS;
import pe.soapros.generacionccm.beans.DetalleServicio;
import pe.soapros.generacionccm.beans.DetalleTXT;
import pe.soapros.generacionccm.beans.DetalleTrazabilidad;
import pe.soapros.generacionccm.beans.Entrada_Peticion;
import pe.soapros.generacionccm.beans.IndHTML_AlmcS3;
import pe.soapros.generacionccm.beans.IndPDF_AlmcS3;
import pe.soapros.generacionccm.beans.IndTXT_AlmcS3;
import pe.soapros.generacionccm.beans.PeticionOUT;
import pe.soapros.generacionccm.beans.Respuesta;
import pe.soapros.generacionccm.beans.Solicitud;
import pe.soapros.generacionccm.beans.detalleHTML;
import pe.soapros.generacionccm.persistance.domain.Detalle;
import pe.soapros.generacionccm.persistance.domain.Peticion;
import pe.soapros.generacionccm.persistance.repository.DetalleRepository;
import pe.soapros.generacionccm.persistance.repository.PeticionRepository;

@Component
public class PeticionBO {

	private static final Logger logger = LogManager.getLogger(PeticionBO.class);
	private static final Marker ADMIN_USER = MarkerManager.getMarker("ADMIN");

	@Autowired
	private PeticionRepository peticionRepository;

	@Autowired
	private DetalleRepository detalleRepository;

	public Respuesta procesarPeticion(Respuesta res, Solicitud sol) throws JsonProcessingException {
		logger.debug(ADMIN_USER, "procesarPeticion parametros; {} - {}", res, sol);

		Respuesta rpta = null;

		Peticion pet = new Peticion();

		pet.setUsuCreacion(res.getOrigen().getUsuario());
		pet.setFecCreacion(new Date());
		pet.setNumOperacion(res.getNumOperacion());
		pet.setIndError(false);
		logger.debug(ADMIN_USER, "Peticion", pet);

		Detalle det = new Detalle();
		det.setUsuCreacion(res.getOrigen().getUsuario());
		det.setFecCreacion(new Date());
		det.setNomFase("Solicitado");

		ObjectMapper mapper = new ObjectMapper();
		String jsonInput = mapper.writeValueAsString(sol);
		logger.debug(ADMIN_USER, "JSON", jsonInput);

		det.setInputJson(jsonInput);
		det.setIndError(0);

		Peticion p = peticionRepository.save(pet);

		det.setIdPeticion(p.getIdPeticion());

		logger.debug(ADMIN_USER, "Detalle", det);

		detalleRepository.save(det);

		logger.debug("JPA Guardado");

		return rpta;
	}

	public PeticionOUT consultarPeticion(Entrada_Peticion solicitud)
			throws JsonParseException, JsonMappingException, IOException {

		logger.debug(ADMIN_USER, "consultarPeticion: " + solicitud.toString());

		List<Detalle> detalles = this.peticionRepository.detallesByOperacion(solicitud.getNumOperacion());
		logger.debug(ADMIN_USER, "Detalles:" + detalles.toString());

		int tam = detalles.size();

		HashMap<String, String> hmap = new HashMap<String, String>();

		for (Detalle det : detalles) {

			hmap.put(det.getNomFase(), det.getInputJson());

		}

		logger.debug(ADMIN_USER, "Input Solicitado: " + hmap.get("Solicitado"));

		ObjectMapper mapper = new ObjectMapper();

		Solicitud sol = mapper.readValue(hmap.get("Solicitado"), Solicitud.class);
		logger.debug(ADMIN_USER, "JSON", sol);

		PeticionOUT respuesta = new PeticionOUT();

		respuesta.setOrigen(sol.getOrigen());

		Cabecera cabecera = new Cabecera();

		DetallePDF detPDF = new DetallePDF();
		DetalleTXT detTXT = new DetalleTXT();
		detalleHTML detHTML = new detalleHTML();

		detPDF.setIndPDF(sol.getCabecera().getDetallePDF().getIndPDF());
		detTXT.setIndTXT(sol.getCabecera().getDetalleTXT().getIndTXT());
		detHTML.setIndHTML(sol.getCabecera().getDetalleHTML().getIndHTML());

		if (hmap.get("Documentos generados") != null) {

			detPDF.setIndExito("S");
			detPDF.setCodError("0");
			detPDF.setMsgError("OK");

			detTXT.setIndExito("S");
			detTXT.setCodError("0");
			detTXT.setMsgError("OK");

			detHTML.setIndExito("S");
			detHTML.setCodError("0");
			detHTML.setMsgError("OK");

		} else {

			detPDF.setIndExito("N");
			detPDF.setCodError("-1");
			detPDF.setMsgError("Error en la generación");

			detTXT.setIndExito("N");
			detTXT.setCodError("-1");
			detTXT.setMsgError("Error en la generación");

			detHTML.setIndExito("N");
			detHTML.setCodError("-1");
			detHTML.setMsgError("Error en la generación");

		}

		cabecera.setDetallepdf(detPDF);

		DetalleSMS detSMS = new DetalleSMS();

		if (hmap.get("Envio SMS") != null) {
			detSMS.setIndSMS("S");
		} else {
			detSMS.setIndSMS("N");
		}

		cabecera.setEnvioSMS(detSMS);

		DetalleTrazabilidad detTraz = new DetalleTrazabilidad();

		detTraz.setIndTrazabilidad(sol.getCabecera().getDetalleTrazabilidadCorreo().getIndTrazabilidad());

		if (hmap.get("Trazabilidad") != null) {

			detTraz.setIndExito("S");
			detTraz.setCodError("0");
			detTraz.setMsgError("OK");
			detTraz.setValorretorno(hmap.get("Trazabilidad"));

		} else {

			detTraz.setIndExito("N");
			detTraz.setCodError("-1");
			detTraz.setMsgError("Error Trazabilidad");
			detTraz.setValorretorno(hmap.get("Trazabilidad"));

		}

		cabecera.setDetalleTrazabilidad(detTraz);

		DetalleServicio detServ = new DetalleServicio();

		detServ.setIndServicio(sol.getCabecera().getDetalleServicioGenerico().getIndServicioGenerico());

		if (hmap.get("Servicio Generico") != null) {

			detServ.setIndExito("S");
			detServ.setCodError("0");
			detServ.setMsgError("");
			detServ.setValorretorno(hmap.get("Servicio Generico"));

		} else {

			detServ.setIndExito("N");
			detServ.setCodError("-1");
			detServ.setMsgError("");

		}

		cabecera.setDetalleServicio(detServ);

		AlmacenamientoS3 almS3 = new AlmacenamientoS3();

		IndPDF_AlmcS3 pdfS3 = new IndPDF_AlmcS3();
		IndTXT_AlmcS3 txtS3 = new IndTXT_AlmcS3();
		IndHTML_AlmcS3 htmlS3 = new IndHTML_AlmcS3();

		pdfS3.setIndS3PDF(sol.getCabecera().getDetalleS3().getIndPDF().getIndS3PDF());
		txtS3.setIndS3TXT(sol.getCabecera().getDetalleS3().getIndTXT().getIndS3TXT());
		htmlS3.setIndS3HTML(sol.getCabecera().getDetalleS3().getIndHTML().getIndS3HTML());

		if (hmap.get("Documentos Subidos S3") != null) {

			pdfS3.setIndExito("S");
			pdfS3.setCodError("0");
			pdfS3.setMsgError("OK");

			if ("S".equals(pdfS3.getIndS3PDF())) {
				pdfS3.setRutaURLDestinoPDF(sol.getCabecera().getDetalleS3().getIndPDF().getRutaURLDestinoPDF());
			}

			txtS3.setIndExito("S");
			txtS3.setCodError("0");
			txtS3.setMsgError("OK");

			if ("S".equals(txtS3.getIndS3TXT())) {
				txtS3.setRutaURLDestinoTXT(sol.getCabecera().getDetalleS3().getIndTXT().getRutaURLDestinoTXT());
			}

			htmlS3.setIndExito("S");
			htmlS3.setCodError("0");
			htmlS3.setMsgError("OK");

			if ("S".equals(htmlS3.getIndS3HTML())) {
				htmlS3.setRutaURLDestinoHTML(sol.getCabecera().getDetalleS3().getIndHTML().getRutaURLDestinoHTML());
			}

		} else {

			pdfS3.setIndExito("N");
			pdfS3.setCodError("-1");
			pdfS3.setMsgError("Error Subida S3");

			txtS3.setIndExito("N");
			txtS3.setCodError("-1");
			txtS3.setMsgError("Error Subida S3");

			htmlS3.setIndExito("N");
			htmlS3.setCodError("-1");
			htmlS3.setMsgError("Error Subida S3");

		}

		almS3.setIndPDF(pdfS3);
		almS3.setIndTXT(txtS3);
		almS3.setIndHTML(htmlS3);

		cabecera.setAlmacenamientoS3(almS3);

		respuesta.setCabecera(cabecera);
		respuesta.setNumOperacion(solicitud.getNumOperacion());

		String estado = detalles.get(tam - 1).getNomFase();
		respuesta.setEstado(estado);
		logger.debug(ADMIN_USER, "Respuesta", respuesta);

		return respuesta;
	}

}
