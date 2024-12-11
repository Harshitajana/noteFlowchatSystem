package com.flow.base;

import org.springframework.data.domain.Sort;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PageRequest {
	
	private int offSet;
	private int size;
	private Sort sort;
	private long total;

}
