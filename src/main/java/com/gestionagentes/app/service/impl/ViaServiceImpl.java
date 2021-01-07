package com.gestionagentes.app.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestionagentes.app.dto.ResponseDto;
import com.gestionagentes.app.entity.ViaEntity;
import com.gestionagentes.app.enums.DefaultAnswerEnum;
import com.gestionagentes.app.repository.IViaRepository;
import com.gestionagentes.app.service.IViaService;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

/** 
 *@author Yonathan Montilla (warthex)
 *@version 1.0.0
 *@date 06/12/2020
**/

@Slf4j @Service
public class ViaServiceImpl implements IViaService{
	
	@Autowired 
	private IViaRepository viaRepository;
	private ResponseDto response = new ResponseDto();
	/**
	 * Ingresa una nueva via en la fuente de datos.
	 * 
	 * @Param viaEntity Corresponde a la entidad via, se usará para buscar e ingresar a una via en la fuente de datos.
	 * @Return true y mensaje de ingreso establecidos, en caso ingresar la vía en la fuente de datos.
	 * 		   false y mensaje de informacion duplicada establecidos.
	 */
	@Override
	public ResponseDto insertVia(@NonNull ViaEntity viaEntity){	
		
		response.setSuccess(false);						
		response.setMessage(DefaultAnswerEnum.MESSAGE_DUPLICATE.getValue());
		
		log.info(viaEntity.getViaType());
		log.info(viaEntity.getViaClass());
		log.info(String.valueOf(viaEntity.getViaNumber()));
		List<ViaEntity> viaData = viaRepository.findByViaTypeAndViaClassAndViaNumber(
				viaEntity.getViaType(), 
				viaEntity.getViaClass(),
				viaEntity.getViaNumber());
		
		if(viaData.isEmpty()) {
			viaRepository.save(viaEntity);			
			response.setSuccess(true);
			response.setMessage(DefaultAnswerEnum.MESSAGE_INSERT.getValue());
		}	
		
		return response;		
	}
	/**
	 * Obtiene todas la vías encontradas en la fuente de datos.
	 * 
	 * @Return  Devuelve una lista de vías encontradas en la fuente de datos.
	 */
	@Override
	public List<ViaEntity> showListOfVias(){		
		List<ViaEntity> viaEntity = viaRepository.findAll();
		log.info("data searched");		
		return viaEntity;
	}
	/**
	 * Modifica a una vía en la fuente de datos.
	 * 
	 * @Param viaEntity Corresponde a la entidad via, se usará para modificar a una via en la fuente de datos.
	 * @Return true y mensaje de modificación establecidos, en caso de encontrar y modificar la vía en la fuente de datos.
	 * 		   false y mensaje de informacion no encontrada establecidos.
	 */
	@Override
	public ResponseDto updateVia(@NonNull ViaEntity viaEntity){
		
		response.setSuccess(false);			
		response.setMessage(DefaultAnswerEnum.MESSAGE_NO_DATA.getValue());	
		
		if(viaRepository.findById(viaEntity.getViaId()).isPresent()){					
			viaRepository.save(viaEntity);			
			response.setSuccess(true);
			response.setMessage(DefaultAnswerEnum.MESSAGE_UPDATE.getValue());
		}	
		
		return response;
	}
	/**
	 * Elimina a una vía en la fuente de datos.
	 * 
	 * @Param viaId Corresponde al id/codigo de vía, se usará para buscar y eliminar en la fuente de datos.
	 * @Return true y mensaje de eliminacion establecidos, en caso de encontrar y eliminar la vía en la fuente de datos.
	 * 		   false y mensaje de informacion no encontrada establecidos.	 
	 */
	@Override
	public ResponseDto deleteVia(@NonNull Long viaId){
		
		response.setSuccess(false);			
		response.setMessage(DefaultAnswerEnum.MESSAGE_NO_DATA.getValue());
		
		if(viaRepository.findById(viaId).isPresent()){			
			viaRepository.deleteById(viaId);						
			response.setSuccess(true);
			response.setMessage(DefaultAnswerEnum.MESSAGE_DELETE.getValue());	
		}			
		
		return response;
	}
	
	@Override
	public Optional<ViaEntity> findViaById(@NonNull Long viaId) {				
		
		return viaRepository.findById(viaId);
		
	}
		
		
}
