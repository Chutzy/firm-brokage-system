package com.example.hubs.infra;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class MapperServiceImpl implements MapperService {

    private final ModelMapper modelMapper = new ModelMapper();

    @Override
    public <D> D map(Object source, Class<D> destination) {
        return source == null ? null : modelMapper.map(source, destination);
    }

   /* @Override
    public <S, D> List<D> map(List<S> source, Class<D> destinationType){
        List<D> destination = new ArrayList<>();
        for (S s : source) {
            destination.add(map(s, destinationType));
        }
        return destination;
    }

    @Override
    public void map(Object source, Object destination){
        modelMapper.map(source, destination);
    }*/
}

