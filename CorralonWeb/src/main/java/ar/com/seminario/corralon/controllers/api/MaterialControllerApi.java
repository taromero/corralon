package ar.com.seminario.corralon.controllers.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import ar.com.seminario.corralon.domain.Madera;
import ar.com.seminario.corralon.dtos.MaderaDTO;
import ar.com.seminario.corralon.exceptions.MaderasNoEncontradasException;
import ar.com.seminario.corralon.services.MaterialService;

public class MaterialControllerApi {
	@Autowired
	private MaterialService materialService;
	
	@RequestMapping(value = "/materiales/{descripcion}/largo/{largo}/cantidad/{cantidad}")
	public @ResponseBody List<MaderaDTO> encontrarMaderasAjax(@PathVariable String descripcion, 
																@PathVariable String largo, 
																	@PathVariable String cantidad) 
																	throws MaderasNoEncontradasException{
		List<MaderaDTO> lMadDTO = new ArrayList<MaderaDTO>();
		List<Madera> lMad = materialService.obtenerMaderasAdecuadas(descripcion, Float.parseFloat(largo));
		Integer stock;
		for(Madera m : lMad){
			stock = materialService.obtenerStockMadera(descripcion, m.getLargo());
			float sobrante = m.getLargo() - Float.parseFloat(largo)*Float.parseFloat(cantidad);
			if (sobrante >= 0){
				MaderaDTO mDTO= new MaderaDTO(m.getDescripcion(), m.getLargo(), stock, sobrante);
				lMadDTO.add(mDTO);
			}
		}
		return lMadDTO;
	}
}
