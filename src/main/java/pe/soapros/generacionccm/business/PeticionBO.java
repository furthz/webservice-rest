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
import pe.soapros.generacionccm.beans.CabeceraIN;
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
		logger.debug(ADMIN_USER, "Peticion: {}", pet);

		Detalle det = new Detalle();
		det.setUsuCreacion(res.getOrigen().getUsuario());
		det.setFecCreacion(new Date());
		det.setNomFase("Solicitado");

		ObjectMapper mapper = new ObjectMapper();
		String jsonInput = mapper.writeValueAsString(sol);
		logger.debug(ADMIN_USER, "JSON: {}", jsonInput);

		det.setInputJson(jsonInput);
		det.setIndError(0);

		Peticion p = peticionRepository.save(pet);

		det.setIdPeticion(p.getIdPeticion());

		logger.debug(ADMIN_USER, "Detalle: {}", det);

		detalleRepository.save(det);

		logger.debug("JPA Guardado");

		return rpta;
	}

	public PeticionOUT consultarPeticion(Entrada_Peticion solicitud)
			throws JsonParseException, JsonMappingException, IOException {

		logger.debug(ADMIN_USER, "consultarPeticion: {}", solicitud);

		List<Detalle> detalles = this.peticionRepository.detallesByOperacion(solicitud.getNumOperacion());
		logger.debug(ADMIN_USER, "Detalles: {}", detalles.toString());

		int tam = detalles.size();

		HashMap<String, String> hmap = new HashMap<String, String>();

		for (Detalle det : detalles) {

			hmap.put(det.getNomFase(), det.getInputJson());

		}

		logger.debug(ADMIN_USER, "Input Solicitado: {}", hmap.get("Solicitado"));

		ObjectMapper mapper = new ObjectMapper();

		Solicitud sol = mapper.readValue(hmap.get("Solicitado"), Solicitud.class);
		logger.debug(ADMIN_USER, "JSON: {}", sol);

		PeticionOUT respuesta = new PeticionOUT();

		respuesta.setOrigen(sol.getOrigen());
		logger.debug(ADMIN_USER, "ORIGEN: {}", sol.getOrigen());

		Cabecera cabecera = new Cabecera();

		/*****************************************************************
		 * GENERAR DOCUMENTOS
		 ****************************************************************/

		DetallePDF detPDF = new DetallePDF();
		DetalleTXT detTXT = new DetalleTXT();
		detalleHTML detHTML = new detalleHTML();

		detPDF.setIndPDF(sol.getCabecera().getDetallePDF().getIndPDF());
		logger.debug(ADMIN_USER, "INDPDF: {}", sol.getCabecera().getDetallePDF().getIndPDF());

		detTXT.setIndTXT(sol.getCabecera().getDetalleTXT().getIndTXT());
		logger.debug(ADMIN_USER, "INDTXT: {}", sol.getCabecera().getDetalleTXT().getIndTXT());

		detHTML.setIndHTML(sol.getCabecera().getDetalleHTML().getIndHTML());
		logger.debug(ADMIN_USER, "INDHTML: {}", sol.getCabecera().getDetalleHTML().getIndHTML());

		CabeceraIN cabIn = sol.getCabecera();

		boolean swSeguir = true;

		if (hmap.get("Documentos generados") != null) {

			logger.debug(ADMIN_USER, "DOCUMENTOS GENERADOS");

			if (cabIn.getDetallePDF().getIndPDF().equals("S")) {
				detPDF.setIndExito("S");
				detPDF.setCodEstado("0");
				detPDF.setMsgEstado("DOCUMENTOS GENERADOS");
			}

			if (cabIn.getDetalleTXT().getIndTXT().equals("S")) {
				detTXT.setIndExito("S");
				detTXT.setCodEstado("0");
				detTXT.setMsgEstado("DOCUMENTOS GENERADOS");
			}

			if (cabIn.getDetalleHTML().getIndHTML().equals("S")) {
				detHTML.setIndExito("S");
				detHTML.setCodEstado("0");
				detHTML.setMsgEstado("DOCUMENTOS GENERADOS");
			}

		} else {

			logger.debug(ADMIN_USER, "DOCUMENTOS NO GENERADOS");

			if (cabIn.getDetallePDF().getIndPDF().equals("S")) {
				detPDF.setIndExito("N");
				detPDF.setCodEstado("-1");
				detPDF.setMsgEstado(hmap.get("Error"));
			}

			if (cabIn.getDetalleTXT().getIndTXT().equals("S")) {
				detTXT.setIndExito("N");
				detTXT.setCodEstado("-1");
				detTXT.setMsgEstado(hmap.get("Error"));
			}

			if (cabIn.getDetalleHTML().getIndHTML().equals("S")) {
				detHTML.setIndExito("N");
				detHTML.setCodEstado("-1");
				detHTML.setMsgEstado(hmap.get("Error"));
			}

			swSeguir = false;
		}

		cabecera.setDetallePDF(detPDF);
		logger.debug(ADMIN_USER, "DETPDF: {}", detPDF);

		cabecera.setDetalleTXT(detTXT);
		logger.debug(ADMIN_USER, "DETTXT: {}", detTXT);

		cabecera.setDetalleHTML(detHTML);
		logger.debug(ADMIN_USER, "DETHTML: {}", detHTML);

		/**************************************************
		 * ALMACENAMIENTO EN S3
		 ************************************************/

		AlmacenamientoS3 almS3 = new AlmacenamientoS3();

		IndPDF_AlmcS3 pdfS3 = new IndPDF_AlmcS3();
		IndTXT_AlmcS3 txtS3 = new IndTXT_AlmcS3();
		IndHTML_AlmcS3 htmlS3 = new IndHTML_AlmcS3();

		try {
			pdfS3.setIndS3PDF(sol.getCabecera().getDetalleS3().getIndPDF().getIndS3PDF());
			logger.debug(ADMIN_USER, "INDS3PDF", sol.getCabecera().getDetalleS3().getIndPDF().getIndS3PDF());

			txtS3.setIndS3TXT(sol.getCabecera().getDetalleS3().getIndTXT().getIndS3TXT());
			logger.debug(ADMIN_USER, "INDS3TXT: {}", sol.getCabecera().getDetalleS3().getIndTXT().getIndS3TXT());

			htmlS3.setIndS3HTML(sol.getCabecera().getDetalleS3().getIndHTML().getIndS3HTML());
			logger.debug(ADMIN_USER, "INDS3HTML: {}", sol.getCabecera().getDetalleS3().getIndHTML().getIndS3HTML());

			if (hmap.get("Documentos Subidos S3") != null) {

				logger.debug(ADMIN_USER, "DOCUMENTOS S3");

				if (cabIn.getDetalleS3().getIndPDF().getIndS3PDF().equals("S")) {
					pdfS3.setIndExito("S");
					pdfS3.setCodEstado("0");
					pdfS3.setMsgEstado("Documento Subido a S3");
					pdfS3.setRutaURLDestinoPDF(sol.getCabecera().getDetalleS3().getIndPDF().getRutaURLDestinoPDF());
				}

				if (cabIn.getDetalleS3().getIndTXT().getIndS3TXT().equals("S")) {
					txtS3.setIndExito("S");
					txtS3.setCodEstado("0");
					txtS3.setMsgEstado("Documento Subido a S3");
					txtS3.setRutaURLDestinoTXT(sol.getCabecera().getDetalleS3().getIndTXT().getRutaURLDestinoTXT());
				}

				if (cabIn.getDetalleS3().getIndHTML().getIndS3HTML().equals("S")) {
					htmlS3.setIndExito("S");
					htmlS3.setCodEstado("0");
					htmlS3.setMsgEstado("Documento Subido a S3");
					htmlS3.setRutaURLDestinoHTML(sol.getCabecera().getDetalleS3().getIndHTML().getRutaURLDestinoHTML());
				}

			} else if (hmap.get("Documentos Subidos S3") == null && swSeguir) {

				logger.debug(ADMIN_USER, "NO DOCUMENTOS S3");

				if (cabIn.getDetalleS3().getIndPDF().getIndS3PDF().equals("S")) {
					pdfS3.setIndExito("N");
					pdfS3.setCodEstado("-1");
					pdfS3.setMsgEstado(hmap.get("Error"));
				}

				if (cabIn.getDetalleS3().getIndTXT().getIndS3TXT().equals("S")) {
					txtS3.setIndExito("N");
					txtS3.setCodEstado("-1");
					txtS3.setMsgEstado(hmap.get("Error"));
				}

				if (cabIn.getDetalleS3().getIndHTML().getIndS3HTML().equals("S")) {
					htmlS3.setIndExito("N");
					htmlS3.setCodEstado("-1");
					htmlS3.setMsgEstado(hmap.get("Error"));
				}

				swSeguir = false;
			}

			almS3.setIndPDF(pdfS3);
			logger.debug(ADMIN_USER, "INDPDFS3: {}", pdfS3);

			almS3.setIndTXT(txtS3);
			logger.debug(ADMIN_USER, "INDTXTS3: {}", txtS3);

			almS3.setIndHTML(htmlS3);
			logger.debug(ADMIN_USER, "INDHTMLS3: {}", htmlS3);

			cabecera.setAlmacenamientoS3(almS3);
			logger.debug(ADMIN_USER, "ALMACENAMIENTO S3 {}", almS3);

		} catch (Exception e) {
			logger.error(ADMIN_USER, "ERROR S3 {}", e);
		}

		/**********************************************
		 * ENVIO A TRAZABILIDAD
		 *********************************************/

		DetalleTrazabilidad detTraz = new DetalleTrazabilidad();

		try {
			detTraz.setIndTrazabilidad(sol.getCabecera().getDetalleTrazabilidadCorreo().getIndTrazabilidad());
			logger.debug(ADMIN_USER, "INDTRAZABILIDAD: {}",
					sol.getCabecera().getDetalleTrazabilidadCorreo().getIndTrazabilidad());

			if (hmap.get("Trazabilidad") != null) {

				logger.debug(ADMIN_USER, "TRAZABILIDAD");

				if (cabIn.getDetalleTrazabilidadCorreo().getIndTrazabilidad().equals("S")) {
					detTraz.setIndExito("S");
					detTraz.setCodEstado("0");
					detTraz.setMsgEstado("Trazabilidad realizada");
					detTraz.setValorretorno(hmap.get("Trazabilidad"));
				}

			} else if (hmap.get("Trazabilidad") == null && swSeguir) {

				logger.debug(ADMIN_USER, "NO TRAZABILIDAD");
				if (cabIn.getDetalleTrazabilidadCorreo().getIndTrazabilidad().equals("S")) {
					detTraz.setIndExito("N");
					detTraz.setCodEstado("-1");
					detTraz.setMsgEstado("Error Trazabilidad");
					detTraz.setValorretorno(hmap.get("Error"));
				}

				swSeguir = false;

			}

			cabecera.setDetalleTrazabilidad(detTraz);
		} catch (Exception e) {

			logger.error(ADMIN_USER, "ERROR TRAZABILIDAD {}", e);

		}

		/***************************************************************
		 * DETALLE DEL SERVICIO GENERICO
		 *************************************************************/

		DetalleServicio detServ = new DetalleServicio();

		try {
			detServ.setIndServicio(sol.getCabecera().getDetalleServicioGenerico().getIndServicioGenerico());
			logger.debug(ADMIN_USER, "IND SERVICIO: {}",
					sol.getCabecera().getDetalleServicioGenerico().getIndServicioGenerico());

			if (hmap.get("Servicio Generico") != null) {

				logger.debug(ADMIN_USER, "SERVICIO GENERICO");
				if (cabIn.getDetalleServicioGenerico().getIndServicioGenerico().equals("S")) {
					detServ.setIndExito("S");
					detServ.setCodEstado("0");
					detServ.setMsgEstado("Servicio Genérico realizado");
					detServ.setValorretorno(hmap.get("Servicio Generico"));
				}

			} else if (hmap.get("Servicio Generico") == null && swSeguir) {

				logger.debug(ADMIN_USER, "NO SERVICIO GENERICO");
				if (cabIn.getDetalleServicioGenerico().getIndServicioGenerico().equals("S")) {
					detServ.setIndExito("N");
					detServ.setCodEstado("-1");
					detServ.setMsgEstado("Error Servicio Genérico");
					detServ.setValorretorno(hmap.get("Error"));
				}

				swSeguir = false;

			}

			cabecera.setDetalleServicio(detServ);
			logger.debug(ADMIN_USER, "DETSERV: {}", detServ);

		} catch (Exception e) {

			logger.error(ADMIN_USER, "ERROR SERVICIO GENERICO {}", e);

		}

		/***********************************
		 * + DETALLE DEL ENVIO A SMS
		 ***********************************/

		DetalleSMS detSMS = new DetalleSMS();

		try {
			if (hmap.get("Envio SMS") != null) {

				logger.debug(ADMIN_USER, "ENVIO SMS");
				detSMS.setIndSMS("S");

			} else if (hmap.get("Envio SMS") == null && swSeguir) {

				logger.debug(ADMIN_USER, "ENVIO SMS NO");
				detSMS.setIndSMS("N");

				swSeguir = false;
			}

			cabecera.setEnvioSMS(detSMS);
			logger.debug(ADMIN_USER, "ENVIO SMS: {}", detSMS);
		} catch (Exception e) {

			logger.error(ADMIN_USER, "ERROR SMS {}", e);
		}

		respuesta.setCabecera(cabecera);
		logger.debug(ADMIN_USER, "CABECERA: {}", cabecera);

		respuesta.setNumOperacion(solicitud.getNumOperacion());

		logger.debug(ADMIN_USER, "Respuesta {}", respuesta);

		return respuesta;
	}

}
