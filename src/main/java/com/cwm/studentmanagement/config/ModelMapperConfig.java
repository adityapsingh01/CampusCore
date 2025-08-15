package com.cwm.studentmanagement.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
 * Copyright (c) 2026 Mahesh Shet
 * Licensed under the MIT License.
 */

@Configuration
public class ModelMapperConfig {
	
	@Bean
	ModelMapper modelMapper() {
		return new ModelMapper();
	}

}
