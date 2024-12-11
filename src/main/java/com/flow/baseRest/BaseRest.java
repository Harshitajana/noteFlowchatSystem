package com.flow.baseRest;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BaseRest implements Serializable {
	 
	 
	private static final long serialVersionUID = 373191072353875456L;
	
	private UUID id;
	
	@JsonIgnore
	Map<String, Object> patchMap = new LinkedHashMap<>();

}
