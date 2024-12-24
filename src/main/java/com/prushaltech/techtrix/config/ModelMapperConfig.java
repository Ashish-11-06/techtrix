package com.prushaltech.techtrix.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.prushaltech.techtrix.util.ModelMapperEx;

@Configuration
public class ModelMapperConfig {

	@Bean
	public ModelMapperEx modelMapper() {
		return new ModelMapperEx();
	}
}
