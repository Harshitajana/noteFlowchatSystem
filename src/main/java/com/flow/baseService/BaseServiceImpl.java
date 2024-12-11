package com.flow.baseService;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;

import com.flow.base.PageRequest;
import com.flow.baseQualification.BasePredicateUtils;
import com.flow.baseQualification.Qualification;
import com.flow.baseRepository.BaseRepository;
import com.flow.base.BaseEntity;

public class BaseServiceImpl<T extends BaseEntity> implements BaseService<T>{
	protected Class<T> domainClass;
	protected BaseRepository<T> repository;

	protected org.slf4j.Logger serviceLogger = LoggerFactory.getLogger("");

	public BaseServiceImpl(Class<T> domainClass, BaseRepository<T> repository) {
		this.domainClass = domainClass;
		this.repository = repository;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public T create(T obj) {
		serviceLogger.trace("End - Before Persist");

		T persistedEntity = repository.save(obj);
		
		if(persistedEntity.getId() == null) {
			return null;
		}	
		//after_create(persistedEntity, obj);
		serviceLogger.trace("End - After Create");
		return persistedEntity;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public T update(UUID id, T incomingObj, Map<String, Object> patchMap) {

		// Before Listener
		//before_update(incomingObj);

		// Recording Timestamp

		T existingObj = repository.getById(id);

		existingObj = performPartialUpdate(incomingObj, existingObj, patchMap);
		// Just Before Persist Listener.
		serviceLogger.trace("Start - Before Persist");
		//before_persist(existingObj);
		serviceLogger.trace("End - Before Persist");

		// Persist
		T persistedEntity = repository.save(existingObj);
		serviceLogger.trace("Persisted to Repository.");

		// After Listener
		serviceLogger.trace("Start - After Update");
		after_update(persistedEntity, incomingObj);
		serviceLogger.trace("End - After Update");
		return persistedEntity;
	}

	@Override
	@Transactional(rollbackFor = Exception.class, readOnly = true)
	public T getById(UUID id) {
		return repository.getById(id);
	}

	@Override
	@Transactional(rollbackFor = Exception.class, readOnly = true)
	public T getOneResultByValue(Qualification qual) {
		return repository.getOneResultByValue(qual);
	}

	@Override
	@Transactional(rollbackFor = Exception.class, readOnly = true)
	public List<T> searchObject(Qualification qual, PageRequest pageRequest) {
		return repository.searchObject(qual, pageRequest);
	}

	@Override
	@Transactional(rollbackFor = Exception.class, readOnly = true)
	public List<T> getList(PageRequest pageRequest) {
		return repository.searchObject(null, pageRequest);
	}

	protected T performPartialUpdate(T incomingModel, T existingModel, Map<String, Object> patchMap) {
		return existingModel;
	}

	protected void after_update(T persistedEntity, T obj) {
	}

	protected void before_update(T obj) {
	}

	protected void after_create(T persistedEntity, T obj) {
	}

	protected void before_persist(T existingObj) {
	}

	protected void before_create(T obj) {
	}

	@Override
	public Page<T> findAll(PageRequest pageRequest, Qualification qualification) {
		Pageable pageable = org.springframework.data.domain.PageRequest.of(pageRequest.getOffSet(),
				pageRequest.getSize(), pageRequest.getSort());
		if (qualification != null) {
			Specification<T> specification = new Specification<T>() {

				private static final long serialVersionUID = 1L;

				@Override
				public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
					return BasePredicateUtils.getPredicate(criteriaBuilder, root, qualification);
				}
			};
			return repository.findAll(specification, pageable);
		}

		return repository.findAll(pageable);
	}

	@Override
	public boolean deleteById(UUID id) {
		try {
			repository.deleteById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	

//	@Override
//	@Transactional(rollbackFor = Exception.class)
//	public long count(Qualification qualification) {
//		if (qualification != null) {
//			Specification<T> specification = (root, query, criteriaBuilder) -> BasePredicateUtils
//					.getPredicate(criteriaBuilder, root, qualification);
//			return repository.count(specification);
//		}
//		return repository.count();
//	}

}
