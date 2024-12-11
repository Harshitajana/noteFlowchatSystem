package com.flow.baseService;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.data.domain.Page;

import com.flow.base.BaseEntity;
import com.flow.base.PageRequest;
import com.flow.baseQualification.Qualification;

public interface BaseService<T extends BaseEntity>  {
	
	public T create( T obj);

	public T update(UUID id, T obj, Map<String, Object> patchMap);

	public T getById(UUID id);

	public T getOneResultByValue(Qualification qual);

	public List<T> searchObject(Qualification qual, PageRequest pageRequest);

	public List<T> getList(PageRequest pageRequest);

	public boolean deleteById(UUID id);

	public Page<T> findAll(PageRequest pageRequest, Qualification qualification);

}
