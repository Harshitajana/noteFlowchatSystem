package com.flow;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.flow.flowchat.Flowchat;
import com.flow.flowchatService.FlowchatService;
@SpringBootTest
class NoteFlowchatSystemApplicationTests {
	
	@Autowired
	FlowchatService service;
	
	Flowchat flow=new Flowchat();
	
	
	@BeforeEach
    void setup() {
        Flowchat flowchat = new Flowchat();
        flowchat.setEdges(List.of("e1", "e2"));
        flowchat.setNodes(List.of("n1", "n2"));
        flow = service.create(flowchat); // `create` method returns an auto-generated ID
    }
	@Test
	void create() {
		
		Flowchat flowchat=new Flowchat();
		
		flowchat.setEdges(List.of("e1", "e2"));
		flowchat.setNodes(List.of("n1", "n2"));
		flowchat =service.create(flowchat);
		Flowchat createdFlowchart  = service.getById(flowchat.getId());
		assertNotNull(createdFlowchart);
	}
	@Test
	void getflow() {
		Flowchat fetchedFlowchart = service.getById(flow.getId());
		assertNotNull(fetchedFlowchart);
	}
	@Test
	void delete() {
		service.deleteById(flow.getId());
		Flowchat deletedFlowchart = service.getById(flow.getId());
		
		assertEquals(null, deletedFlowchart);
	}
	@Test
	void update() {
		Map<String, Object> patchMap=new HashMap<>();
		Flowchat flowchat=new Flowchat();
		flowchat.setEdges(List.of("w2", "w3"));
		patchMap.put("1", flowchat);
        service.update(flow.getId(), flowchat, patchMap);

        Flowchat fetchedFlowchart = service.getById(flow.getId());
        assertNotNull(fetchedFlowchart);
		
	}
	
}

