package com.gestionagentes.app.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @Builder @AllArgsConstructor @NoArgsConstructor
@Table(name = "tbl_agente_transito")
public class TransitAgentEntity {
	@Id	
	@Column(name = "age_tra_codigo")
	private String agentCode;
	
	@Column(name = "age_tra_nombre")
	private String agentName;
	
	@Column(name = "age_tra_apellido")
	private String agentSurname;
	
	@Column(name = "age_tra_años_experiencia")
	private Float agentYearsExperience;
	
	@Column(name = "age_tra_codigo_secretaria")
	private String agentSecretaryCode;
	
	@Column(name = "age_tra_via_actual")
	private Integer agentViaCode;
}
