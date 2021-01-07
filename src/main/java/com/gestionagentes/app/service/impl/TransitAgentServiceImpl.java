package com.gestionagentes.app.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestionagentes.app.dto.ResponseDto;
import com.gestionagentes.app.entity.TransitAgentEntity;
import com.gestionagentes.app.enums.DefaultAnswerEnum;
import com.gestionagentes.app.repository.ITransitAgentRepository;
import com.gestionagentes.app.service.ITransitAgentService;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

/** 
 *@author Yonathan Montilla (warthex)
 *@version 1.0.0
 *@date 06/12/2020
**/
@Slf4j @Service
public class TransitAgentServiceImpl implements ITransitAgentService{
		
	@Autowired
	private ITransitAgentRepository agentRepository;
	private ResponseDto response = new ResponseDto();
	
	/**
	 * Ingresa un nuevo agente de tránsito en la fuente de datos.
	 * 
	 * @Param transitAgentEntity Corresponde a la entidad del agente, se usará para buscar e ingresar un nuevo agente de tránsito en la fuente de datos.
	 * @Return true y mensaje de ingreso establecidos, en caso ingresar al agente en la fuente de datos.
	 * 		   false y mensaje de informacion duplicada establecidos.
	 */
	@Override
	public ResponseDto  insertTransitAgent(@NonNull TransitAgentEntity transitAgentEntity){
		
		response.setSuccess(false);				
		response.setMessage(DefaultAnswerEnum.MESSAGE_DUPLICATE.getValue());
		
		if(agentRepository.findById(transitAgentEntity.getAgentCode()).isEmpty()){
			response.setSuccess(true);
			response.setMessage(DefaultAnswerEnum.MESSAGE_INSERT.getValue());
			agentRepository.save(transitAgentEntity);														
		}
		
		return response;
	}
	/**
	 * Obtiene una lista con todos agentes de tránsito encontrados en la fuente de datos.
	 * 
	 * @Return  Devuelve una lista con todos agentes de tránsito en la fuente de datos.
	 */
	@Override
	public List<TransitAgentEntity> showListOfAgents(){		
		List<TransitAgentEntity> agentData = agentRepository.findAll();
		log.info("data searched");		
		return agentData;
	}
	
	/**
	 * Elimina a un agente de tránsito en la fuente de datos.
	 * 
	 * @Param agentCode Corresponde al codigo de agente de tránsito, se usará para buscar y eliminar en la fuente de datos.
	 * @Return true y mensaje de eliminación establecidos, en caso de encontrar y eliminar al agente en la fuente de datos.
	 * 		   false y mensaje de información no encontrada establecidos.	 
	 */
	@Override
	public ResponseDto deleteTransitAgent(@NonNull String agentCode) {							
		
		response.setSuccess(false);	
		response.setMessage(DefaultAnswerEnum.MESSAGE_NO_DATA.getValue());
		
		if(agentRepository.findById(agentCode).isPresent()){	
			agentRepository.deleteById(agentCode);			
			response.setSuccess(true);
			response.setMessage(DefaultAnswerEnum.MESSAGE_DELETE.getValue());
		}
		
		return response;
	}
	/**
	 * Modifica a un agente de tránsito en la fuente de datos.
	 * 
	 * @Param transitAgentEntity Corresponde a la entidad del agente, se usará para modificar a un agente de tránsito en la fuente de datos.
	 * @Return true y mensaje de modificación establecidos, en caso de encontrar y modificar el agente en la fuente de datos.
	 * 		   false y mensaje de informacion no encontrada establecidos.
	 */
	@Override
	public ResponseDto updateTransitAgent(@NonNull TransitAgentEntity transitAgentEntity){				
		
		response.setSuccess(false);	
		response.setMessage(DefaultAnswerEnum.MESSAGE_NO_DATA.getValue());
		
		if(agentRepository.findById(transitAgentEntity.getAgentCode()).isPresent()){					
			agentRepository.save(transitAgentEntity);			
			response.setSuccess(true);
			response.setMessage(DefaultAnswerEnum.MESSAGE_UPDATE.getValue());
		}
		
		return response;
	}
	
	@Override	
	public Optional<TransitAgentEntity> findTransitAgentById(@NonNull String agentCode) {				
		
		return agentRepository.findById(agentCode);
		
	}

		
}
