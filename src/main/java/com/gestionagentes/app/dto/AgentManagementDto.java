package com.gestionagentes.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class AgentManagementDto {
	
	private Long agentManagementId;
	private String agentCode;
	private Long viaCode;
	private String dateAssignment;
	private String routeName;
	
}
