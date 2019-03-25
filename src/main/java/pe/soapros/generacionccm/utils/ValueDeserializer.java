package pe.soapros.generacionccm.utils;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonComponent;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import pe.soapros.generacionccm.beans.CabeceraIN;
import pe.soapros.generacionccm.beans.DetalleCorreoIN;
import pe.soapros.generacionccm.beans.DetalleFilenetIN;
import pe.soapros.generacionccm.beans.DetalleHTMLIN;
import pe.soapros.generacionccm.beans.DetallePDFIN;
import pe.soapros.generacionccm.beans.DetalleS3IN;
import pe.soapros.generacionccm.beans.DetalleSMSIN;
import pe.soapros.generacionccm.beans.DetalleServicioGenericoIN;
import pe.soapros.generacionccm.beans.DetalleTXTIN;
import pe.soapros.generacionccm.beans.DetalleTrazabilidadCorreoIN;
import pe.soapros.generacionccm.beans.IndHTMLFilenetIN;
import pe.soapros.generacionccm.beans.IndHTMLINDetalleS3;
import pe.soapros.generacionccm.beans.IndPDFFilenetIN;
import pe.soapros.generacionccm.beans.IndPDFINDetalleS3;
import pe.soapros.generacionccm.beans.IndTXTFilenetIN;
import pe.soapros.generacionccm.beans.IndTXTINDetalleS3;
import pe.soapros.generacionccm.beans.Origen;
import pe.soapros.generacionccm.beans.Solicitud;
import pe.soapros.generacionccm.business.SistemaBO;

@JsonComponent
public class ValueDeserializer extends JsonDeserializer<Solicitud> {

	private static final Logger logger = LogManager.getLogger(ValueDeserializer.class);
	private static final Marker ADMIN_USER = MarkerManager.getMarker("ADMIN");

	@Autowired
	private SistemaBO sistemaBO;

