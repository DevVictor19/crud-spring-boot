package com.devvictor.user_crud_spring.mappers;

import org.modelmapper.ModelMapper;
import org.modelmapper.record.RecordModule;

import java.util.List;

public class ApplicationModelMapper {
    private final static ModelMapper mapper = new ModelMapper()
            .registerModule(new RecordModule()); // read records

    public static <O, D> D parseObject(O origin, Class<D> destination) {
        return mapper.map(origin, destination);
    }

    public static <O, D> List<D> parseList(List<O> origin, Class<D> destination ) {
        return origin.stream()
                .map((item) -> mapper.map(item, destination))
                .toList();
    }
}
