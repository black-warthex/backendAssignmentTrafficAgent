package com.gestionagentes.app.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "tbl_agente_transito_tbl_via")
public class AgentManagementEntity {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "age_tra_via_id")
	private Long agentManagementId;
	
	@Column(name = "age_tra_via_codigo_agente")
	private String agentCode;
	
	@Column(name = "age_tra_via_id_via")
	private Long viaCode;
	
	@Column(name = "age_tra_via_fecha_asignacion")
	private String dateAssignment;
	
	@Column(name = "via_nombre_gestion")
	private String routeName;
	
	
} 