	@Override
	public Solicitud deserialize(JsonParser p, DeserializationContext ctxt)
			throws IOException, JsonProcessingException {

		logger.debug(ADMIN_USER, "deserialize", p, ctxt);

		ObjectCodec oc = p.getCodec();
		JsonNode node = oc.readTree(p);

		JsonNode origen = null;
		JsonNode cabecera = null;
		JsonNode detallePdf = null;
		JsonNode detalleTxt = null;
		JsonNode detalleHtml = null;
		JsonNode detalleCorreo = null;
		JsonNode detalleSms = null;
		JsonNode detalleTrazCor = null;
		JsonNode detServGen = null;
		JsonNode detS3 = null;
		JsonNode indPdf = null;
		JsonNode indTxt = null;
		JsonNode indHtml = null;
		JsonNode detFNet = null;
		JsonNode indPDFFilenet = null;
		JsonNode indTXTFilenet = null;
		JsonNode indHTMLFilenet = null;
		JsonNode PropiedadesFilenetHtml = null;
		JsonNode contentStreamFilenetHtml = null;

		JsonNode PropiedadesFilenetTxt = null;
		JsonNode contentStreamFileneTxt = null;

		JsonNode PropiedadesFilenetPdf = null;
		JsonNode contentStreamFilenetPdf = null;

		JsonNode metaDataPdf = null;
		JsonNode metaDataTxt = null;
		JsonNode metaDataHtml = null;
		JsonNode propFilenetPDF = null;
		JsonNode contStreamFilenetPDF = null;
		JsonNode propFilenetTXT = null;
		JsonNode contStreamFileNetTXT = null;
		JsonNode propFilenetHtml = null;
		JsonNode contStreamFileNetHtml = null;

		JsonNode para = null;
		JsonNode destinatario = null;

		JsonNode jsonData = null;

		Solicitud solicitudMod = new Solicitud();
		Origen o = new Origen();
		CabeceraIN cab = new CabeceraIN();
		// JsonDataIN json = new JsonDataIN();

		DetallePDFIN detPdf = new DetallePDFIN();
		DetalleTXTIN detTxt = new DetalleTXTIN();
		DetalleHTMLIN detHtml = new DetalleHTMLIN();
		DetalleCorreoIN detCorreo = new DetalleCorreoIN();
		DetalleSMSIN detSms = new DetalleSMSIN();
		DetalleTrazabilidadCorreoIN detTraz = new DetalleTrazabilidadCorreoIN();
		DetalleServicioGenericoIN detSGenerico = new DetalleServicioGenericoIN();
		DetalleS3IN detalS3 = new DetalleS3IN();
		IndPDFINDetalleS3 indPdfS3 = new IndPDFINDetalleS3();
		IndTXTINDetalleS3 indTxtS3 = new IndTXTINDetalleS3();
		IndHTMLINDetalleS3 indHtmlS3 = new IndHTMLINDetalleS3();
		DetalleFilenetIN detFileNet = new DetalleFilenetIN();
		IndPDFFilenetIN fileNetPdf = new IndPDFFilenetIN();
		IndTXTFilenetIN fileNetTxt = new IndTXTFilenetIN();
		IndHTMLFilenetIN fileNetHtml = new IndHTMLFilenetIN();

		/**********************************************************************************
		 * VALIDANDO LOS DATOS DEL ORIGEN
		 *********************************************************************************/

		if (node.has("origen")) {

			origen = node.get("origen");
			logger.debug(ADMIN_USER, "ORIGEN" + origen.toString());

		} else {

			throw new JsonProcessingException("origen, La etiqueta no existe") {
				private static final long serialVersionUID = 1L;

				@SuppressWarnings("unused")
				private void init() {
					this.initCause(this);
				}
			};

		}

		// sistema
		if (origen.has("sistema")) {

			o.setSistema(origen.get("sistema").asText());
			logger.debug(ADMIN_USER, origen.get("sistema").asText());

		} else {

			throw new JsonProcessingException("origen.sistema, La etiqueta no existe ") {

				private static final long serialVersionUID = 1L;

				@SuppressWarnings("unused")
				private void init() {
					this.initCause(this);
				}
			};

		}

		if ("".equals(o.getSistema()) || "null".equals(o.getSistema()) || o.getSistema() == null) {

			throw new JsonProcessingException("origen.sistema, La etiqueta no puede ser vacío o nula") {

				private static final long serialVersionUID = 1L;

				@SuppressWarnings("unused")
				private void init() {
					this.initCause(this);
				}
			};

		} else {
			logger.debug(ADMIN_USER, "Validar DB");

			boolean validado = sistemaBO.validateSistema(o.getSistema());

			if (!validado) {
				throw new JsonProcessingException(
						"origen.sistema, El Sistema ingresado no tiene permisos para hacer una solicitud") {

					private static final long serialVersionUID = 1L;

					@SuppressWarnings("unused")
					private void init() {
						this.initCause(this);
					}
				};
			}
		}

		// Ambiente
		if (origen.has("ambiente")) {

			o.setAmbiente(origen.get("ambiente").asText());
			logger.debug(ADMIN_USER, origen.get("ambiente").asText());

		} else {

			throw new JsonProcessingException("origen.ambiente, La etiqueta no existe ") {

				private static final long serialVersionUID = 1L;

				@SuppressWarnings("unused")
				private void init() {
					this.initCause(this);
				}
			};
		}

		// PROCESO
		if (origen.has("proceso")) {

			o.setProceso(origen.get("proceso").asText());
			logger.debug(ADMIN_USER, origen.get("proceso").asText());

		} else {

			throw new JsonProcessingException("origen.proceso, La etiqueta no existe ") {

				private static final long serialVersionUID = 1L;

				@SuppressWarnings("unused")
				private void init() {
					this.initCause(this);
				}
			};

		}

		// SUBPROCESO
		if (origen.has("subproceso")) {

			o.setSubproceso(origen.get("subproceso").asText());
			logger.debug(ADMIN_USER, origen.get("subproceso").asText());

		} else {

			throw new JsonProcessingException("origen.subproceso, La etiqueta no existe ") {
				private static final long serialVersionUID = 1L;

				@SuppressWarnings("unused")
				private void init() {
					this.initCause(this);
				}
			};
		}

		// FECHA DE ENVIO
		if (origen.has("fechadeEnvio")) {

			o.setFechadeEnvio(origen.get("fechadeEnvio").asText());
			logger.debug(ADMIN_USER, origen.get("fechadeEnvio").asText());

		} else {
			throw new JsonProcessingException("origen.fechadeEnvio, La etiqueta no existe ") {
				private static final long serialVersionUID = 1L;

				@SuppressWarnings("unused")
				private void init() {
					this.initCause(this);
				}
			};
		}

		// USUARIO
		if (origen.has("usuario")) {

			o.setUsuario(origen.get("usuario").asText());
			logger.debug(ADMIN_USER, origen.get("usuario").asText());

		} else {
			throw new JsonProcessingException("origen.usuario, La etiqueta no existe ") {

				private static final long serialVersionUID = 1L;

				@SuppressWarnings("unused")
				private void init() {
					this.initCause(this);
				}
			};
		}

		if ("".equals(o.getUsuario()) || "null".equals(o.getUsuario()) || o.getUsuario() == null) {

			throw new JsonProcessingException("origen.usuario, La etiqueta no puede ser vacío o nula") {

				private static final long serialVersionUID = 1L;

				@SuppressWarnings("unused")
				private void init() {
					this.initCause(this);
				}
			};
		}

		// IDENTIFICADOR
		if (origen.has("identificador")) {

			JsonNode identificador = origen.get("identificador");
			logger.debug(ADMIN_USER, origen.get("identificador"));

			String[] identificadores = new String[3];
			int ind = 0;
			for (JsonNode j : identificador) {
				identificadores[ind++] = j.asText();
			}

			o.setIdentificador(identificadores);

		} else {
			throw new JsonProcessingException("origen.identificador, La etiqueta no existe ") {

				private static final long serialVersionUID = 1L;

				@SuppressWarnings("unused")
				private void init() {
					this.initCause(this);
				}
			};
		}

		/**********************************************************************************
		 * VALIDANDO LOS DATOS DE LA CABECERA
		 *********************************************************************************/

		// VALIDANDO LA ETIQUETA CABECERAIN
		if (node.has("cabecera")) {

			cabecera = node.get("cabecera");
			logger.debug(ADMIN_USER, node.get("cabecera"));

		} else {
			throw new JsonProcessingException("cabecera, La etiqueta no existe ") {

				private static final long serialVersionUID = 1L;

				@SuppressWarnings("unused")
				private void init() {
					this.initCause(this);
				}
			};
		}

		/********************************************************************************
		 * DETALLEPDF
		 *******************************************************************************/

		if (cabecera.has("detallePDF")) {

			detallePdf = cabecera.get("detallePDF");
			logger.debug(ADMIN_USER, cabecera.get("detallePDF"));

		} else {
			throw new JsonProcessingException("cabecera.detallePDF, La etiqueta no existe ") {

				private static final long serialVersionUID = 1L;

				@SuppressWarnings("unused")
				private void init() {
					this.initCause(this);
				}
			};
		}

		// VALIDACION DE LOS CAMPOS DETALLEPDF
		if (detallePdf.has("indPDF")) {

			detPdf.setIndPDF(detallePdf.get("indPDF").asText());
			logger.debug(ADMIN_USER, detallePdf.get("indPDF").asText());

		} else {

			throw new JsonProcessingException("cabecera.detallePDF.indPDF, La etiqueta no existe ") {

				private static final long serialVersionUID = 1L;

				@SuppressWarnings("unused")
				private void init() {
					this.initCause(this);
				}
			};
		}

		// VALIDAMOS QUE EL CAMPO INDPDF SEA = S
		if (detPdf.getIndPDF() != null && detPdf.getIndPDF().equals("S")) {

			// 1: CODIGOPLANTILLA
			if (detallePdf.has("codigoPlantilla")) {

				detPdf.setCodigoPlantilla(detallePdf.get("codigoPlantilla").asText());
				logger.debug(ADMIN_USER, detallePdf.get("codigoPlantilla").asText());

			} else {

				throw new JsonProcessingException("cabecera.detallePDF.codigoPlantilla, La etiqueta no existe ") {

					private static final long serialVersionUID = 1L;

					@SuppressWarnings("unused")
					private void init() {
						this.initCause(this);
					}
				};
			}

			// VALIDAR QUE LOS CAMPOS ENTEN LLENOS
			if (detPdf.getCodigoPlantilla().equals("") || detPdf.getCodigoPlantilla().equals("null")
					|| detPdf.getCodigoPlantilla().equals(" ")) {

				throw new JsonProcessingException("cabecera.detallePDF.codigoPlantilla No puede ser nulo ") {

					private static final long serialVersionUID = 1L;

					@SuppressWarnings("unused")
					private void init() {
						this.initCause(this);
					}
				};

			} else {

				boolean validado = this.sistemaBO.validatePlantilla(o.getSistema(), detPdf.getCodigoPlantilla());

				if (!validado) {
					throw new JsonProcessingException(
							"cabecera.detallePDF.codigoPlantilla Esta plantilla no está registrada para: " + o.getSistema()) {

						private static final long serialVersionUID = 1L;

						@SuppressWarnings("unused")
						private void init() {
							this.initCause(this);
						}
					};
				}

				detPdf.setCodigoPlantilla(detallePdf.get("codigoPlantilla").asText());
			}

			// 2: INDVISUALIZACION
			if (detallePdf.has("indVisualizacion")) {

				detPdf.setIndVisualizacion(detallePdf.get("indVisualizacion").asText());
				logger.debug(ADMIN_USER, detallePdf.get("indVisualizacion").asText());

			} else {

				throw new JsonProcessingException("cabecera.detallePDF.indVisualizacion, La etiqueta no existe ") {

					private static final long serialVersionUID = 1L;

					@SuppressWarnings("unused")
					private void init() {
						this.initCause(this);
					}
				};
			}

			// validar visualización
			if (detPdf.getIndVisualizacion().equals("") || detPdf.getIndVisualizacion().equals("null")
					|| detPdf.getIndVisualizacion().equals(" ")) {

				throw new JsonProcessingException("cabecera.detallePDF.indVisualizacion No puede ser nulo ") {

					private static final long serialVersionUID = 1L;

					@SuppressWarnings("unused")
					private void init() {
						this.initCause(this);
					}
				};
			} else {
				detPdf.setIndVisualizacion(detallePdf.get("indVisualizacion").asText());

			}

			// 3: NOMBREDOCUMENTO
			if (detallePdf.has("nombreDocumento")) {

				detPdf.setNombreDocumento(detallePdf.get("nombreDocumento").asText());
				logger.debug(ADMIN_USER, detallePdf.get("nombreDocumento").asText());

			} else {

				throw new JsonProcessingException("cabecera.detallePDF.nombreDocumento, La etiqueta no existe ") {

					private static final long serialVersionUID = 1L;

					@SuppressWarnings("unused")
					private void init() {
						this.initCause(this);
					}
				};
			}

			// validado los nombreDocumento
			if (detPdf.getNombreDocumento().equals("") || detPdf.getNombreDocumento().equals("null")
					|| detPdf.getNombreDocumento().equals(" ")) {
				throw new JsonProcessingException("cabecera.detallePDF.nombreDocumento No puede ser nulo ") {

					private static final long serialVersionUID = 1L;

					@SuppressWarnings("unused")
					private void init() {
						this.initCause(this);
					}
				};
			} else {
				detPdf.setNombreDocumento(detallePdf.get("nombreDocumento").asText());
			}

			// 4: INDGUARDADO
			if (detallePdf.has("indGuardado")) {

				detPdf.setIndGuardado(detallePdf.get("indGuardado").asText());
				logger.debug(ADMIN_USER, detallePdf.get("indGuardado").asText());

			} else {
				throw new JsonProcessingException("cabecera.detallePDF.indGuardado, La etiqueta no existe ") {

					private static final long serialVersionUID = 1L;

					@SuppressWarnings("unused")
					private void init() {
						this.initCause(this);
					}
				};
			}

			// validar indGuardado
			if (detPdf.getIndGuardado().equals("") || detPdf.getIndGuardado().equals("null")
					|| detPdf.getIndGuardado().equals(" ")) {
				throw new JsonProcessingException("cabecera.detallePDF.indGuardado No puede ser nulo ") {

					private static final long serialVersionUID = 1L;

					@SuppressWarnings("unused")
					private void init() {
						this.initCause(this);
					}
				};
			} else {
				detPdf.setIndGuardado(detallePdf.get("indGuardado").asText());
			}

			cab.setDetallePDF(detPdf);

		} else if (detPdf.getIndPDF().equals("N")) {

			detPdf.setIndPDF(detallePdf.get("indPDF").asText());
			logger.debug(ADMIN_USER, detallePdf.get("indPDF").asText());

			cab.setDetallePDF(detPdf);

		} else {
			throw new JsonProcessingException("cabecera.detallePDF.indPDF, No puede tener otro valor que no sea S/N") {

				private static final long serialVersionUID = 1L;

				@SuppressWarnings("unused")
				private void init() {
					this.initCause(this);
				}
			};
		}

		/********************************************************************************
		 * DETALLETXT
		 *******************************************************************************/

		if (cabecera.has("detalleTXT")) {

			detalleTxt = cabecera.get("detalleTXT");
			logger.debug(ADMIN_USER, cabecera.get("detalleTXT"));

		} else {
			throw new JsonProcessingException("cabecera.detalleTXT, La etiqueta no existe ") {

				private static final long serialVersionUID = 1L;

				@SuppressWarnings("unused")
				private void init() {
					this.initCause(this);
				}
			};
		}

		// VALIDACION DE LAS ETIQUETAS DE DETALLETXT
		if (detalleTxt.has("indTXT")) {

			detTxt.setIndTXT(detalleTxt.get("indTXT").asText());
			logger.debug(ADMIN_USER, "INDTXT", detalleTxt.get("indTXT").asText());

		} else {
			throw new JsonProcessingException("cabecera.detalleTXT.indTXT, La etiqueta no existe ") {

				private static final long serialVersionUID = 1L;

				@SuppressWarnings("unused")
				private void init() {
					this.initCause(this);
				}
			};
		}

		// VALIDAMOS QUE EL CAMPO INDTXT SEA = S
		if (detTxt.getIndTXT() != null && detTxt.getIndTXT().equals("S")) {

			// 1: CODIGOPLANTILLA
			if (detalleTxt.has("codigoPlantilla")) {

				detTxt.setCodigoPlantilla(detalleTxt.get("codigoPlantilla").asText());
				logger.debug(ADMIN_USER, "CodigoPlantilla", detalleTxt.get("codigoPlantilla").asText());

			} else {

				throw new JsonProcessingException("cabecera.detalleTXT.codigoPlantilla, La etiqueta no existe ") {

					private static final long serialVersionUID = 1L;

					@SuppressWarnings("unused")
					private void init() {
						this.initCause(this);
					}
				};
			}

			if (detTxt.getCodigoPlantilla().equals("") || detTxt.getCodigoPlantilla().equals("null")
					|| detTxt.getCodigoPlantilla().equals(" ")) {
				// validar que no este vacio el indTXT
				throw new JsonProcessingException("cabecera.detalleTXT.codigoPlantilla No puede ser nulo ") {

					private static final long serialVersionUID = 1L;

					@SuppressWarnings("unused")
					private void init() {
						this.initCause(this);
					}
				};
			} else {

				boolean validado = this.sistemaBO.validatePlantilla(o.getSistema(), detTxt.getCodigoPlantilla());

				if (!validado) {
					throw new JsonProcessingException(
							"cabecera.detallePDF.codigoPlantilla Esta plantilla no está registrada para: " + o.getSistema()) {

						private static final long serialVersionUID = 1L;

						@SuppressWarnings("unused")
						private void init() {
							this.initCause(this);
						}
					};
				}

				detTxt.setCodigoPlantilla(detallePdf.get("codigoPlantilla").asText());
			}

			// 2: NOMBREDOCUMENTO
			if (detalleTxt.has("nombreDocumento")) {

				detTxt.setNombreDocumento(detalleTxt.get("nombreDocumento").asText());
				logger.debug(ADMIN_USER, "NOMBREDOCUMENTO", detalleTxt.get("nombreDocumento").asText());

			} else {
				throw new JsonProcessingException("cabecera.detalleTXT.nombreDocumento, La etiqueta no existe ") {

					private static final long serialVersionUID = 1L;

					@SuppressWarnings("unused")
					private void init() {
						this.initCause(this);
					}
				};
			}

			if (detTxt.getNombreDocumento().equals("") || detTxt.getNombreDocumento().equals("null")
					|| detTxt.getNombreDocumento().equals(" ")) {

				throw new JsonProcessingException("cabecera.detalleTXT.nombreDocumento No puede ser nulo ") {

					private static final long serialVersionUID = 1L;

					@SuppressWarnings("unused")
					private void init() {
						this.initCause(this);
					}
				};

			} else {
				detTxt.setNombreDocumento(detallePdf.get("nombreDocumento").asText());
			}

			// 3: INDGUARDADO
			if (detalleTxt.has("indGuardado")) {

				detTxt.setIndGuardado(detalleTxt.get("indGuardado").asText());
				logger.debug(ADMIN_USER, "INDGUARDADO", detalleTxt.get("indGuardado").asText());

			} else {

				throw new JsonProcessingException("cabecera.detalleTXT.indGuardado, La etiqueta no existe ") {

					private static final long serialVersionUID = 1L;

					@SuppressWarnings("unused")
					private void init() {
						this.initCause(this);
					}
				};
			}

			if (detTxt.getIndGuardado().equals("") || detTxt.getIndGuardado().equals("null")
					|| detTxt.getIndGuardado().equals(" ")) {
				throw new JsonProcessingException("cabecera.detalleTXT.indGuardado No puede ser nulo ") {

					private static final long serialVersionUID = 1L;

					@SuppressWarnings("unused")
					private void init() {
						this.initCause(this);
					}
				};
			} else {
				detTxt.setIndGuardado(detalleTxt.get("indGuardado").asText());
			}

			cab.setDetalleTXT(detTxt);

		} else if (detTxt.getIndTXT().equals("N")) {

			detTxt.setIndTXT(detalleTxt.get("indTXT").asText());
			logger.debug(ADMIN_USER, "INDTXT", detalleTxt.get("indTXT").asText());

			cab.setDetalleTXT(detTxt);

		} else {
			throw new JsonProcessingException("cabecera.detallePDF.indTXT, No puede ser nulo ") {

				private static final long serialVersionUID = 1L;

				@SuppressWarnings("unused")
				private void init() {
					this.initCause(this);
				}
			};
		}

		/********************************************************************************
		 * DETALLEHTML
		 *******************************************************************************/

		if (cabecera.has("detalleHTML")) {

			detalleHtml = cabecera.get("detalleHTML");
			logger.debug(ADMIN_USER, "DETALLEHTML", cabecera.get("detalleHTML"));

		} else {

			throw new JsonProcessingException("cabecera.detalleHTML, La etiqueta no existe ") {

				private static final long serialVersionUID = 1L;

				@SuppressWarnings("unused")
				private void init() {
					this.initCause(this);
				}
			};

		}

		// VALIDANDO LAS ETIQUETAS DE DETALLEHTML
		if (detalleHtml.has("indHTML")) {

			detHtml.setIndHTML(detalleHtml.get("indHTML").asText());
			logger.debug(ADMIN_USER, "INDHTML", detalleHtml.get("indHTML").asText());

		} else {

			throw new JsonProcessingException("cabecera.detalleHTML.indHTML, La etiqueta no existe ") {

				private static final long serialVersionUID = 1L;

				@SuppressWarnings("unused")
				private void init() {
					this.initCause(this);
				}
			};

		}

		// VALIDAMOS QUE EL CAMPO INDHTML SEA = S
		if (detHtml.getIndHTML() != null && detHtml.getIndHTML().equals("S")) {

			// 1: CODIGOPLANTILLA
			if (detalleHtml.has("codigoPlantilla")) {

				detHtml.setCodigoPlantilla(detalleHtml.get("codigoPlantilla").asText());
				logger.debug(ADMIN_USER, "CODIGOPLANTILLA", detalleHtml.get("codigoPlantilla").asText());

			} else {

				throw new JsonProcessingException("cabecera.detalleHTML.codigoPlantilla, La etiqueta no existe ") {

					private static final long serialVersionUID = 1L;

					@SuppressWarnings("unused")
					private void init() {
						this.initCause(this);
					}
				};
			}

			if (detHtml.getCodigoPlantilla().equals("") || detHtml.getCodigoPlantilla().equals(" ")
					|| detHtml.getCodigoPlantilla().equals("null")) {

				throw new JsonProcessingException("cabecera.detalleHTML.codigoPlantilla No puede ser nulo ") {
					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					@SuppressWarnings("unused")
					private void init() {
						this.initCause(this);
					}
				};

			} else {

				boolean validado = this.sistemaBO.validatePlantilla(o.getSistema(), detHtml.getCodigoPlantilla());

				if (!validado) {
					throw new JsonProcessingException(
							"cabecera.detallePDF.codigoPlantilla Esta plantilla no está registrada para: " + o.getSistema()) {

						private static final long serialVersionUID = 1L;

						@SuppressWarnings("unused")
						private void init() {
							this.initCause(this);
						}
					};
				}

				detHtml.setCodigoPlantilla(detalleHtml.get("codigoPlantilla").asText());
			}

			// 2: NOMBREDOCUMENTO
			if (detalleHtml.has("nombreDocumento")) {

				detHtml.setNombreDocumento(detalleHtml.get("nombreDocumento").asText());
				logger.debug(ADMIN_USER, "NOMBREDOCUMENTO", detalleHtml.get("nombreDocumento").asText());

			} else {

				throw new JsonProcessingException("cabecera.detalleHTML.nombreDocumento, La etiqueta no existe ") {
					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					@SuppressWarnings("unused")
					private void init() {
						this.initCause(this);
					}
				};

			}

			if (detHtml.getNombreDocumento().equals("") || detHtml.getNombreDocumento().equals(" ")
					|| detHtml.getNombreDocumento().equals("null")) {
				throw new JsonProcessingException("cabecera.detalleHTML.nombreDocumento No puede ser nulo ") {
					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					@SuppressWarnings("unused")
					private void init() {
						this.initCause(this);
					}
				};
			} else {
				detHtml.setNombreDocumento(detalleHtml.get("nombreDocumento").asText());
			}

			// 3: INDGUARDADO
			if (detalleHtml.has("indGuardado")) {

				detHtml.setIndGuardado(detalleHtml.get("indGuardado").asText());
				logger.debug(ADMIN_USER, "INDGUARDADO", detalleHtml.get("indGuardado").asText());

			} else {

				throw new JsonProcessingException("cabecera.detalleHTML.indGuardado, La etiqueta no existe ") {
					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					@SuppressWarnings("unused")
					private void init() {
						this.initCause(this);
					}
				};
			}

			if (detHtml.getIndGuardado().equals("") || detHtml.getIndGuardado().equals(" ")
					|| detHtml.getIndGuardado().equals("null")) {

				throw new JsonProcessingException("cabecera.detalleHTML.indGuardado, No puede ser nulo ") {
					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					@SuppressWarnings("unused")
					private void init() {
						this.initCause(this);
					}
				};

			} else {
				detHtml.setIndGuardado(detalleHtml.get("indGuardado").asText());
			}

			cab.setDetalleHTML(detHtml);

		} else if (detHtml.getIndHTML().equals("N")) {

			detHtml.setIndHTML(detalleHtml.get("indHTML").asText());
			cab.setDetalleHTML(detHtml);

		} else {
			throw new JsonProcessingException("cabecera.detalleHTML.indHTML, No puede ser nulo ") {
				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				@SuppressWarnings("unused")
				private void init() {
					this.initCause(this);
				}
			};
		}

		/********************************************************************************
		 * DETALLECORREO
		 *******************************************************************************/

		if (cabecera.has("detalleCorreo")) {

			detalleCorreo = cabecera.get("detalleCorreo");
			logger.debug(ADMIN_USER, "DETALLECORREO", cabecera.get("detalleCorreo"));

		} else {

			throw new JsonProcessingException("cabecera.detalleCorreo, La etiqueta no existe ") {
				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				@SuppressWarnings("unused")
				private void init() {
					this.initCause(this);
				}
			};

		}

		// VALIDANDO LAS ETIQUETAS DE DETALLECORREO
		if (detalleCorreo.has("indCorreo")) {

			detCorreo.setIndCorreo(detalleCorreo.get("indCorreo").asText());
			logger.debug(ADMIN_USER, "INDCORREO", detalleCorreo.get("indCorreo").asText());

		} else {

			throw new JsonProcessingException("cabecera.detalleCorreo.indCorreo, La etiqueta no existe ") {
				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				@SuppressWarnings("unused")
				private void init() {
					this.initCause(this);
				}
			};

		}

		// VALIDAMOS QUE INDCORREO = S
		if (detCorreo.getIndCorreo() != null && detCorreo.getIndCorreo().equals("S")) {

			// 1: DE
			if (detalleCorreo.has("de")) {

				detCorreo.setDe(detalleCorreo.get("de").asText());
				logger.debug(ADMIN_USER, "DE", detalleCorreo.get("de").asText());

			} else {

				throw new JsonProcessingException("cabecera.detalleCorreo.de, La etiqueta no existe ") {
					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					@SuppressWarnings("unused")
					private void init() {
						this.initCause(this);
					}
				};

			}

			if (detCorreo.getDe().equals("") || detCorreo.getDe().equals(" ") || detCorreo.getDe().equals("null")) {
				throw new JsonProcessingException("cabecera.detalleCorreo.de, No puede ser nulo ") {
					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					@SuppressWarnings("unused")
					private void init() {
						this.initCause(this);
					}
				};
			} else {
				detCorreo.setDe(detalleCorreo.get("de").asText());
			}

			// 2: ALIAS
			if (detalleCorreo.has("aliasDe")) {

				detCorreo.setAliasDe(detalleCorreo.get("aliasDe").asText());
				logger.debug(ADMIN_USER, "ALIAS", detalleCorreo.get("aliasDe").asText());

			} else {

				throw new JsonProcessingException("cabecera.detalleCorreo.aliasDe, La etiqueta no existe ") {
					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					@SuppressWarnings("unused")
					private void init() {
						this.initCause(this);
					}
				};

			}

			if (detCorreo.getAliasDe().equals("") || detCorreo.getAliasDe().equals(" ")
					|| detCorreo.getAliasDe().equals("null")) {
				throw new JsonProcessingException("cabecera.detalleCorreo.aliasDe, No puede ser nulo ") {
					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					@SuppressWarnings("unused")
					private void init() {
						this.initCause(this);
					}
				};
			} else {
				detCorreo.setAliasDe(detalleCorreo.get("aliasDe").asText());
			}

			// 3: PARA
			if (detalleCorreo.has("para")) {

				para = detalleCorreo.get("para");
				logger.debug(ADMIN_USER, "PARA", detalleCorreo.get("para"));

				String[] corPara = new String[5];
				int ind = 0;
				for (JsonNode j : para) {

					corPara[ind++] = j.asText();
				}

				detCorreo.setPara(corPara);

			} else {

				throw new JsonProcessingException("cabecera.detalleCorreo.para, La etiqueta no existe ") {
					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					@SuppressWarnings("unused")
					private void init() {
						this.initCause(this);
					}
				};
			}

			if ((detCorreo.getPara().length == 0) && (detCorreo.getPara() != null)) {
				throw new JsonProcessingException("cabecera.detalleCorreo.para, No puede ser nulo ") {
					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					@SuppressWarnings("unused")
					private void init() {
						this.initCause(this);
					}
				};

			} else {
				for (String cpara : detCorreo.getPara()) {

					if (cpara != null) {
						logger.debug(ADMIN_USER, cpara);
						Pattern pattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
								Pattern.CASE_INSENSITIVE);

						Matcher matcher = pattern.matcher(cpara);

						if (!matcher.find()) {
							throw new JsonProcessingException(
									"cabecera.detalleCorreo.para, Uno de los correo es inválido") {
								/**
								 * 
								 */
								private static final long serialVersionUID = 1L;

								@SuppressWarnings("unused")
								private void init() {
									this.initCause(this);
								}
							};
						}
					}
				}

			}

			// 4: ASUNTOPLANTILLA
			if (detalleCorreo.has("asuntoPlantilla")) {

				detCorreo.setAsuntoPlantilla(detalleCorreo.get("asuntoPlantilla").asText());
				logger.debug(ADMIN_USER, "ASUNTO PLANTILLA", detalleCorreo.get("asuntoPlantilla").asText());

			} else {

				throw new JsonProcessingException("cabecera.detalleCorreo.asuntoPlantilla, La etiqueta no existe ") {
					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					@SuppressWarnings("unused")
					private void init() {
						this.initCause(this);
					}
				};

			}

			if (detCorreo.getAsuntoPlantilla().equals("") || detCorreo.getAsuntoPlantilla().equals("null")
					|| detCorreo.getAsuntoPlantilla().equals(" ")) {
				throw new JsonProcessingException("cabecera.detalleCorreo.asuntoPlantilla No puede ser nulo ") {
					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					@SuppressWarnings("unused")
					private void init() {
						this.initCause(this);
					}
				};
			} else {
				detCorreo.setAsuntoPlantilla(detalleCorreo.get("asuntoPlantilla").asText());

			}

			// 5: ASUNTO
			if (detalleCorreo.has("asunto")) {

				detCorreo.setAsunto(detalleCorreo.get("asunto").asText());
				logger.debug(ADMIN_USER, "ASUNTO", detalleCorreo.get("asunto").asText());

			} else {
				throw new JsonProcessingException("cabecera.detalleCorreo.asunto, La etiqueta no existe ") {
					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					@SuppressWarnings("unused")
					private void init() {
						this.initCause(this);
					}
				};
			}

			if (detCorreo.getAsunto().equals("") || detCorreo.getAsunto().equals("null")
					|| detCorreo.getAsunto().equals(" ")) {

				throw new JsonProcessingException("cabecera.detalleCorreo.asunto, No puede ser nulo") {
					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					@SuppressWarnings("unused")
					private void init() {
						this.initCause(this);
					}
				};

			} else {
				detCorreo.setAsunto(detalleCorreo.get("asunto").asText());
				logger.debug(ADMIN_USER, "ASUNTO", detalleCorreo.get("asunto").asText());

			}

			if (detalleCorreo.has("indAdjuntarPDF")) {
				detCorreo.setIndAdjuntarPDF(detalleCorreo.get("indAdjuntarPDF").asText());
			}

			if (detalleCorreo.has("indAdjuntarTXT")) {
				detCorreo.setIndAdjuntarTXT(detalleCorreo.get("indAdjuntarTXT").asText());
			}

			if (detalleCorreo.has("conCopia")) {
				// FALTA
			}

			if (detalleCorreo.has("conCopiaOculta")) {
				// FALTA
			}

			if (detalleCorreo.has("adjuntosadicionales")) {
				// FALTA
			}

			cab.setDetalleCorreo(detCorreo);

		} else if (detCorreo.getIndCorreo().equals("N")) {

			detCorreo.setIndCorreo(detalleCorreo.get("indCorreo").asText());
			logger.debug(ADMIN_USER, "INDCORREO", detalleCorreo.get("indCorreo").asText());

			cab.setDetalleCorreo(detCorreo);

		} else {

			throw new JsonProcessingException("cabecera.detalleCorreo.indCorreo, No puede ser nulo") {
				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				@SuppressWarnings("unused")
				private void init() {
					this.initCause(this);
				}
			};
		}

