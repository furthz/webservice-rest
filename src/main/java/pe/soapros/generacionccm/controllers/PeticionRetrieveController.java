package pe.soapros.generacionccm.controllers;

import java.io.IOException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import pe.soapros.generacionccm.beans.Entrada_Peticion;
import pe.soapros.generacionccm.beans.PeticionOUT;
import pe.soapros.generacionccm.business.PeticionBO;

@Controller
public class PeticionRetrieveController {

	@Autowired
	private PeticionBO peticionBO;

	@RequestMapping(method = RequestMethod.POST, value = "/consultar/pedido")

	@ResponseBody
	public PeticionOUT getTodasPeticiones(@Valid @RequestBody Entrada_Peticion peticion) {
  
		PeticionOUT rpta = null;
		
		try {
			rpta = this.peticionBO.consultarPeticion(peticion);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return rpta;
	}

}
