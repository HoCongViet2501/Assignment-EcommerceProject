package com.assignment.springboot.utils;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MappingData {
    public static ModelMapper modelMapper;

    @Autowired
    public MappingData(ModelMapper modelMapper) {
        MappingData.modelMapper = modelMapper;
    }

    public static <T, S> S mapObject(T data, Class<S> type) {
        return modelMapper.map(data, type);
    }

    public static <D, T> List<D> mapListObject(List<T> typeList, Class<D> outClass) {
        return typeList.stream().map(entity -> mapObject(entity, outClass)).collect(Collectors.toList());
    }
}