		/********************************************************************************
		 * DETALLESMS
		 *******************************************************************************/

		if (cabecera.has("detalleSMS")) {

			detalleSms = cabecera.get("detalleSMS");
			logger.debug(ADMIN_USER, "DETALLESMS", cabecera.get("detalleSMS"));

		} else {

			throw new JsonProcessingException("cabecera.detalleSMS, La etiqueta no existe ") {
				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				@SuppressWarnings("unused")
				private void init() {
					this.initCause(this);
				}
			};
		}

		// VALIDANDO LAS ETIQUETAS DE DETALLESMS
		if (detalleSms.has("indSMS")) {

			detSms.setIndSMS(detalleSms.get("indSMS").asText());
			logger.debug(ADMIN_USER, "INDSMS", detalleSms.get("indSMS").asText());

		} else {

			throw new JsonProcessingException("cabecera.DetalleSMS.indSMS, La etiqueta no existe ") {
				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				@SuppressWarnings("unused")
				private void init() {
					this.initCause(this);
				}
			};

		}

		// VALIDAMOS QUE INDSMS SEA = S
		if (detSms.getIndSMS() != null && detSms.getIndSMS().equals("S")) {

			// 1: DATASOURCE
			if (detalleSms.has("dataSource")) {

				detSms.setDataSource(detalleSms.get("dataSource").asText());
				logger.debug(ADMIN_USER, "DATASOURCE", detalleSms.get("dataSource").asText());

			} else {

				throw new JsonProcessingException("cabecera.DetalleSMS.dataSource, La etiqueta no existe ") {
					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					@SuppressWarnings("unused")
					private void init() {
						this.initCause(this);
					}
				};

			}

			if (detSms.getDataSource().equals("") || detSms.getDataSource().equals("null")
					|| detSms.getDataSource().equals(" ")) {
				throw new JsonProcessingException("cabecera.detalleSMS.dataSource, No puede ser nulo ") {
					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					@SuppressWarnings("unused")
					private void init() {
						this.initCause(this);
					}
				};
			} else {
				detSms.setDataSource(detalleSms.get("dataSource").asText());
			}

			// 2: DESTINATARIO
			if (detalleSms.has("destinatario")) {

				destinatario = detalleSms.get("destinatario");
				logger.debug(ADMIN_USER, "DESTINATARIO", detalleSms.get("destinatario"));

				String[] destinatarios = new String[3];
				int ind = 0;

				for (JsonNode j : destinatario) {
					destinatarios[ind++] = j.asText();
				}

				detSms.setDestinatario(destinatarios);

			} else {
				throw new JsonProcessingException("cabecera.DetalleSMS.destinatario, La etiqueta no existe ") {
					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					@SuppressWarnings("unused")
					private void init() {
						this.initCause(this);
					}
				};
			}

			cab.setDetalleSMS(detSms);

		} else if (detSms.getIndSMS().equals("N")) {

			detSms.setIndSMS(detalleSms.get("indSMS").asText());
			cab.setDetalleSMS(detSms);

		} else {
			throw new JsonProcessingException("cabecera.detalleSMS.indSMS, No puede ser nulo ") {
				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				@SuppressWarnings("unused")
				private void init() {
					this.initCause(this);
				}
			};
		}

