package com.flow.baseController;

import java.util.Map;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.flow.baseRest.BaseRest;

public class BaseController {

	protected <UR extends BaseRest> UR convertToUR(Map<String, Object> patchMap, Class<UR> clazz) {
		UR updateReq = null;
		try {
			ObjectMapper mapper = new ObjectMapper();
			mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
			updateReq = mapper.readValue(mapper.writeValueAsString(patchMap), clazz);
			updateReq.setPatchMap(patchMap);

		} catch (InvalidFormatException e) {

		} catch (Throwable e) {

		}

		return updateReq;
	}
}
