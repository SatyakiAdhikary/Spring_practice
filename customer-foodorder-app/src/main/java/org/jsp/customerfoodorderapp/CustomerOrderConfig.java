package org.jsp.customerfoodorderapp;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = { "org.jsp.customerfoodorderapp" })
public class CustomerOrderConfig {
	@Bean
	public EntityManager entityManager() {
		return Persistence.createEntityManagerFactory("development").createEntityManager();
	}

}
