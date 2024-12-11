package com.flow.baseRepository;

import java.util.List;
import java.util.UUID;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import com.flow.base.BaseEntity;
import com.flow.base.PageRequest;
import com.flow.baseQualification.Qualification;

@NoRepositoryBean
public interface BaseRepository<T extends BaseEntity> extends JpaRepository<T, UUID>, JpaSpecificationExecutor<T> {

	public <S extends T> S save(S entity);

	public T getById(UUID id);

	public T getOneResultByValue(Qualification Value);

	public List<T> searchObject(Qualification Value, PageRequest pageRequest);

	public boolean deleteByUUIDId(UUID id);
}
