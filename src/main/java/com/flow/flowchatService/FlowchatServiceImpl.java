package com.flow.flowchatService;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flow.baseService.BaseServiceImpl;
import com.flow.flowchat.Flowchat;
import com.flow.flowchatRepository.FlowchatRepository;

@Service
public class FlowchatServiceImpl extends BaseServiceImpl<Flowchat> implements FlowchatService {

	public FlowchatServiceImpl(@Autowired FlowchatRepository repository) {
		super(Flowchat.class, repository);
	}
	
	
	@Override
	protected Flowchat performPartialUpdate(Flowchat incomingModel, Flowchat existingModel, Map<String, Object> patchMap) {
		serviceLogger.info("-------------Flowchat Update Method of FlowchatServiceImpl");
		existingModel = super.performPartialUpdate(incomingModel, existingModel, patchMap);

		if (patchMap.containsKey("nodes")) {
			serviceLogger.debug("update Flowchat Nodes Id, old Data = {}, new Data ={}, id = {}",
					existingModel.getNodes(), incomingModel.getNodes(), existingModel.getId());
			existingModel.setNodes(incomingModel.getNodes());
		}
		if (patchMap.containsKey("edges")) {
			serviceLogger.debug("update Flowchat Edges Id, old Data = {}, new Data ={}, id = {}",
					existingModel.getEdges(), incomingModel.getEdges(), existingModel.getId());
			existingModel.setEdges(incomingModel.getEdges());
		}

		return existingModel;
	}


}
