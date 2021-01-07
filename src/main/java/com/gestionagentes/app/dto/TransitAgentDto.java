package com.gestionagentes.app.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class TransitAgentDto {
	
	private String agentCode;
	private String agentName;
	private String agentSurname;
	private Float agentYearsExperience;
	private String agentSecretaryCode;
	private Integer agentViaCode;
	
}
