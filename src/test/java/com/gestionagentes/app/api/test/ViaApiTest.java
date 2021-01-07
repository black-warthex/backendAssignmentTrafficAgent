package com.gestionagentes.app.api.test;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import com.gestionagentes.app.dto.ViaDto;
import com.gestionagentes.app.entity.ViaEntity;

import org.junit.Assert;
import lombok.extern.slf4j.Slf4j;


@RunWith(SpringRunner.class)
@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ViaApiTest{
	//trafficAgentApiTest
	@LocalServerPort
	private int port;
	@Autowired
	private TestRestTemplate restTemplate;
	HttpHeaders headers = new HttpHeaders();
	
	
	 private Connection getConnection() throws SQLException {
	        return DriverManager.getConnection(
	            "jdbc:sqlserver://localhost;databaseName=policia_transito",
	            "sa",
	            "prueba");
	 }
	 
	 private int findViaId(ViaDto viaDto) {
		 int viaId =0;
		 try {
		 ps = getConnection().prepareStatement("SELECT via_id FROM tbl_via WHERE via_tipo = ? AND  via_clase = ? AND via_numero=?");
		 ps.setString(1, viaDto.getViaType());
		 ps.setString(2, viaDto.getViaClass());
		 ps.setInt(3, viaDto.getViaNumber());
		 rs = ps.executeQuery();
		 while(rs.next()) {
			 viaId=Integer.parseInt(rs.getString(1));
		 }
		 }catch (SQLException e) {
				log.info("database error: "+e);
			}
		 return viaId;
		 
	 }
	 
	 private void clearViaTable(){
		 try{
			 ps = getConnection().prepareStatement("DELETE FROM tbl_via");
			 ps.executeUpdate();
		 }catch (SQLException e) {
			 log.info("database error: "+e);
		}
	 }
	 
	 private void deleteViaDb(int viaCode) {
			try{
				ps = getConnection().prepareStatement("DELETE FROM tbl_via WHERE via_id = ?");
				ps.setInt(1, viaCode);
				ps.executeUpdate();							
			}catch (SQLException e) {
				log.info("database error: "+e);
			}
	 }
	 
	 private void insertViaDb(){
		 try{
				ps = getConnection().prepareStatement("INSERT INTO tbl_via (via_tipo,via_clase,via_numero,via_nivel_congestion) VALUES (?,?,?,?)");
				ps.setString(1, ("Autopista"));
				ps.setString(2, "Calle");
				ps.setInt(3,80);
				ps.setFloat(4, (float)30.6);				
				ps.executeUpdate();							
			}catch (SQLException e) {
				log.info("database error insert : "+e);
			}
	 }
	 private void insertListViasDb(){
		 try{
				ps = getConnection().prepareStatement("INSERT INTO tbl_via (via_tipo,via_clase,via_numero,via_nivel_congestion) VALUES (?,?,?,?)");
				ps.setString(1, ("Autopista"));
				ps.setString(2, "Calle");
				ps.setInt(3,80);
				ps.setFloat(4, (float)30.6);				
				ps.executeUpdate();	

				ps = getConnection().prepareStatement("INSERT INTO tbl_via (via_tipo,via_clase,via_numero,via_nivel_congestion) VALUES (?,?,?,?)");
				ps.setString(1, ("Autopista"));
				ps.setString(2, "Calle");
				ps.setInt(3,81);
				ps.setFloat(4, (float)30.6);				
				ps.executeUpdate();	

				ps = getConnection().prepareStatement("INSERT INTO tbl_via (via_tipo,via_clase,via_numero,via_nivel_congestion) VALUES (?,?,?,?)");
				ps.setString(1, ("Autopista"));
				ps.setString(2, "Calle");
				ps.setInt(3,82);
				ps.setFloat(4, (float)30.6);				
				ps.executeUpdate();	
				
			}catch (SQLException e) {
				log.info("database error insert : "+e);
			}
	 }
	 private boolean checkViaDbWithInsertVia(ViaDto viaDto) {
		 boolean check  = false;
		 int numberOfVias=0;
		 try {			
			 
			 ps = getConnection().prepareStatement("SELECT COUNT(*) FROM tbl_via WHERE via_tipo = ? AND  via_clase = ? AND via_numero=?");
			 ps.setString(1, viaDto.getViaType());
			 ps.setString(2, viaDto.getViaClass());
			 ps.setInt(3, viaDto.getViaNumber());
			 rs = ps.executeQuery();
			 while(rs.next()) {
				 numberOfVias=Integer.parseInt(rs.getString(1));
			 }
			 
			 if(numberOfVias<=1) {
				 ps = getConnection().prepareStatement("SELECT * FROM tbl_via WHERE via_tipo = ? AND  via_clase = ? AND via_numero=?");
				 ps.setString(1, viaDto.getViaType());
				 ps.setString(2, viaDto.getViaClass());
				 ps.setInt(3, viaDto.getViaNumber());
				 rs = ps.executeQuery();			
				 while(rs.next()){						 
					 ViaDto via = new ViaDto(rs.getLong(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getFloat(5));				
					 if(viaDto.getViaType().equals(via.getViaType()) &&
						viaDto.getViaClass().equals(via.getViaClass()) &&
						viaDto.getViaNumber().equals(via.getViaNumber()) &&
						viaDto.getViaCongestion().equals(via.getViaCongestion())){					
						 check = true;
							   }
						}
			 }
	
					
		}catch (SQLException e) {
			log.info("database error: "+e);
		}				 			 			 
		return check;
	 }
	 
	 private boolean findViaById(int viaId) {		 
		 boolean found  = false;		
		 try {			
			 ps = getConnection().prepareStatement("SELECT * FROM tbl_via WHERE via_id = ?");
			 ps.setLong(1,viaId);
			 rs = ps.executeQuery();
			 if(rs.next()){
				 log.info("id: "+rs.getLong(1));
				 found=true;
			 }
			 
		 }catch (SQLException e) {
			log.info("database error: "+e);
		 }				 			 			 
		return found;
	 };
	 PreparedStatement ps =null;
	 ResultSet rs = null;	
	@org.junit.jupiter.api.Test
	void testInsertNewVia() throws URISyntaxException{			
		
		clearViaTable();
		
		final String urlConf = "http://localhost:"+port+"/via/insertVia";
		URI uri = new URI(urlConf); 
		boolean state = false;
		
		ViaDto viaData = new ViaDto();
		viaData.setViaId((long) 0);
		viaData.setViaType("Autopista");
		viaData.setViaClass("Calle");
		viaData.setViaNumber(80);
		viaData.setViaCongestion((float) 30.2);		
		
		HttpEntity<ViaDto> request = new HttpEntity<>(viaData);
		ResponseEntity<String> result = this.restTemplate.postForEntity(uri, request,String.class);
		state = checkViaDbWithInsertVia(viaData);
		log.info("new via:"+state);
		
		deleteViaDb(findViaId(viaData));
		Assert.assertEquals(true, state);
		Assert.assertEquals(200,result.getStatusCodeValue());		
	}
	
	@org.junit.jupiter.api.Test
	void testInsertDuplicateAgent() throws URISyntaxException{	
		clearViaTable();
		insertViaDb();
		final String urlConf = "http://localhost:"+port+"/via/insertVia";
		URI uri = new URI(urlConf); 
		boolean state = true;
		
		ViaDto viaData = new ViaDto();
		viaData.setViaId(null);
		viaData.setViaType("Autopista");
		viaData.setViaClass("Calle");
		viaData.setViaNumber(80);
		viaData.setViaCongestion((float) 30.2);	
		
		HttpEntity<ViaDto> request = new HttpEntity<>(viaData);
		ResponseEntity<String> result = this.restTemplate.postForEntity(uri, request,String.class);	
		state = checkViaDbWithInsertVia(viaData);
		log.info("insert duplicate agent:"+state);
		deleteViaDb(findViaId(viaData));
		Assert.assertEquals(false, state);
		Assert.assertEquals(200,result.getStatusCodeValue());						
	}
	
	@org.junit.jupiter.api.Test
	void testDeleteAgent() throws URISyntaxException{	
		clearViaTable();
		insertViaDb();
		final String urlConf = "http://localhost:"+port+"/via/deleteVia/{viaId}";		
		boolean state = true;
		
		ViaDto viaData = new ViaDto();
		viaData.setViaId((long) 0);
		viaData.setViaType("Autopista");
		viaData.setViaClass("Calle");
		viaData.setViaNumber(80);
		viaData.setViaCongestion((float) 30.2);	
		
		
		state = findViaById(findViaId(viaData));
		log.info("before delete:"+state);
		
		Map<String, String> params = new HashMap<String, String>();		
		params.put("viaId", String.valueOf(findViaId(viaData)));
		restTemplate.delete(urlConf,params);	
		state = findViaById(findViaId(viaData));
		
		log.info("after delete:"+state);
		Assert.assertEquals(false, state);							
	}
	
	
		@org.junit.jupiter.api.Test
		void testUpdateViaWithData() throws URISyntaxException{	
			clearViaTable();
			insertViaDb();
			final String urlConf = "http://localhost:"+port+"/via/updateVia";			
			boolean state = true;
			
			ViaDto viaData = new ViaDto();		
			viaData.setViaType("Autopista");
			viaData.setViaClass("Calle");
			viaData.setViaNumber(80);
			viaData.setViaCongestion((float) 37.2);				
			viaData.setViaId((long) findViaId(viaData));
			
			state = checkViaDbWithInsertVia(viaData);
			log.info("update after:"+state);
			HttpEntity<ViaDto> request = new HttpEntity<>(viaData);
			restTemplate.put(urlConf, request);				
			state = checkViaDbWithInsertVia(viaData);
			log.info("update before:"+state);
			deleteViaDb(findViaId(viaData));
			Assert.assertEquals(true, state);							
		}	
		@org.junit.jupiter.api.Test
		void testUpdateAgentWithNoData() throws URISyntaxException{		 
			clearViaTable();
			final String urlConf = "http://localhost:"+port+"/via/updateVia";			
			boolean state = true;
			
			ViaDto viaData = new ViaDto();			
			viaData.setViaId((long)1);
			viaData.setViaType("Autopista");
			viaData.setViaClass("Calle");
			viaData.setViaNumber(80);
			viaData.setViaCongestion((float) 37.2);				
			
			state = findViaById(viaData.getViaId().intValue());
			log.info("update after no data:"+state);
			Assert.assertEquals(false, state);	
			HttpEntity<ViaDto> request = new HttpEntity<>(viaData);
			restTemplate.put(urlConf, request);				
			state = findViaById(viaData.getViaId().intValue());
			log.info("update before no data:"+state);			
			Assert.assertEquals(false, state);							
		}
		
		@org.junit.jupiter.api.Test
		void testShowListVia() throws URISyntaxException{		 
			clearViaTable();
			insertListViasDb();
			final String urlConf = "http://localhost:"+port+"/via/showListOfVias";						
			
		    HttpHeaders headers = new HttpHeaders();
		    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		    headers.setContentType(MediaType.APPLICATION_JSON);
		    
			HttpEntity<ViaDto> request = new HttpEntity<>(headers);			
			ResponseEntity<String> result = this.restTemplate.exchange(urlConf,HttpMethod.GET, request,String.class);	
			log.info("get "+result.getBody().length());
			clearViaTable();
			Assert.assertEquals(true, result.getBody().length()>2);							
		}
		
		@org.junit.jupiter.api.Test
		void testFindAgentById() throws URISyntaxException{		 
			clearViaTable();
			insertViaDb();
			
			ViaDto viaData = new ViaDto();
			
			viaData.setViaType("Autopista");
			viaData.setViaClass("Calle");
			viaData.setViaNumber(80);
			viaData.setViaCongestion((float) 30.2);
			viaData.setViaId((long) findViaId(viaData));
			
			final String urlConf = "http://localhost:"+port+"/via/findViaById/{viaId}";						
								
			Map<String, String> params = new HashMap<String, String>();
			    params.put("viaId",String.valueOf(viaData.getViaId()));			
		    		
			ViaEntity result = this.restTemplate.getForObject(urlConf,ViaEntity.class,params);	
			log.info("get BY ID: "+result.getViaId());			
			Assert.assertEquals(true, result.getViaId()==findViaId(viaData));							
		}
		
}
