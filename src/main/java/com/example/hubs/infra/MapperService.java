package com.example.hubs.infra;

public interface MapperService {

    <D> D map(Object source, Class<D> destination);
}
