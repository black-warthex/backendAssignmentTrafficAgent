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
import com.gestionagentes.app.dto.TransitAgentDto;
import com.gestionagentes.app.entity.TransitAgentEntity;


import org.junit.Assert;
import lombok.extern.slf4j.Slf4j;


@RunWith(SpringRunner.class)
@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TransitAgentApiTest{
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
	 
	 private void clearAgentTable(){
		 try{
			 ps = getConnection().prepareStatement("DELETE FROM tbl_agente_transito");
			 ps.executeUpdate();
		 }catch (SQLException e) {
			 log.info("database error: "+e);
		}
	 }
	 
	 private void deleteAgentDb(String agentCode) {
			try{
				ps = getConnection().prepareStatement("DELETE FROM tbl_agente_transito WHERE age_tra_codigo = ?");
				ps.setString(1, agentCode);
				ps.executeUpdate();							
			}catch (SQLException e) {
				log.info("database error: "+e);
			}
	 }
	 
	 private void insertAgentDb(){
		 try{
				ps = getConnection().prepareStatement("INSERT INTO tbl_agente_transito VALUES (?,?,?,?,?,?)");
				ps.setString(1, "WTX-1");
				ps.setString(2, "Yonathan");
				ps.setString(3, "Montilla");
				ps.setFloat(4, (float) 1.6);
				ps.setString(5, "SCTRY");
				ps.setString(6, null);
				ps.executeUpdate();							
			}catch (SQLException e) {
				log.info("database error insert : "+e);
			}
	 }
	 private void insertListAgentDb(){
		 try{
				ps = getConnection().prepareStatement("INSERT INTO tbl_agente_transito VALUES (?,?,?,?,?,?)");
				ps.setString(1, "WTX-1");
				ps.setString(2, "Yonathan");
				ps.setString(3, "Montilla");
				ps.setFloat(4, (float) 1.6);
				ps.setString(5, "SCTRY");
				ps.setString(6, null);
				ps.executeUpdate();	

				ps = getConnection().prepareStatement("INSERT INTO tbl_agente_transito VALUES (?,?,?,?,?,?)");
				ps.setString(1, "WTX-2");
				ps.setString(2, "Yonathan");
				ps.setString(3, "Montilla");
				ps.setFloat(4, (float) 1.6);
				ps.setString(5, "SCTRY");
				ps.setString(6, null);
				ps.executeUpdate();	
				
				ps = getConnection().prepareStatement("INSERT INTO tbl_agente_transito VALUES (?,?,?,?,?,?)");
				ps.setString(1, "WTX-3");
				ps.setString(2, "Yonathan");
				ps.setString(3, "Montilla");
				ps.setFloat(4, (float) 1.6);
				ps.setString(5, "SCTRY");
				ps.setString(6, null);
				ps.executeUpdate();	
			}catch (SQLException e) {
				log.info("database error insert : "+e);
			}
	 }
	 private boolean checkAgentDbWithInsertAgent(TransitAgentDto agentDto) {
		 boolean check  = false;
		 int numberOfAgents=0;
		 try {			
			 
			 ps = getConnection().prepareStatement("SELECT COUNT(*) FROM tbl_agente_transito WHERE age_tra_codigo = ?");
			 ps.setString(1,agentDto.getAgentCode());
			 rs = ps.executeQuery();
			 while(rs.next()) {
				 numberOfAgents=Integer.parseInt(rs.getString(1));
			 }
			 
			 if(numberOfAgents<=1) {
				 ps = getConnection().prepareStatement("SELECT * FROM tbl_agente_transito WHERE age_tra_codigo = ?");
				 ps.setString(1,agentDto.getAgentCode());
				 rs = ps.executeQuery();			
				 while(rs.next()){				 
					 TransitAgentDto agent = new TransitAgentDto(rs.getString(1),rs.getString(2),rs.getString(3),rs.getFloat(4),rs.getString(5),rs.getInt(6));				
					 if(agentDto.getAgentCode().equals(agent.getAgentCode()) && 
				   	    agentDto.getAgentName().equals(agent.getAgentName()) &&
						agentDto.getAgentSurname().equals(agent.getAgentSurname()) &&
						agentDto.getAgentYearsExperience().equals(agent.getAgentYearsExperience()) &&
						agentDto.getAgentSecretaryCode().equals(agent.getAgentSecretaryCode())){					
						 check = true;
							   }
						}
			 }
	
					
		}catch (SQLException e) {
			log.info("database error: "+e);
		}				 			 			 
		return check;
	 }
	 
	 private boolean findAgentById(String agentCode) {
		 boolean found  = false;		
		 try {			
			 ps = getConnection().prepareStatement("SELECT * FROM tbl_agente_transito WHERE age_tra_codigo = ?");
			 ps.setString(1,agentCode);
			 rs = ps.executeQuery();
			 if(rs.next()){
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
	void testInsertNewAgent() throws URISyntaxException{			
		
		clearAgentTable();
		
		final String urlConf = "http://localhost:"+port+"/transitAgent/insertAgent";
		URI uri = new URI(urlConf); 
		boolean state = false;
		
		TransitAgentDto agentData = new TransitAgentDto();
		agentData.setAgentCode("WTX-1");
		agentData.setAgentName("warthex");
		agentData.setAgentSurname("leviatan");
		agentData.setAgentYearsExperience((float)1.3);
		agentData.setAgentSecretaryCode("second");
		agentData.setAgentViaCode(null);
		
		HttpEntity<TransitAgentDto> request = new HttpEntity<>(agentData);
		ResponseEntity<String> result = this.restTemplate.postForEntity(uri, request,String.class);
		state = checkAgentDbWithInsertAgent(agentData);
		log.info("new agent:"+state);			
		deleteAgentDb(agentData.getAgentCode());
		Assert.assertEquals(true, state);
		Assert.assertEquals(200,result.getStatusCodeValue());		
	}
	
	@org.junit.jupiter.api.Test
	void testInsertDuplicateAgent() throws URISyntaxException{	
		clearAgentTable();
		insertAgentDb();
		final String urlConf = "http://localhost:"+port+"/transitAgent/insertAgent";
		URI uri = new URI(urlConf); 
		boolean state = true;
		
		TransitAgentDto agentData = new TransitAgentDto();
		agentData.setAgentCode("WTX-1");
		agentData.setAgentName("warthex");
		agentData.setAgentSurname("leviatan");
		agentData.setAgentYearsExperience((float)1.3);
		agentData.setAgentSecretaryCode("second");
		agentData.setAgentViaCode(null);
		
		HttpEntity<TransitAgentDto> request = new HttpEntity<>(agentData);
		ResponseEntity<String> result = this.restTemplate.postForEntity(uri, request,String.class);	
		state = checkAgentDbWithInsertAgent(agentData);
		log.info("insert duplicate agent:"+state);
		deleteAgentDb(agentData.getAgentCode());
		Assert.assertEquals(false, state);
		Assert.assertEquals(200,result.getStatusCodeValue());			
	}

	@org.junit.jupiter.api.Test
	void testDeleteAgent() throws URISyntaxException{		 
		insertAgentDb();
		final String urlConf = "http://localhost:"+port+"/transitAgent/deleteAgent/{agentCode}";		
		boolean state = true;
		state = findAgentById("WTX-1");
		log.info("before:"+state);
		Map<String, String> params = new HashMap<String, String>();
		params.put("agentCode", "WTX-1");
		restTemplate.delete(urlConf,params);	
		state = findAgentById("WTX-1");
		
		log.info("after:"+state);
		Assert.assertEquals(false, state);							
	}
	
	
		@org.junit.jupiter.api.Test
		void testUpdateAgentWithData() throws URISyntaxException{		 
			insertAgentDb();
			final String urlConf = "http://localhost:"+port+"/transitAgent/updateAgent";			
			boolean state = true;
			
			TransitAgentDto agentData = new TransitAgentDto();
			agentData.setAgentCode("WTX-1");
			agentData.setAgentName("warthex");
			agentData.setAgentSurname("leviatan");
			agentData.setAgentYearsExperience((float)1.3);
			agentData.setAgentSecretaryCode("second");
			agentData.setAgentViaCode(null);
			state = checkAgentDbWithInsertAgent(agentData);
			log.info("update after:"+state);
			HttpEntity<TransitAgentDto> request = new HttpEntity<>(agentData);
			restTemplate.put(urlConf, request);				
			state = checkAgentDbWithInsertAgent(agentData);
			log.info("update before:"+state);
			deleteAgentDb(agentData.getAgentCode());
			Assert.assertEquals(true, state);							
		}
		
		@org.junit.jupiter.api.Test
		void testUpdateAgentWithNoData() throws URISyntaxException{		 
			clearAgentTable();
			final String urlConf = "http://localhost:"+port+"/transitAgent/updateAgent";			
			boolean state = true;
			
			TransitAgentDto agentData = new TransitAgentDto();
			agentData.setAgentCode("WTX-1");
			agentData.setAgentName("warthex");
			agentData.setAgentSurname("leviatan");
			agentData.setAgentYearsExperience((float)1.3);
			agentData.setAgentSecretaryCode("second");
			agentData.setAgentViaCode(null);
			state = findAgentById(agentData.getAgentCode());
			log.info("update after:"+state);
			Assert.assertEquals(false, state);	
			HttpEntity<TransitAgentDto> request = new HttpEntity<>(agentData);
			restTemplate.put(urlConf, request);				
			state = findAgentById(agentData.getAgentCode());
			log.info("update before:"+state);			
			Assert.assertEquals(false, state);							
		}
		
		@org.junit.jupiter.api.Test
		void testShowListAgent() throws URISyntaxException{		 
			clearAgentTable();
			insertListAgentDb();
			final String urlConf = "http://localhost:"+port+"/transitAgent/showListOfAgents";						
			
		    HttpHeaders headers = new HttpHeaders();
		    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		    headers.setContentType(MediaType.APPLICATION_JSON);
		    
			HttpEntity<TransitAgentDto> request = new HttpEntity<>(headers);			
			ResponseEntity<String> result = this.restTemplate.exchange(urlConf,HttpMethod.GET, request,String.class);	
			log.info("get "+result.getBody().length());
			clearAgentTable();
			Assert.assertEquals(true, result.getBody().length()>2);							
		}
		
		@org.junit.jupiter.api.Test
		void testFindAgentById() throws URISyntaxException{		 
			clearAgentTable();
			insertAgentDb();
			final String urlConf = "http://localhost:"+port+"/transitAgent//findTransitAgentById/{agentCode}";						
								
			Map<String, String> params = new HashMap<String, String>();
			    params.put("agentCode", "WTX-1");			
		    		
			TransitAgentEntity result = this.restTemplate.getForObject(urlConf,TransitAgentEntity.class,params);	
			log.info("get "+result.getAgentCode());			
			Assert.assertEquals(true, result.getAgentCode().equals("WTX-1"));							
		}
		
		
	
	
}
