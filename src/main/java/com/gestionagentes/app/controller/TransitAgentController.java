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

import com.gestionagentes.app.dto.TransitAgentDto;
import com.gestionagentes.app.entity.TransitAgentEntity;
import com.gestionagentes.app.dto.ResponseDto;
import com.gestionagentes.app.service.ITransitAgentService;
import com.gestionagentes.app.utility.DtoToEntity;
import com.gestionagentes.app.utility.EntityToDto;



@RestController 
@RequestMapping("/transitAgent")

public class TransitAgentController {
	
	@Autowired
	private ITransitAgentService service;
	private DtoToEntity changeToEntity = new DtoToEntity();
	private EntityToDto changeToDto = new EntityToDto();
	
	/**
	 * Ingresa un nuevo agente de tránsito en la fuente de datos.
	 * 
	 * @Param transitAgentDto Corresponde a dto del agente, se usará para convertir en entidad y buscar e ingresar un nuevo agente de tránsito en la fuente de datos.
	 * @Return true y mensaje de ingreso establecidos, en caso ingresar al agente en la fuente de datos.
	 * 		   false y mensaje de informacion duplicada establecidos.
	 */
	@PostMapping(path = "/insertAgent" ,consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<ResponseDto> insertTransitAgent(@RequestBody TransitAgentDto transitAgentDto){		
		
			return new ResponseEntity<>(service.insertTransitAgent(changeToEntity.transitAgentDtoToEntity(transitAgentDto)),
					   HttpStatus.OK);
			
	}
	
	/**
	 * Obtiene una lista con todos agentes de tránsito encontrados en la fuente de datos.
	 * 
	 * @Return  Devuelve una lista con todos agentes de tránsito en la fuente de datos.
	 */
	@GetMapping(path = "/showListOfAgents" ,produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<List<TransitAgentDto>> showListOfAgents(){
					
			return new ResponseEntity<>(changeToDto.transitAgentEntityToDto(service.showListOfAgents()),HttpStatus.OK);
	}
	
	@GetMapping(path = "/findTransitAgentById/{agentCode}" ,produces = MediaType.APPLICATION_JSON_VALUE)	
	public Optional<TransitAgentEntity> findTransitAgentById(@PathVariable String agentCode){				
		
			return service.findTransitAgentById(agentCode);
	}
	
	/**
	 * Elimina a un agente de tránsito en la fuente de datos.
	 * 
	 * @Param agentCode Corresponde al código de agente de tránsito, se usará para buscar y eliminar en la fuente de datos.
	 * @Return true y mensaje de eliminación establecidos, en caso de encontrar y eliminar al agente en la fuente de datos.
	 * 		   false y mensaje de información no encontrada establecidos.	 
	 */
	@DeleteMapping(path =  "/deleteAgent/{agentCode}",produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<ResponseDto> deleteTransitAgent(@PathVariable String agentCode){
		
			return new ResponseEntity<>(service.deleteTransitAgent(agentCode),HttpStatus.OK);
	}
	
	/**
	 * Modifica a un agente de tránsito en la fuente de datos.
	 * 
	 * @Param transitAgentDto Corresponde a dto del agente, se usará para convetir en entidad y modificar a un agente de tránsito en la fuente de datos.
	 * @Return true y mensaje de modificación establecidos, en caso de encontrar y modificar el agente en la fuente de datos.
	 * 		   false y mensaje de informacion no encontrada establecidos.
	 */
	@PutMapping(path = "/updateAgent",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<ResponseDto> updateAgenteTransito(@RequestBody TransitAgentDto transitAgentDto){				
						
			return new ResponseEntity<>(service.updateTransitAgent(changeToEntity.transitAgentDtoToEntity(transitAgentDto)),
					   HttpStatus.OK);
	}
	
}
