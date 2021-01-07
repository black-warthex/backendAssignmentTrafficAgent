package com.gestionagentes.app.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestionagentes.app.dto.ResponseDto;
import com.gestionagentes.app.entity.AgentManagementEntity;
import com.gestionagentes.app.enums.DefaultAnswerEnum;
import com.gestionagentes.app.repository.IAgentManagementRepository;
import com.gestionagentes.app.service.IAgentManagementService;

import lombok.NonNull;

/** 
 *@author Yonathan Montilla (warthex)
 *@version 1.0.0
 *@date 12/12/2020
**/
@Service
public class AgentManagementServiceImpl implements IAgentManagementService{
	
	@Autowired
	private IAgentManagementRepository managementRepository;	
	private ResponseDto response = new ResponseDto();
	
	/**
	 * Ingresa una nueva asignaión en la fuente de datos.
	 * 
	 * @Param agenManagementEntity Corresponde a la entidad gestion agente, se usará para buscar e ingresar una nueva asignación en la fuente de datos.
	 * @Return true y mensaje de ingreso establecidos, en caso de ingresar la asignación en la fuente de datos.
	 * false y mensaje de informacion duplicada establecidos. 
	 */
	@Override
	public ResponseDto insertAssignment(@NonNull AgentManagementEntity agentManagementEntity){
										
		response.setSuccess(false);
		
		int currentCongestion = managementRepository.findCongestion(agentManagementEntity.getViaCode());
		
		if(currentCongestion < 30){
			response.setMessage(DefaultAnswerEnum.MESSAGE_PERCENTAJE.getValue());
		}else if(managementRepository.countByDateAssignmentAndAgentCodeAndAndViaCode(
				agentManagementEntity.getDateAssignment(),
				agentManagementEntity.getAgentCode(),
				agentManagementEntity.getViaCode())>=3){
			response.setMessage(DefaultAnswerEnum.MESSAGE_LIMIT.getValue());
		}else{			
			managementRepository.insertAssignment(
					agentManagementEntity.getAgentCode(),
					agentManagementEntity.getViaCode(),
					agentManagementEntity.getDateAssignment());
			response.setSuccess(true);
			response.setMessage(DefaultAnswerEnum.MESSAGE_INSERT.getValue());		
		}
			
		return response;
	}
	
	/**
	 * Obtiene todas las asignaciones encontradas en la fuente de datos.
	 * 
	 * @Return Devuelve una lista de asignaciones encontradas en la fuente de datos.
	 */
	
	/**
	 * Busca asignaciones de acuerdo a un paramentro en la fuente de datos.
	 * 
	 * @Param search Corresponde a una cadena de caracteres, se usará para buscar por nombre de via o codigo de agente en la fuente de datos.
	 * @Return Devuelve una lista de asignaciones encontradas en la fuente de datos. 
	 */
	@Override
	public List<AgentManagementEntity> searchAssignmentHistory(String search){
		
		List<AgentManagementEntity> agentManagementEntity = new ArrayList<>();
		agentManagementEntity.addAll(managementRepository.searchAssignmentHistory(search));
		
		return agentManagementEntity;
		
	}
	
	@Override 
	public List<AgentManagementEntity> showAssignmentHistory(){
		
		List<AgentManagementEntity> agentManagementEntity = new ArrayList<>();
		agentManagementEntity.addAll(managementRepository.showAssignmentHistory());
		
		return agentManagementEntity;
		
	}
	/**
	 * Revisa el porcentaje de congestion en la vía y comprueba el limite de asignaciones por día.	
	 * 
	 * @param agentManagementEntity Corresponde a la entidad gestión de agente, se usara para buscar y verificar en la fuente de datos.
	 * @return true si el porcentaje de congestión es mayor a 30 y el limite de asignación es inferior a 3.
	 * 	       false si el porcesaje de congestión es inferior a 30 o el limite de asignación es mayor a 3.
	 */

	
	
}