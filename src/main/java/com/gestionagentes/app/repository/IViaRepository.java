package com.gestionagentes.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gestionagentes.app.entity.ViaEntity;


public interface IViaRepository extends JpaRepository<ViaEntity, Long>{
	
	public List<ViaEntity> findByViaTypeAndViaClassAndViaNumber(String viaType,String viaClass,Integer viaNumber);
	
	
}
