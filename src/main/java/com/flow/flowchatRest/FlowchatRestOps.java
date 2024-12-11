package com.flow.flowchatRest;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;


import com.flow.baseRest.BaseRestOps;
import com.flow.flowchat.Flowchat;

@Component
public class FlowchatRestOps extends BaseRestOps<Flowchat, FlowchatRest>{
	
	
	
	@Override
	public Flowchat toDomain(Flowchat hqBase, FlowchatRest createRequest) {
		hqBase = super.toDomain(hqBase, createRequest);
		hqBase.setNodes(createRequest.getNodes());
		hqBase.setEdges(createRequest.getEdges());
		return hqBase;
	}

	@Override
	public FlowchatRest toGetResponse(Flowchat hqBase) {
		FlowchatRest rest = super.toGetResponse(hqBase);
		rest.setNodes(hqBase.getNodes());
		rest.setEdges(hqBase.getEdges());
		return rest;
	}

	@Override
	protected FlowchatRest getRestModel() {
		return new FlowchatRest();
	}
	
}
