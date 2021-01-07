package com.gestionagentes.app.utility;


import com.gestionagentes.app.dto.TransitAgentDto;
import com.gestionagentes.app.dto.AgentManagementDto;
import com.gestionagentes.app.dto.ViaDto;
import com.gestionagentes.app.entity.TransitAgentEntity;
import com.gestionagentes.app.entity.AgentManagementEntity;
import com.gestionagentes.app.entity.ViaEntity;

import lombok.NonNull;


public class DtoToEntity {
	
	/**
	 * Convierte la clase dto vía en una entidad vía.
	 * 
	 * @param viaDto Corresponde al Dto de vía, se usará los atributos para asignarlos a una entidad de vía.
	 * @return Devuelve una entidad de via, sus datos seran iguales a los ingresados en el dto.
	 */
	public ViaEntity viaDtoToEntity(@NonNull ViaDto viaDto){
				
		ViaEntity viaEntity = new ViaEntity();
		viaEntity.setViaId(viaDto.getViaId());
		viaEntity.setViaType(viaDto.getViaType());
		viaEntity.setViaClass(viaDto.getViaClass());
		viaEntity.setViaNumber(viaDto.getViaNumber());
		viaEntity.setViaCongestion(viaDto.getViaCongestion());											
		
		return viaEntity;
		
	}
	/**
	 * Conviente la clase dto agente de tránsito en una entidad de agente de tránsito.
	 * 
	 * @param transitAgentDto Corresponde al Dto de agent de tránsito, se usará los atributos para asginarlos a una entidad de agente de tránsito.
	 * @return Devuelve una entidad de agente de tránsito, sus datos seran iguales a los ingresados en el dto.
	 */
	public TransitAgentEntity transitAgentDtoToEntity (@NonNull TransitAgentDto transitAgentDto){
		
		TransitAgentEntity transitAgentEntity = new TransitAgentEntity(); 
		transitAgentEntity.setAgentCode(transitAgentDto.getAgentCode());  
		transitAgentEntity.setAgentName(transitAgentDto.getAgentName());  
		transitAgentEntity.setAgentSurname(transitAgentDto.getAgentSurname());
		transitAgentEntity.setAgentYearsExperience(transitAgentDto.getAgentYearsExperience());
		transitAgentEntity.setAgentSecretaryCode(transitAgentDto.getAgentSecretaryCode());
		transitAgentEntity.setAgentViaCode(transitAgentDto.getAgentViaCode());
			
		return transitAgentEntity;
			
	}
	/**
	 * Conviente la clase dto gestión de agente en una entidad de gestión de agente.
	 * 
	 * @param agentManagement Corresponde al Dto de gestión de agente, se usará los atributos para asignarlos a una entidad de gestión agente.
	 * @return Devuelve una entidad de gestión de agente, sus datos seran iguales a los ingresados en el dto.
	 */
	public AgentManagementEntity agentManagementDtoToEntity(@NonNull AgentManagementDto agentManagement){
		
		AgentManagementEntity agentManagementEntity = new AgentManagementEntity();
		agentManagementEntity.setAgentCode(agentManagement.getAgentCode());
		agentManagementEntity.setViaCode(agentManagement.getViaCode());		
		agentManagementEntity.setDateAssignment(agentManagement.getDateAssignment());
		
		return agentManagementEntity;
			
	}
		
}
