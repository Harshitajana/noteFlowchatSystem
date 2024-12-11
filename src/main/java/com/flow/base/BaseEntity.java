package com.flow.base;

import java.io.Serializable;
import java.util.HashMap;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public abstract class BaseEntity implements Serializable {

	private static final long serialVersionUID = -2736644676007794348L;
	
	
	@Id
	@Column(name = "id", updatable = false, nullable = false)
	@GeneratedValue
	private UUID id;
	
	
	@JsonIgnore
	@javax.persistence.Transient
	private HashMap<String, Object> contextMap = new HashMap<>();

	public void addContextMap(String key, Object value) {
		this.contextMap.put(key, value);
	}
}