		/********************************************************************************
		 * TRAZABILIDAD
		 *******************************************************************************/

		if (cabecera.has("detalleTrazabilidadCorreo")) {

			detalleTrazCor = cabecera.get("detalleTrazabilidadCorreo");
			logger.debug(ADMIN_USER, "TRAZABILIDAD", cabecera.get("detalleTrazabilidadCorreo"));

		} else {
			throw new JsonProcessingException("cabecera.detalleTrazabilidadCorreo, La etiqueta no existe ") {
				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				@SuppressWarnings("unused")
				private void init() {
					this.initCause(this);
				}
			};
		}

		// VALIDANDO LAS ETIQUETAS TRAZABILIDAD
		if (detalleTrazCor.has("indTrazabilidad")) {

			detTraz.setIndTrazabilidad(detalleTrazCor.get("indTrazabilidad").asText());
			logger.debug(ADMIN_USER, "INDTRAZABILIDAD", detalleTrazCor.get("indTrazabilidad").asText());

		} else {

			throw new JsonProcessingException(
					"cabecera.detalleTrazabilidadCorreo.indTrazabilidad, La etiqueta no existe ") {
				/**
						 * 
						 */
				private static final long serialVersionUID = 1L;

				@SuppressWarnings("unused")
				private void init() {
					this.initCause(this);
				}
			};
		}

