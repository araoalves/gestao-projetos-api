package com.gestao.projeto.config.api;

import java.util.TimeZone;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.gestao.projeto.model.ERole;
import com.gestao.projeto.model.Role;
import com.gestao.projeto.repository.RoleRepository;

@Configuration
public class ApiConfiguration {
	
	@Autowired
	RoleRepository roleRepository;
	
	@Bean
	InitializingBean sendDatabase() {
	    return () -> {
	    	roleRepository.save(new Role(1,ERole.ROLE_ADMIN));
	    	roleRepository.save(new Role(2,ERole.ROLE_MODERATOR));
	    	roleRepository.save(new Role(3,ERole.ROLE_USER));
	      };
	 }
	
	@Bean
	public void timeZone() {
		TimeZone.setDefault(TimeZone.getTimeZone("America/Sao_Paulo"));
	}
	
}
