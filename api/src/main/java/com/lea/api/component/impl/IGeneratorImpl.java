package com.lea.api.component.impl;

import com.lea.api.component.IGenerator;
import org.dozer.Mapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class IGeneratorImpl implements IGenerator {

    private Mapper mapper;

    public IGeneratorImpl(Mapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public <T, S> T convert(S s, Class<T> clz) {
        return mapper.map(s, clz);
    }

    @Override
    public <T, S> List<T> convert(Collection<S> sCollection, Class<T> clz) {

        List<T> destinationList = new ArrayList<>();

        for (S s : sCollection) {
            destinationList.add(convert(s, clz));
        }

        return destinationList;
    }


}
