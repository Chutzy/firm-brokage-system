package com.example.hubs.infra;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MapperServiceImpl implements MapperService {

    private final ModelMapper modelMapper = new ModelMapper();

    @Override
    public <D> D map(Object source, Class<D> destination) {
        return source == null ? null : modelMapper.map(source, destination);
    }
}
