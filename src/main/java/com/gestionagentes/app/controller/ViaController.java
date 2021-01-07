package com.gestionagentes.app.controller;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.gestionagentes.app.dto.ResponseDto;
import com.gestionagentes.app.dto.ViaDto;
import com.gestionagentes.app.entity.ViaEntity;
import com.gestionagentes.app.service.IViaService;
import com.gestionagentes.app.utility.DtoToEntity;
import com.gestionagentes.app.utility.EntityToDto;

@RestController

@RequestMapping("/via")
public class ViaController {
	
	@Autowired
	private IViaService service;
	private DtoToEntity changeToEntity = new DtoToEntity();
	private EntityToDto changeToDto = new EntityToDto();
	
	/**
	 * Ingresa una nueva via en la fuente de datos.
	 * 
	 * @Param viaDto Corresponde a el dto via, se usará para convertir en entidad y buscar e ingresar a una via en la fuente de datos.
	 * @Return true y mensaje de ingreso establecidos, en caso ingresar la vía en la fuente de datos.
	 * 		   false y mensaje de informacion duplicada establecidos.
	 */
	@PostMapping(path = "/insertVia" ,consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<ResponseDto> insertVia(@RequestBody ViaDto viaDto){
		
			return new ResponseEntity<>(service.insertVia(changeToEntity.viaDtoToEntity(viaDto)),HttpStatus.OK);
	}
	
	/**
	 * Obtiene todas la vías encontradas en la fuente de datos.
	 * 
	 * @Return  Devuelve una lista de vías encontradas en la fuente de datos.
	 */
	
	@GetMapping(path = "/showListOfVias" ,produces = MediaType.APPLICATION_JSON_VALUE)	
	public @ResponseBody ResponseEntity<List<ViaDto>> showListOfVias(){				
		
			return new ResponseEntity<>(changeToDto.viaEntityToDto(service.showListOfVias()),HttpStatus.OK);
	}
	
	@GetMapping(path = "/findViaById/{viaId}" ,produces = MediaType.APPLICATION_JSON_VALUE)	
	public Optional<ViaEntity> findViaById(@PathVariable long viaId){				
		
			return service.findViaById(viaId);
	}

	/**
	 * Modifica a una vía en la fuente de datos.
	 * 
	 * @Param viaEntity Corresponde a dto via, se usará para convetir en entidad vía y modificar a una via en la fuente de datos.
	 * @Return true y mensaje de modificación establecidos, en caso de encontrar y modificar la vía en la fuente de datos.
	 * 		   false y mensaje de informacion no encontrada establecidos.
	 */
	@PutMapping(path = "/updateVia",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<ResponseDto> updateVia(@RequestBody ViaDto viaDto){
						
			return new ResponseEntity<>(service.updateVia(changeToEntity.viaDtoToEntity(viaDto)),HttpStatus.OK);
	}
	
	/**
	 * Elimina a una vía en la fuente de datos.
	 * 
	 * @Param viaId Corresponde al id/codigo de vía, se usará para buscar y eliminar en la fuente de datos.
	 * @Return true y mensaje de eliminacion establecidos, en caso de encontrar y eliminar la vía en la fuente de datos.
	 * 		   false y mensaje de informacion no encontrada establecidos.	 
	 */
	@DeleteMapping(path = "/deleteVia/{viaId}",produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<ResponseDto> deleteVia(@PathVariable Long viaId){
		
			return new ResponseEntity<>(service.deleteVia(viaId),HttpStatus.OK);
	}
}
