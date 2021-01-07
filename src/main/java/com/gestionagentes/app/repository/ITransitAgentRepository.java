package com.gestionagentes.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gestionagentes.app.entity.TransitAgentEntity;


public interface ITransitAgentRepository extends JpaRepository<TransitAgentEntity, String>{
			
}
