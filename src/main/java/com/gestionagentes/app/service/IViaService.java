package com.gestionagentes.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.gestionagentes.app.dto.ResponseDto;
import com.gestionagentes.app.entity.ViaEntity;

import lombok.NonNull;

@Service
public interface IViaService {
		
	/**
	 * Ingresa una nueva via en la fuente de datos.
	 * 
	 * @Param viaEntity Corresponde a la entidad via, se usará para buscar e ingresar a una via en la fuente de datos.
	 * @Return true y mensaje de ingreso establecidos, en caso ingresar la vía en la fuente de datos.
	 * 		   false y mensaje de informacion duplicada establecidos.
	 */
	public ResponseDto insertVia(@NonNull ViaEntity viaEntity);
	
	/**
	 * Obtiene todas la vías encontradas en la fuente de datos.
	 * 
	 * @Return  Devuelve una lista de vías encontradas en la fuente de datos.
	 */
	public List<ViaEntity> showListOfVias();
	
	/**
	 * Modifica a una vía en la fuente de datos.
	 * 
	 * @Param viaEntity Corresponde a la entidad via, se usará para modificar a una via en la fuente de datos.
	 * @Return true y mensaje de modificación establecidos, en caso de encontrar y modificar la vía en la fuente de datos.
	 * 		   false y mensaje de informacion no encontrada establecidos.
	 */
	public ResponseDto updateVia(@NonNull ViaEntity viaEntity);
	
	/**
	 * Elimina a una vía en la fuente de datos.
	 * 
	 * @Param viaId Corresponde al id/codigo de vía, se usará para buscar y eliminar en la fuente de datos.
	 * @Return true y mensaje de eliminacion establecidos, en caso de encontrar y eliminar la vía en la fuente de datos.
	 * 		   false y mensaje de informacion no encontrada establecidos.	 
	 */
	public ResponseDto deleteVia(@NonNull Long viaId);

	public Optional<ViaEntity> findViaById(@NonNull Long viaId);
}

