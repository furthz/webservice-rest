package pe.soapros.generacionccm.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import pe.soapros.generacionccm.beans.AlmacenamientoFilenet;
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
import pe.soapros.generacionccm.beans.Origen;
import pe.soapros.generacionccm.beans.Peticion;
import pe.soapros.generacionccm.beans.Solicitud;
import pe.soapros.generacionccm.beans.detalleHTML;
import pe.soapros.generacionccm.beans.indHTML_AlmcFilenet3;
import pe.soapros.generacionccm.beans.indPDF_AlmcFilenet;
import pe.soapros.generacionccm.beans.indTXT_AlmcFilenet2;

@Controller
public class PeticionRetrieveController {

	@RequestMapping(method = RequestMethod.GET, value="/student/peticion")
	
	@ResponseBody
	public List<Peticion> getTodasPeticiones(@Valid  @RequestBody Entrada_Peticion peticion) {
	List<Peticion> peticionVr;
	peticionVr = new ArrayList<Peticion>();

			peticionVr = new ArrayList<Peticion>();
			Origen org = new Origen();
			Peticion peti = new Peticion();
			Cabecera cab = new Cabecera();
			//============Origen==========================
			org.getSistema();
			org.setAmbiente("Test");
			org.setProceso("B-001");
			org.setSubproceso("B-001-01");
			org.setFechadeEnvio("2019/02/02 14:23:00");
			org.setUsuario("asas");
			org.setIdentificador1("1");
			org.setIdentificador2("2");
			
			//===============Cabecera======================
			
				//PDF
				DetallePDF detpdf = new DetallePDF();
				detpdf.setIndExito("S");
				detpdf.setIndPDF("S");
				detpdf.setCodError("0");
				detpdf.setMsgError("OK");
			
				//Texto
				DetalleTXT dettext = new DetalleTXT();
				dettext.setIndTXT("S");
				dettext.setArchivo("ruta local");
				dettext.setIndExito("S");
				dettext.setCodError("0");
				dettext.setMsgError("OK");
				
				//HTML
				detalleHTML dethtml = new detalleHTML();
				dethtml.setIndHTML("S");
				dethtml.setArchivo("ruta local + filename");
				dethtml.setIndExito("S");
				dethtml.setCodError("0");
				dethtml.setMsgError("OK");
				
				//SMS
				DetalleSMS detsms = new DetalleSMS();
				detsms.setIndSMS("S");
				detsms.setArchivo("ruta local + filename");
				detsms.setIndExito("S");
				detsms.setCodError("0");
				detsms.setMsgError("OK");
				detsms.setValorretorno("");
				
				//Trazabilidad
				DetalleTrazabilidad dettraz = new DetalleTrazabilidad();
				dettraz.setIndTrazabilidad("S");
				dettraz.setIndExito("S");
				dettraz.setCodError("0");
				dettraz.setMsgError("OK");
				dettraz.setValorretorno("");
				
				//Servicio
				DetalleServicio detsServicio = new DetalleServicio();
				detsServicio.setIndServicio("S");
				detsServicio.setIndExito("S");
				detsServicio.setCodError("0");
				detsServicio.setMsgError("OK");
				detsServicio.setValorretorno(" ");
				
				//==============Almacenamiento S3====================
				AlmacenamientoS3 alms3 = new AlmacenamientoS3();
				
						//======PDF======
						IndPDF_AlmcS3 pdf = new IndPDF_AlmcS3();
						pdf.setIndS3PDF("S");
						pdf.setIndExito("S");
						pdf.setCodError("0");
						pdf.setMsgError("OK");
						pdf.setRutaURLDestinoPDF("https//buckets-amazon/PDF/sinistrosvida/");
						//======TXT======
						IndTXT_AlmcS3 txt = new IndTXT_AlmcS3();
						txt.setIndS3TXT("S");
						txt.setIndExito("S");
						txt.setCodError("0");
						txt.setMsgError("OK");
						txt.setRutaURLDestinoTXT("https//buckets-amazon/TXT/sinistrosvida/");
						//======HTML======
						IndHTML_AlmcS3 html = new IndHTML_AlmcS3();
						html.setIndS3HTML("S");
						html.setIndExito("S");
						html.setCodError("0");
						html.setMsgError("OK");
						html.setRutaURLDestinoHTML("https//buckets-amazon/HTML/sinistrosvida/");
				alms3.setIndPDF(pdf);
				alms3.setIndTXT(txt);
				alms3.setIndHTML(html);
				
				
				//==============Almacenamiento Filenet====================
				AlmacenamientoFilenet filenet = new AlmacenamientoFilenet();
				
						//====PDF====
						indPDF_AlmcFilenet pdf_fil =  new indPDF_AlmcFilenet();
						pdf_fil.setInfFilenetPDF("S");
						pdf_fil.setIndExito("S");
						pdf_fil.setCodError("0");
						pdf_fil.setMsgError("OK");
						pdf_fil.setObjectid("asfsdgdgds");
						//====TXT=====
						indTXT_AlmcFilenet2 TXT_fil = new indTXT_AlmcFilenet2();
						TXT_fil.setInfFilenetTXT("S");
						TXT_fil.setIndExito("S");
						TXT_fil.setCodError("0");
						TXT_fil.setMsgError("OK");
						TXT_fil.setObjectid("asfsdgdgds");
						//=====HTML====
						indHTML_AlmcFilenet3 html_fil = new indHTML_AlmcFilenet3();
						html_fil.setInfFilenetHTML("S");
						html_fil.setIndExito("S");
						html_fil.setCodError("0");
						html_fil.setMsgError("OK");
						html_fil.setObjectid("asfsdgdgds");
						
				filenet.setIndPDF(pdf_fil);
				filenet.setIndTXT(TXT_fil);
				filenet.setIndHTML(html_fil);
			
			cab.setDetallepdf(detpdf);
			cab.setDetalleTXT(dettext);
			cab.setDetalleHTML(dethtml);
			cab.setEnvioSMS(detsms);
			cab.setDetalleTrazabilidad(dettraz);
			cab.setDetalleServicio(detsServicio);
			cab.setAlmacenamientoS3(alms3);
			cab.setAlmacenamientoFilenet(filenet);

				//peticionVr.add(pr);
	peti.setOrigen(org);
	peti.setCabecera(cab);
	peti.setNumOperacion("COD001");
	peticionVr.add(peti);
	return peticionVr;
	}
	
	
	
}
