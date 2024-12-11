package com.flow.baseRest;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;

import com.flow.base.BaseEntity;

public abstract class BaseRestOps<T extends BaseEntity, R extends BaseRest > {
	
	public T toDomain(T base, R createRequest) {
		base.setId(createRequest.getId());
		return base;
	}

	public R toGetResponse(T base) {
		R response = getRestModel();
		response.setId(base.getId());
		
		return response;
	}

	protected abstract R getRestModel();

	public List<R> toRestList(List<T> domainModelList) {
		List<R> restList = null;
		if (CollectionUtils.isNotEmpty(domainModelList)) {
			restList = domainModelList.stream().map(m -> toGetResponse(m)).filter(java.util.Objects::nonNull)
					.collect(Collectors.toList());
		}
		return restList;
	}

}