		// VALIDAMOS QUE INDTRAZABILIDAD SEA = S
		if (detTraz.getIndTrazabilidad() != null && detTraz.getIndTrazabilidad().equals("S")) {

			if (detalleTrazCor.has("dataSource")) {

				detTraz.setDataSource(detalleTrazCor.get("dataSource").asText());
				logger.debug(ADMIN_USER, "DATASOURCE", detalleTrazCor.get("dataSource").asText());

			} else {

				throw new JsonProcessingException(
						"cabecera.detalleTrazabilidadCorreo.dataSource, La etiqueta no existe ") {
					/**
							 * 
							 */
					private static final long serialVersionUID = 1L;

					@SuppressWarnings("unused")
					private void init() {
						this.initCause(this);
					}
				};
			}

			// VALIDAMOS QUE LOS CAMPOS NO ESTEN VACIAS
			if (detTraz.getDataSource().equals("") || detTraz.getDataSource().equals("null")
					|| detTraz.getDataSource().equals(" ")) {

				throw new JsonProcessingException("cabecera.detalleTrazabilidad.dataSource, No puede ser nulo ") {
					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					@SuppressWarnings("unused")
					private void init() {
						this.initCause(this);
					}
				};

			} else {

				detTraz.setDataSource(detalleTrazCor.get("dataSource").asText());

			}

			cab.setDetalleTrazabilidadCorreo(detTraz);

		} else if (detTraz.getIndTrazabilidad().equals("N")) {

			detTraz.setIndTrazabilidad(detalleTrazCor.get("indTrazabilidad").asText());
			cab.setDetalleTrazabilidadCorreo(detTraz);

		} else {

			throw new JsonProcessingException("cabecera.detalleTrazabilidad.indTrazabilidad, No puede ser nulo ") {
				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				@SuppressWarnings("unused")
				private void init() {
					this.initCause(this);
				}
			};
		}

