package com.flow.baseQualification;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes(value = { @JsonSubTypes.Type(value = RelationalQualification.class, name = "relational"),
		@JsonSubTypes.Type(value = BatchQualification.class, name = "batch") })
public class Qualification {
	
	
	private String type;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
