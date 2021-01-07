package com.gestionagentes.app.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.gestionagentes.app.dto.ResponseDto;
import com.gestionagentes.app.entity.AgentManagementEntity;

import lombok.NonNull;

@Service
public interface IAgentManagementService {

	/**
	 * Ingresa una nueva asignaión en la fuente de datos.
	 * 
	 * @Param agenManagementEntity Corresponde a la entidad gestion agente, se usará para buscar e ingresar una nueva asignación en la fuente de datos.
	 * @Return true y mensaje de ingreso establecidos, en caso de ingresar la asignación en la fuente de datos.
	 *         false y mensaje de informacion duplicada establecidos. 
	 */
	public ResponseDto insertAssignment(@NonNull AgentManagementEntity agentManagementEntity);
	
	/**
	 * Obtiene todas las asignaciones encontradas en la fuente de datos.
	 * 
	 * @Return Devuelve una lista de asignaciones encontradas en la fuente de datos.
	 */
	public List<AgentManagementEntity> showAssignmentHistory();
	
	/**
	 * Busca asignaciones de acuerdo a un paramentro en la fuente de datos.
	 * 
	 * @Param searchGestionAgente Corresponde a una cadena de caracteres, se usará para buscar por nombre de via o codigo de agente en la fuente de datos.
	 * @Return Devuelve una lista de asignaciones encontradas en la fuente de datos. 
	 */	
	public List<AgentManagementEntity> searchAssignmentHistory(@NonNull String searchGestionAgente);
}