		/********************************************************************************
		 * DETALLEGENERICO
		 *******************************************************************************/

		if (cabecera.has("detalleServicioGenerico")) {

			detServGen = cabecera.get("detalleServicioGenerico");
			logger.debug(ADMIN_USER, "DETALLESERVICIOGENERICO", cabecera.get("detalleServicioGenerico"));

		} else {

			throw new JsonProcessingException("cabecera.detalleServicioGenerico, La etiqueta no existe ") {
				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				@SuppressWarnings("unused")
				private void init() {
					this.initCause(this);
				}
			};

		}

		// VALIDANDO LAS ETIQUETAS DETALLEGENERICO
		if (detServGen.has("indServicioGenerico")) {

			detSGenerico.setIndServicioGenerico(detServGen.get("indServicioGenerico").asText());
			logger.debug(ADMIN_USER, "INDSERVICIOGENERICO", detServGen.get("indServicioGenerico").asText());

		} else {

			throw new JsonProcessingException(
					"cabecera.detalleServicioGenerico.indServicioGenerico, La etiqueta no existe ") {
				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				@SuppressWarnings("unused")
				private void init() {
					this.initCause(this);
				}
			};

		}

		// VALIDAMOS QUE INDSERVICIOGENERICO SEA = S
		if (detSGenerico.getIndServicioGenerico() != null && detSGenerico.getIndServicioGenerico().equals("S")) {

			if (detServGen.has("dataSource")) {
				detSGenerico.setDataSource(detServGen.get("dataSource").asText());
			} else {
				throw new JsonProcessingException(
						"cabecera.detalleServicioGenerico.dataSource, La etiqueta no existe ") {
					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					@SuppressWarnings("unused")
					private void init() {
						this.initCause(this);
					}
				};
			}
			// VALIDAMOS QUE LOS CAMPOS NO ESTEN VACIOS
			if (detSGenerico.getDataSource().equals("") || detSGenerico.getDataSource().equals("null")
					|| detSGenerico.getDataSource().equals(" ")) {
				throw new JsonProcessingException("cabecera.detalleServicioGenerico.dataSource, No puede ser nulo ") {
					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					@SuppressWarnings("unused")
					private void init() {
						this.initCause(this);
					}
				};
			} else {
				detSGenerico.setDataSource(detServGen.get("dataSource").asText());
			}

			cab.setDetalleServicioGenerico(detSGenerico);

		} else if (detSGenerico.getIndServicioGenerico().equals("N")) {

			detSGenerico.setIndServicioGenerico(detServGen.get("indServicioGenerico").asText());
			cab.setDetalleServicioGenerico(detSGenerico);

		} else {
			throw new JsonProcessingException(
					"cabecera.detalleServicioGenerico.indServicioGenerico, No puede ser nulo ") {
				/**
						 * 
						 */
				private static final long serialVersionUID = 1L;

				@SuppressWarnings("unused")
				private void init() {
					this.initCause(this);
				}
			};
		}

		/********************************************************************************
		 * DETALLES3
		 *******************************************************************************/
		if (cabecera.has("detalleS3")) {

			detS3 = cabecera.get("detalleS3");
			logger.debug(ADMIN_USER, "DETALLES3", cabecera.get("detalleS3"));

		} else {

			throw new JsonProcessingException("cabecera.detalleS3, La etiqueta no existe ") {
				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				@SuppressWarnings("unused")
				private void init() {
					this.initCause(this);
				}
			};
		}

		// VALIDAR LA ETIQUETAS indPDF de DETALLES3
		if (detS3.has("indPDF")) {
			indPdf = detS3.get("indPDF");
		} else {
			throw new JsonProcessingException("cabecera.detalleS3.indPDF, La etiqueta no existe ") {
				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				@SuppressWarnings("unused")
				private void init() {
					this.initCause(this);
				}
			};
		}

		// VALIDAR LAS ETIQUETAS indPDF de DETALLES3

		if (indPdf.has("indS3PDF")) {
			indPdfS3.setIndS3PDF(indPdf.get("indS3PDF").asText());
		} else {
			throw new JsonProcessingException("cabecera.detalleS3.indPDF.indS3PDF, La etiqueta no existe ") {
				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				@SuppressWarnings("unused")
				private void init() {
					this.initCause(this);
				}
			};
		}
		// VALIDAMOS QUE INDS3PDF SEA = S

		if (indPdfS3.getIndS3PDF() != null && indPdfS3.getIndS3PDF().equals("S")) {
			// VALIDAMOS QUE LAS ETIQUETAS EXISTAN
			if (indPdf.has("metadata")) {
				metaDataPdf = indPdf.get("metadata");
			} else {
				throw new JsonProcessingException("cabecera.detalleS3.indPdf.metadata, La etiqueta no existe ") {
					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					@SuppressWarnings("unused")
					private void init() {
						this.initCause(this);
					}
				};
			}
			if (indPdf.has("rutaURLDestinoPDF")) {
				indPdfS3.setRutaURLDestinoPDF(indPdf.get("rutaURLDestinoPDF").asText());
			} else {
				throw new JsonProcessingException(
						"cabecera.detalleS3.indPDF.rutaURLDestinoPDF, La etiqueta no existe ") {
					/**
							 * 
							 */
					private static final long serialVersionUID = 1L;

					@SuppressWarnings("unused")
					private void init() {
						this.initCause(this);
					}
				};
			}
			// VALIDACION DE CAMPOS NO ESTEN VACIO
			if (indPdfS3.getRutaURLDestinoPDF().equals("") || indPdfS3.getRutaURLDestinoPDF().equals("null")
					|| indPdfS3.getRutaURLDestinoPDF().equals(" ")) {
				throw new JsonProcessingException("cabecera.detalleS3.indPDF.rutaURLDestinoPDF, No puede ser nulo ") {
					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					@SuppressWarnings("unused")
					private void init() {
						this.initCause(this);
					}
				};
			} else {
				indPdfS3.setRutaURLDestinoPDF(indPdf.get("rutaURLDestinoPDF").asText());
			}

			detalS3.setIndPDF(indPdfS3);

		} else if (indPdfS3.getIndS3PDF().equals("N")) {
			indPdfS3.setIndS3PDF(indPdf.get("indS3PDF").asText());
			detalS3.setIndPDF(indPdfS3);
		} else {
			throw new JsonProcessingException("cabecera.detalleS3.indPDF.indS3PDF, No puede ser nulo ") {
				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				@SuppressWarnings("unused")
				private void init() {
					this.initCause(this);
				}
			};
		}

		// VALIDAR LA ETIQUETA DETALLES3TXT

