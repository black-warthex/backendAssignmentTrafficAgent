package com.gestionagentes.app.enums;

public enum DefaultAnswerEnum {
	
	MESSAGE_INSERT("successful insert"),
	MESSAGE_DELETE("successful delete"),
	MESSAGE_UPDATE("sucessful update"),
	MESSAGE_ERROR("error process failed"),
	MESSAGE_DUPLICATE("you cannot enter, you are already registered"),
	MESSAGE_NO_DATA("no information was found in the database"),
	MESSAGE_LIMIT("you can not assign this route to the agent has exceeded the limit of 3 per day"),
	MESSAGE_PERCENTAJE("it is not possible to assign a traffic agent, the congestion of the road is below thirty")
	;
	
	private String value;
	
	private DefaultAnswerEnum(String value){
		this.value=value;
	}
	
	public String getValue() {
		return value;
	}

}
