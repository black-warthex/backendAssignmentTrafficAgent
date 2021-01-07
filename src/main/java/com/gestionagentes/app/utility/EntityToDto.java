package com.gestionagentes.app.utility;

import java.util.ArrayList;
import java.util.List;

import com.gestionagentes.app.dto.TransitAgentDto;
import com.gestionagentes.app.dto.AgentManagementDto;
import com.gestionagentes.app.dto.ViaDto;
import com.gestionagentes.app.entity.TransitAgentEntity;
import com.gestionagentes.app.entity.AgentManagementEntity;
import com.gestionagentes.app.entity.ViaEntity;


public class EntityToDto {
	/**
	 * Convierte una lista de entidad via en una lista dto via.
	 * 
	 * @param viaEntity Corresponde a una lista de entidad vía, se usará los atributos para asignarlos a una lista de dto vía. 
	 * @return Devuelve una lista de dto via, sus datos seran iguales a la lista de dto vía.
	 */
	public List<ViaDto> viaEntityToDto(List<ViaEntity> viaEntity){
		
		List<ViaDto> viaDto = new ArrayList<>();		
		viaEntity.stream().forEach(i->{
			
			ViaDto viaData = new ViaDto();			
			viaData.setViaId(i.getViaId());
			viaData.setViaType(i.getViaType());
			viaData.setViaClass(i.getViaClass());
			viaData.setViaNumber(i.getViaNumber());
			viaData.setViaCongestion(i.getViaCongestion());			
			viaDto.add(viaData);
		});		
		
		return viaDto;
	}
	/**
	 * Convierte una lista de entidad de agente de tránsito en un lista dto agente de tránsito.
	 * 
	 * @param transitAgentEntity Corresponde  a una lista de entidad agente de tránsito, se usará los atributos para asignarlos a una lista de dto agente de tránsito.
	 * @return Devuelve una lista dto agente de tránsito, sus datos seran iguales a la lista dto agente tránsito.
	 */
	public List<TransitAgentDto> transitAgentEntityToDto(List<TransitAgentEntity> transitAgentEntity){
		
		List<TransitAgentDto> transitAgentData = new ArrayList<>();		
		transitAgentEntity.stream().forEach(i->{
			
			TransitAgentDto transitAgent = new TransitAgentDto();			
			transitAgent.setAgentCode(i.getAgentCode());
			transitAgent.setAgentName(i.getAgentName());
			transitAgent.setAgentSurname(i.getAgentSurname());
			transitAgent.setAgentYearsExperience(i.getAgentYearsExperience());
			transitAgent.setAgentSecretaryCode(i.getAgentSecretaryCode());
			transitAgent.setAgentViaCode(i.getAgentViaCode());
			transitAgentData.add(transitAgent);			
		});
			
		return transitAgentData;
					
	}
	/**
	 * Conviente una lista de entidad de gestión agente a una lista dto gestión agente.
	 * 
	 * @param agentManagementEntity Corresponde a una lista de entidad de gestión agente, se usará los atributos para asignalos a una lista de dto gestión agente.
	 * @return 	Devuelve una lista dto gestión agente, sus datos seran iguales a la lista de dto gestión agente.
	 */
	public List<AgentManagementDto> agentManagementEntityToDto(List<AgentManagementEntity> agentManagementEntity){
		
		List<AgentManagementDto> agentManagementDto = new ArrayList<>();		
		agentManagementEntity.stream().forEach(i->{
			
			AgentManagementDto agentManagementData = new AgentManagementDto();			
			agentManagementData.setAgentCode(i.getAgentCode());
			agentManagementData.setAgentManagementId(i.getAgentManagementId());
			agentManagementData.setViaCode(i.getViaCode());
			agentManagementData.setDateAssignment(i.getDateAssignment());
			agentManagementData.setRouteName(i.getRouteName());			
			
			agentManagementDto.add(agentManagementData);
		});		
		
		return agentManagementDto;
	}
	
}
