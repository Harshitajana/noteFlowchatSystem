package com.flow.baseRepository;

import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.jpa.repository.query.QueryUtils;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;


import com.flow.base.BaseEntity;
import com.flow.base.PageRequest;
import com.flow.baseQualification.BasePredicateUtils;
import com.flow.baseQualification.Qualification;
import com.google.common.collect.Lists;


public class BaseRepositoryImpl<T extends BaseEntity> extends SimpleJpaRepository<T, UUID>
implements BaseRepository<T> {
	
	
	protected Class<T> domainClass;
	protected EntityManager em;
	
	public BaseRepositoryImpl(Class<T> domainClass, EntityManager em) {
		super(domainClass, em);
		this.domainClass = domainClass;
		this.em = em;
	}	


	@Override
	public <S extends T> S save(S entity) {
		entity = super.save(entity);
		return entity;
	}

	@Override
	public T getById(UUID id) {
		T object = null;
		if (id.equals(null)) {
			return null;
		}
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<T> query = cb.createQuery(domainClass);
		Root<T> root = query.from(domainClass);
		object = getOneResult(cb.equal(root.get("id"), id), query, root, true);

		return object;
	}

	private <S> S getOneResult(Predicate predicate, CriteriaQuery<S> query, Root<T> root, boolean doNotSort) {
		List<S> resultList = getResultSet(predicate, root, query, null, doNotSort);
		S obj = null;
		if (resultList != null && resultList.size() > 0) {
			obj = resultList.get(0);
		}
		return obj;
	}

	private <S> List<S> getResultSet(Predicate predicate, Root<T> root, CriteriaQuery<S> query, PageRequest pageRequest,
			boolean doSort) {
		if (predicate != null) {
			query = query.where(predicate);
		}

		// Sorting
		if (doSort) {
			Sort sort = null;
			if (pageRequest != null) {
				sort = pageRequest.getSort();
			}
			// Default Sort
			if (sort == null) {
				sort = Sort.by(Lists.newArrayList(Order.desc("id")));
			}

			query.orderBy(QueryUtils.toOrders(sort, root, em.getCriteriaBuilder()));

		}
		TypedQuery<S> result = em.createQuery(query);

		// Paging
		if (pageRequest != null && pageRequest.getSize() != 0) {
			result.setFirstResult(pageRequest.getOffSet());
			result.setMaxResults(pageRequest.getSize());
		}

		List<S> resultList = null;
		if (result != null) {
			resultList = result.getResultList();
		}
		return resultList;
	}

	@Override
	public List<T> searchObject(Qualification qual, PageRequest pageRequest) {

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<T> query = cb.createQuery(domainClass);
		Root<T> root = query.from(domainClass);
		Predicate predicate = BasePredicateUtils.getPredicate(cb, root, qual);

		return getResultSet(predicate, root, query, pageRequest, true);
	}

	protected <P, S> Selection<?>[] buildSelectionArray(Root<T> root, List<String> dbColumns) {
		Selection<?>[] selectionArray = new Selection<?>[dbColumns.size()];
		for (int index = 0; index < dbColumns.size(); index++) {
			selectionArray[index] = root.get(dbColumns.get(index)).alias(dbColumns.get(index));
		}
		return selectionArray;
	}

	@Override
	public boolean deleteByUUIDId(UUID id) {
		try {
			super.deleteById(id);
			return true;

		} catch (Exception e) {
		}
		return false;
	}


	@Override
	public T getOneResultByValue(Qualification qual) {
		T obj = null;

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<T> query = cb.createQuery(domainClass);
		Root<T> root = query.from(domainClass);
		obj = getOneResult(BasePredicateUtils.getPredicate(cb, root, qual), query, root, true);
		return obj;
	}

}
