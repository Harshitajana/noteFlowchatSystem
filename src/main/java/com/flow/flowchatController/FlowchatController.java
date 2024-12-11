package com.flow.flowchatController;

import java.util.Map;
import java.util.UUID;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.flow.baseController.BaseController;
import com.flow.baseRest.DeleteResponse;
import com.flow.flowchat.Flowchat;
import com.flow.flowchatRest.FlowchatRest;
import com.flow.flowchatRest.FlowchatRestOps;
import com.flow.flowchatService.FlowchatService;

@RestController
public class FlowchatController  extends BaseController{
	private static final Log LOGGER = LogFactory.getLog(FlowchatController.class);
	
	@Autowired
	FlowchatService flowchatService;

	@Autowired
	FlowchatRestOps restOps;
	
	
	
	@PostMapping(value = "/flowchat")
	@ResponseStatus(code = HttpStatus.OK)
	public FlowchatRest create(@RequestBody FlowchatRest request) {
		Flowchat domainModel = new Flowchat();
		domainModel = restOps.toDomain(domainModel, request);
		domainModel = flowchatService.create(domainModel);
		return restOps.toGetResponse(domainModel);
	}
	
	
	@PatchMapping(value = "/flowchat/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public FlowchatRest update(@PathVariable("id") UUID id,@RequestBody Map<String, Object> patchMap) {
		FlowchatRest request = convertToUR(patchMap, FlowchatRest.class);
		Flowchat domainModel = new Flowchat();
		domainModel = restOps.toDomain(domainModel, request);
		domainModel = flowchatService.update(id, domainModel, patchMap);
		return restOps.toGetResponse(domainModel);
	}
	

	@GetMapping(value = "/flowchat/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public FlowchatRest getFlowchat(@PathVariable("id") UUID id) {
		Flowchat flow = flowchatService.getById(id);
		return restOps.toGetResponse(flow);
	}
	
	
	@DeleteMapping(value = "/flowchat/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public DeleteResponse DeteteFlowchat(@PathVariable("id") UUID id) {
		LOGGER.info("Delete flowchat Request by id = {}" + id);
		flowchatService.deleteById(id);
		LOGGER.info("Delete flowchat response =  {}" + id);
		return new DeleteResponse(id, HttpStatus.OK);
	}
	
	
	
	
	
	

}