		if (detS3.has("indTXT")) {
			indTxt = detS3.get("indTXT");
		} else {
			throw new JsonProcessingException("cabecera.detalleS3.indTXT, La etiqueta no existe ") {
				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				@SuppressWarnings("unused")
				private void init() {
					this.initCause(this);
				}
			};
		}

		// VALIDAMOS LAS ETIQUETA DE INDTXT
		if (indTxt.has("indS3TXT")) {

			indTxtS3.setIndS3TXT(indTxt.get("indS3TXT").asText());

		} else {
			throw new JsonProcessingException("cabecera.detalleS3.indTXT.indS3TXT, La etiqueta no existe ") {
				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				@SuppressWarnings("unused")
				private void init() {
					this.initCause(this);
				}
			};
		}

		// VALIDAMOS QUE INDTXT SEA = S
		if (indTxtS3.getIndS3TXT() != null && indTxtS3.getIndS3TXT().equals("S")) {

			if (indTxt.has("metadata")) {
				metaDataHtml = indTxt.get("metadata");
			} else {
				throw new JsonProcessingException("cabecera.detalleS3.indTXT.metadata, La etiqueta no existe ") {
					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					@SuppressWarnings("unused")
					private void init() {
						this.initCause(this);
					}
				};
			}

			if (indTxt.has("rutaURLDestinoTXT")) {
				indTxtS3.setRutaURLDestinoTXT(indTxt.get("rutaURLDestinoTXT").asText());
			} else {
				throw new JsonProcessingException(
						"cabecera.detalleS3.indTXT.rutaURLDestinoTXT, La etiqueta no existe ") {
					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					@SuppressWarnings("unused")
					private void init() {
						this.initCause(this);
					}
				};
			}

			// VALIDAR QUE LOS CAMPOS NO ESTEN VACIOS
			if (indTxtS3.getRutaURLDestinoTXT().equals("") || indTxtS3.getRutaURLDestinoTXT().equals("null")
					|| indTxtS3.getRutaURLDestinoTXT().equals(" ")) {
				throw new JsonProcessingException("cabecera.detalleS3.indTXT.rutaURLDestinoTXT, No puede ser nulo ") {
					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					@SuppressWarnings("unused")
					private void init() {
						this.initCause(this);
					}
				};
			} else {
				// indTxtS3.getRutaURLDestinoTXT(indTxt.get("rutaURLDestinoTXT").asText());
				indTxtS3.setRutaURLDestinoTXT(indTxt.get("rutaURLDestinoTXT").asText());
			}

			detalS3.setIndTXT(indTxtS3);

		} else if (indTxtS3.getIndS3TXT().equals("N")) {

			indTxtS3.setIndS3TXT(indTxt.get("indS3TXT").asText());
			detalS3.setIndTXT(indTxtS3);

		} else {
			throw new JsonProcessingException("cabecera.detalleS3.indTXT.indS3TXT, No puede ser nulo ") {
				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				@SuppressWarnings("unused")
				private void init() {
					this.initCause(this);
				}
			};
		}

		// VALIDANDO ETIQUETA INDHTML DE DETALLES3
		if (detS3.has("indHTML")) {

			indHtml = detS3.get("indHTML");

		} else {

			throw new JsonProcessingException("cabecera.detalleS3.indHTML, La etiqueta no existe ") {
				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				@SuppressWarnings("unused")
				private void init() {
					this.initCause(this);
				}
			};
		}

		// VALIDANDO LAS ETIQUETAS indHTML DETALLES3

		if (indHtml.has("indS3HTML")) {
			indHtmlS3.setIndS3HTML(indHtml.get("indS3HTML").asText());
		} else {
			throw new JsonProcessingException("cabecera.detalleS3.indHTML.indS3HTML, La etiqueta no existe ") {
				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				@SuppressWarnings("unused")
				private void init() {
					this.initCause(this);
				}
			};
		}
		// VALIDAMOS QUE INDHTML SEA = S
		if (indHtmlS3.getIndS3HTML() != null && indHtmlS3.getIndS3HTML().equals("S")) {
			// VALISAMOS QUE EXISTA LAS ETIQUETAS
			if (indHtml.has("metadata")) {
				metaDataHtml = indHtml.get("metadata");
			} else {
				throw new JsonProcessingException("cabecera.detalleS3.indHTML.metadata, La etiqueta no existe ") {
					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					@SuppressWarnings("unused")
					private void init() {
						this.initCause(this);
					}
				};
			}
			if (indHtml.has("rutaURLDestinoHTML")) {
				indHtmlS3.setRutaURLDestinoHTML(indHtml.get("rutaURLDestinoHTML").asText());
			} else {
				throw new JsonProcessingException(
						"cabecera.detalleS3.indHTML.rutaURLDestinoHTML, La etiqueta no existe ") {
					/**
							 * 
							 */
					private static final long serialVersionUID = 1L;

					@SuppressWarnings("unused")
					private void init() {
						this.initCause(this);
					}
				};
			}
			// VALIDAR QUE LOS CAMPOS NO ESTEN VACIO
			if (indHtmlS3.getRutaURLDestinoHTML().equals("")) {
				throw new JsonProcessingException("cabecera.detalleS3.indHTML.rutaURLDestinoHTML, No puede ser nulo ") {
					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					@SuppressWarnings("unused")
					private void init() {
						this.initCause(this);
					}
				};
			} else {
				indHtmlS3.setRutaURLDestinoHTML(indHtml.get("rutaURLDestinoHTML").asText());
			}

			detalS3.setIndHTML(indHtmlS3);
//
		} else if (indHtmlS3.getIndS3HTML().equals("N")) {
			indHtmlS3.setIndS3HTML(indHtml.get("indS3HTML").asText());
			detalS3.setIndHTML(indHtmlS3);
		} else {
			throw new JsonProcessingException("cabecera.detalleS3.indHTML.indS3HTML, No puede ser nulo ") {
				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				@SuppressWarnings("unused")
				private void init() {
					this.initCause(this);
				}
			};
		}

		cab.setDetalleS3(detalS3);

		/********************************************************************************
		 * DETALLEFILENET
		 *******************************************************************************/

		if (cabecera.has("detalleFilenet")) {
			detFNet = cabecera.get("detalleFilenet");
		} else {
			throw new JsonProcessingException("cabecera.detalleFilenet, La etiqueta no existe ") {
				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				@SuppressWarnings("unused")
				private void init() {
					this.initCause(this);
				}
			};
		}

		// VALIDAMOS LAS ETIQUETA DE DETALLEFILENET
		if (detFNet.has("repositoryId")) {
			detFileNet.setRepositoryId(detFNet.get("repositoryId").asText());
		} else {
			throw new JsonProcessingException("cabecera.detalleFilenet.repositoryId, La etiqueta no existe ") {
				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				@SuppressWarnings("unused")
				private void init() {
					this.initCause(this);
				}
			};
		}
		// VALIDAR ETIQUETA INDPDF DETALLEFINET

		if (detFNet.has("indPDF")) {
			indPDFFilenet = detFNet.get("indPDF");
		} else {
			throw new JsonProcessingException("detalleFilenet.indPDF, La etiqueta no existe ") {
				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				@SuppressWarnings("unused")
				private void init() {
					this.initCause(this);
				}
			};
		}

		// VALIDAR LAS ETIQUETAS DE INDPDF DE DETALLEFINET
		if (indPDFFilenet.has("indFilenetPDF")) {
			fileNetPdf.setIndFilenetPDF(indPDFFilenet.get("indFilenetPDF").asText());
		} else {
			throw new JsonProcessingException("detalleFilenet.indPDF.indFilenetPDF, La etiqueta no existe ") {
				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				@SuppressWarnings("unused")
				private void init() {
					this.initCause(this);
				}
			};
		}

