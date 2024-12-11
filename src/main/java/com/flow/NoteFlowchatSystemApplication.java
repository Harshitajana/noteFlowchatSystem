package com.flow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;

import com.flow.baseRepository.BaseRepositoryFactoryBean;

@SpringBootApplication
@EnableAsync
@EnableAutoConfiguration
@EnableJpaRepositories(repositoryFactoryBeanClass = BaseRepositoryFactoryBean.class)
public class NoteFlowchatSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(NoteFlowchatSystemApplication.class, args);
	}

}
