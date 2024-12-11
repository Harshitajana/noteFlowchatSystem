package com.flow.flowchatRest;

import java.util.List;

import com.flow.baseRest.BaseRest;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FlowchatRest  extends BaseRest{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8274361920359431752L;
	private List<String> nodes;
    private List<String> edges;
    
	
}
