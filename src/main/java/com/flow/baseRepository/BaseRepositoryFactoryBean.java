package com.flow.baseRepository;

import java.io.Serializable;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.JpaEntityInformationSupport;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.data.repository.core.RepositoryInformation;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;

import com.flow.baseRepository.BaseRepositoryImpl;
import com.flow.base.BaseEntity;


public class BaseRepositoryFactoryBean<R extends JpaRepository<T, UUID>, T extends BaseEntity, UUID extends Serializable>
extends JpaRepositoryFactoryBean<R, T, UUID> {

	public BaseRepositoryFactoryBean(Class<? extends R> repositoryInterface) {
		super(repositoryInterface);
	}
	
	
	@Override
	@SuppressWarnings("rawtypes")
	protected RepositoryFactorySupport createRepositoryFactory(EntityManager entityManager) {
		return new HqRepositoryFactory(entityManager);
	}

	private static class HqRepositoryFactory<T extends BaseEntity, UUID extends Serializable>
			extends JpaRepositoryFactory {
		private EntityManager entityManager;

		public HqRepositoryFactory(EntityManager entityManager) {
			super(entityManager);
			this.entityManager = entityManager;
		}

		@SuppressWarnings({ "unchecked", "rawtypes" })
		@Override
		protected JpaRepositoryImplementation<?, ?> getTargetRepository(RepositoryInformation metadata,
				EntityManager entityManager) {
			JpaEntityInformation<T, UUID> entityInfo = (JpaEntityInformation<T, UUID>) JpaEntityInformationSupport
					.getEntityInformation((Class<T>) metadata.getDomainType(), entityManager);
			return new BaseRepositoryImpl<>(entityInfo.getJavaType(), entityManager);
		}

		@Override
		protected Class<?> getRepositoryBaseClass(RepositoryMetadata metadata) {
			return BaseRepositoryImpl.class;
		}

	}

}
