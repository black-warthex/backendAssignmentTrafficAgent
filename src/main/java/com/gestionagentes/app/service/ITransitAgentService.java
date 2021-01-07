package com.gestionagentes.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.gestionagentes.app.dto.ResponseDto;
import com.gestionagentes.app.entity.TransitAgentEntity;

import lombok.NonNull;

@Service
public interface ITransitAgentService {
	
	/**
	 * Ingresa un nuevo agente de tránsito en la fuente de datos.
	 * 
	 * @Param transitAgentEntity Corresponde a la entidad del agente, se usará para buscar e ingresar un nuevo agente de tránsito en la fuente de datos.
	 * @Return true y mensaje de ingreso establecidos, en caso ingresar al agente en la fuente de datos.
	 * 		   false y mensaje de informacion duplicada establecidos.
	 */
	public ResponseDto insertTransitAgent(@NonNull TransitAgentEntity transitAgentEntity);
	
	/**
	 * Obtiene una lista con todos agentes de tránsito encontrados en la fuente de datos.
	 * 
	 * @Return  Devuelve una lista con todos agentes de tránsito en la fuente de datos.
	 */
	public List<TransitAgentEntity> showListOfAgents();
	
	/**
	 * Elimina a un agente de tránsito en la fuente de datos.
	 * 
	 * @Param agentCode Corresponde al codigo de agente de tránsito, se usará para buscar y eliminar en la fuente de datos.
	 * @Return true y mensaje de eliminación establecidos, en caso de encontrar y eliminar al agente en la fuente de datos.
	 * 		   false y mensaje de información no encontrada establecidos.	 
	 */
	public ResponseDto deleteTransitAgent(@NonNull String agentCode);
	
	/**
	 * Modifica a un agente de tránsito en la fuente de datos.
	 * 
	 * @Param transitAgentEntity Corresponde a la entidad del agente, se usará para modificar a un agente de tránsito en la fuente de datos.
	 * @Return true y mensaje de modificación establecidos, en caso de encontrar y modificar el agente en la fuente de datos.
	 * 		   false y mensaje de informacion no encontrada establecidos.
	 */
	public ResponseDto updateTransitAgent(@NonNull TransitAgentEntity transitAgentEntity);

	public Optional<TransitAgentEntity> findTransitAgentById(@NonNull String agentCode);
	
}
