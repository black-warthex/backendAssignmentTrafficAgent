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
@Table(name = "tbl_via")
public class ViaEntity {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "via_id")
	private Long viaId;
	
	@Column(name = "via_tipo")
	private String viaType;
	
	@Column(name = "via_clase")
	private String viaClass;
	
	@Column(name = "via_numero")
	private Integer viaNumber;
	
	@Column(name = "via_nivel_congestion")
	private Float viaCongestion;
	
}