		// VALIDAMOS QUE INDFILENETPDF SEA = S
		if (fileNetPdf.getIndFilenetPDF() != null && fileNetPdf.getIndFilenetPDF().equals("S")) {
			// VALIDAR QUE LAS ETIQUETAS EXITAN
			if (indPDFFilenet.has("propiedades")) {
				propFilenetPDF = indPDFFilenet.get("propiedades");

			} else {
				throw new JsonProcessingException("detalleFilenet.indPDF.propiedades, La etiqueta no existe ") {
					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					@SuppressWarnings("unused")
					private void init() {
						this.initCause(this);
					}
				};
			}
			if (indPDFFilenet.has("contentStream")) {
				contStreamFilenetPDF = indPDFFilenet.get("contentStream");
			} else {
				throw new JsonProcessingException("detalleFilenet.indPDF.contentStream, La etiqueta no existe ") {
					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					@SuppressWarnings("unused")
					private void init() {
						this.initCause(this);
					}
				};
			}
			// VALIDAR QUE LOS CAMPOS NO ESTEN VACIOS
			if (propFilenetPDF == null) {
				throw new JsonProcessingException("cabecera.detalleFilenet.indPDF.propiedades, No puede ser nulo ") {
					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					@SuppressWarnings("unused")
					private void init() {
						this.initCause(this);
					}
				};
			}

			if (contStreamFilenetPDF == null) {
				throw new JsonProcessingException("cabecera.detalleFilenet.indPDF.contentStream, No puede ser nulo ") {
					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					@SuppressWarnings("unused")
					private void init() {
						this.initCause(this);
					}
				};
			}

			detFileNet.setIndPDF(fileNetPdf);

		} else if (fileNetPdf.getIndFilenetPDF().equals("N")) {
			fileNetPdf.setIndFilenetPDF(indPDFFilenet.get("indFilenetPDF").asText());
			detFileNet.setIndPDF(fileNetPdf);

		} else {
			throw new JsonProcessingException("cabecera.detalleFilenet.indTXT.indFilenetPDF, No puede ser nulo") {
				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				@SuppressWarnings("unused")
				private void init() {
					this.initCause(this);
				}
			};
		}

		// VALIDAR ETIQUETA INDTXT DETALLEFINET
		if (detFNet.has("indTXT")) {
			indTXTFilenet = detFNet.get("indTXT");
		} else {
			throw new JsonProcessingException("detalleFilenet.indTXT, La etiqueta no existe ") {
				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				@SuppressWarnings("unused")
				private void init() {
					this.initCause(this);
				}
			};
		}
		// VALIDAR LAS ETIQUETAS DE INDTXT DE DETALLEFINET

		if (indTXTFilenet.has("indFilenetTXT")) {
			fileNetTxt.setIndFilenetTXT(indTXTFilenet.get("indFilenetTXT").asText());
		} else {
			throw new JsonProcessingException("detalleFilenet.indTXT.indTXTFilenet, La etiqueta no existe ") {
				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				@SuppressWarnings("unused")
				private void init() {
					this.initCause(this);
				}
			};
		}
		// VALIDAMOS QUE INDFILENETTXT SEA = S

		if (fileNetTxt.getIndFilenetTXT() != null && fileNetTxt.getIndFilenetTXT().equals("S")) {
			// VALIDAR QUE EXISTA LAS ETIQUETAS
			if (indTXTFilenet.has("propiedades")) {
				propFilenetTXT = indTXTFilenet.get("propiedades");
			} else {
				throw new JsonProcessingException("detalleFilenet.indTXT.propiedades, La etiqueta no existe ") {
					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					@SuppressWarnings("unused")
					private void init() {
						this.initCause(this);
					}
				};
			}

			if (indTXTFilenet.has("contentStream")) {
				contStreamFileNetTXT = indTXTFilenet.get("contentStream");
			} else {
				throw new JsonProcessingException("detalleFilenet.indTXT.contentStream, La etiqueta no existe ") {
					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					@SuppressWarnings("unused")
					private void init() {
						this.initCause(this);
					}
				};
			}
			// VALIDAR QUE EL CAMPO NO ESTE VACIO

			if (propFilenetTXT == null) {
				throw new JsonProcessingException("cabecera.detalleFilenet.indTXT.propiedades, No puede ser nulo") {
					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					@SuppressWarnings("unused")
					private void init() {
						this.initCause(this);
					}
				};
			}
			if (contStreamFileNetTXT == null) {
				throw new JsonProcessingException("cabecera.detalleFilenet.indTXT.contentStream, No puede ser nulo") {
					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					@SuppressWarnings("unused")
					private void init() {
						this.initCause(this);
					}
				};
			}

			detFileNet.setIndTXT(fileNetTxt);

		} else if (fileNetTxt.getIndFilenetTXT().equals("N")) {
			fileNetTxt.setIndFilenetTXT(indTXTFilenet.get("indFilenetTXT").asText());
			detFileNet.setIndTXT(fileNetTxt);
		} else {
			throw new JsonProcessingException("cabecera.detalleFilenet.indTXT.indFilenetTXT, No puede ser nulo") {
				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				@SuppressWarnings("unused")
				private void init() {
					this.initCause(this);
				}
			};
		}

		// VALIDAR ETIQUETA INDHTML DETALLEFINET
		if (detFNet.has("indHTML")) {
			indHTMLFilenet = detFNet.get("indHTML");
		} else {
			throw new JsonProcessingException("detalleFilenet.indHTML, La etiqueta no existe ") {
				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				@SuppressWarnings("unused")
				private void init() {
					this.initCause(this);
				}
			};
		}
		// VALIDAR LAS ETIQUETAS DE INDHTML DE DETALLEFINET

		if (indHTMLFilenet.has("indFilenetHTML")) {
			fileNetHtml.setIndFilenetHTML(indHTMLFilenet.get("indFilenetHTML").asText());
		} else {
			throw new JsonProcessingException("detalleFilenet.indHTML.indFilenetHTML, La etiqueta no existe ") {
				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				@SuppressWarnings("unused")
				private void init() {
					this.initCause(this);
				}
			};
		}

		// VALIDAMOS QUE INDHTML SEA =S
		if (fileNetHtml.getIndFilenetHTML() != null && fileNetHtml.getIndFilenetHTML().equals("S")) {
			// VALIDAR QUE LAS ETIQUETAS EXISTAN
			if (indHTMLFilenet.has("propiedades")) {
				propFilenetHtml = indHTMLFilenet.get("propiedades");
			} else {
				throw new JsonProcessingException("detalleFilenet.indHTML.propiedades, La etiqueta no existe ") {
					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					@SuppressWarnings("unused")
					private void init() {
						this.initCause(this);
					}
				};
			}
			if (indHTMLFilenet.has("contentStream")) {
				contStreamFileNetHtml = indHTMLFilenet.get("contentStream");
			} else {
				throw new JsonProcessingException("detalleFilenet.indHTML.contentStream, La etiqueta no existe ") {
					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					@SuppressWarnings("unused")
					private void init() {
						this.initCause(this);
					}
				};
			}
			// VALIDAR QUE LOS CAMPOS NO ESTEN VACIOS
			if (propFilenetHtml == null) {
				throw new JsonProcessingException("cabecera.detalleFilenet.indHTML.propiedades, No puede ser nulo") {
					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					@SuppressWarnings("unused")
					private void init() {
						this.initCause(this);
					}
				};
			}
			if (contStreamFileNetHtml == null) {
				throw new JsonProcessingException("cabecera.detalleFilenet.indHTML.contentStream, No puede ser nulo") {
					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					@SuppressWarnings("unused")
					private void init() {
						this.initCause(this);
					}
				};
			}

			detFileNet.setIndHTML(fileNetHtml);

		} else if (fileNetHtml.getIndFilenetHTML().equals("N")) {
			fileNetHtml.setIndFilenetHTML(indHTMLFilenet.get("indFilenetHTML").asText());
			detFileNet.setIndHTML(fileNetHtml);

		} else {
			throw new JsonProcessingException("cabecera.detalleFilenet.indHTML.contentStream, No puede ser nulo") {
				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				@SuppressWarnings("unused")
				private void init() {
					this.initCause(this);
				}
			};
		}

		cab.setDetalleFilenet(detFileNet);

		// VALIDANDO JSON
		if (node.has("jsonData")) {
			jsonData = node.get("jsonData");
		} else {
			throw new JsonProcessingException("jsonData, La etiqueta no existe ") {
				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				@SuppressWarnings("unused")
				private void init() {
					this.initCause(this);
				}
			};
		}

		solicitudMod.setOrigen(o);

		solicitudMod.setCabecera(cab);

		solicitudMod.setJsonData(jsonData);

		logger.debug(ADMIN_USER, solicitudMod);

		return solicitudMod;
	}

}
