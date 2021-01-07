package com.gestionagentes.app.api.test;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
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
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import com.gestionagentes.app.dto.AgentManagementDto;
import com.gestionagentes.app.dto.ViaDto;

import java.net.URI;

import lombok.extern.slf4j.Slf4j;


@RunWith(SpringRunner.class)
@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AssignmentApiTest{
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
	 
	 private void clearAllTables(){
		 try{
			 ps = getConnection().prepareStatement("DELETE FROM tbl_via");
			 ps.executeUpdate();
			 
			 ps = getConnection().prepareStatement("DELETE FROM tbl_agente_transito");
			 ps.executeUpdate();
			 
			 ps = getConnection().prepareStatement("DELETE FROM tbl_agente_transito_tbl_via");
			 ps.executeUpdate();
		 }catch (SQLException e) {
			 log.info("database error: "+e);
		}
	 }
	 
	 
	 private void insertDataDb(){
		 try{
				ps = getConnection().prepareStatement("INSERT INTO tbl_agente_transito VALUES (?,?,?,?,?,?)");
				ps.setString(1, "WTX-1");
				ps.setString(2, "Yonathan");
				ps.setString(3, "Montilla");
				ps.setFloat(4, (float) 1.6);
				ps.setString(5, "SCTRY");
				ps.setString(6, null);
				ps.executeUpdate();	
				
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
	 private void insertBadDataDb(){
		 try{
				ps = getConnection().prepareStatement("INSERT INTO tbl_agente_transito VALUES (?,?,?,?,?,?)");
				ps.setString(1, "WTX-1");
				ps.setString(2, "Yonathan");
				ps.setString(3, "Montilla");
				ps.setFloat(4, (float) 1.6);
				ps.setString(5, "SCTRY");
				ps.setString(6, null);
				ps.executeUpdate();	
				
				ps = getConnection().prepareStatement("INSERT INTO tbl_via (via_tipo,via_clase,via_numero,via_nivel_congestion) VALUES (?,?,?,?)");
				ps.setString(1, ("Autopista"));
				ps.setString(2, "Calle");
				ps.setInt(3,80);
				ps.setFloat(4, (float)20.6);				
				ps.executeUpdate();	
				
			}catch (SQLException e) {
				log.info("database error insert : "+e);
			}
	 }
	 
	 private void insertListAssignmentDb(){
		 try{
				ps = getConnection().prepareStatement("INSERT INTO tbl_agente_transito VALUES (?,?,?,?,?,?)");
				ps.setString(1, "WTX-1");
				ps.setString(2, "Yonathan");
				ps.setString(3, "Montilla");
				ps.setFloat(4, (float) 1.6);
				ps.setString(5, "SCTRY");
				ps.setString(6, null);
				ps.executeUpdate();							
				
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
				
				ps = getConnection().prepareStatement("exec listAssignment");
				ps.executeUpdate();	
							
				
		 }catch (SQLException e) {
				log.info("database error insert : "+e);
			}			
			
	 }
	 

	 private boolean checkAssignmentDbWithInsertAssignment(AgentManagementDto assignment) {
		 boolean check  = false;
		 int numberOfVias=0;
		 try {			
			 
			 ps = getConnection().prepareStatement("SELECT COUNT(*) FROM tbl_agente_transito_tbl_via WHERE age_tra_via_codigo_agente  = ? AND  age_tra_via_id_via  = ? AND age_tra_via_fecha_asignacion =?");
			 ps.setString(1, assignment.getAgentCode());
			 ps.setLong(2, assignment.getViaCode());
			 ps.setString(3, assignment.getDateAssignment());
			 rs = ps.executeQuery();
			 while(rs.next()) {
				 numberOfVias=Integer.parseInt(rs.getString(1));
			 }
			 
			 if(numberOfVias<=1) {
				 ps = getConnection().prepareStatement("SELECT * FROM tbl_agente_transito_tbl_via WHERE age_tra_via_codigo_agente  = ? AND  age_tra_via_id_via  = ? AND age_tra_via_fecha_asignacion =?");
				 ps.setString(1, assignment.getAgentCode());
				 ps.setLong(2, assignment.getViaCode());
				 ps.setString(3, assignment.getDateAssignment());
				 rs = ps.executeQuery();			
				 while(rs.next()){						 
					 AgentManagementDto assg = new AgentManagementDto();
					 assg.setAgentCode(rs.getString(2));
					 assg.setViaCode(rs.getLong(3));
					 assg.setDateAssignment(rs.getString(4));					
					 if(assignment.getAgentCode().equals(assg.getAgentCode()) &&
						assignment.getViaCode().equals(assg.getViaCode()) &&
						assignment.getDateAssignment().equals(assg.getDateAssignment())){					
						 check = true;
							   }
						}
			 }
	
					
		}catch (SQLException e) {
			log.info("database error: "+e);
		}				 			 			 
		return check;
	 }
	 
	 PreparedStatement ps =null;
	 ResultSet rs = null;
	@Test
	void testInsertNewAssignment() throws URISyntaxException{			
		
		clearAllTables();
		insertDataDb();
		
		ViaDto viaData = new ViaDto();		
		viaData.setViaType("Autopista");
		viaData.setViaClass("Calle");
		viaData.setViaNumber(80);
		viaData.setViaId((long) findViaId(viaData));	
		
		final String urlConf = "http://localhost:"+port+"/agentManagement/insertAssignment";
		URI uri = new URI(urlConf); 
		boolean state = false;
		
		AgentManagementDto assignment = new AgentManagementDto();
		assignment.setAgentCode("WTX-1");
		assignment.setViaCode(viaData.getViaId());
		assignment.setDateAssignment("2020-12-31");
	
		
		HttpEntity<AgentManagementDto> request = new HttpEntity<>(assignment);
		ResponseEntity<String> result = this.restTemplate.postForEntity(uri, request,String.class);
		state = checkAssignmentDbWithInsertAssignment(assignment);
		log.info(""+result);
		log.info("new assignment:"+state);
		
		clearAllTables();
		Assert.assertEquals(true, state);
		
	}
	@Test
	@Sql()
	void testInsertNewBadAssignment() throws URISyntaxException{			
		
		clearAllTables();
		insertBadDataDb();
		
		ViaDto viaData = new ViaDto();		
		viaData.setViaType("Autopista");
		viaData.setViaClass("Calle");
		viaData.setViaNumber(80);
		viaData.setViaId((long) findViaId(viaData));	
		
		final String urlConf = "http://localhost:"+port+"/agentManagement/insertAssignment";
		URI uri = new URI(urlConf); 
		boolean state = false;
		
		AgentManagementDto assignment = new AgentManagementDto();
		assignment.setAgentCode("WTX-1");
		assignment.setViaCode(viaData.getViaId());
		assignment.setDateAssignment("2020-12-31");
	
		
		HttpEntity<AgentManagementDto> request = new HttpEntity<>(assignment);
		ResponseEntity<String> result = this.restTemplate.postForEntity(uri, request,String.class);
		state = checkAssignmentDbWithInsertAssignment(assignment);
		log.info(""+result);
		log.info("new bad assignment:"+state);
		clearAllTables();		
		Assert.assertEquals(false, state);
		
	}
	

		@org.junit.jupiter.api.Test
		void testShowListAssignment() throws URISyntaxException{		 
			clearAllTables();
			insertListAssignmentDb();
			final String urlConf = "http://localhost:"+port+"/agentManagement/showAssignmentHistory";						
			
		    HttpHeaders headers = new HttpHeaders();
		    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		    headers.setContentType(MediaType.APPLICATION_JSON);
		    
			HttpEntity<AgentManagementDto> request = new HttpEntity<>(headers);			
			ResponseEntity<String> result = this.restTemplate.exchange(urlConf,HttpMethod.GET, request,String.class);			
			log.info("get "+result.getBody());
			clearAllTables();
			Assert.assertEquals(true, result.getBody().length()>2);							
		}
		
		@org.junit.jupiter.api.Test
		void testSearchByCodeAgentOrRouteName() throws URISyntaxException{		 
			clearAllTables();
			insertListAssignmentDb();			
			
			final String urlConf = "http://localhost:"+port+"/agentManagement/searchAssignmentHistory/{search}";						
								
			Map<String, String> params = new HashMap<String, String>();
			    params.put("search","calle 80");			
		    		
			    HttpHeaders headers = new HttpHeaders();
			    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
			    headers.setContentType(MediaType.APPLICATION_JSON);
			    
				HttpEntity<AgentManagementDto> request = new HttpEntity<>(headers);				
				ResponseEntity<String> result = this.restTemplate.exchange(urlConf,HttpMethod.GET, request,String.class,params);
			 							
				clearAllTables();
				Assert.assertEquals(true, result.getBody().length()>2);													
												
		}
	
}
