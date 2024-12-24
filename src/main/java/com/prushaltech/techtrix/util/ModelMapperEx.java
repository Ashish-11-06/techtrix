package com.prushaltech.techtrix.util;

import java.util.List;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ModelMapperEx extends ModelMapper {
	public <T, U> List<U> mapList(List<T> source, Class<U> targetClass) {
		return source.stream().map(element -> map(element, targetClass)).toList();
	}

	public <T, U> List<U> mapSet(Set<T> source, Class<U> targetClass) {
		return source.stream().map(element -> map(element, targetClass)).toList();
	}
}
