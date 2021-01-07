package com.gestionagentes.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class ViaDto {
	
	private Long viaId;
	private String viaType;
	private String viaClass;
	private Integer viaNumber;
	private Float viaCongestion;
	
}
