package com.gestionagentes.app.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


import com.gestionagentes.app.dto.AgentManagementDto;
import com.gestionagentes.app.dto.ResponseDto;
import com.gestionagentes.app.service.IAgentManagementService;
import com.gestionagentes.app.utility.DtoToEntity;
import com.gestionagentes.app.utility.EntityToDto;

@RestController
@RequestMapping("/agentManagement")
public class AgentManagementController {
	
	@Autowired
	private IAgentManagementService service;
	private DtoToEntity changeToEntity = new DtoToEntity();
	private EntityToDto changeToDto = new EntityToDto();
	
	/**
	 * Ingresa una nueva asignaión en la fuente de datos.
	 * 
	 * @Param agenManagementDto Corresponde a dto gestión agente, se usará para convertir en entidad y buscar e ingresar una nueva asignación en la fuente de datos.
	 * @Return true y mensaje de ingreso establecidos, en caso de ingresar la asignación en la fuente de datos.
	 *         false y mensaje de informacion duplicada establecidos. 
	 */
	@PostMapping(path = "/insertAssignment", consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<ResponseDto> insertAssignment(
						@RequestBody AgentManagementDto agentManagementDto){	
		
			return new ResponseEntity<>(service.insertAssignment(changeToEntity.agentManagementDtoToEntity(agentManagementDto)),
					   HttpStatus.OK);
	}
	
	/**
	 * Obtiene todas las asignaciones encontradas en la fuente de datos.
	 * 
	 * @Return Devuelve una lista de asignaciones encontradas en la fuente de datos.
	 */
	@GetMapping(path = "/showAssignmentHistory",produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<List<AgentManagementDto>> showAssignmentHistory(){		
		
			return new ResponseEntity<>(changeToDto.agentManagementEntityToDto(service.showAssignmentHistory()),HttpStatus.OK);
	}
	
	/**
	 * Busca asignaciones de acuerdo a un paramentro en la fuente de datos.
	 * 
	 * @Param search Corresponde a una cadena de caracteres, se usará para buscar por nombre de via o codigo de agente en la fuente de datos.
	 * @Return Devuelve una lista de asignaciones encontradas en la fuente de datos. 
	 */	
	@GetMapping(path = "/searchAssignmentHistory/{search}",produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody ResponseEntity<List<AgentManagementDto>> searchAssignmentHistory(
		                 @PathVariable String search){
		
			return new ResponseEntity<>(changeToDto.agentManagementEntityToDto(service.searchAssignmentHistory(search)),
					   HttpStatus.OK);
	}
	
	

}
