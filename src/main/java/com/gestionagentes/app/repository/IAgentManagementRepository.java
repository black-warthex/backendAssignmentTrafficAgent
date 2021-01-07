package com.gestionagentes.app.repository;


import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.gestionagentes.app.entity.AgentManagementEntity;




public interface IAgentManagementRepository extends JpaRepository<AgentManagementEntity, Long> {
	
	/**
	 * Obtiene el número de registros de una asignación mediante el código de agente/vía y fecha de asignación.
	 *  
	 * @param date Corresponde a la fecha de asignación, se usará para buscar en la fuente de datos.
	 * @param agentCode Corresponde a el código del agente, se usará para buscar en la fuente de datos.
	 * @param viaCode Corresponde a el código de vía, se usará para buscar en la fuente de datos.
	 * @return Devuelve el número de registro de una asignación.
	 */
	public long countByDateAssignmentAndAgentCodeAndAndViaCode
	(String date,
	 String agentCode,
	 long viaCode);
	
	/**
	 * Obtiene el nivel de congestión de una vía.
	 * 
	 * @param viaCode Corresponde a el código de vía , se usará para buscar en la fuente de datos.
	 * @return Devuelve el nivel de congestión de una vía.
	 */
	@Query(value = "EXEC find_number_congestion @via_id=:via_id",nativeQuery=true)
	public Integer findCongestion(@Param("via_id") Long viaCode);
	
	/**
	 * Obtiene en una lista todas las asignaciones encontradas en la fuente de datos.
	 * 
	 * @return Devuelve una lista de asignaciones encontradas en la fuente de datos.
	 */
	@Query(value = "exec search_assignment_history",nativeQuery = true)
	public List<AgentManagementEntity> showAssignmentHistory();
	
	/**
	 * Busca todas las asignaciones que concuerden con una palabra en la fuente de datos.
	 * 
	 * @param search Corresponde a una cadena de caracteres, se usará para buscar en la fuente de datos.
	 * 
	 * @return Devuelve una lista de asignaciones encontradas en la fuente de datos.
	 */
	@Query(value = "exec search_assignment_history @search=:search",nativeQuery = true)
	public List<AgentManagementEntity> searchAssignmentHistory(@Param("search") String search );
	
	/**
	 * Ingresa un nueva asignación en la fuente de datos.
	 * 
	 * @param agentCode Corresponde al código del agente, se usará para ingresarlo en la fuente de datos.
	 * @param viaCode Corresponde al código de vía, se usará para ingresarlo en la fuente de datos.	 
	 * @param assignmentDate Corresponde a la fecha de asignación, se usará para ingresarlo en la fuente de datos.
	 */
	@Modifying
	@Transactional
	@Query(value = "EXEC insert_assignment @agentCode=:agentCode,@viaCode=:viaCode,@assignmentDate=:assignmentDate",nativeQuery = true)
	public void insertAssignment(@Param("agentCode") String agentCode,
								@Param("viaCode") Long viaCode,
								@Param("assignmentDate") String assignmentDate);
				
}

